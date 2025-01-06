package org.supercat.growstone.events;

public class EventClanMemberExpulsion extends ClanEvent {
    public final long playerId;
    public final long targetPlayerId;
    public final long packetId;

    public EventClanMemberExpulsion(long playerId, long targetPlayerId, long packetId) {
        super(ClanEventType.EXPULSION_CLAN_MEMBER);
        this.playerId = playerId;
        this.targetPlayerId = targetPlayerId;
        this.packetId = packetId;
    }
}
