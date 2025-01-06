package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZClanJoin;

public class EventChangeClanIntroduction extends ClanEvent {
    public final String introduction;
    public final ZClanJoin.Type joinType;
    public final long playerId;
    public final long packetId;
    public EventChangeClanIntroduction(String introduction, ZClanJoin.Type type, long playerId, long packetId) {
        super(ClanEventType.CHANGE_INTRODUCTION);
        this.introduction = introduction;
        this.joinType = type;
        this.playerId = playerId;
        this.packetId = packetId;    }
}
