package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.TAttackRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Constants;
import org.supercat.growstone.SRedis;
import org.supercat.growstone.UtcZoneDateTime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldRankComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldRankComponent.class);
    private Instant nextDailyResetSchedule;

    public WorldRankComponent() {
    }

    public void setNextDailyResetScheduleTime(Instant nextDailyResetSchedule) {
        this.nextDailyResetSchedule = nextDailyResetSchedule;
    }
    public List<TAttackRank> getRanks() {
        if(Objects.isNull(nextDailyResetSchedule)) {
            return List.of();
        }

        // 정산될때는 랭킹을 보여주지 않는다.
        var now = Instant.now();
        var before = nextDailyResetSchedule.minusSeconds(Constants.ATTACK_RANK_BUFFER_MINUTE * 60);
        var after = nextDailyResetSchedule.minusSeconds(Constants.ATTACK_RANK_BUFFER_MINUTE * 60);
        if(now.isAfter(before) && now.isBefore(after)) {
            return List.of();
        }

        int nowYmd = UtcZoneDateTime.getYmd();
        var entries = SRedis.INSTANCE.rank.playerAttackPowerRank.entries(nowYmd, 0, Constants.MAX_ATTACK_RANK_COUNT - 1);
        if(entries.isEmpty()) {
            return List.of();
        }

        int rank = 0;
        var l = new ArrayList<TAttackRank>();
        for(var entry : entries) {
            ++rank;
            long playerId = Long.parseLong(entry.getKey());
            long attackPower = entry.getValue();

            var model = SRedis.INSTANCE.rank.playerAttackPowerRank.getSnapshot(playerId);
            if(Objects.isNull(model)) {
                logger.error("playerAttackPowerRank.getSnapshot is null. playerId: {}", playerId);
                continue;
            }

            l.add(TAttackRank.newBuilder()
                    .setRank(rank)
                    .setAvatarId(model.avatarId)
                    .setPortraitId(model.portraitId)
                    .setLevel(model.level)
                    .setPlayerId(playerId)
                    .setName(model.name)
                    .setAttackPower(attackPower)
                    .build());
        }

        return l;
    }

    public TAttackRank getPlayerRankInfo(long playerId) {
        int nowYmd = UtcZoneDateTime.getYmd();
        long rank = SRedis.INSTANCE.rank.playerAttackPowerRank.getRank(nowYmd, playerId);
        if(rank == -1) {
            return TAttackRank.newBuilder().build();
        }

        var model = SRedis.INSTANCE.rank.playerAttackPowerRank.getSnapshot(playerId);
        if(Objects.isNull(model)) {
            logger.error("playerAttackPowerRank.getSnapshot is null. playerId: {}", playerId);
            return TAttackRank.newBuilder().build();
        }

        return TAttackRank.newBuilder()
                .setRank((int)rank)
                .setAvatarId(model.avatarId)
                .setPortraitId(model.portraitId)
                .setLevel(model.level)
                .setPlayerId(playerId)
                .setName(model.name)
                .setAttackPower(model.attackPower)
                .build();
    }
}
