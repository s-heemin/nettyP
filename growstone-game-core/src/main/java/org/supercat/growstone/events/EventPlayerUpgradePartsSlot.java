package org.supercat.growstone.events;

public class EventPlayerUpgradePartsSlot extends Event {
    public final int levelUpCount;
    public EventPlayerUpgradePartsSlot(int levelUpCount) {
        super(EventType.PLAYER_UPGRADE_PARTS_SLOT);
        this.levelUpCount = levelUpCount;
    }
}
