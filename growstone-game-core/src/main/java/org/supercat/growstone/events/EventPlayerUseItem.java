package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZCategory;

public class EventPlayerUseItem extends Event {
    public final ZCategory.Type type;
    public final long itemDataId;
    public final long count;
    public EventPlayerUseItem(ZCategory.Type type, long itemDataId, long count) {
        super(EventType.PLAYER_USE_ITEM);
        this.type = type;
        this.itemDataId = itemDataId;
        this.count = count;
    }
}
