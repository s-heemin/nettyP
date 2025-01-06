package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Async;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.events.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WorldCommunityComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldCommunityComponent.class);
    public EventTopic topic;
    public WorldCommunityComponent() {
        this.topic = new EventTopic(new EventDispatcher());
        topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_ADD_FRIEND, this::handle_PLAYER_ADD_FRIEND)
                .on(EventType.PLAYER_DELETE_FRIEND, this::handle_PLAYER_DELETE_FRIEND)
                .on(EventType.PLAYER_ADD_FRIEND_RESPONSE, this::handle_PLAYER_ADD_FRIEND_RESPONSE)
                .on(EventType.PLAYER_ADD_FRIEND_CANCEL, this::handle_PLAYER_ADD_FRIEND_CANCEL)
                .on(EventType.PLAYER_BLOCK, this::handle_PLAYER_BLOCK)
                .on(EventType.PLAYER_UNBLOCK, this::handle_PLAYER_UNBLOCK)
        );
    }

    public void initialize() {
        start();
    }

    public void start() {
        Async.repeat(() -> update(), 0, 83, TimeUnit.MILLISECONDS);
    }
    public void update() {
        topic.getEventDispatcher().update();
    }
    public void handle_PLAYER_ADD_FRIEND(EventPlayerAddFriend event) {
        long playerId = event.playerId;
        String targetFriendCode = event.friendCode;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }

        var resultBuilder = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(targetFriendCode, resultBuilder);
        player.sendPacket(packetId, ZAddFriendResponse.newBuilder()
                .setStatus(status)
                .setFriend(resultBuilder));
    }

    public void handle_PLAYER_DELETE_FRIEND(EventPlayerDeleteFriend event) {
        long playerId = event.playerId;
        long targetFriendId = event.targetFriendId;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }

        int status = player.friend.deleteFriend(targetFriendId);
        player.sendPacket(packetId, ZDeleteFriendResponse.newBuilder()
                .setStatus(status));
    }

    public void handle_PLAYER_ADD_FRIEND_CANCEL(EventPlayerAddFriendCancel event) {
        long playerId = event.playerId;
        long targetFriendId = event.targetFriendId;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }

        int status = player.friend.addFriendCancel(targetFriendId);
        player.sendPacket(packetId, ZAddFriendCancelResponse.newBuilder()
                .setStatus(status));
    }

    public void handle_PLAYER_ADD_FRIEND_RESPONSE(EventPlayerAddFriendResponse event) {
        long playerId = event.playerId;
        long targetFriendId = event.targetFriendId;
        boolean accept = event.isAccept;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }
        var resultBuilder = TFriendInfo.newBuilder();
        int status = player.friend.addFriendResponse(targetFriendId, accept, resultBuilder);
        player.sendPacket(packetId, ZAddFriendResponseResponse.newBuilder()
                .setStatus(status)
                .setFriend(resultBuilder));
    }

    public void handle_PLAYER_BLOCK(EventPlayerBlock event) {
        long playerId = event.playerId;
        long targetPlayerId = event.targetPlayerId;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }

        var resultBuilder = TPlayerSimpleInfo.newBuilder();
        if (player.block.isBlock(targetPlayerId)) {
            player.sendPacket(packetId, ZBlockPlayerResponse.newBuilder()
                    .setStatus(StatusCode.ALREADY_BLOCK_PLAYER)
                    .setPlayer(resultBuilder));
            return;
        }

        if (player.block.getBlockSize() >= GameData.COMMUNITY.friendBlockMaxCount) {
            player.sendPacket(packetId, ZBlockPlayerResponse.newBuilder()
                    .setStatus(StatusCode.BLOCK_ENABLE_COUNT_FULL)
                    .setPlayer(resultBuilder));
            return;
        }

        int status = player.block.block(targetPlayerId, resultBuilder);
        if (!StatusCode.isSuccess(status)) {
            player.sendPacket(packetId, ZBlockPlayerResponse.newBuilder()
                    .setStatus(status)
                    .setPlayer(resultBuilder));
            return;
        }

        player.sendPacket(packetId, ZBlockPlayerResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setPlayer(resultBuilder));
    }

    public void handle_PLAYER_UNBLOCK(EventPlayerUnBlock event) {
        long playerId = event.playerId;
        long targetPlayerId = event.targetPlayerId;
        long packetId = event.packetId;

        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.isNull(player)) {
            return;
        }

        int status = player.block.unBlock(targetPlayerId);
        if (!StatusCode.isSuccess(status)) {
            return;
        }

        player.sendPacket(packetId, ZUnBlockPlayerResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }
}
