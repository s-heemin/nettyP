package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZGrowth;

public class EventPlayerPlayGacha extends Event {
    public final long levelGroupId;
    public final int count;
    public EventPlayerPlayGacha(long levelGroupId, int count) {
        super(EventType.PLAYER_PLAY_GACHA);
        this.levelGroupId = levelGroupId;
        this.count = count;
    }
}
