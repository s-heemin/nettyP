package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.events.*;
import org.supercat.growstone.player.WorldPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FriendController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public FriendController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.FriendListRequest, this::handle_FriendListRequest);
        handlers.put(PacketType.AddFriendRequest, this::handle_AddFriendRequest);
        handlers.put(PacketType.DeleteFriendRequest, this::handle_DeleteFriendRequest);
        handlers.put(PacketType.GiveFriendGiftRequest, this::handle_GiveFriendGiftRequest);
        handlers.put(PacketType.SearchFriendRequest, this::handle_SearchFriendRequest);
        handlers.put(PacketType.AddFriendCancelRequest, this::handle_AddFriendCancelRequest);
        handlers.put(PacketType.AddFriendResponseRequest, this::handle_AddFriendResponseRequest);
        handlers.put(PacketType.RecommendFriendRequest, this::handle_RecommendFriendRequest);
        handlers.put(PacketType.PlayerBlockListRequest, this::handle_PlayerBlockListRequest);
        handlers.put(PacketType.BlockPlayerRequest, this::handle_BlockPlayerRequest);
        handlers.put(PacketType.UnBlockPlayerRequest, this::handle_UnBlockPlayerRequest);

        return handlers;
    }

    private void handle_FriendListRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZFriendListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllFriends(player.friend.getTFriends()));
    }

    private void handle_AddFriendRequest(Packet packet) {
        var request = packet.getAddFriendRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerAddFriend(player.getId(), request.getFriendCode(), packet.getId()));
    }

    private void handle_DeleteFriendRequest(Packet packet) {
        var request = packet.getDeleteFriendRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerDeleteFriend(player.getId(), request.getFriendId(), packet.getId()));
    }

    private void handle_GiveFriendGiftRequest(Packet packet) {
        var request = packet.getGiveFriendGiftRequest();
        var out = new ArrayList<TFriendInfo>();
        int status = player.friend.sendGiftToFriends(request.getFriendIdsList(),out);
        worldSession.sendPacket(packet.getId(), ZGiveFriendGiftResponse.newBuilder()
                .setStatus(status)
                .addAllFriends(out));
    }

    private void handle_SearchFriendRequest(Packet packet) {
        var request = packet.getSearchFriendRequest();
        var searchFriend = TPlayerSimpleInfo.newBuilder();
        int status = player.friend.searchFriendFromCode(request.getSearchText(), searchFriend);
        worldSession.sendPacket(packet.getId(), ZSearchFriendResponse.newBuilder()
                .setStatus(status)
                .setFriend(searchFriend));
    }

    private void handle_AddFriendCancelRequest(Packet packet) {
        var request = packet.getAddFriendCancelRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerAddFriendCancel(player.getId(), request.getFriendId(), packet.getId()));
    }

    private void handle_AddFriendResponseRequest(Packet packet) {
        var request = packet.getAddFriendResponseRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerAddFriendResponse(player.getId(), request.getFriendId(), request.getIsAccept(), packet.getId()));

    }
    private void handle_RecommendFriendRequest(Packet packet) {
        var recommendFriends = new ArrayList<TPlayerSimpleInfo>();
        int status = player.friend.getRecommendFriends(recommendFriends);
        worldSession.sendPacket(packet.getId(), ZRecommendFriendResponse.newBuilder()
                .setStatus(status)
                .addAllFriends(recommendFriends));
    }

    private void handle_PlayerBlockListRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZPlayerBlockListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllBlockPlayers(player.block.getTBlockPlayers()));
    }
    private void handle_BlockPlayerRequest(Packet packet) {
        var request = packet.getBlockPlayerRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerBlock(player.getId(), request.getPlayerId(), packet.getId()));
    }

    private void handle_UnBlockPlayerRequest(Packet packet) {
        var request = packet.getUnBlockPlayerRequest();
        World.INSTANCE.community.topic.publish(
                new EventPlayerUnBlock(player.getId(), request.getPlayerId(), packet.getId()));
    }
}


