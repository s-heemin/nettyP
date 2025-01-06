package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZCategory;

public class EventPlayerGetItem extends Event {
    public final ZCategory.Type type;
    public final long itemDataId;
    public final long count;
    public EventPlayerGetItem(ZCategory.Type type, long itemDataId, long count) {
        super(EventType.PLAYER_GET_ITEM);
        this.type = type;
        this.itemDataId = itemDataId;
        this.count = count;
    }
}
