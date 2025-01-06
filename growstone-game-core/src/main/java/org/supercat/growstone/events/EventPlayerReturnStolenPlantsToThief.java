package org.supercat.growstone.events;

public class EventPlayerReturnStolenPlantsToThief extends Event {
    public long packetId;
    public long playerId;
    public long targetPlayerId;
    public int slotIndex;

    public EventPlayerReturnStolenPlantsToThief(long packetId, long playerId, long targetPlayerId, int slotIndex) {
        super(EventType.PLAYER_FARM_RETURN_STOLEN_PLANTS_TO_THIEF);

        this.packetId = packetId;
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
        this.slotIndex = slotIndex;
    }
}
