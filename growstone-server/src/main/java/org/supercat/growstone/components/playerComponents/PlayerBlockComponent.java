package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.TPlayerSimpleInfo;
import com.supercat.growstone.network.messages.ZDeleteFriendNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Out;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.models.DMPlayerBlock;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerBlockComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerBlockComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<Long, DMPlayerBlock> models;

    public PlayerBlockComponent(WorldPlayer player) {
        this.player = player;
        this.models = new ConcurrentHashMap<>();
    }

    public void initialize() {
        var l = SDB.dbContext.block.getByPlayerId(player.getId());
        for (var model : l) {
            models.put(model.target_player_id, model);
        }
    }

    public DMPlayerBlock get(long playerId) {
        return models.get(playerId);
    }

    public boolean isBlock(long playerId) {
        return models.containsKey(playerId);
    }

    public List<TPlayerSimpleInfo> getTBlockPlayers() {
        var result = new ArrayList<TPlayerSimpleInfo>();
        for (var model : models.values()) {
            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(model.target_player_id);
            if (Objects.isNull(targetPlayer)) {
                var offline = SDB.dbContext.player.get(model.target_player_id);
                result.add(TPlayerSimpleInfo.newBuilder()
                        .setPlayerId(offline.id)
                        .setName(offline.name)
                        .setLevel(offline.level)
                        .setTimeSinceLastLogoutTime(Instant.now().getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                        .setAttackPower(offline.attack_power)
                        .setPortraitId(offline.portrait_id)
                        .build());
                continue;
            }

            result.add(TPlayerSimpleInfo.newBuilder()
                    .setPlayerId(targetPlayer.getId())
                    .setName(targetPlayer.getName())
                    .setLevel(targetPlayer.getLevel())
                    .setPortraitId(targetPlayer.getPortraitIcon())
                    .setAttackPower(targetPlayer.getAttackPower())
                    .build());
        }
        return result;
    }

    public int getBlockSize() {
        return models.size();
    }

    public int block(long targetPlayerId, TPlayerSimpleInfo.Builder builder) {
        var model = DMPlayerBlock.of(player.getId(), targetPlayerId);
        int affected = SDB.dbContext.block.insert(model);
        if (affected <= 0) {
            logger.error("block player failed. playerId: {}, targetId({})", player.getId(), targetPlayerId);
            return StatusCode.FAIL;
        }

        var now = Instant.now();
        var friend = player.friend.getFriend(targetPlayerId);
        if(Objects.nonNull(friend)) {
            player.friend.remove(friend.id);
        }

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(targetPlayerId);
        if (Objects.nonNull(targetPlayer)) {
            var targetFriend = targetPlayer.friend.getFriend(player.getId());
            if (Objects.nonNull(targetFriend)) {
                long targetFriendId = targetFriend.id;
                targetPlayer.friend.remove(targetFriendId);
                targetPlayer.sendPacket(0, ZDeleteFriendNotify.newBuilder()
                        .setDeleteFriendId(targetFriendId));
            }
            builder.mergeFrom(TPlayerSimpleInfo.newBuilder()
                    .setPlayerId(targetPlayer.getId())
                    .setName(targetPlayer.getName())
                    .setLevel(targetPlayer.getLevel())
                    .setTimeSinceLastLogoutTime(0)
                    .setAttackPower(targetPlayer.getAttackPower())
                    .setPortraitId(targetPlayer.getPortraitIcon())
                    .build());
        } else {
            var targetFriend = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(targetPlayerId, player.getId());
            if(Objects.nonNull(targetFriend)) {
                SDB.dbContext.friend.delete(targetFriend.id);
            }
            var offline = SDB.dbContext.player.get(targetPlayerId);
            builder.mergeFrom(TPlayerSimpleInfo.newBuilder()
                    .setPlayerId(offline.id)
                    .setName(offline.name)
                    .setLevel(offline.level)
                    .setTimeSinceLastLogoutTime(now.getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                    .setAttackPower(offline.attack_power)
                    .setPortraitId(offline.portrait_id)
                    .build());
        }

        models.put(model.target_player_id, model);

        return StatusCode.SUCCESS;
    }

    private void remove(long id, long targetPlayerId, Out<Long> targetFriendId) {
        int affected = SDB.dbContext.friend.delete(id);
        if (affected <= 0) {
            logger.error("blockFriend failed. friend id: {}, playerId({}), targetPlayerId({})", id, player.getId(), targetPlayerId);
        }

        var targetModel = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(targetPlayerId, player.getId());
        if (Objects.nonNull(targetModel)) {
            affected = SDB.dbContext.friend.delete(targetModel.id);
            if (affected <= 0) {
                logger.error("blockFriend failed. friend id: {}, playerId({}), targetPlayerId({})", id, player.getId(), targetPlayerId);
            }

            targetFriendId.set(targetModel.id);
        }
    }

    public int unBlock(long targetPlayerId) {
        var model = models.get(targetPlayerId);
        if (Objects.isNull(model)) {
            return StatusCode.NOT_FOUND_FRIEND;
        }

        int affected = SDB.dbContext.block.delete(model.id);
        if (affected <= 0) {
            logger.error("delete player failed. playerId: {}, targetPlayerId({})", player.getId(), targetPlayerId);
            return StatusCode.FAIL;
        }

        models.remove(model.target_player_id);

        var targetPlayerFriend = SDB.dbContext.friend.getByPlayerIdAndTargetPlayerId(targetPlayerId, player.getId());
        if (Objects.nonNull(targetPlayerFriend)) {
            var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(targetPlayerId);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.friend.remove(targetPlayerFriend.id);
                targetPlayer.sendPacket(0L, ZDeleteFriendNotify.newBuilder()
                        .setDeleteFriendId(targetPlayerFriend.id));
            }

            SDB.dbContext.friend.delete(targetPlayerFriend.id);
        }

        return StatusCode.SUCCESS;
    }

}
