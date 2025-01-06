package org.supercat.growstone.events;

public class EventPlayerLimitBreakGrowth extends Event {
    public final long growthId;
    public final int limitBreakLevel;

    public EventPlayerLimitBreakGrowth(long growthId, int limitBreakLevel) {
        super(EventType.LIMIT_BREAK_GROWTH);
        this.growthId = growthId;
        this.limitBreakLevel = limitBreakLevel;
    }
}
