package org.supercat.growstone.events;

import org.supercat.growstone.models.DMEvent;

import java.util.List;

public class EventPlayerUpdateEvent extends Event {
    public final List<DMEvent> addEvents;
    public final List<Long> removeEvents;

    public EventPlayerUpdateEvent(List<DMEvent> addEvents, List<Long> removeEvents) {
        super(EventType.PLAYER_EVENT_UPDATE);
        this.addEvents = addEvents;
        this.removeEvents = removeEvents;
    }
}
