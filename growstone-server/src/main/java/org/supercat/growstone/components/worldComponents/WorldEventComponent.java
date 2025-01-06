package org.supercat.growstone.components.worldComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TWorldEvent;
import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;
import org.supercat.growstone.Async;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.World;
import org.supercat.growstone.components.playerEventComponents.PlayerEvent;
import org.supercat.growstone.components.playerEventComponents.PlayerEventAttendance;
import org.supercat.growstone.components.playerEventComponents.PlayerEventCumulativeConsume;
import org.supercat.growstone.events.EventPlayerUpdateEvent;
import org.supercat.growstone.events.ResourceAttendanceEvent;
import org.supercat.growstone.events.ResourceEvent;
import org.supercat.growstone.models.DMEvent;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WorldEventComponent {
    private ConcurrentHashMap<Long, DMEvent> activeEvents = new ConcurrentHashMap<>();
    private ImmutableMap<ZEvent.Type, Function<Long, PlayerEvent>> factory = createFactory();

    public WorldEventComponent() {
        this.factory = createFactory();
    }

    public void initialize() {
        start();
    }

    private void start() {
        Async.repeat(this::update, 0, 60, TimeUnit.SECONDS);
    }

    private void update() {
        var events = SDB.dbContext.worldEvent.getAllByActive().stream()
                .collect(Collectors.toConcurrentMap(x -> x.id, x -> x));

        var removeEvents = new ArrayList<Long>();
        for(var event : activeEvents.entrySet()) {
            if(!events.containsKey(event.getKey())) {
                removeEvents.add(event.getKey());
            }
        }

        var addEvents = events.values().stream()
                .filter(x -> !activeEvents.containsKey(x.id))
                .collect(Collectors.toList());

        if(!removeEvents.isEmpty() || !addEvents.isEmpty()) {
            for (var player : World.INSTANCE.worldPlayers.getPlayers()) {
                player.topic.publish(new EventPlayerUpdateEvent(addEvents, removeEvents));
            }

            activeEvents = new ConcurrentHashMap<>(events);
        }
    }

    public List<DMEvent> getActiveEvents() {
        return new ArrayList<>(activeEvents.values());
    }
    public int isActiveEvent(long eventId) {
        var now = Instant.now();
        var model = activeEvents.get(eventId);
        if(Objects.isNull(model)) {
            return StatusCode.NOT_EXIST_EVENT;
        }

        if(now.isAfter(model.end_at)) {
            return StatusCode.ALREADY_END_EVENT;
        }

        return StatusCode.SUCCESS;
    }

    public List<TWorldEvent> getTEvent() {
        return activeEvents.values().stream()
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }

    private ImmutableMap<ZEvent.Type, Function<Long, PlayerEvent>> createFactory() {
        var factory = new HashMap<ZEvent.Type, Function<Long, PlayerEvent>>();
        factory.put(ZEvent.Type.ATTENDANCE, PlayerEventAttendance::new);
        factory.put(ZEvent.Type.FIRST_PURCHASE_ATTENDANCE, PlayerEventAttendance::new);
        factory.put(ZEvent.Type.CUMULATIVE_CONSUMPTION_EVENT, PlayerEventCumulativeConsume::new);

        return ImmutableMap.copyOf(factory);
    }


    @SuppressWarnings("unchecked")

    public <T extends PlayerEvent> T build(ZEvent.Type type, long eventId, WorldPlayer player, DMPlayerEvent model) {
        var event = Optional.ofNullable(factory.get(type))
                .map(x -> x.apply(eventId))
                .orElseGet(null);

        if(Objects.isNull(event)) {
            return null;
        }

        event.setOwner(player, model);

        return (T) event;
    }
}
