package org.supercat.growstone.events;

public class EventPlayerBlock extends Event {
    public long playerId;
    public long targetPlayerId;
    public long packetId;

    public EventPlayerBlock(long playerId, long targetPlayerId, long packetId) {
        super(EventType.PLAYER_BLOCK);
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
        this.packetId = packetId;
    }
}
