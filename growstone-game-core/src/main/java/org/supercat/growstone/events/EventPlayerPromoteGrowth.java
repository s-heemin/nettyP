package org.supercat.growstone.events;

public class EventPlayerPromoteGrowth extends Event {
    public final long growthId;
    public final int promoteLevel;

    public EventPlayerPromoteGrowth(long growthId, int promoteLevel) {
        super(EventType.PROMOTE_GROWTH);
        this.growthId = growthId;
        this.promoteLevel = promoteLevel;
    }
}
