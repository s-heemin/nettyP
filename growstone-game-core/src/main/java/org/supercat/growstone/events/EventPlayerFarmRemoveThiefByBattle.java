package org.supercat.growstone.events;

public class EventPlayerFarmRemoveThiefByBattle extends Event {
    public long packetId;
    public long playerId;
    public int slotIndex;

    public EventPlayerFarmRemoveThiefByBattle(long packetId, long playerId, int slotIndex) {
        super(EventType.PLAYER_FARM_REMOVE_THIEF_BY_BATTLE);

        this.packetId = packetId;
        this.playerId = playerId;
        this.slotIndex = slotIndex;
    }
}
