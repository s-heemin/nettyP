package org.supercat.growstone.components.playerComponents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.EventPlayerTimeBasedScheduledTask;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.models.DMPlayerScheduleTask;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.ScheduleTaskType;

import java.util.Objects;

public class PlayerScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(PlayerScheduleTask.class);

    private WorldPlayer player;

    public PlayerScheduleTask(WorldPlayer player) {
        this.player = player;

        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_TIME_BASED_SCHEDULED_TASK, this::handle_Event_PLAYER_TIME_BASED_SCHEDULED_TASK)
        );
    }

    private void handle_Event_PLAYER_TIME_BASED_SCHEDULED_TASK(EventPlayerTimeBasedScheduledTask event) {
        updateDailyResetSchedule(event.todayYmd);
    }

    private void updateDailyResetSchedule(int todayYmd) {
        var dailyModel = SDB.dbContext.scheduleTask.get(player.getId(), ScheduleTaskType.DAILY_RESET.value);
        if(Objects.isNull(dailyModel)) {
            dailyModel = DMPlayerScheduleTask.of(player.getId(), ScheduleTaskType.DAILY_RESET.value, 0);
            SDB.dbContext.scheduleTask.insert(dailyModel);
        }

        if(todayYmd == dailyModel.reset_at) {
            return;
        }

        dailyModel.reset_at = todayYmd;
        SDB.dbContext.scheduleTask.updateResetAt(dailyModel.id, todayYmd);

        // 일간 초기화
        player.topic.publish(new EventPlayerDailyResetSchedule());
    }
}
