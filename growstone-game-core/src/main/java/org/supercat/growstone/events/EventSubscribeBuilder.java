package org.supercat.growstone.events;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class EventSubscribeBuilder {
    private static final Logger logger = LoggerFactory.getLogger(EventSubscribeBuilder.class);

    private final HashMap<EventType, Consumer<Event>> handlers = new HashMap<>();

    private EventSubscribeBuilder() {

    }

    public static EventSubscribeBuilder newBuilder() {
        return new EventSubscribeBuilder();
    }

    public static <T extends Event> Consumer<Event> newHandler(EventType type, Consumer<T> consumer) {
        return event -> {
            try {
                if (Objects.isNull(event)) {
                    logger.error("event is null - eventType({})", type);
                    return;
                }

                @SuppressWarnings("unchecked")
                T event2 = (T) event;
                consumer.accept(event2);

            } catch (Exception e) {
                logger.error("event handling error - eventType({}) dispatchType({})", type, event.type);
                SLog.logException(e);
            }
        };
    }

    public <T extends Event> EventSubscribeBuilder on(EventType type, Consumer<T> consumer) {
        handlers.put(type, newHandler(type, consumer));
        return this;
    }

    public Map<EventType, Consumer<Event>> build() {
        return ImmutableMap.copyOf(handlers);
    }

}
