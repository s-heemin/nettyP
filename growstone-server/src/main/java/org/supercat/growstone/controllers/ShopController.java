package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ShopController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public ShopController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.PlayerSpawnGachaRequest, this::handle_PlayerSpawnGachaRequest);
        handlers.put(PacketType.PlayerPickUpGachaRewardRequest, this::handle_PlayerPickUpGachaRewardRequest);
        handlers.put(PacketType.PlayerViewGachaCommercialRequest, this::handle_PlayerViewGachaRequest);
        handlers.put(PacketType.PlayerGachaInfoRequest, this::handle_PlayerGachaInfoRequest);
        handlers.put(PacketType.PlayerPurchaseShopItemRequest, this::handle_PlayerPurchaseShopItemRequest);
        handlers.put(PacketType.PlayerShopItemInfoRequest, this::handle_PlayerShopItemInfoRequest);

        // 상점 패스
        handlers.put(PacketType.PlayerShopPassInfoRequest, this::handle_PlayerShopPassInfoRequest);
        handlers.put(PacketType.PlayerShopPassRewardRequest, this::handle_PlayerShopPassRewardRequest);
        return handlers;
    }

    private void handle_PlayerSpawnGachaRequest(Packet packet) {
        var request = packet.getPlayerSpawnGachaRequest();
        var outResult = new ArrayList<TContentReward>();
        int status = player.shop.gacha(request.getShopDataId(), request.getCount(), false, outResult);
        player.sendPacket(packet.getId(), ZPlayerSpawnGachaResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(outResult));
    }

    private void handle_PlayerPickUpGachaRewardRequest(Packet packet) {
        var request = packet.getPlayerPickUpGachaRewardRequest();
        int status = player.shop.getPickUpReward(request.getShopDataId(), request.getPoint());
        player.sendPacket(packet.getId(), ZPlayerPickUpGachaRewardResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_PlayerViewGachaRequest(Packet packet) {
        var request = packet.getPlayerViewGachaCommercialRequest();
        var outResult = new ArrayList<TContentReward>();
        int status = player.shop.viewAdvertise(request.getShopDataId(), outResult);
        player.sendPacket(packet.getId(), ZPlayerViewGachaCommercialResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(outResult));
    }

    private void handle_PlayerGachaInfoRequest(Packet packet) {
        var request = packet.getPlayerGachaInfoRequest();
        player.sendPacket(packet.getId(), ZPlayerGachaInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setGacha(player.shop.getTGacha(request.getShopDataId()))
                .setPickUpGacha(player.shop.getTPickUpGacha(request.getShopDataId())));
    }



    private void handle_PlayerPurchaseShopItemRequest(Packet packet) {
        var request = packet.getPlayerPurchaseShopItemRequest();
        var outResult = new ArrayList<TContentReward>();
        int status = player.shop.purchaseShopItem(request.getShopDataId(), request.getCount(), Instant.now(), outResult);
        player.sendPacket(packet.getId(), ZPlayerPurchaseShopItemResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(outResult));
    }

    private void handle_PlayerShopItemInfoRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZPlayerShopItemInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllShopItems(player.shop.getTBuyShopItems()));
    }

    private void handle_PlayerShopPassInfoRequest(Packet packet) {
        var request = packet.getPlayerShopPassInfoRequest();
        player.sendPacket(packet.getId(), ZPlayerShopPassInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setShopPass(player.shopPass.getTShopPass(request.getShopPassDataId())));
    }

    private void handle_PlayerShopPassRewardRequest(Packet packet) {
        var request = packet.getPlayerShopPassRewardRequest();
        var outResult = new ArrayList<TContentReward>();
        int status = player.shopPass.getRewards(request.getShopPassDataId(), request.getStep(), request.getType(), outResult);
        player.sendPacket(packet.getId(), ZPlayerShopPassRewardResponse.newBuilder()
                .setStatus(status)
                .addAllRewards(outResult));
    }


}


