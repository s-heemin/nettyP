package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DiggingController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(DiggingController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public DiggingController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.DiggingRequest, this::handle_DiggingRequest);
        handlers.put(PacketType.DiggingInfoRequest, this::handle_DiggingInfoRequest);
        handlers.put(PacketType.DiggingUpgradeRequest, this::handle_DiggingUpgradeRequest);
        handlers.put(PacketType.DiggingUpgradeInfoRequest, this::handle_DiggingUpgradeInfoRequest);
        handlers.put(PacketType.DiggingCompleteRequest, this::handle_DiggingCompleteRequest);
        handlers.put(PacketType.ViewDiggingCommercialRequest, this::handle_ViewDiggingCommercialRequest);
        handlers.put(PacketType.UseAcceleratorRequest, this::handle_UseAcceleratorRequest);
        handlers.put(PacketType.UseMultiAcceleratorRequest, this::handle_UseMultiAcceleratorRequest);
        return handlers;
    }

    private void handle_DiggingRequest(Packet packet) {
        var request = packet.getDiggingRequest();
        var results = new ArrayList<TDigging>();
        int status = player.digging.digging(request.getIndexList(), results, Instant.now());
        player.sendPacket(packet.getId(), ZDiggingResponse.newBuilder()
                .setStatus(status)
                .addAllDiggings(results));
    }

    private void handle_DiggingInfoRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZDiggingInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllDiggings(player.digging.getTDigging()));
    }

    private void handle_DiggingCompleteRequest(Packet packet) {
        var request = packet.getDiggingCompleteRequest();
        var rewards = new ArrayList<TContentReward>();
        int status = player.digging.diggingComplete(request.getIndexList(), rewards);
        player.sendPacket(packet.getId(), ZDiggingCompleteResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(rewards));
    }

    private void handle_DiggingUpgradeRequest(Packet packet) {
        var request = packet.getDiggingUpgradeRequest();
        var response = ZDiggingUpgradeResponse.newBuilder();
        int status = player.digging.upgradeDigging(request.getType(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_DiggingUpgradeInfoRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZDiggingUpgradeInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllUpgrades(player.digging.getTDiggingUpgrades()));
    }

    private void handle_ViewDiggingCommercialRequest(Packet packet) {
        int status = player.digging.viewAdvertise();
        long count = player.itemBag.getItemCount(GameData.DIGGING.spoonId);
        player.sendPacket(packet.getId(), ZViewDiggingCommercialResponse.newBuilder()
                .setStatus(status)
                .setTItem(TItem.newBuilder()
                        .setId(GameData.DIGGING.spoonId)
                        .setCount(count)));
    }

    private void handle_UseAcceleratorRequest(Packet packet) {
        var request = packet.getUseAcceleratorRequest();
        var response = ZUseAcceleratorResponse.newBuilder();
        int status = player.digging.useAccelerator(request.getIndex(), request.getCount(), request.getIsCommercial(), Instant.now(), response);
        player.sendPacket(packet.getId(), response
                .setStatus(status));
    }

    private void handle_UseMultiAcceleratorRequest(Packet packet) {
        var results = new ArrayList<TDigging>();
        var request = packet.getUseMultiAcceleratorRequest();
        int status = player.digging.useMultiAccelerator(request.getCount(), results);
        player.sendPacket(packet.getId(), ZUseMultiAcceleratorResponse.newBuilder()
                .setStatus(status)
                .addAllDigging(results));
    }

}
