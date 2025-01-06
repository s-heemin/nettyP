package org.supercat.growstone.events;

public class EventPlayerLevelUp extends Event {
    public final int level;

    public EventPlayerLevelUp(int level) {
        super(EventType.LEVEL_UP);
        this.level = level;
    }
}
