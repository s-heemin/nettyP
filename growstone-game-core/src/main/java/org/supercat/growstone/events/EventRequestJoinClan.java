package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZClanMember;

public class EventRequestJoinClan extends ClanEvent {
    public final long packetId;
    public final long playerId;
    public final long clanId;
    public final ZClanMember.Role role;

    public EventRequestJoinClan(long packetId, long playerId, long clanId, ZClanMember.Role role) {
        super(ClanEventType.REQUEST_JOIN_CLAN);
        this.packetId = packetId;
        this.playerId = playerId;
        this.clanId = clanId;
        this.role = role;
    }
}
