package org.supercat.growstone.components.playerEventComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.EventRules;
import org.supercat.growstone.setups.SDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerEventComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerEventComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<Long, PlayerEvent> models;

    public PlayerEventComponent(WorldPlayer player) {
        this.player = player;
        this.models = new ConcurrentHashMap<>();
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_EVENT_UPDATE, this::handle_EventPlayerUpdateEvent)
                .on(EventType.PLAYER_EVENT_LOGIN, this::handle_EventPlayerLogin));
    }

    public List<TPlayerEvent> getTEvent() {
        return models.values().stream()
                .map(x -> TBuilderOf.buildOf(x.getModel()))
                .collect(Collectors.toList());
    }
    public void initialize() {
        for(var model : SDB.dbContext.event.getByPlayerId(player.getId())) {
            var resEvent = ResourceManager.INSTANCE.event.get(model.event_data_id);
            if(Objects.isNull(resEvent)) {
                continue;
            }

            var event = World.INSTANCE.event.build(resEvent.type, model.event_id, player, model);
            if(Objects.isNull(event)) {
                logger.error("failed to build playerId({}) eventId({})",player.getId(), model.event_id);
                continue;
            }

            models.put(model.event_id, event);
        }
    }

    private void handle_EventPlayerLogin(EventPlayerLogin event) {
        var events = World.INSTANCE.event.getActiveEvents();
        for(var eventModel : events) {
            var resEvent = ResourceManager.INSTANCE.event.get(eventModel.event_data_id);
            if(Objects.isNull(resEvent)) {
                continue;
            }

            var model = models.get(eventModel.id);
            if(Objects.isNull(model)) {
                int state = EventRules.NEED_TRIGGER_START_TYPE.contains(resEvent.type) ? ZEventProgress.State.NOT_STARTED.getNumber() : ZEventProgress.State.PROGRESS.getNumber();
                var dm = DMPlayerEvent.of(player.getId(), eventModel.id, eventModel.event_data_id, state);
                model = World.INSTANCE.event.build(resEvent.type, eventModel.id, player, dm);
            }

            if(Objects.isNull(model)) {
                logger.error("failed to build playerId({}) eventId({})", player.getId(), eventModel.id);
                continue;
            }

            if(EventRules.ATTENDANCE_TYPES.contains(resEvent.type)) {
                var attendanceEvent = (PlayerEventAttendance) model;
                if(Objects.isNull(attendanceEvent)) {
                    continue;
                }

                attendanceEvent.attendance();
            }

            models.put(eventModel.id, model);
        }
    }
    private void handle_EventPlayerUpdateEvent(EventPlayerUpdateEvent event) {
        var tempEvents = new ConcurrentHashMap<>(models);
        var addActiveEvents = new ArrayList<TWorldEvent>();
        for(var model : event.addEvents) {
            var resEvent = ResourceManager.INSTANCE.event.get(model.event_data_id);
            if(Objects.isNull(resEvent)) {
                continue;
            }

            int state = EventRules.NEED_TRIGGER_START_TYPE.contains(resEvent.type) ? ZEventProgress.State.NOT_STARTED.getNumber() : ZEventProgress.State.PROGRESS.getNumber();
            var newEvent = DMPlayerEvent.of(player.getId(), model.id, model.event_data_id, state);
            var eventModel = World.INSTANCE.event.build(resEvent.type, model.id, player, newEvent);
            if(Objects.isNull(eventModel)) {
                logger.error("failed to build playerId({}) eventId({})", player.getId(), model.id);
                continue;
            }

            tempEvents.put(model.id, eventModel);
            addActiveEvents.add(TBuilderOf.buildOf(model));
        }

        for(var removeId : event.removeEvents) {
            tempEvents.remove(removeId);
        }

        player.sendPacket(0L, ZWorldEventNotify.newBuilder()
                .addAllWorldActiveEvents(addActiveEvents)
                .addAllWorldDeactiveEvents(event.removeEvents));

        models = tempEvents;
    }

    public PlayerEvent getEvent(long eventId) {
        var model = models.get(eventId);
        if(Objects.isNull(model)) {
            return null;
        }

        return model;
    }
    public int getReward(long eventId, int value, TPlayerEvent.Builder tEvent, List<TContentReward> outRewards) {
        var model = models.get(eventId);
        if(Objects.isNull(model)) {
            logger.error("invalid event playerId({}) eventId({})", player.getId(), eventId);
            return StatusCode.INVALID_REQUEST;
        }

        var resEvent = ResourceManager.INSTANCE.event.get(model.getModel().event_data_id);
        if(Objects.isNull(resEvent)) {
            logger.error("invalid event type playerId({}) eventId({})", player.getId(), model.getModel().event_id);
            return StatusCode.INVALID_RESOURCE;
        }


        return model.getReward(value, tEvent, outRewards);
    }
}
