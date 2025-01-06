package org.supercat.growstone.events;

public class EventChangeClanName extends ClanEvent {
    public final long playerId;
    public final String newName;
    public final long packetId;

    public EventChangeClanName(long playerId, String newName, long packetId) {
        super(ClanEventType.CHANGE_CLAN_NAME);
        this.playerId = playerId;
        this.newName = newName;
        this.packetId = packetId;
    }
}
