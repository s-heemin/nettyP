package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.models.DMPlayerFriend;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.FriendRules;
import org.supercat.growstone.rules.MailRules;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerFriendComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerFriendComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<Long, DMPlayerFriend> models = new ConcurrentHashMap<>();

    public PlayerFriendComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var friends = SDB.dbContext.friend.getByPlayerId(player.getId());
        if (friends.isEmpty()) {
            return;
        }

        for (var friend : friends) {
            models.put(friend.id, friend);
        }
    }

    public DMPlayerFriend get(long friendId) {
        return models.get(friendId);
    }

    private void insert(DMPlayerFriend model) {
        models.put(model.id, model);
    }

    public void changeState(long friendId, ZFriend.State state) {
        var model = models.get(friendId);
        if (Objects.isNull(model)) {
            return;
        }

        model.state = state.getNumber();
    }

    public List<Long> getAcceptedFriendIds() {
        return models.values().stream()
                .filter(x -> x.state == ZFriend.State.ACCEPTED.getNumber())
                .map(x -> x.target_player_id)
                .collect(Collectors.toList());
    }

    public List<TFriendInfo> getTFriends() {
        var result = new ArrayList<TFriendInfo>();
        var now = Instant.now();
        for (var model : models.values()) {
            result.add(getTFriend(model, now));
        }
        return result;
    }

    public TFriendInfo getTFriend(DMPlayerFriend model, Instant now) {
        long remainExpireTime = Math.max(0, model.send_gift_expire_at.getEpochSecond() - now.getEpochSecond());
        var tFriend = TFriendInfo.newBuilder()
                .setFriendId(model.id)
                .setPlayerId(model.target_player_id)
                .setGiftExpireTime(remainExpireTime)
                .setState(NetEnumRules.ofFriend(model.state));

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
        if (Objects.isNull(targetPlayer)) {
            setOfflinePlayerInfo(model.target_player_id, now, tFriend);
        } else {
            tFriend.setLevel(targetPlayer.getLevel())
                    .setName(targetPlayer.getName())
                    .setAttackPower(targetPlayer.getAttackPower())
                    .setTimeSinceLastLogoutTime(0)
                    .setPortraitId(targetPlayer.getPortraitIcon())
                    .setFriendCode(targetPlayer.getFriendCode());
        }

        return tFriend.build();
    }

    public int remove(long friendId) {
        int affected = SDB.dbContext.friend.delete(friendId);
        if (affected <= 0) {
            logger.error("deleteFriend failed. id: {}, playerId({})", friendId, player.getId());
            return StatusCode.FAIL;
        }

        models.remove(friendId);

        return StatusCode.SUCCESS;
    }

    public int deleteFriend(long friendId) {
        var model = models.get(friendId);
        if (Objects.isNull(model)) {
            return StatusCode.NOT_FOUND_FRIEND;
        }

        int status = remove(friendId);
        if (status != StatusCode.SUCCESS) {
            return status;
        }

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(model.target_player_id, player.getId());
        if (Objects.nonNull(targetModel)) {
            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.friend.remove(targetModel.id);
                targetPlayer.sendPacket(0, ZDeleteFriendNotify.newBuilder()
                        .setDeleteFriendId(targetModel.id));
            } else {
                SDB.dbContext.friend.delete(targetModel.id);
            }
        }

        return StatusCode.SUCCESS;
    }

    public int addFriendCancel(long friendId) {
        var model = models.get(friendId);
        if (Objects.isNull(model)) {
            return StatusCode.NOT_FOUND_FRIEND;
        }

        if (model.state != ZFriend.State.PENDING_REQUEST.getNumber()) {
            return StatusCode.NOT_PENDING_FRIEND_REQUEST;
        }

        int status = remove(friendId);
        if (status != StatusCode.SUCCESS) {
            return status;
        }

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(model.target_player_id, player.getId());
        if (Objects.nonNull(targetModel)) {
            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.friend.remove(targetModel.id);
                targetPlayer.sendPacket(0, ZDeleteFriendNotify.newBuilder()
                        .setDeleteFriendId(targetModel.id));
            } else {
                SDB.dbContext.friend.delete(targetModel.id);
            }
        }

        return StatusCode.SUCCESS;
    }

    public int addFriendResponse(long friendId, boolean isAccept, TFriendInfo.Builder builder) {
        var model = models.get(friendId);
        if (Objects.isNull(model)) {
            return StatusCode.NOT_FOUND_FRIEND;
        }

        if (model.state != ZFriend.State.REQUEST_RECEIVED.getNumber()) {
            return StatusCode.NOT_PENDING_FRIEND_REQUEST;
        }

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(model.target_player_id, player.getId());
        if(Objects.isNull(targetModel)) {
            logger.error("friend response target not found. id({}), playerId({}), targetPlayerId({})", friendId, model.target_player_id, player.getId());
            return StatusCode.FAIL;
        }

        if (isAccept) {
            model.state = ZFriend.State.ACCEPTED.getNumber();
            int affected = SDB.dbContext.friend.save(model);
            if (affected <= 0) {
                logger.error("friend response accept failed. id: {}, playerId({})", model.id, player.getId());
                return StatusCode.FAIL;
            }

            targetModel.state = ZFriend.State.ACCEPTED.getNumber();
            affected = SDB.dbContext.friend.save(targetModel);
            if (affected <= 0) {
                return StatusCode.FAIL;
            }

            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.friend.changeState(targetModel.id, ZFriend.State.ACCEPTED);
                targetPlayer.sendPacket(0, ZChangeFriendStateNotify.newBuilder()
                        .setFriendId(targetModel.id)
                        .setState(ZFriend.State.ACCEPTED));

                builder.mergeFrom(TFriendInfo.newBuilder()
                        .setFriendId(friendId)
                        .setPlayerId(targetPlayer.getId())
                        .setName(targetPlayer.getName())
                        .setLevel(targetPlayer.getLevel())
                        .setTimeSinceLastLogoutTime(0)
                        .setAttackPower(targetPlayer.getAttackPower())
                        .setGiftExpireTime(0)
                        .setPortraitId(targetPlayer.getPortraitIcon())
                        .setState(ZFriend.State.ACCEPTED)
                        .build());
            } else {
                var offline = SDB.dbContext.player.get(model.target_player_id);
                if (Objects.nonNull(offline)) {
                    builder.mergeFrom(TFriendInfo.newBuilder()
                            .setFriendId(friendId)
                            .setPlayerId(offline.id)
                            .setName(offline.name)
                            .setLevel(offline.level)
                            .setPortraitId(offline.portrait_id)
                            .setTimeSinceLastLogoutTime(Instant.now().getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                            .setAttackPower(offline.attack_power)
                            .setGiftExpireTime(0)
                            .setState(ZFriend.State.ACCEPTED)
                            .build());
                }
            }
        } else {
            int status = remove(friendId);
            if (!StatusCode.isSuccess(status)) {
                return status;
            }

            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.friend.remove(targetModel.id);
                targetPlayer.sendPacket(0, ZDeleteFriendNotify.newBuilder()
                        .setDeleteFriendId(targetModel.id));
            } else {
                SDB.dbContext.friend.delete(targetModel.id);
            }
        }

        return StatusCode.SUCCESS;
    }
    public int addFriend(String friendCode, TFriendInfo.Builder builder) {
        var targetPlayer = SDB.dbContext.player.getPlayerByFriendCode(friendCode);
        if (Objects.isNull(targetPlayer)) {
            return StatusCode.NOT_FOUND_PLAYER;
        }

        //차단된 유저인지 체크
        if (player.block.isBlock(targetPlayer.id)) {
            return StatusCode.ALREADY_BLOCK_PLAYER;
        }

        // 친구로 등록 되어 있는 상태라면
        var friend = models.values().stream()
                .filter(x -> x.target_player_id == targetPlayer.id)
                .findAny()
                .orElse(null);
        if (Objects.nonNull(friend)) {
            if (friend.state == ZFriend.State.PENDING_REQUEST.getNumber()) {
                return StatusCode.ALREADY_APPLY_FRIEND;
            }

            return StatusCode.ALREADY_ADD_FRIEND;
        }

        // 친구 카운트 체크
        long size = models.values().stream()
                .filter(x -> x.state == ZFriend.State.ACCEPTED.getNumber())
                .count();
        if (size >= GameData.COMMUNITY.friendMaxCount) {
            return StatusCode.MAX_FRIEND_COUNT;
        }

        int targetFriendSize = SDB.dbContext.friend.getFriendSizeByPlayerId(targetPlayer.id);
        if (targetFriendSize >= GameData.COMMUNITY.friendMaxCount) {
            return StatusCode.TARGET_PLAYER_MAX_FRIEND_COUNT;
        }

        // 추가하려는 유저
        var requestModel = DMPlayerFriend.of(player.getId(), targetPlayer.id, ZFriend.State.PENDING_REQUEST.getNumber());
        int affected = SDB.dbContext.friend.save(requestModel);
        if (affected <= 0) {
            return StatusCode.ALREADY_ADD_FRIEND;
        }

        // 추가 요청 당한 유저
        var receiveModel = DMPlayerFriend.of(targetPlayer.id, player.getId(), ZFriend.State.REQUEST_RECEIVED.getNumber());
        affected = SDB.dbContext.friend.save(receiveModel);
        if (affected <= 0) {
            return StatusCode.ALREADY_ADD_FRIEND;
        }

        models.put(requestModel.id, requestModel);

        var now = Instant.now();
        builder.setState(ZFriend.State.PENDING_REQUEST)
                .setFriendId(requestModel.id)
                .setPlayerId(targetPlayer.id)
                .setGiftExpireTime(0);

        var connectPlayer = World.INSTANCE.worldPlayers.getPlayer(targetPlayer.id);
        if (Objects.isNull(connectPlayer)) {
            builder.setName(targetPlayer.name)
                    .setLevel(targetPlayer.level)
                    .setTimeSinceLastLogoutTime(now.getEpochSecond() - targetPlayer.last_disconnected_at.getEpochSecond())
                    .setPortraitId(targetPlayer.portrait_id)
                    .setAttackPower(targetPlayer.attack_power);
        } else {
            // 상대방에게 전달
            sendOnlineAddTargetPlayer(receiveModel.id, connectPlayer);
            connectPlayer.friend.insert(receiveModel);
            builder.setName(connectPlayer.getName())
                    .setLevel(connectPlayer.getLevel())
                    .setTimeSinceLastLogoutTime(0)
                    .setPortraitId(connectPlayer.getPortraitIcon())
                    .setAttackPower(connectPlayer.getAttackPower());
        }

        return StatusCode.SUCCESS;
    }

    public int sendGiftToFriends(List<Long> friends, ArrayList<TFriendInfo> out) {
        var now = Instant.now();
        for (var friendId : friends) {
            var model = models.get(friendId);
            if (Objects.isNull(model)) {
                return StatusCode.NOT_FOUND_FRIEND;
            }

            if (model.send_gift_expire_at.isAfter(now)) {
                return StatusCode.ALREADY_SEND_GIFT;
            }

            var expireAt = UtcZoneDateTime.ofNextResetTime(0).toInstant();
            model.send_gift_expire_at = expireAt;

            SDB.dbContext.friend.updateSendGiftExpiretime(model);
            SDB.dbContext.friend.updateReceiveGiftExpiretime(model.target_player_id, player.getId(), expireAt);

            // 메일 보내기
            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.isNull(targetPlayer)) {
                var json = JsonConverter.to(FriendRules.sendDailyGiftReward());
                SDB.dbContext.mail.insert(DMPlayerMail.of(player.getId(), ZMail.Type.DAILY_GIFT_FROM_FRIEND_VALUE, player.getName(), json, expireAt));
            } else {
                targetPlayer.mail.insertMail(ZMail.Type.DAILY_GIFT_FROM_FRIEND_VALUE, player.getName(),
                        MailRules.getJMPlayerMailReward(FriendRules.sendDailyGiftReward()), expireAt);
            }

            out.add(getTFriend(model, now));
        }

        return StatusCode.SUCCESS;
    }

    public int searchFriendFromCode(String text, TPlayerSimpleInfo.Builder tSearchFriend) {
        var now = Instant.now();
        // 친구 코드인지, 친구 이름인지 체크를 길이로 한다.
        var friend = new DMPlayer();
        if (text.length() != Constants.FRIEND_CODE_LENGTH) {
            friend = SDB.dbContext.player.getPlayerByFriendName(text);
            if (Objects.isNull(friend)) {
                return StatusCode.NOT_FOUND_PLAYER;
            }
        } else {
            friend = SDB.dbContext.player.getPlayerByFriendCode(text);
            if (Objects.isNull(friend)) {
                return StatusCode.NOT_FOUND_PLAYER;
            }
        }

        if (friend.id <= 0) {
            return StatusCode.NOT_FOUND_FRIEND;
        }

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(friend.id);
        if (Objects.isNull(targetPlayer)) {
            var offline = SDB.dbContext.player.get(friend.id);
            if (Objects.isNull(offline)) {
                return StatusCode.FAIL;
            }
            tSearchFriend
                    .setPlayerId(friend.id)
                    .setFriendCode(friend.friend_code)
                    .setLevel(offline.level)
                    .setName(offline.name)
                    .setPortraitId(offline.portrait_id)
                    .setAttackPower(offline.attack_power)
                    .setTimeSinceLastLogoutTime(now.getEpochSecond() - offline.last_disconnected_at.getEpochSecond());
        } else {
            tSearchFriend
                    .setPlayerId(friend.id)
                    .setFriendCode(friend.friend_code)
                    .setLevel(targetPlayer.getLevel())
                    .setName(targetPlayer.getName())
                    .setPortraitId(targetPlayer.getPortraitIcon())
                    .setAttackPower(targetPlayer.getAttackPower())
                    .setTimeSinceLastLogoutTime(0);
        }

        return StatusCode.SUCCESS;
    }

    public int getRecommendFriends(List<TPlayerSimpleInfo> friends) {
        // 랜덤으로 갖고올 친구 수 - 양쪽 다 체크해야하기때문에 2배 해준 후 버퍼를 더해준다.
        int friendCount = GameData.COMMUNITY.friendMaxCount * 2 + Constants.RECOMMEND_FRIEND_BUFFER;
        var recommendFriends = SRedis.INSTANCE.content.players.getPlayerRandomPlayer(friendCount);
        if (Objects.isNull(recommendFriends) || recommendFriends.isEmpty()) {
            return StatusCode.FAIL;
        }

        var now = Instant.now();
        for (var recommendFriendId : recommendFriends) {
            if (recommendFriendId == player.getId()) {
                continue;
            }

            if (friends.size() == GameData.COMMUNITY.recommendFriendCount) {
                break;
            }

            boolean isMyFriend = isMyFriend(recommendFriendId);
            if (isMyFriend) {
                continue;
            }

            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(recommendFriendId);
            if (Objects.isNull(targetPlayer)) {
                var offline = SDB.dbContext.player.get(recommendFriendId);
                if (Objects.isNull(offline)) {
                    continue;
                }

                friends.add(TPlayerSimpleInfo.newBuilder()
                        .setPlayerId(offline.id)
                        .setFriendCode(offline.friend_code)
                        .setName(offline.name)
                        .setLevel(offline.level)
                        .setPortraitId(offline.portrait_id)
                        .setAttackPower(offline.attack_power)
                        .setTimeSinceLastLogoutTime(now.getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                        .build());
            } else {
                friends.add(TPlayerSimpleInfo.newBuilder()
                        .setPlayerId(targetPlayer.getId())
                        .setFriendCode(targetPlayer.getFriendCode())
                        .setName(targetPlayer.getName())
                        .setLevel(targetPlayer.getLevel())
                        .setPortraitId(targetPlayer.getPortraitIcon())
                        .setAttackPower(targetPlayer.getAttackPower())
                        .setTimeSinceLastLogoutTime(0)
                        .build());
            }
        }
        return StatusCode.SUCCESS;
    }

    private boolean isMyFriend(long targetPlayerId) {
        return models.values().stream()
                .anyMatch(x -> x.target_player_id == targetPlayerId);
    }

    private void sendOnlineAddTargetPlayer(long friendId, WorldPlayer targetPlayer) {
        // 차단 상태이면 보내지 않는다.
        if (targetPlayer.block.isBlock(player.getId())) {
            return;
        }

        var builder = TFriendInfo.newBuilder()
                .setFriendId(friendId)
                .setPlayerId(player.getId())
                .setPortraitId(player.getPortraitIcon())
                .setName(player.getName())
                .setLevel(player.getLevel())
                .setTimeSinceLastLogoutTime(0)
                .setAttackPower(player.getAttackPower())
                .setGiftExpireTime(0)
                .setState(ZFriend.State.REQUEST_RECEIVED)
                .build();

        targetPlayer.sendPacket(0, ZAddFriendNotify.newBuilder()
                .setRequestPlayer(builder));
    }

    public DMPlayerFriend getFriend(long playerId) {
        return models.values().stream()
                .filter(x -> x.target_player_id == playerId)
                .findAny()
                .orElse(null);
    }

    private void setOfflinePlayerInfo(long targetPlayerId, Instant now, TFriendInfo.Builder builder) {
        var offline = SDB.dbContext.player.get(targetPlayerId);
        if (Objects.nonNull(offline)) {
            builder.setLevel(offline.level)
                    .setName(offline.name)
                    .setAttackPower(offline.attack_power)
                    .setTimeSinceLastLogoutTime(now.getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                    .setPortraitId(offline.portrait_id);
        }
    }
}

