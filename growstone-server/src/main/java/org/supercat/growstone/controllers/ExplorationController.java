package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.player.WorldPlayer;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ExplorationController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(ExplorationController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public ExplorationController(WorldSession session) {
        this.worldSession = session;
    }


    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.ExplorationStartRequest, this::handle_ExplorationStartRequest);
        handlers.put(PacketType.ExplorationEndRequest, this::handle_ExplorationEndRequest);
        handlers.put(PacketType.ExplorationChangeRequest, this::handle_ExplorationChangeRequest);
        handlers.put(PacketType.ViewExplorationCommercialRequest, this::handle_ViewExplorationCommercialRequest);
        handlers.put(PacketType.ExplorationCommercialRequest, this::handle_ExplorationCommercialRequest);
        handlers.put(PacketType.ExplorationAccelerationRequest, this::handle_ExplorationAccelerationRequest);
        handlers.put(PacketType.ExplorationEditAutoRequest, this::handle_ExplorationEditAutoRequest);

        return handlers;
    }

    private void handle_ExplorationEditAutoRequest(Packet packet) {
        var req = packet.getExplorationEditAutoRequest();

        var response = ZExplorationEditAutoResponse.newBuilder();
        int status = player.exploration.auto(req.getAuto(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ExplorationAccelerationRequest(Packet packet) {
        var req = packet.getExplorationAccelerationRequest();

        var response = ZExplorationAccelerationResponse.newBuilder();
        int status = player.exploration.acceleration(req.getSlotId(), req.getCount(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ExplorationCommercialRequest(Packet packet) {
        var req = packet.getExplorationCommercialRequest();

        var response = ZExplorationCommercialResponse.newBuilder();
        int status = player.exploration.advertise(req.getSlotId(), req.getViewCount(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ViewExplorationCommercialRequest(Packet packet) {
        var response = ZViewExplorationCommercialResponse.newBuilder();

        int status = player.exploration.viewCommercial(response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ExplorationChangeRequest(Packet packet) {
        var req = packet.getExplorationChangeRequest();

        var response = ZExplorationChangeResponse.newBuilder();
        int status = player.exploration.change(req.getSlotId(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ExplorationEndRequest(Packet packet) {
        var req = packet.getExplorationEndRequest();

        var response = ZExplorationEndResponse.newBuilder();
        int status = player.exploration.end(req.getSlotId(), Instant.now(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_ExplorationStartRequest(Packet packet) {
        var req = packet.getExplorationStartRequest();

        var response = ZExplorationStartResponse.newBuilder();
        int status = player.exploration.start(req.getSlotId(), req.getCreatedYmd(), req.getPetIdList(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

}


