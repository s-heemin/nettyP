package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SRedis;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.World;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorldDuelComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldDuelComponent.class);
    public int getDuelPlayerInfo(long playerId, ZDuelResponse.Builder builder) {
        var stats = new HashMap<ZStat.Type, Double>();
        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if (Objects.nonNull(player)) {
            builder.addAllStats(player.stat.getTStats()).setPlayerDetailInfo(World.INSTANCE.worldPlayers.getPlayerDetailInfo(playerId));
            return StatusCode.SUCCESS;
        }

        var rMap = SRedis.INSTANCE.content.playerStat.getStatByPlayerId(playerId);
        if (rMap.isEmpty()) {
            return StatusCode.FAIL;
        }

        for (var entry : rMap.entrySet()) {
            var type = ZStat.Type.valueOf(Integer.parseInt(entry.getKey()));
            var value = Double.valueOf(entry.getValue());
            stats.put(type, value);
        }

        var TStats = stats.entrySet().stream()
                .map(x -> TStat.newBuilder().setType(x.getKey()).setValue(x.getValue()).build())
                .collect(Collectors.toList());
        builder.addAllStats(TStats)
                .setPlayerDetailInfo(World.INSTANCE.worldPlayers.getPlayerDetailInfo(playerId));

        return StatusCode.SUCCESS;
    }

    public TPlayerDuelHistory.Builder addDuelHistory(long playerId, TPlayerDuelHistory history) {
        int nowYW = UtcZoneDateTime.getYW();
        var winPlayer = history.getWinPlayer();
        if(Objects.isNull(winPlayer)) {
            return TPlayerDuelHistory.newBuilder();
        }

        var losePlayer = history.getLosePlayer();
        if(Objects.isNull(losePlayer)) {
            return TPlayerDuelHistory.newBuilder();
        }

        var win = computePlayerDuelInfo(winPlayer.getPlayerId(), winPlayer.getSeed());
        var lose = computePlayerDuelInfo(losePlayer.getPlayerId(), losePlayer.getSeed());

        var model = SRedis.INSTANCE.content.playerDuelHistory.add(nowYW, playerId, history.getCriticalSeed(), win, lose);

        return TPlayerDuelHistory.newBuilder()
                .setId(model.id)
                .setCriticalSeed(model.critical_seed)
                .setWinPlayer(win)
                .setLosePlayer(lose);
    }

    public TPlayerDuel computePlayerDuelInfo(long playerId, int seed) {
        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        if(Objects.nonNull(player)) {
            return TPlayerDuel.newBuilder()
                    .setAvatarId(player.avatar.getEquipAvatarId())
                    .setName(player.getName())
                    .setPlayerId(playerId)
                    .setSeed(seed).build();
        } else {
            var offline = SDB.dbContext.player.get(playerId);
            if(Objects.isNull(offline)) {
                return TPlayerDuel.newBuilder().build();
            }

            long avatarId = 0;
            var avatar = SDB.dbContext.avatar.getByEquipAvatar(offline.id);
            if(Objects.nonNull(avatar)) {
                avatarId = avatar.avatar_id;
            }

            return TPlayerDuel.newBuilder()
                    .setAvatarId(avatarId)
                    .setName(offline.name)
                    .setPlayerId(playerId)
                    .setSeed(seed).build();
        }
    }

    public List<TPlayerDuelHistory> getAllHistories(long playerId) {
        int nowYW = UtcZoneDateTime.getYW();
        var histories = SRedis.INSTANCE.content.playerDuelHistory.getAll(nowYW, playerId);
        return histories.stream()
                .map(x -> TPlayerDuelHistory.newBuilder()
                        .setId(x.id)
                        .setCriticalSeed(x.critical_seed)
                        .setWinPlayer(TPlayerDuel.newBuilder()
                                .setPlayerId(x.win_player_id)
                                .setName(x.win_player_name)
                                .setAvatarId(x.win_player_avatar_id)
                                .setSeed(x.win_player_seed)
                                .build())
                        .setLosePlayer(TPlayerDuel.newBuilder()
                                .setPlayerId(x.lose_player_id)
                                .setName(x.lose_player_name)
                                .setAvatarId(x.lose_player_avatar_id)
                                .setSeed(x.lose_player_seed)
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

}
