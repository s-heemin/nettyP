package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZClanMember;

public class EventPlayerJoinClan extends Event {
    public final long clanId;


    public EventPlayerJoinClan(long clanId) {
        super(EventType.PLAYER_JOIN_CLAN);
        this.clanId = clanId;
    }
}
