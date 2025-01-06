package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.ZMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.jsons.JMPlayerMailReward;
import org.supercat.growstone.model.RMPlayerAttackPower;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.rules.MailRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.DungeonMode;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class WorldDailyResetTaskComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldDailyResetTaskComponent.class);

    public void start() {
        try {
            logger.info("--------------------------daily reward --------------------------");

            // 일간 전투력 랭킹 초기화
            resetDailyAttackRank();

            // 일간 레이드 보상 작업
            int prevYmd = UtcZoneDateTime.getPreviousDayYmd();
            giveCooperativeRaidDungeonRewards(prevYmd);
            giveCompetitiveRaidDungeonRewards(prevYmd);

        } catch (Exception e) {
            SLog.logException(e);
        }
    }

    private boolean giveCooperativeRaidDungeonRewards(int prevYmd) {
        // 협동 레이드 보상
        var resDungeons = ResourceManager.INSTANCE.dungeon.getAllRaidDungeonByMode(DungeonMode.COOPERATIVE_RAID);
        if (Objects.isNull(resDungeons) || resDungeons.isEmpty()) {
            return false;
        }


        for (var resDungeon : resDungeons) {
            var accumulatedScore = SRedis.INSTANCE.content.cooperativeRaidScore.get(resDungeon.id, prevYmd);
            var playerIds = SRedis.INSTANCE.content.cooperativeRaidScore.getAll(resDungeon.id, prevYmd);
            if (Objects.isNull(playerIds) || playerIds.isEmpty()) {
                continue;
            }

            var resRewards = resDungeon.serverTotalPointRewards.floorEntry(accumulatedScore);
            if (Objects.isNull(resRewards)) {
                continue;
            }

            int successPlayer = 0;
            for (var playerId : playerIds.values()) {
                var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
                var rewards = MailRules.getJMPlayerMailReward(resRewards.getValue());
                if (!rewards.isEmpty()) {
                    if (Objects.isNull(player)) {
                        //우편함으로 바로 토스
                        int affected = sendOfflinePlayerMail(playerId, ZMail.Type.RAID_COOPERATIVE_REWARD_VALUE,
                                rewards, UtcZoneDateTime.getPlusDay(7));
                        if (affected <= 0) {
                            logger.error("daily cooperative raid mail send fail - playerId({}), raidDungeonId({}), ymd({})", playerId, resDungeon.id, prevYmd);
                        }

                    } else {
                        player.mail.insertMail(ZMail.Type.RAID_COOPERATIVE_REWARD_VALUE, "system",
                                rewards, UtcZoneDateTime.getPlusDay(7));
                    }
                }
                ++successPlayer;
            }

            logger.info("daily cooperative raid reward - raidDungeonId({}), ymd({}), accumulatedScore({}), successPlayer({})",
                    resDungeon.id, prevYmd, accumulatedScore, successPlayer);
        }

        return true;
    }

    public boolean giveCooperativeRaidDungeonRewardsForTest(int prevYmd) {
        return giveCooperativeRaidDungeonRewards(prevYmd);
    }

    private boolean giveCompetitiveRaidDungeonRewards(int prevYmd) {
        // 협동 레이드 보상
        var resDungeons = ResourceManager.INSTANCE.dungeon.getAllRaidDungeonByMode(DungeonMode.COMPETITIVE_RAID);
        if (Objects.isNull(resDungeons) || resDungeons.isEmpty()) {
            return false;
        }

        for (var resDungeon : resDungeons) {
            int getLastRank = resDungeon.getLastRank();
            var scores = SRedis.INSTANCE.rank.competitiveRank.entries(resDungeon.id, prevYmd, 0, getLastRank);
            int rank = 0;
            int successPlayer = 0;
            for (var score : scores) {
                ++rank;
                long playerId = Long.parseLong(score.getKey());

                var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
                var rewards = MailRules.getJMPlayerMailReward(resDungeon.getRankRewards(rank));
                if (!rewards.isEmpty()) {
                    if (Objects.isNull(player)) {
                        //우편함으로 바로 토스
                        int affected = sendOfflinePlayerMail(playerId, ZMail.Type.RAID_COMPETITIVE_REWARD_VALUE,
                                rewards, UtcZoneDateTime.getPlusDay(7));
                        if (affected <= 0) {
                            logger.error("daily competitive raid mail send fail - playerId({}), raidDungeonId({}), ymd({})", playerId, resDungeon.id, prevYmd);
                        }

                    } else {
                        player.mail.insertMail(ZMail.Type.RAID_COMPETITIVE_REWARD_VALUE, "system",
                                rewards, UtcZoneDateTime.getPlusDay(7));
                    }
                }
                ++successPlayer;
            }
            logger.info("daily competitive raid reward - raidDungeonId({}), ymd({}), rank({}), successPlayer({})",
                    resDungeon.id, prevYmd, rank, successPlayer);
        }

        return true;
    }

    public boolean giveCompetitiveRaidDungeonRewardsForTest(int prevYmd) {
        return giveCompetitiveRaidDungeonRewards(prevYmd);
    }

    private int sendOfflinePlayerMail(long playerId, int mailType, List<JMPlayerMailReward> rewards, Instant expireDay) {
        var json = JsonConverter.to(rewards);
        return SDB.dbContext.mail.insert(DMPlayerMail.of(playerId, mailType, "system", json, expireDay));
    }

    public void resetDailyAttackRankForTest() {
        resetDailyAttackRank();
    }

    private void resetDailyAttackRank() {
        int nowYmd = UtcZoneDateTime.getYmd();
        var rMaps = SRedis.INSTANCE.content.playerAttackPower.getAllPlayersAttackPower();
        var attackPowers = List.copyOf(rMaps);
        for (var attackPower : attackPowers) {
            var model = JsonConverter.of(attackPower.getValue(), RMPlayerAttackPower.class);
            SRedis.INSTANCE.rank.playerAttackPowerRank.addOrUpdateScore(nowYmd, model.playerId, model);
        }
    }
}
