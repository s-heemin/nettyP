package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.TFarmBoost;

import java.time.Instant;
import java.util.List;

public class EventPlayerFarmBoost extends Event {
    public long packetId;
    public long playerId;
    public List<TFarmBoost> boosts;
    public Instant at;

    public EventPlayerFarmBoost(long packetId, long playerId, List<TFarmBoost> boosts, Instant at) {
        super(EventType.PLAYER_FARM_BOOST);

        this.packetId = packetId;
        this.playerId = playerId;
        this.boosts = boosts;
        this.at = at;
    }
}
