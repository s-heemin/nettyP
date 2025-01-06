package org.supercat.growstone.events;

import java.time.Instant;

public class EventPlayerFarmSteal extends Event {
    public long packetId;
    public long playerId;
    public long targetPlayerId;
    public int slotIndex;
    public Instant at;

    public EventPlayerFarmSteal(long packetId, long playerId, long targetPlayerId, int slotIndex, Instant at) {
        super(EventType.PLAYER_FARM_STEAL);

        this.packetId = packetId;
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
        this.slotIndex = slotIndex;
        this.at = at;
    }
}
