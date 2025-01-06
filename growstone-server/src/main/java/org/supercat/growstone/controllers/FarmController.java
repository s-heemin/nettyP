package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.events.*;
import org.supercat.growstone.player.WorldPlayer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FarmController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(FarmController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public FarmController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();
        handlers.put(PacketType.FarmInfoRequest, this::handle_FarmInfoRequest);
        handlers.put(PacketType.FarmMyRestrictionsInfoRequest, this::handlers_FarmMyRestrictionsInfoRequest);
        handlers.put(PacketType.FarmPlantSeedRequest, this::handle_FarmPlantSeedRequest);
        handlers.put(PacketType.FarmPlantBoostRequest, this::handle_FarmPlantBoostRequest);
        handlers.put(PacketType.FarmPlantHarvestRequest, this::handle_FarmPlanHarvestRequest);
        handlers.put(PacketType.FarmStealRequest, this::handle_FarmStealRequest);
        handlers.put(PacketType.FarmReturnStolenPlantsToThiefRequest, this::handle_FarmReturnStolenPlantsToThiefRequest);
        handlers.put(PacketType.FarmRemoveThiefByBattleRequest, this::handle_FarmRemoveThiefByBattleRequest);
        handlers.put(PacketType.FarmHistoryListRequest, this::handle_FarmHistoryListRequest);
        handlers.put(PacketType.FarmFriendListRequest, this::handle_FarmFriendListRequest);
        handlers.put(PacketType.FarmOtherListRequest, this::handle_FarmOtherListRequest);
        handlers.put(PacketType.FarmCommercialInfoRequest, this::handle_FarmCommercialInfoRequest);
        handlers.put(PacketType.FarmCommercialSeedViewRequest, this::handle_FarmCommercialSeedViewRequest);
        handlers.put(PacketType.FarmCookInfoRequest, this::handle_FarmCookInfoRequest);
        handlers.put(PacketType.FarmCookRequest, this::handle_FarmCookRequest);
        handlers.put(PacketType.FarmCookCommercialViewRequest, this::handle_FarmCookCommercialViewRequest);

        return handlers;
    }

    private void handle_FarmInfoRequest(Packet packet) {
        var p = packet.getFarmInfoRequest();
        var tFarm = World.INSTANCE.worldFarm.getTFarmByPlayerId(p.getPlayerId());
        player.sendPacket(packet.getId(), ZFarmInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(tFarm)
        );
    }

    private void handlers_FarmMyRestrictionsInfoRequest(Packet packet) {
        var response = ZFarmMyRestrictionsInfoResponse.newBuilder();
        int status = World.INSTANCE.worldFarm.getMyRestrictionsInfo(player, response);
        player.sendPacket(packet.getId(), response.setStatus(status));

    }

    private void handle_FarmPlantSeedRequest(Packet packet) {
        var p = packet.getFarmPlantSeedRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerFarmSeed(packet.getId(), player.getId(), p.getSlotIndexList(), Instant.now()));
    }

    private void handle_FarmPlantBoostRequest(Packet packet) {
        var p = packet.getFarmPlantBoostRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerFarmBoost(packet.getId(), player.getId(), p.getBoostsList(), Instant.now()));
    }

    private void handle_FarmPlanHarvestRequest(Packet packet) {
        var p = packet.getFarmPlantHarvestRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerFarmHarvest(packet.getId(), player.getId(), p.getSlotIndexList(), Instant.now()));
    }

    private void handle_FarmStealRequest(Packet packet) {
        var p = packet.getFarmStealRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerFarmSteal(packet.getId(), player.getId(), p.getPlayerId(), p.getSlotIndex(), Instant.now()));
    }

    private void handle_FarmReturnStolenPlantsToThiefRequest(Packet packet) {
        var p = packet.getFarmReturnStolenPlantsToThiefRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerReturnStolenPlantsToThief(packet.getId(), player.getId(), p.getPlayerId(), p.getSlotIndex()));
    }

    private void handle_FarmRemoveThiefByBattleRequest(Packet packet) {
        var p = packet.getFarmRemoveThiefByBattleRequest();
        World.INSTANCE.worldFarm.topic.publish(new EventPlayerFarmRemoveThiefByBattle(packet.getId(), player.getId(), p.getSlotIndex()));
    }

    private void handle_FarmHistoryListRequest(Packet packet) {
        var list = player.farm.getAllByTFarmHistory(Instant.now());
        player.sendPacket(packet.getId(), ZFarmHistoryListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllHistories(list)
        );
    }

    private void handle_FarmFriendListRequest(Packet packet) {
        var list = new ArrayList<TFarm>();
        int status = World.INSTANCE.worldFarm.getFarmFriendList(player, list);
        player.sendPacket(packet.getId(), ZFarmFriendListResponse.newBuilder()
                .setStatus(status)
                .addAllFriends(list)
        );
    }

    private void handle_FarmOtherListRequest(Packet packet) {
        var response = ZFarmOtherListResponse.newBuilder();
        int status = World.INSTANCE.worldFarm.getFarmOtherList(player.getId(), response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_FarmCommercialInfoRequest(Packet packet) {
        var response = ZFarmCommercialInfoResponse.newBuilder();
        int status = player.farm.getCommercialInfo(response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_FarmCommercialSeedViewRequest(Packet packet) {
        var response = ZFarmCommercialSeedViewResponse.newBuilder();
        int status = player.farm.advertiseSeed(response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_FarmCookInfoRequest(Packet packet) {
        var response = ZFarmCookInfoResponse.newBuilder();
        int status = player.farm.getCookInfo(response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_FarmCookRequest(Packet packet) {
        var response = ZFarmCookResponse.newBuilder();
        int status = player.farm.cook(response);
        player.sendPacket(packet.getId(), response.setStatus(status));
    }

    private void handle_FarmCookCommercialViewRequest(Packet packet) {
        var p = packet.getFarmCookCommercialViewRequest();
        var response = ZFarmCookCommercialViewResponse.newBuilder();
        int status = player.farm.cookCommercial(p.getSlotId(), response, Instant.now());
        player.sendPacket(packet.getId(), response.setStatus(status));
    }
}


