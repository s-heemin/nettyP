package org.supercat.growstone.events;

import java.time.Instant;
import java.util.List;

public class EventPlayerFarmSeed extends Event {
    public long packetId;
    public long playerId;
    public List<Integer> slotIndexes;
    public Instant at;

    public EventPlayerFarmSeed(long packetId, long playerId, List<Integer> slotIndexes, Instant at) {
        super(EventType.PLAYER_FARM_SEED);

        this.packetId = packetId;
        this.playerId = playerId;
        this.slotIndexes = slotIndexes;
        this.at = at;
    }
}
