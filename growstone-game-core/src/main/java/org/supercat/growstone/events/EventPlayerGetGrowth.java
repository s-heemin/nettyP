package org.supercat.growstone.events;

public class EventPlayerGetGrowth extends Event {
    public final long growthId;

    public EventPlayerGetGrowth(long growthId) {
        super(EventType.GET_GROWTH);
        this.growthId = growthId;
    }
}
