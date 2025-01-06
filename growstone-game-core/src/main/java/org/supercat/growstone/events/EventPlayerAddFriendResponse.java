package org.supercat.growstone.events;

public class EventPlayerAddFriendResponse extends Event {
    public long playerId;
    public long targetFriendId;
    public boolean isAccept;
    public long packetId;

    public EventPlayerAddFriendResponse(long playerId, long targetPlayerId, boolean isAccept, long packetId) {
        super(EventType.PLAYER_ADD_FRIEND_RESPONSE);
        this.playerId = playerId;
        this.targetFriendId = targetPlayerId;
        this.isAccept = isAccept;
        this.packetId = packetId;
    }
}
