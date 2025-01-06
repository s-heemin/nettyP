package org.supercat.growstone.events;

public class EventPlayerAddFriend extends Event {
    public long playerId;
    public String friendCode;
    public long packetId;

    public EventPlayerAddFriend(long playerId, String friendCode, long packetId) {
        super(EventType.PLAYER_ADD_FRIEND);
        this.playerId = playerId;
        this.friendCode = friendCode;
        this.packetId = packetId;
    }
}
