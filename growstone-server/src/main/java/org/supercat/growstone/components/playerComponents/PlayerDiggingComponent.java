package org.supercat.growstone.components.playerComponents;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.models.DMPlayerDigging;
import org.supercat.growstone.models.DMPlayerDiggingUpgrade;
import org.supercat.growstone.rules.DiggingRules;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PlayerDiggingComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDiggingComponent.class);

    private WorldPlayer player;
    private PlayerDiggingUpgrade upgrade;
    private LoadingCache<Integer, DMPlayerDigging> cache;

    public PlayerDiggingComponent(WorldPlayer player) {
        this.player = player;
        this.upgrade = new PlayerDiggingUpgrade(player);
        this.cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));

        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder().on(EventType.PLAYER_DAILY_RESET,
                this::handle_EventPlayerDailyReset));
    }

    public List<TDigging> getTDigging() {
        var now = Instant.now();
        var results = new ArrayList<TDigging>();
        var upgradeModel = upgrade.getOrCreate(ZDiggingUpgrade.Type.ZONE);
        int defaultZone = GameData.DIGGING.diggingZoneMaxCount;
        if(upgradeModel.level > 0) {
            var resZone = ResourceManager.INSTANCE.digging.getZone(upgradeModel.level);
            if (Objects.isNull(resZone)) {
                return results;
            }
            defaultZone += resZone.addZoneCount;
        }


        for (int i = 0; i < defaultZone; i++) {
            var model = getOrCreate(i);
            if (!model.is_digging) {
                continue;
            }

            results.add(TDigging.newBuilder()
                    .setIndex(model.index)
                    .setType(NetEnumRules.ofTier(model.tier))
                    .setRemainTime(Math.max(0, now.getEpochSecond() - model.complete_at.getEpochSecond()))
                    .build());
        }

        return results;
    }

    private void handle_EventPlayerDailyReset(EventPlayerDailyResetSchedule event) {
        var upgradeModel = upgrade.getOrCreate(ZDiggingUpgrade.Type.SPOON);
        if (Objects.isNull(upgradeModel)) {
            // 여기에 들어올 일은 없지만 혹시나 몰라 에러로그를 남겨 놓는다.
            logger.error("dailyReset spoon model not found. playerId: ({})", player.getId());
            return;
        }

        long addSpoonCount = GameData.DIGGING.diggingDefaultSpoonCount;
        if(upgradeModel.level > 0) {
            var resSpoon = ResourceManager.INSTANCE.digging.getSpoon(upgradeModel.level);
            if (Objects.isNull(resSpoon)) {
                return;
            }

            addSpoonCount += resSpoon.addSpoonCount;;
        }

        long count = player.itemBag.getItemCount(GameData.DIGGING.spoonId);
        if (count >= addSpoonCount) {
            return;
        }

        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.DIGGING.spoonId, Math.max(0, (addSpoonCount - count)));
    }

    private DMPlayerDigging load(int index) {
        var model = SDB.dbContext.digging.getByIndex(player.getId(), index);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerDigging.of(player.getId(), index);
        return model;
    }

    public DMPlayerDigging getOrCreate(int index) {
        try {
            return cache.getUnchecked(index);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    public int digging(List<Integer> indexes, List<TDigging> results, Instant now) {
        // 발굴하려고 하는 발굴지가 유효한 곳인지 체크
        var upgradeModel = upgrade.getOrCreate(ZDiggingUpgrade.Type.ZONE);
        if (Objects.isNull(upgradeModel)) {
            return StatusCode.INVALID_REQUEST;
        }

        if (!DiggingRules.isValidIndexes(indexes, DiggingRules.getMaxZoneCount(upgradeModel.level))) {
            return StatusCode.INVALID_REQUEST;
        }

        // 숟가락 체크
        long spoonCount = player.itemBag.getItemCount(GameData.DIGGING.spoonId);
        if (spoonCount < indexes.size()) {
            return StatusCode.NOT_ENOUGH_MATERIAL;
        }

        // 발굴하려는 발굴지가 발굴중인지 체크
        var diggings = new ArrayList<DMPlayerDigging>();
        for (var index : indexes) {
            var model = getOrCreate(index);
            if (Objects.isNull(model)) {
                return StatusCode.INVALID_REQUEST;
            }

            if (model.complete_at.isAfter(now) && model.is_digging) {
                return StatusCode.DIGGING_NOT_FINISH;
            }

            diggings.add(model);
        }

        // 여기까지 왔으면 발굴이 가능한 상태이다.
        // 발굴 숟가락 소모
        int status = player.itemBag.use(GameData.DIGGING.spoonId, indexes.size());
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 발굴 시간 감소 퍼센트 체크
        upgradeModel = upgrade.getOrCreate(ZDiggingUpgrade.Type.TIME);
        if (Objects.isNull(upgradeModel)) {
            return StatusCode.INVALID_REQUEST;
        }

        float reducePercent = DiggingRules.getReduceTimePercent(upgradeModel.level);
        long diggingSecond = (long) (GameData.DIGGING.diggingDefaultSecond * (1 - reducePercent));
        var completeAt = now.plusSeconds(diggingSecond);

        // 발굴 진행하자
        for (var model : diggings) {
            model.complete_at = completeAt;
            model.tier = DiggingRules.getRandomTier(model.tier).getNumber();
            model.is_digging = true;
            SDB.dbContext.digging.save(model);

            results.add(TDigging.newBuilder()
                    .setIndex(model.index)
                    .setType(NetEnumRules.ofTier(model.tier))
                    .setRemainTime(Math.max(0, now.getEpochSecond() - model.complete_at.getEpochSecond()))
                    .build());
        }

        return StatusCode.SUCCESS;
    }

    public int diggingComplete(List<Integer> indexes, List<TContentReward> rewards) {
        var now = Instant.now();
        var diggings = new ArrayList<DMPlayerDigging>();
        for (var index : indexes) {
            var model = getOrCreate(index);
            if (Objects.isNull(model)) {
                return StatusCode.INVALID_REQUEST;
            }

            if (model.complete_at.isAfter(now) && model.is_digging) {
                return StatusCode.DIGGING_NOT_FINISH;
            }

            diggings.add(model);
        }

        // 발굴 완료 보상
        for (var model : diggings) {
            var resReward = ResourceManager.INSTANCE.digging.getReward(NetEnumRules.ofTier(model.tier));
            if (Objects.isNull(resReward)) {
                logger.error("digging reward resource not found. playerId: ({}), tier: ({}))", model.player_id, model.tier);
                continue;
            }

            var reward = DiggingRules.getRandomReward(resReward);
            if (Objects.isNull(reward)) {
                logger.error("digging reward item not found. playerId: ({}), tier: ({}))", model.player_id, model.tier);
                continue;
            }

            int status = player.categoryFilter.add(reward.type, reward.rewardId, reward.count);
            if (!StatusCode.isSuccess(status)) {
                logger.error("digging reward add fail. playerId: ({}), tier: ({}), category: ({}), dataId({}))",
                        model.player_id, model.tier, reward.type.getNumber(), reward.rewardId);
                continue;
            }

            model.is_digging = false;
            SDB.dbContext.digging.save(model);

            rewards.add(TContentReward.newBuilder()
                    .setCategory(reward.type)
                    .setDataId(reward.rewardId)
                    .setCount(reward.count)
                    .build());

        }

        return StatusCode.SUCCESS;
    }

    public List<TDiggingUpgrade> getTDiggingUpgrades() {
        var results = new ArrayList<TDiggingUpgrade>();
        for (var type : ZDiggingUpgrade.Type.values()) {
            var model = upgrade.getOrCreate(type);
            if (Objects.isNull(model)) {
                continue;
            }

            results.add(TDiggingUpgrade.newBuilder()
                    .setType(type)
                    .setLevel(model.level)
                    .build());
        }

        return results;
    }

    public int upgradeDigging(ZDiggingUpgrade.Type type, ZDiggingUpgradeResponse.Builder builder) {
        if (type == ZDiggingUpgrade.Type.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = upgrade.getOrCreate(type);
        if (Objects.isNull(model)) {
            return StatusCode.INVALID_REQUEST;
        }

        if (DiggingRules.isMaxLevel(type, model.level)) {
            return StatusCode.ALREADY_MAX_LEVEL;
        }

        var material = DiggingRules.computeNeedCost(type, model.level + 1);
        if (Objects.isNull(material)) {
            return StatusCode.INVALID_REQUEST;
        }

        long hasMaterialCount = player.itemBag.getItemCount(material.getId());
        if (hasMaterialCount < material.getCount()) {
            return StatusCode.NOT_ENOUGH_MATERIAL;
        }

        int status = player.itemBag.use(material.getId(), material.getCount());
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        ++model.level;

        // 숟가락은 증가된 숫가락 만큼 더 지급해준다.
        if (type == ZDiggingUpgrade.Type.SPOON) {
            var resSpoon = ResourceManager.INSTANCE.digging.getSpoon(model.level);
            if (Objects.nonNull(resSpoon)) {
                player.itemBag.add(GameData.DIGGING.spoonId, resSpoon.addSpoonCount);
            }
        }

        SDB.dbContext.diggingUpgrade.save(model);

        builder.setUpgrade(TDiggingUpgrade.newBuilder()
                        .setType(type)
                        .setLevel(model.level)
                        .build());
        return StatusCode.SUCCESS;
    }

    public int viewAdvertise() {
        // 발굴지 광고는 숟가락 갯수가 존재하지 않을때 볼 수 있다.
        long count = player.itemBag.getItemCount(GameData.DIGGING.spoonId);
        if (count > 0) {
            return StatusCode.INVALID_REQUEST;
        }

        int viewCount = player.advertise.getViewCommercial(ZContentAdvertise.Type.DIGGING);
        if (viewCount >= GameData.DIGGING.maxCommercialViewCount) {
            return StatusCode.ADVERTISE_VIEW_COUNT_EXCEEDED;
        }

        // 광고를 봤을때는 초기화 되었을때 아이템을 받는다.
        var model = upgrade.getOrCreate(ZDiggingUpgrade.Type.SPOON);
        var resSpoon = ResourceManager.INSTANCE.digging.getSpoon(model.level);
        int addSpoonCount = Objects.isNull(resSpoon) ? GameData.DIGGING.diggingDefaultSpoonCount : GameData.DIGGING.diggingDefaultSpoonCount + resSpoon.addSpoonCount;
        int status = player.itemBag.add(GameData.DIGGING.spoonId, addSpoonCount);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        player.advertise.addViewCommercial(ZContentAdvertise.Type.DIGGING);
        return StatusCode.SUCCESS;
    }

    public int useMultiAccelerator(int useCount, List<TDigging> results) {
        if (useCount <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        var resZone = ResourceManager.INSTANCE.digging.getZone(upgrade.getOrCreate(ZDiggingUpgrade.Type.ZONE).level);
        int enableZoneCount = Objects.isNull(resZone) ? GameData.DIGGING.diggingDefaultZoneCount : GameData.DIGGING.diggingDefaultZoneCount + resZone.addZoneCount;
        var acceleratorItemCount = player.itemBag.getItemCount(GameData.PLAYER.acceleratorItemId);
        if (acceleratorItemCount < useCount) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        int status = player.itemBag.use(GameData.PLAYER.acceleratorItemId, useCount);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var actives = new ArrayList<DMPlayerDigging>();
        for (int i = 0; i < enableZoneCount; i++) {
            var model = getOrCreate(i);
            if (Objects.isNull(model) || !model.is_digging) {
                continue;
            }

            actives.add(model);
        }

        // 오름차순으로 정렬
        Collections.sort(actives, DMPlayerDigging::compareTo);

        var now = Instant.now();
        long acceleratorTime = GameData.PLAYER.acceleratorSecond * useCount;
        for (var active : actives) {
            long remainSecond = active.complete_at.getEpochSecond() - now.getEpochSecond();
            remainSecond -= acceleratorTime;
            active.complete_at = remainSecond <= 0 ? now : active.complete_at.minusSeconds(acceleratorTime);

            SDB.dbContext.digging.save(active);

            results.add(TDigging.newBuilder()
                    .setIndex(active.index)
                    .setType(NetEnumRules.ofTier(active.tier))
                    .setRemainTime(Math.max(0, now.getEpochSecond() - active.complete_at.getEpochSecond()))
                    .build());
        }

        return StatusCode.SUCCESS;
    }

    public int useAccelerator(int index, int useCount, boolean isCommercial, Instant now, ZUseAcceleratorResponse.Builder builder) {
        if (useCount <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        var resZone = ResourceManager.INSTANCE.digging.getZone(upgrade.getOrCreate(ZDiggingUpgrade.Type.ZONE).level);
        int enableZoneCount = Objects.isNull(resZone) ? GameData.DIGGING.diggingDefaultZoneCount : GameData.DIGGING.diggingDefaultZoneCount + resZone.addZoneCount;

        if (!isCommercial) {
            var acceleratorItemCount = player.itemBag.getItemCount(GameData.PLAYER.acceleratorItemId);
            if (acceleratorItemCount < useCount) {
                return StatusCode.NOT_ENOUGH_ITEM;
            }

            int status = player.itemBag.use(GameData.PLAYER.acceleratorItemId, useCount);
            if (!StatusCode.isSuccess(status)) {
                return status;
            }
        } else {
            if(useCount != 1) {
                return StatusCode.INVALID_REQUEST;
            }

            if(!player.advertise.isEnableUseAcceleratorByViewCommercial()) {
                return StatusCode.FAIL;
            }
        }

        if (!DiggingRules.isValidIndexes(List.of(index), enableZoneCount)) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(index);
        if (!model.is_digging) {
            return StatusCode.INVALID_REQUEST;
        }

        long acceleratorTime = GameData.PLAYER.acceleratorSecond * useCount;
        long remainSecond = model.complete_at.getEpochSecond() - now.getEpochSecond();
        remainSecond -= acceleratorTime;

        model.complete_at = remainSecond <= 0 ? now : model.complete_at.minusSeconds(acceleratorTime);

        SDB.dbContext.digging.save(model);

        if(isCommercial) {
            player.advertise.addViewCommercial(ZContentAdvertise.Type.ACCELERATOR);
        }

        builder.setDigging(TDigging.newBuilder()
                .setIndex(model.index)
                .setType(NetEnumRules.ofTier(model.tier))
                .setRemainTime(Math.max(0, now.getEpochSecond() - model.complete_at.getEpochSecond()))
                .build());

        return StatusCode.SUCCESS;
    }

    public DMPlayerDiggingUpgrade getUpgradeModelForCheat(ZDiggingUpgrade.Type type) {
        return upgrade.getOrCreate(type);
    }
}
