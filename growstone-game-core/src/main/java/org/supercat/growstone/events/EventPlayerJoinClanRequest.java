package org.supercat.growstone.events;

public class EventPlayerJoinClanRequest extends Event {
    public long packetId;
    public long clanId;
    public EventPlayerJoinClanRequest(long packetId, long clanId) {
        super(EventType.PLAYER_JOIN_CLAN_REQUEST);
        this.packetId = packetId;
        this.clanId = clanId;
    }
}
