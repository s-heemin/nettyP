package org.supercat.growstone.events;

public class EventPlayerClearDungeon extends Event {
    public final long dungeonId;
    public EventPlayerClearDungeon(long dungeonId) {
        super(EventType.PLAYER_CLEAR_DUNGEON);
        this.dungeonId = dungeonId;
    }
}
