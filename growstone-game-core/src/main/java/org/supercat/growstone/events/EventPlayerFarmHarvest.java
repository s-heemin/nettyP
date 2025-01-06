package org.supercat.growstone.events;

import java.time.Instant;
import java.util.List;

public class EventPlayerFarmHarvest extends Event {
    public long packetId;
    public long playerId;
    public List<Integer> slotIndexes;
    public Instant at;

    public EventPlayerFarmHarvest(long packetId, long playerId, List<Integer> slotIndexes, Instant at) {
        super(EventType.PLAYER_FARM_HARVEST);

        this.packetId = packetId;
        this.playerId = playerId;
        this.slotIndexes = slotIndexes;
        this.at = at;
    }
}
