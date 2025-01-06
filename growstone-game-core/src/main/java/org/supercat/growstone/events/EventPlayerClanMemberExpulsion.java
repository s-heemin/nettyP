package org.supercat.growstone.events;

public class EventPlayerClanMemberExpulsion extends Event {
    public final long clanId;

    public EventPlayerClanMemberExpulsion(long clanId) {
        super(EventType.PLAYER_EXPULSION_CLAN);
        this.clanId = clanId;
    }
}
