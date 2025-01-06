package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.containers.ContentReward;
import org.supercat.growstone.player.WorldPlayer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public EventController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();
        handlers.put(PacketType.WorldEventRequest, this::handle_WorldEventRequest);
        handlers.put(PacketType.PlayerEventRequest, this::handle_PlayerEventRequest);
        handlers.put(PacketType.PlayerEventRewardRequest, this::handle_PlayerEventRewardRequest);
        handlers.put(PacketType.PlayerDailyContentRequest, this::handle_PlayerDailyContentRequest);
        handlers.put(PacketType.PlayerDailyContentRewardRequest, this::handle_PlayerDailyContentRewardRequest);

        return handlers;
    }

    private void handle_WorldEventRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZWorldEventResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllWorldEvents(World.INSTANCE.event.getTEvent()));
    }

    private void handle_PlayerEventRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZPlayerEventResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllPlayerEvents(player.event.getTEvent()));
    }

    private void handle_PlayerEventRewardRequest(Packet packet) {
        var request = packet.getPlayerEventRewardRequest();
        var outRewards = new ArrayList<TContentReward>();
        var tEvent = TPlayerEvent.newBuilder();
        int status = player.event.getReward(request.getEventId(), request.getValue(), tEvent, outRewards);
        worldSession.sendPacket(packet.getId(), ZPlayerEventRewardResponse.newBuilder()
                .setStatus(status)
                .setEvent(tEvent)
                .addAllRewards(outRewards));
    }

    private void handle_PlayerDailyContentRequest(Packet packet) {
        var request = packet.getPlayerDailyContentRequest();
        worldSession.sendPacket(packet.getId(), ZPlayerDailyContentResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllDailyContents(player.dailyContent.getTDailyContent(request.getTypesList())));
    }

    private void handle_PlayerDailyContentRewardRequest(Packet packet) {
        var request = packet.getPlayerDailyContentRewardRequest();
        var outRewards = new ArrayList<TContentReward>();
        int status = player.dailyContent.getReward(request.getType(), Instant.now(), outRewards);
        worldSession.sendPacket(packet.getId(), ZPlayerDailyContentRewardResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(outRewards));
    }
}
