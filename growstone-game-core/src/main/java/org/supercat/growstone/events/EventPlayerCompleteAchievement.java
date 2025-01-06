package org.supercat.growstone.events;

import org.supercat.growstone.types.ResetType;

public class EventPlayerCompleteAchievement extends Event {
    public final ResetType resetType;
    public EventPlayerCompleteAchievement(ResetType type) {
        super(EventType.PLAYER_COMPLETE_ACHIEVEMENT);
        this.resetType = type;
    }

}
