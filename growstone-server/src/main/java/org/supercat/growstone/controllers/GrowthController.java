package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.containers.ResultContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GrowthController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(GrowthController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public GrowthController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.GrowthLevelUpRequest, this::handle_GrowthLevelUpRequest);
        handlers.put(PacketType.GrowthPromoteRequest, this::handle_GrowthPromoteRequest);
        handlers.put(PacketType.GrowthLimitBreakRequest, this::handle_GrowthLimitBreakRequest);

        //슬롯 강화
        handlers.put(PacketType.PartsSlotRequest, this::handle_PartsSlotRequest);
        handlers.put(PacketType.PartsSlotLevelUpRequest, this::handle_PartsSlotLevelUpRequest);
        return handlers;
    }


    private void handle_GrowthLevelUpRequest(Packet packet) {
        var request = packet.getGrowthLevelUpRequest();
        var out = TGrowth.newBuilder();
        int status = player.growth.levelUp(request.getGrowthId(), request.getMaterial(), out);
        worldSession.sendPacket(packet.getId(),
                ZGrowthLevelUpResponse.newBuilder()
                        .setStatus(status)
                        .setGrowth(out));
    }

    private void handle_GrowthPromoteRequest(Packet packet) {
        var request = packet.getGrowthPromoteRequest();
        var outResults = new ArrayList<TGrowth>();
        int status = player.growth.promote(request.getPromoteTargetsList(), outResults);
        worldSession.sendPacket(packet.getId(),
                ZGrowthPromoteResponse.newBuilder()
                        .setStatus(status)
                        .addAllGrowths(outResults));
    }

    private void handle_GrowthLimitBreakRequest(Packet packet) {
        var request = packet.getGrowthLimitBreakRequest();
        var outs = new ArrayList<TGrowth>();
        int status = player.growth.limitBreak(request.getLimitBreakTargetsList(), outs);
        worldSession.sendPacket(packet.getId(),
                ZGrowthLimitBreakResponse.newBuilder()
                        .setStatus(status)
                        .addAllGrowths(outs));
    }

    private void handle_PartsSlotLevelUpRequest(Packet packet) {
       var request = packet.getPartsSlotLevelUpRequest();
        var out = new ResultContainer<TPartsSlot>();
        var status = player.partsSlot.partsSlotLevelUp(request.getType(), request.getLevelUpCount(), out);
        worldSession.sendPacket(packet.getId(), ZPartsSlotLevelUpResponse.newBuilder()
                .setStatus(status)
                .setParts(out.getResultData()));

    }

    private void handle_PartsSlotRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZPartsSlotResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllPartsSlots(player.partsSlot.getTPartsSlots()));

    }
}


