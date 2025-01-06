package org.supercat.growstone.events;

public class EventPlayerUnBlock extends Event {
    public long playerId;
    public long targetPlayerId;
    public long packetId;

    public EventPlayerUnBlock(long playerId, long targetPlayerId, long packetId) {
        super(EventType.PLAYER_UNBLOCK);
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
        this.packetId = packetId;
    }
}
