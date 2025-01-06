package org.supercat.growstone.components.worldComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.EventPlayerTimeBasedScheduledTask;
import org.supercat.growstone.models.DMWorldScheduleTask;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.ScheduleTaskType;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WorldScheduleTaskComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldScheduleTaskComponent.class);
    private Map<ScheduleTaskType, DMWorldScheduleTask> models = new HashMap<>();
    private Instant nextResetTimeSchedule;
    private final long channelId;

    private WorldDailyResetTaskComponent dailyResetTask;

    public WorldScheduleTaskComponent(long channelId) {
        this.channelId = channelId;
        dailyResetTask = new WorldDailyResetTaskComponent();
    }

    public void initialize() {
        nextResetTimeSchedule = UtcZoneDateTime.ofNextResetTime(0).toInstant();

        models = SDB.dbContext.worldScheduleTask.getByChannelId(channelId).stream()
                .collect(Collectors.toMap(x -> ScheduleTaskType.of(x.type), x -> x));

        World.INSTANCE.worldRank.setNextDailyResetScheduleTime(nextResetTimeSchedule);

        start();
    }

    private DMWorldScheduleTask getOrCreate(ScheduleTaskType type) {
        var model = models.get(type);
        if (Objects.isNull(model)) {
            model = DMWorldScheduleTask.of(channelId, type.value, 0);
            models.put(type, model);
        }

        return model;
    }

    public void start() {
        Async.repeat(() -> update(), 0, 60, TimeUnit.SECONDS);
    }

    private void update() {
        try {
            var now = Instant.now();
            var nowYmd = UtcZoneDateTime.getYmd();
            var dailyResetModel = getOrCreate(ScheduleTaskType.DAILY_RESET);
            boolean isReset = false;
            if (nowYmd != dailyResetModel.reset_at && now.isAfter(nextResetTimeSchedule)) {
                logger.info("--------------------------daily reset success--------------------------");
                for (var wp : World.INSTANCE.worldPlayers.getPlayers()) {
                    wp.topic.publish(new EventPlayerTimeBasedScheduledTask(UtcZoneDateTime.getYmd()));
                }

                dailyResetTask.start();
                dailyResetModel.reset_at = nowYmd;
                isReset = true;
                SDB.dbContext.worldScheduleTask.save(dailyResetModel);
            }

            int yw = UtcZoneDateTime.getYW();
            var weeklyResetModel = getOrCreate(ScheduleTaskType.WEEKLY_RESET);
            if (yw != weeklyResetModel.reset_at && now.isAfter(nextResetTimeSchedule)) {
                logger.info("--------------------------weekly reset success--------------------------");

                weeklyResetModel.reset_at = yw;
                SDB.dbContext.worldScheduleTask.save(weeklyResetModel);
            }

            if(isReset) {
                nextResetTimeSchedule = UtcZoneDateTime.ofNextResetTime(0).toInstant();
                World.INSTANCE.worldRank.setNextDailyResetScheduleTime(nextResetTimeSchedule);
                World.INSTANCE.worldClan.dailyReset();
            }
        } catch (Exception e) {
            SLog.logException(e);
        }
    }

    public WorldDailyResetTaskComponent dailyResetTaskByTest() {
        return dailyResetTask;
    }
}
