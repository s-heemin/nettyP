package org.supercat.growstone.components.playerEventComponents;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.ResourceDailyContent;
import org.supercat.growstone.models.DMPlayerDailyContent;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.rules.RewardRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class PlayerDailyContentComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDailyContentComponent.class);
    private WorldPlayer player;
    private LoadingCache<ZDailyContent.Type, DMPlayerDailyContent> cache;

    public PlayerDailyContentComponent(WorldPlayer player) {
        this.player = player;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
    }

    private DMPlayerDailyContent load(ZDailyContent.Type type) {
        var model = SDB.dbContext.dailyContent.getByType(player.getId(), type.getNumber());
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerDailyContent.of(player.getId(), type.getNumber());

        return model;
    }

    public DMPlayerDailyContent getOrCreate(ZDailyContent.Type type) {
        int nowYmd = UtcZoneDateTime.getYmd();
        var resDailyContent = ResourceManager.INSTANCE.dailyContent.getByType(type);
        if(Objects.isNull(resDailyContent)) {
            return null;
        }

        try {
            var model = cache.getUnchecked(type);
            initContent(type, nowYmd, model, resDailyContent);
            return cache.getUnchecked(type);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }


    private void initContent(ZDailyContent.Type type, int Ymd, DMPlayerDailyContent model, ResourceDailyContent resDailyContent) {
        if (type == ZDailyContent.Type.STONE_PRESS) {
            resetStonePress(Ymd, model);
        } else if (type == ZDailyContent.Type.ATTENDANCE) {
            resetAttendance(Ymd, model, resDailyContent);
        }
    }

    public int getReward(ZDailyContent.Type type, Instant now, List<TContentReward> rewards) {
        var resDailyContent = ResourceManager.INSTANCE.dailyContent.getByType(type);
        if (Objects.isNull(resDailyContent)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var model = getOrCreate(type);
        if(Objects.isNull(model)) {
            return StatusCode.INVALID_REQUEST;
        }

        int nowYmd = UtcZoneDateTime.getYmd(now);
        initContent(type, nowYmd, model, resDailyContent);

        if (model.state == ZReward.State.GOT_REWARD.getNumber()) {
            return StatusCode.INVALID_REQUEST;
        }

        int result = StatusCode.SUCCESS;
        if (type == ZDailyContent.Type.ATTENDANCE) {
            result = getAttendanceReward(model, resDailyContent, rewards);
        } else if (type == ZDailyContent.Type.STONE_PRESS) {
            result = getStonePressReward(model, resDailyContent, now, rewards);
        }

        return result;
    }

    public List<TPlayerDailyContent> getTDailyContent(List<ZDailyContent.Type> types) {
        var result = new ArrayList<TPlayerDailyContent>();
        var now = Instant.now();
        for (var type : types) {
            var resDailyContents = ResourceManager.INSTANCE.dailyContent.getByType(type);
            if (Objects.isNull(resDailyContents)) {
                continue;
            }

            long remainTime = 0;
            long nextDay = UtcZoneDateTime.ofNextResetTime(0).toInstant().getEpochSecond();
            var model = getOrCreate(type);
            if(Objects.isNull(model)) {
                continue;
            }

            if (type == ZDailyContent.Type.STONE_PRESS) {
                if (model.progress == resDailyContents.dailyCount) {
                    remainTime = nextDay - now.getEpochSecond();
                } else {
                    remainTime = Math.max(0, model.last_updated_date + resDailyContents.coolTimeSecond - now.getEpochSecond());
                }

            } else if (type == ZDailyContent.Type.ATTENDANCE) {
                remainTime = nextDay - now.getEpochSecond();
            }

            SDB.dbContext.dailyContent.save(model);

            result.add(TPlayerDailyContent.newBuilder()
                    .setType(type)
                    .setProgress(model.progress)
                    .setState(NetEnumRules.ofReward(model.state))
                    .setRemainTime(remainTime)
                    .setRewards(model.rewards)
                    .build());
        }

        return result;
    }

    private void resetStonePress(int nowYmd, DMPlayerDailyContent model) {
        int updated_date = UtcZoneDateTime.getYmd(Instant.ofEpochSecond(model.last_updated_date));
        if (updated_date != nowYmd) {
            model.progress = 0;
            model.last_updated_date = 0;
            model.state = ZReward.State.CAN_REWARD.getNumber();
        }

        SDB.dbContext.dailyContent.save(model);
    }

    private void resetAttendance(int nowYmd, DMPlayerDailyContent model, ResourceDailyContent resDailyContent) {
        if (model.last_updated_date == nowYmd) {
            return;
        }
        if (resDailyContent.dailyRewards.size() == model.progress) {
            var state = NetEnumRules.ofReward(model.state);
            if (state == ZReward.State.CAN_REWARD) {
                return;
            }

            model.progress = 1;
            model.rewards = JsonConverter.to(List.of());
        } else {
            ++model.progress;
        }

        model.last_updated_date = UtcZoneDateTime.getYmd();
        model.state = ZReward.State.CAN_REWARD.getNumber();


        SDB.dbContext.dailyContent.save(model);

    }

    private int getStonePressReward(DMPlayerDailyContent model, ResourceDailyContent resDailyContent, Instant
            now, List<TContentReward> outRewards) {
        if (model.progress >= resDailyContent.dailyCount) {
            return StatusCode.INVALID_REQUEST;
        }

        if (model.last_updated_date + resDailyContent.coolTimeSecond > now.getEpochSecond()) {
            return StatusCode.DAILY_CONTENT_COOL_TIME_NOT_YET;
        }

        int rand = SRandomUtils.nextIntEnd(0, resDailyContent.getMaxRatio());
        for (var ratio : resDailyContent.gachaRewards) {
            rand -= ratio.ratio;
            if (rand <= 0) {
                outRewards.add(TContentReward.newBuilder().setCategory(ratio.type).setDataId(ratio.rewardId).setCount(ratio.count).build());
                break;
            }
        }

        // 보상은 무조건 1개이다.
        if (outRewards.size() != 1) {
            return StatusCode.FAIL;
        }

        var reward = outRewards.get(0);
        int status = player.categoryFilter.add(reward.getCategory(), reward.getDataId(), reward.getCount());
        if (status != StatusCode.SUCCESS) {
            logger.error("failed to add reward. playerId({}), category({}), dataId({}), count({})", player.getId(), reward.getCategory(), reward.getDataId(), reward.getCount());
            return status;
        }

        ++model.progress;
        model.last_updated_date = now.getEpochSecond();

        if (model.progress == resDailyContent.dailyCount) {
            model.state = ZReward.State.GOT_REWARD.getNumber();
        }

        return StatusCode.SUCCESS;
    }

    private int getAttendanceReward(DMPlayerDailyContent model, ResourceDailyContent
            resDailyContent, List<TContentReward> outRewards) {
        var rewards = RewardRules.getReward(model.rewards);
        int lastRewardDay = rewards.stream().max(Integer::compareTo).orElse(0);
        for (int i = lastRewardDay + 1; i <= model.progress; ++i) {
            var attendanceRewards = resDailyContent.dailyRewards.get(i);
            if (Objects.isNull(attendanceRewards)) {
                return StatusCode.INVALID_RESOURCE;
            }

            int status = player.categoryFilter.add(attendanceRewards, 1);
            if (status != StatusCode.SUCCESS) {
                logger.error("failed to add reward. playerId({}), day({})", player.getId(), model.progress);
            }

            outRewards.add(TContentReward.newBuilder().setCategory(attendanceRewards.type).setDataId(attendanceRewards.rewardId).setCount(attendanceRewards.count).build());
            rewards.add(i);
        }

        model.state = ZReward.State.GOT_REWARD.getNumber();
        model.rewards = JsonConverter.to(rewards);

        SDB.dbContext.dailyContent.save(model);

        return StatusCode.SUCCESS;
    }

    public void resetForCheat(ZDailyContent.Type type, int ymd) {
        var model = getOrCreate(type);

        var resDailyContent = ResourceManager.INSTANCE.dailyContent.getByType(type);
        initContent(type, ymd, model, resDailyContent);

    }
}
