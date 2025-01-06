package org.supercat.growstone.events;

public class EventReplyJoinClanRequest extends ClanEvent {
    public final long requestId;
    public final long playerId;
    public final boolean isAccept;
    public final long packetId;

    public EventReplyJoinClanRequest(long requestId, long playerId, boolean isAccept, long packetId) {
        super(ClanEventType.REPLY_JOIN_CLAN);
        this.requestId = requestId;
        this.playerId = playerId;
        this.isAccept = isAccept;
        this.packetId = packetId;
    }
}
