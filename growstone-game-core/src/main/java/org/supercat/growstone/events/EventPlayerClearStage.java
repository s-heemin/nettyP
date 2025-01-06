package org.supercat.growstone.events;

public class EventPlayerClearStage extends Event {
    public final long groupId;
    public final int stageId;

    public EventPlayerClearStage(long groupId, int stageId) {
        super(EventType.CLEAR_STAGE);
        this.groupId = groupId;
        this.stageId = stageId;
    }
}
