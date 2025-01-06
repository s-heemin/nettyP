package org.supercat.growstone.events;

public class EventPlayerLeaveClan extends Event {
    public final int clanId;
    public EventPlayerLeaveClan(int clanId) {
        super(EventType.PLAYER_LEAVE_CLAN);
        this.clanId = clanId;
    }
}
