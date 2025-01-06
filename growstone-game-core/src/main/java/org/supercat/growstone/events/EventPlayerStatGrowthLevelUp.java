package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZStat;

public class EventPlayerStatGrowthLevelUp extends Event {
    public final ZStat.Type stat;
    public final int levelUpCount;
    public EventPlayerStatGrowthLevelUp(ZStat.Type type, int levelUpCount) {
        super(EventType.PLAYER_STAT_GROWTH_LEVEL_UP);
        this.stat = type;
        this.levelUpCount = levelUpCount;
    }
}
