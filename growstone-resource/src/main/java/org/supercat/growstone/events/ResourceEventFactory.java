package org.supercat.growstone.events;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ResourceEventFactory {
    private static final Map<ZEvent.Type, Function<Element, ResourceEvent>> factory = createFactory();

    private static Map<ZEvent.Type, Function<Element, ResourceEvent>> createFactory() {
        var factory = new HashMap<ZEvent.Type, Function<Element, ResourceEvent>>();
        factory.put(ZEvent.Type.ATTENDANCE, ResourceAttendanceEvent::new);
        factory.put(ZEvent.Type.FIRST_PURCHASE_ATTENDANCE, ResourceAttendanceEvent::new);
        factory.put(ZEvent.Type.CUMULATIVE_CONSUMPTION_EVENT, ResourceCumulativeConsumeEvent::new);

        return ImmutableMap.copyOf(factory);
    }

    public static ResourceEvent build(ZEvent.Type type, Element e) {
        return Optional.ofNullable(factory.get(type))
                .map(x -> x.apply(e))
                .orElseGet(() -> new ResourceEvent(e));
    }
}
