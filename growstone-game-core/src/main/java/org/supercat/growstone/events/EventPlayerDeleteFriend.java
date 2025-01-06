package org.supercat.growstone.events;

public class EventPlayerDeleteFriend extends Event {
    public long playerId;
    public long targetFriendId;
    public long packetId;
    public EventPlayerDeleteFriend(long playerId, long targetFriendId, long packetId) {
        super(EventType.PLAYER_DELETE_FRIEND);
        this.playerId = playerId;
        this.targetFriendId = targetFriendId;
        this.packetId = packetId;
    }
}
