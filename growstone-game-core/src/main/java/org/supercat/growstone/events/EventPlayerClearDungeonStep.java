package org.supercat.growstone.events;

public class EventPlayerClearDungeonStep extends Event {
    public final long dungeonId;

    public EventPlayerClearDungeonStep(long dungeonId) {
        super(EventType.CLEAR_DUNGEON_STEP);
        this.dungeonId = dungeonId;
    }
}
