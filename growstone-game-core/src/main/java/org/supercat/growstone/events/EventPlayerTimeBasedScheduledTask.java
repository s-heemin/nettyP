package org.supercat.growstone.events;

public class EventPlayerTimeBasedScheduledTask extends Event {
    public final int todayYmd;

    public EventPlayerTimeBasedScheduledTask(int ymd) {
        super(EventType.PLAYER_TIME_BASED_SCHEDULED_TASK);

        this.todayYmd = ymd;
    }
}
