package org.supercat.growstone.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SLog;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventTopic {
    private static final Logger logger = LoggerFactory.getLogger(EventTopic.class);

    private final ConcurrentHashMap<EventType, CopyOnWriteArrayList<Consumer<Event>>> subscribers = new ConcurrentHashMap<>();
    private final EventDispatcher eventDispatcher;

    public EventTopic(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    private void dispatch(Event evt) {
        // O(1) 로 통합
        var list = subscribers.get(evt.type);
        if (Objects.isNull(list)) {
            return;
        }

        list.forEach(x -> {
            try {
                x.accept(evt);
            } catch (Exception e) {
                SLog.logException(e);
            }

        });
    }

    public void publish(Event e) {
        if (Objects.isNull(eventDispatcher)) {
            logger.warn("You cannot send event because EventDispatcher is NULL. - type({})", e.type);
            return;
        }

        //
        eventDispatcher.addEvent(() -> dispatch(e));
    }

    public Runnable subscribes(EventSubscribeBuilder eventSubscribe) {
        var unsubscribes = new ArrayList<Runnable>();
        eventSubscribe.build().forEach((k, v) -> {
            var unsubscribe = subscribe(k, v);
            unsubscribes.add(unsubscribe);
        });

        return () -> {
            unsubscribes.forEach(Runnable::run);
            unsubscribes.clear();
        };
    }

    private Runnable subscribe(EventType evtTy, Consumer<Event> listener) {
        var subscriber = subscribers.computeIfAbsent(evtTy, k -> new CopyOnWriteArrayList<>());

        //
        if (!subscriber.contains(listener)) {
            subscriber.add(listener);
        }

        return () -> subscriber.remove(listener);
    }

    public boolean emptyEventQueue() {
        return eventDispatcher.isEmpty();
    }
}
