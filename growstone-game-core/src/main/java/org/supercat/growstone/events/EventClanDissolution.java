package org.supercat.growstone.events;

public class EventClanDissolution extends ClanEvent {
    public long clanId;
    public EventClanDissolution(long clanId) {
        super(ClanEventType.DISSOLUTION);
        this.clanId = clanId;
    }
}
