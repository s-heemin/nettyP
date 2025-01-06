package org.supercat.growstone.events;

public class EventPlayerCreateClan extends Event {
    public final String clanName;
    public final long packetId;

    public EventPlayerCreateClan(String clanName, long packetId) {
        super(EventType.PLAYER_CREATE_CLAN);
        this.clanName = clanName;
        this.packetId = packetId;
    }
}
