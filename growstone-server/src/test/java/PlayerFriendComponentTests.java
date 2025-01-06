import com.supercat.growstone.network.messages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PlayerFriendComponentTests extends BaseServerTests {
    @Test
    void addFriendTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend("-1", out);
        Assertions.assertEquals(StatusCode.NOT_FOUND_PLAYER, status);

        out.clear();
        status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        Assertions.assertEquals(friend.getId(), playerFriendModel.get(0).target_player_id);
        Assertions.assertEquals(playerFriendModel.get(0).state, ZFriend.State.PENDING_REQUEST.getNumber());

        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());
        Assertions.assertEquals(player.getId(), friendModel.get(0).target_player_id);
        Assertions.assertEquals(friendModel.get(0).state, ZFriend.State.REQUEST_RECEIVED.getNumber());

        // out
        Assertions.assertEquals(playerFriendModel.get(0).id, out.getFriendId());
        Assertions.assertEquals(friend.getName(), out.getName());
        Assertions.assertEquals(friend.getLevel(), out.getLevel());
        Assertions.assertEquals(friend.getPortraitIcon(), out.getPortraitId());
        Assertions.assertEquals(friend.getModel().attack_power, out.getAttackPower());
        Assertions.assertEquals(ZFriend.State.PENDING_REQUEST, out.getState());
        Assertions.assertEquals(0, out.getGiftExpireTime());
        Assertions.assertEquals(0, out.getTimeSinceLastLogoutTime());
    }

    @Test
    void addFriendMaxCountFailTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var friends = new ArrayList<WorldPlayer>();
        for (int i = 0; i < 50; i++) {
            friends.add(TestPlayerUtils.of());
        }

        var out = TFriendInfo.newBuilder();
        for (var fd : friends) {
            int status = player.friend.addFriend(fd.getFriendCode(), out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            var model = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(fd.getId(), player.getId());
            fd.friend.addFriendResponse(model.id, true, TFriendInfo.newBuilder());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.MAX_FRIEND_COUNT, status);

        //친구 한명 삭제
        var model = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(player.getId(), friends.get(0).getId());
        SDB.dbContext.friend.delete(model.id);
        player.friend.remove(friends.get(0).getId());

        out.clear();
        status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.TARGET_PLAYER_MAX_FRIEND_COUNT, status);
    }

    @Test
    void deleteFriendTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        Assertions.assertEquals(friend.getId(), playerFriendModel.get(0).target_player_id);
        Assertions.assertEquals(playerFriendModel.get(0).state, ZFriend.State.PENDING_REQUEST.getNumber());

        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());
        Assertions.assertEquals(player.getId(), friendModel.get(0).target_player_id);
        Assertions.assertEquals(friendModel.get(0).state, ZFriend.State.REQUEST_RECEIVED.getNumber());

        out.clear();
        status = player.friend.deleteFriend(playerFriendModel.get(0).id);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(0, playerFriendModel.size());

        friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(0, friendModel.size());
    }

    @Test
    void sendGiftTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var l = List.of(out.getFriendId());
        var outList = new ArrayList<TFriendInfo>();
        status = player.friend.sendGiftToFriends(List.of(1L), outList);
        Assertions.assertEquals(StatusCode.NOT_FOUND_FRIEND, status);

        status = player.friend.sendGiftToFriends(l, outList);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(player.getId(), friend.getId());
        Assertions.assertNotNull(model);
        var sendGiftExpireAt = model.send_gift_expire_at;
        Assertions.assertTrue(sendGiftExpireAt.isAfter(Instant.now()));

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(friend.getId(), player.getId());
        Assertions.assertNotNull(targetModel);
        var receiveGiftExpireAt = targetModel.receive_gift_expire_at;
        Assertions.assertEquals(sendGiftExpireAt, receiveGiftExpireAt);

        var mails = SDB.dbContext.mail.getAllByNoReadMail(friend.getId());
        Assertions.assertTrue(!mails.isEmpty());
        Assertions.assertEquals(1, mails.size());
    }

    @Test
    void sendGiftFailTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var l = List.of(out.getFriendId());
        var outList = new ArrayList<TFriendInfo>();

        status = player.friend.sendGiftToFriends(List.of(1L), outList);
        Assertions.assertEquals(StatusCode.NOT_FOUND_FRIEND, status);

        status = player.friend.sendGiftToFriends(l, outList);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(player.getId(), friend.getId());
        Assertions.assertNotNull(model);
        var sendGiftExpireAt = model.send_gift_expire_at;
        Assertions.assertTrue(sendGiftExpireAt.isAfter(Instant.now()));

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(friend.getId(), player.getId());
        Assertions.assertNotNull(targetModel);
        var receiveGiftExpireAt = targetModel.receive_gift_expire_at;
        Assertions.assertEquals(sendGiftExpireAt, receiveGiftExpireAt);

        var mails = SDB.dbContext.mail.getAllByNoReadMail(friend.getId());
        Assertions.assertTrue(!mails.isEmpty());
        Assertions.assertEquals(1, mails.size());
    }

    @Test
    void searchFriendTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TPlayerSimpleInfo.newBuilder();
        int status = player.friend.searchFriendFromCode("-1", out);
        Assertions.assertEquals(StatusCode.NOT_FOUND_PLAYER, status);

        status = player.friend.searchFriendFromCode(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(friend.getFriendCode(), out.getFriendCode());
        Assertions.assertEquals(friend.getName(), out.getName());
        Assertions.assertEquals(friend.getLevel(), out.getLevel());
        Assertions.assertEquals(friend.getPortraitIcon(), out.getPortraitId());
        Assertions.assertEquals(friend.getModel().attack_power, out.getAttackPower());
        Assertions.assertEquals(0, out.getTimeSinceLastLogoutTime());
    }

    @Test
    void addFriendCancelTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        Assertions.assertEquals(ZFriend.State.PENDING_REQUEST, ZFriend.State.forNumber(playerFriendModel.get(0).state));
        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());
        Assertions.assertEquals(ZFriend.State.REQUEST_RECEIVED, ZFriend.State.forNumber(friendModel.get(0).state));

        player.friend.changeState(playerFriendModel.get(0).id, ZFriend.State.ACCEPTED);
        status = player.friend.addFriendCancel(playerFriendModel.get(0).id);
        Assertions.assertEquals(StatusCode.NOT_PENDING_FRIEND_REQUEST, status);

        player.friend.changeState(playerFriendModel.get(0).id, ZFriend.State.PENDING_REQUEST);
        status = player.friend.addFriendCancel(playerFriendModel.get(0).id);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(0, playerFriendModel.size());
        friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(0, friendModel.size());
    }

    @Test
    void addFriendResponseNotifyTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());

        friend.friend.changeState(friendModel.get(0).id, ZFriend.State.PENDING_REQUEST);
        status = friend.friend.addFriendResponse(friendModel.get(0).id, false, TFriendInfo.newBuilder());
        Assertions.assertEquals(StatusCode.NOT_PENDING_FRIEND_REQUEST, status);

        friend.friend.changeState(friendModel.get(0).id, ZFriend.State.REQUEST_RECEIVED);
        status = friend.friend.addFriendResponse(friendModel.get(0).id, false, TFriendInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertNull(player.friend.get(playerFriendModel.get(0).id));
        Assertions.assertNull(friend.friend.get(friendModel.get(0).id));
        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(0, playerFriendModel.size());
        friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(0, friendModel.size());
    }

