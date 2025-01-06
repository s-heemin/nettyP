package org.supercat.growstone.events;

public class EventChangeClanNotice extends ClanEvent {
    public final String notice;
    public final long playerId;
    public final long packetId;
    public EventChangeClanNotice(String notice, long playerId, long packetId) {
        super(ClanEventType.CHANGE_NOTICE);
        this.notice = notice;
        this.playerId = playerId;
        this.packetId = packetId;
    }
}
