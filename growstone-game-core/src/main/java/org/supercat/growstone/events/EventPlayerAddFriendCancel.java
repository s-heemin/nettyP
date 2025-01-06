package org.supercat.growstone.events;

public class EventPlayerAddFriendCancel  extends Event {
    public long playerId;
    public long targetFriendId;
    public long packetId;

    public EventPlayerAddFriendCancel(long playerId, long targetFriendId, long packetId) {
        super(EventType.PLAYER_ADD_FRIEND_CANCEL);
        this.playerId = playerId;
        this.targetFriendId = targetFriendId;
        this.packetId = packetId;
    }
}