/*    @Test
    void blockFriendTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());

        status = player.block.block(playerFriendModel.get(0).id, TPlayerSimpleInfo.newBuilder());
        Assertions.assertEquals(StatusCode.NOT_BLOCK_CONDITION, status);

        status = friend.friend.addFriendResponse(friendModel.get(0).id, true, TFriendInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(ZFriend.State.ACCEPTED.getNumber(), player.friend.get(playerFriendModel.get(0).id).state);
        Assertions.assertEquals(ZFriend.State.ACCEPTED.getNumber(), friend.friend.get(friendModel.get(0).id).state);
        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(ZFriend.State.ACCEPTED.getNumber(), playerFriendModel.get(0).state);
        Assertions.assertEquals(ZFriend.State.ACCEPTED.getNumber(), friendModel.get(0).state);

        status = player.friend.isBlock(playerFriendModel.get(0).id, TPlayerSimpleInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(ZFriend.State.BLOCKED.getNumber(), player.friend.get(playerFriendModel.get(0).id).state);
        Assertions.assertNull(friend.friend.get(friendModel.get(0).id));
        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(ZFriend.State.BLOCKED.getNumber(), playerFriendModel.get(0).state);
        friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(0, friendModel.size());

    }

    @Test
    void unBlockFriendTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());

        status = friend.friend.addFriendResponse(friendModel.get(0).id, true, TFriendInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.friend.getBlockFriendCount(playerFriendModel.get(0).id, TPlayerSimpleInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        final long friendId = playerFriendModel.get(0).id;
        status = player.block.unBlock(friend.getId());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(0, playerFriendModel.size());

        var model = player.friend.get(friendId);
        Assertions.assertNull(model);
    }*/

    @Test
    void recommendFriendTest() {
        var player = TestPlayerUtils.of();
        var l = new ArrayList<WorldPlayer>();
        for (int i = 0; i < 200; i++) {
            l.add(TestPlayerUtils.of());
        }

        int friendCount = GameData.COMMUNITY.friendMaxCount * 2 + Constants.RECOMMEND_FRIEND_BUFFER;
        var recommendFriends = SRedis.INSTANCE.content.players.getPlayerRandomPlayer(friendCount);
        Assertions.assertNotNull(recommendFriends);
        Assertions.assertTrue(!recommendFriends.isEmpty());

        var recommends = new ArrayList<TPlayerSimpleInfo>();
        int status = player.friend.getRecommendFriends(recommends);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertTrue(!l.isEmpty());
        Assertions.assertEquals(Constants.RECOMMEND_COUNT, recommends.size());
    }

    @Test
    void friendDetailInfoTest() {
        var player = TestPlayerUtils.of();
        var friend = TestPlayerUtils.of();

        var out = TFriendInfo.newBuilder();
        int status = player.friend.addFriend(friend.getFriendCode(), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var playerFriendModel = SDB.dbContext.friend.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerFriendModel.size());
        var friendModel = SDB.dbContext.friend.getByPlayerId(friend.getId());
        Assertions.assertEquals(1, friendModel.size());

        status = friend.friend.addFriendResponse(friendModel.get(0).id, true, TFriendInfo.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void makeFriendTest() {
        final int friendCount = 200;
        for(int i = 0; i < friendCount; i++) {
            var player = TestPlayerUtils.of();

            SDB.dbContext.player.save(player.getModel());
        }
    }
}
