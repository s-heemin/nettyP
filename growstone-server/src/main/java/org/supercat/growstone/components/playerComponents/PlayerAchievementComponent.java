package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SLog;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.ZipUtils;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayerAchievement;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.AchievementRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.ResetType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerAchievementComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerAchievementComponent.class);

    private WorldPlayer player;
    private DMPlayerAchievement model;
    private ConcurrentHashMap<Long, TAchievement.Builder> achievements = new ConcurrentHashMap<>();

    public PlayerAchievementComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_DAILY_RESET, this::handle_EventPlayerDailyReset)
                .on(EventType.PLAYER_WEEKLY_RESET, this::handle_EventPlayerWeeklyReset)
                .on(EventType.CLEAR_STAGE, this::handle_EventPlayerClearStage)
                .on(EventType.PLAYER_CLEAR_DUNGEON, this::handle_EventPlayerClearDungeon)
                .on(EventType.PLAYER_PLAY_ARENA, this::handle_EventPlayerPlayArena)
                .on(EventType.PLAYER_ATTENDANCE, this::handle_EventPlayerAttendance)
                .on(EventType.PLAYER_PLAY_GACHA, this::handle_EventPlayerPlayGacha)
                .on(EventType.PLAYER_GET_ITEM, this::handle_EventPlayerGetItem)
                .on(EventType.PLAYER_USE_ITEM, this::handle_EventPlayerUseItem)
                .on(EventType.PLAYER_COMPLETE_ACHIEVEMENT, this::handle_EventPlayerCompleteAchievement)
        );
    }

    public void initialize() {
        model = SDB.dbContext.achievement.getByPlayerId(player.getId());
        if (Objects.isNull(model)) {
            model = DMPlayerAchievement.of(player.getId(), null);
        }

        if (Objects.nonNull(model.data)) {
            var achievements = AchievementRules.extractAchievements(model);
            if (Objects.nonNull(achievements)) {
                for (var achievement : achievements.getAchievementsList()) {
                    var resAchievement = ResourceManager.INSTANCE.achievement.get(achievement.getId());
                    if (Objects.isNull(resAchievement) || !resAchievement.visible) {
                        continue;
                    }

                    this.achievements.put(achievement.getId(), achievement.toBuilder());
                }
            }
        }
    }

    public TAchievements getTAchievements() {
        return copyTAchievements().build();
    }

    public void reward(List<Long> achievementIds, List<TContentReward> rewards) {
        var updates = new ArrayList<TAchievement>();
        for(var achievementId : achievementIds) {
            var ach = achievements.get(achievementId);
            if (Objects.isNull(ach)) {
                continue;
            }

            var resAchievement = ResourceManager.INSTANCE.achievement.get(achievementId);
            if (Objects.isNull(resAchievement) || !resAchievement.visible) {
                continue;
            }

            if (ach.getState() != ZClear.State.COMPLETE) {
                continue;
            }

            ach.setState(ZClear.State.REWARDED);

            for(var reward : resAchievement.rewards) {
                int status = player.categoryFilter.add(reward, 1);
                if(!StatusCode.isSuccess(status)) {
                    logger.error("reward failed. playerId: {}, achievementId: {}, categoryId: {}, dataId: {}",
                            player.getId(), achievementId, reward.type.getNumber(), reward.rewardId);
                    continue;
                }

                rewards.add(TContentReward.newBuilder()
                        .setCategory(reward.type)
                        .setDataId(reward.rewardId)
                        .setCount(reward.count)
                        .build());
            }

            updates.add(ach.build());
        }

        save();

        player.sendPacket(0L, ZPlayerAchievementNotify.newBuilder()
                .setAchievements(TAchievements.newBuilder()
                        .addAllAchievements(updates)));
    }

    public TAchievement.Builder getOrCreate(long achievementId) {
        var ach = achievements.get(achievementId);
        if (Objects.nonNull(ach)) {
            return ach;
        }

        var resAchievement = ResourceManager.INSTANCE.achievement.get(achievementId);
        if (Objects.isNull(resAchievement) || !resAchievement.visible) {
            return null;
        }

        var builder = TAchievement.newBuilder()
                .setId(achievementId)
                .setState(ZClear.State.DOING)
                .setProgress(0);

        achievements.put(achievementId, builder);

        return builder;
    }

    public void save() {
        try {
            var l = copyTAchievements();
            this.model.data = ZipUtils.compressBytes(l.build().toByteArray());

            SDB.dbContext.achievement.save(model);
        } catch (Exception e) {
            SLog.logException(e);
        }
    }

    private TAchievements.Builder copyTAchievements() {
        var buildAchievements = achievements.values()
                .stream()
                .map(TAchievement.Builder::build)
                .collect(Collectors.toList());

        return TAchievements.newBuilder()
                .addAllAchievements(buildAchievements);
    }

    private void increase(long param, long completeParam, long achievementId) {
        var model = getOrCreate(achievementId);
        if (Objects.isNull(model)) {
            return;
        }

        if (model.getState() != ZClear.State.DOING) {
            return;
        }

        model.setProgress(Math.min(completeParam, model.getProgress() + param));

        if (model.getProgress() == completeParam) {
            model.setState(ZClear.State.COMPLETE);

            var resAchievement = ResourceManager.INSTANCE.achievement.get(achievementId);
            player.topic.publish(new EventPlayerCompleteAchievement(resAchievement.resetType));
        }


        player.sendPacket(0L, ZPlayerAchievementNotify.newBuilder()
                .setAchievements(TAchievements.newBuilder()
                        .addAchievements(model.build())));
    }

    private void handle_EventPlayerDailyReset(EventPlayerDailyResetSchedule event) {
        for (var ach : achievements.values()) {
            var resAch = ResourceManager.INSTANCE.achievement.get(ach.getId());
            if (Objects.isNull(resAch) || !resAch.visible) {
                continue;
            }

            if (resAch.resetType != ResetType.DAILY) {
                continue;
            }


            ach.setState(ZClear.State.DOING).setProgress(0);
        }
    }

    private void handle_EventPlayerWeeklyReset(EventPlayerWeeklyResetSchedule event) {
        for (var ach : achievements.values()) {
            var resAch = ResourceManager.INSTANCE.achievement.get(ach.getId());
            if (Objects.isNull(resAch) || !resAch.visible) {
                continue;
            }

            if (resAch.resetType != ResetType.WEEKLY) {
                continue;
            }


            ach.setState(ZClear.State.DOING).setProgress(0);
        }
    }

    private void handle_EventPlayerClearStage(EventPlayerClearStage event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.CLEAR_STAGE)) {
            if (!achievement.visible) {
                continue;
            }

            increase(1, achievement.condition.param1, achievement.id);
        }

    }

    private void handle_EventPlayerClearDungeon(EventPlayerClearDungeon event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.CLEAR_DUNGEON)) {
            if (!achievement.visible) {
                continue;
            }

            if (achievement.condition.param1 != event.dungeonId) {
                continue;
            }

            increase(1, achievement.condition.param2, achievement.id);
        }
    }

    private void handle_EventPlayerPlayArena(EventPlayerPlayArena event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.PLAY_ARENA)) {
            if (!achievement.visible) {
                continue;
            }

            increase(1, achievement.condition.param1, achievement.id);
        }
    }

    private void handle_EventPlayerAttendance(EventPlayerAttendance event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.ATTENDANCE)) {
            if (!achievement.visible) {
                continue;
            }

            increase(1, achievement.condition.param1, achievement.id);
        }
    }

    private void handle_EventPlayerPlayGacha(EventPlayerPlayGacha event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.PLAY_GACHA)) {
            if (!achievement.visible) {
                continue;
            }

            if (achievement.condition.param1 != event.levelGroupId) {
                continue;
            }

            increase(event.count, achievement.condition.param2, achievement.id);
        }
    }

    private void handle_EventPlayerGetItem(EventPlayerGetItem event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.GET_ITEM)) {
            if (!achievement.visible) {
                continue;
            }

            if (achievement.condition.type != event.type) {
                continue;
            }

            if (achievement.condition.param1 != event.itemDataId) {
                continue;
            }

            increase(event.count, achievement.condition.param2, achievement.id);
        }
    }

    private void handle_EventPlayerUseItem(EventPlayerUseItem event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.USE_ITEM)) {
            if (!achievement.visible) {
                continue;
            }

            if (achievement.condition.type != event.type) {
                continue;
            }

            if (achievement.condition.param1 != event.itemDataId) {
                continue;
            }

            increase(event.count, achievement.condition.param2, achievement.id);
        }
    }

    private void handle_EventPlayerCompleteAchievement(EventPlayerCompleteAchievement event) {
        for (var achievement : ResourceManager.INSTANCE.achievement.getAllByType(ZCondition.Type.COMPLETE_ACHIEVEMENT)) {
            if (!achievement.visible) {
                continue;
            }

            if (achievement.condition.param1 != event.resetType.value) {
                continue;
            }

            increase(1, achievement.condition.param2, achievement.id);

        }
    }
}
