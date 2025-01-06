package org.supercat.growstone.components.instanceDungeons;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.EventPlayerClearDungeon;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.model.RMPlayerCompetitiveRaid;
import org.supercat.growstone.models.DMPlayerRaidDungeon;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.DungeonMode;
import org.supercat.growstone.types.DungeonType;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRaidDungeonComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerRaidDungeonComponent.class);
    private WorldPlayer player;
    private Map<Long, DMPlayerRaidDungeon> raidDungeons = new HashMap<>();

    public PlayerRaidDungeonComponent(WorldPlayer player) {
        this.player = player;
    }
    public void initialize() {
        raidDungeons = SDB.dbContext.raidDungeon.getByPlayerId(player.getId()).stream()
                .collect(Collectors.toMap(x -> x.raid_data_id, x -> x));
    }

    public List<TRaidDungeon> getTRaidDungeon() {
        int nowYmd = UtcZoneDateTime.getYmd();
        return raidDungeons.values().stream()
                .filter(x -> x.reset_ymd == nowYmd)
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }

    public DMPlayerRaidDungeon getOrCreate(long raidDataId) {
        var model = raidDungeons.get(raidDataId);
        if (Objects.isNull(model)) {
            model = DMPlayerRaidDungeon.of(player.getId(), raidDataId);
            raidDungeons.put(raidDataId, model);
        }

        int nowYmd = UtcZoneDateTime.getYmd();
        if (model.reset_ymd != nowYmd) {
            reset(model, nowYmd);
        }

        return model;
    }

    public TCooperativeRaidDungeon getCooperativeRaidInfo(long dataId) {
        int nowYmd = UtcZoneDateTime.getYmd();
        var model = getOrCreate(dataId);
        var serverAccumulatedScore = SRedis.INSTANCE.content.cooperativeRaidScore.get(dataId, nowYmd);
        return TBuilderOf.buildOf(model, player.getName(), serverAccumulatedScore);
    }

    public TCompetitiveRaidDungeon getCompetitiveRaidInfo(long dataId) {
        var player = getCompetitiveRaidPlayerInfo(dataId);
        var topPlayer = getCompetitiveRaidTopPlayerInfo(dataId);

        return TBuilderOf.buildOf(player, topPlayer);
    }

    public TCompetitiveRaidDungeonPlayer getCompetitiveRaidPlayerInfo(long dataId) {
        int nowYmd = UtcZoneDateTime.getYmd();
        var model = getOrCreate(dataId);
        long rank = SRedis.INSTANCE.rank.competitiveRank.getRank(dataId, nowYmd, player.getId());
        var bucket = SRedis.INSTANCE.rank.competitiveRank.getCached(dataId, nowYmd, player.getId());
        if(Objects.isNull(bucket)) {
            logger.error("get competitive raid info fail - playerId({}), raidDungeonId({})", player.getId(), dataId);
            return TBuilderOf.buildOf(model, player.getName(), (int)rank, 0);
        }

        var rmCompetitive = JsonConverter.of(bucket, RMPlayerCompetitiveRaid.class);
        if(Objects.isNull(rmCompetitive)) {
            logger.error("competitive raid info parse fail - playerId({}), raidDungeonId({})", player.getId(), dataId);
            return TBuilderOf.buildOf(model, player.getName(), (int)rank, 0);
        }

        return TBuilderOf.buildOf(model, player.getName(), (int)rank, rmCompetitive.avatar_id);
    }

    private TCompetitiveRaidDungeonTopPlayer getCompetitiveRaidTopPlayerInfo(long dataId) {
        int nowYmd = UtcZoneDateTime.getYmd();
        var topPlayerId = SRedis.INSTANCE.rank.competitiveRank.getTopRankPlayerId(dataId, nowYmd);
        long playerId = Long.parseLong(topPlayerId);
        long score = SRedis.INSTANCE.rank.competitiveRank.getTopRankPlayerScore(dataId, nowYmd);
        var bucket = SRedis.INSTANCE.rank.competitiveRank.getCached(dataId,  nowYmd, playerId);
        if(Objects.isNull(bucket)) {
            logger.error("competitive raid top player info fail - playerId({}), raidDungeonId({})", playerId, dataId);
            return TBuilderOf.buildOf("", score, 0);
        }
        var rmCompetitive = JsonConverter.of(bucket, RMPlayerCompetitiveRaid.class);
        if(Objects.isNull(rmCompetitive)) {
            logger.error("competitive raid info parse fail - playerId({}), raidDungeonId({})", player.getId(), dataId);
            return TBuilderOf.buildOf("", score, 0);
        }

        return TBuilderOf.buildOf(rmCompetitive.name, score, rmCompetitive.avatar_id);
    }
    public int isEnableStartRaid(long dataId) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resDungeon.type != DungeonType.RAID) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);

        if(model.remain_ticket_count <= 0) {
            return StatusCode.NOT_ENOUGH_DUNGEON_TICKET;
        }

        return StatusCode.SUCCESS;
    }

    private void reset(DMPlayerRaidDungeon model, int nowYmd) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(model.raid_data_id);
        if (Objects.isNull(resDungeon)) {
            logger.error("raid dungeon model reset fail - playerId({}), raidDungeonId({})", player.getId(), model.raid_data_id);
            return;
        }

        model.reset_ymd = nowYmd;
        model.remain_view_ad_count = resDungeon.adViewMaxCount;
        if (Objects.nonNull(resDungeon.ticket) && model.remain_ticket_count < resDungeon.ticket.ticketCount) {
            model.remain_ticket_count = resDungeon.ticket.ticketCount;
        }
        model.score = 0;

        SDB.dbContext.raidDungeon.save(model);
    }
    public int viewAdvertise(long dataId, TRaidDungeon.Builder out) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resDungeon.type != DungeonType.RAID) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if(model.remain_view_ad_count <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        --model.remain_view_ad_count;
        ++model.remain_ticket_count;
        SDB.dbContext.raidDungeon.save(model);

        out.mergeFrom(TBuilderOf.buildOf(model));

        return StatusCode.SUCCESS;
    }

    public int clearRaidDungeon(long dataId, int stage, long score, List<TContentReward> items, TRaidDungeon.Builder out) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resDungeon.type != DungeonType.RAID) {
            return StatusCode.INVALID_REQUEST;
        }

        var rewards = resDungeon.clearRewardsByStage.get(stage);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_REQUEST;
        }

        long diff = 0;
        var model = getOrCreate(dataId);
        if(score > model.score) {
            diff = score - model.score;
            model.score = score;
        }

        SDB.dbContext.raidDungeon.save(model);

        int nowYmd = UtcZoneDateTime.getYmd();
        if(resDungeon.mode == DungeonMode.COOPERATIVE_RAID) {
            clearCooperativeRaid(resDungeon.id, diff, nowYmd);
        } else if(resDungeon.mode == DungeonMode.COMPETITIVE_RAID && diff > 0) {
            clearCompetitiveRaid(resDungeon.id, model.score, nowYmd);
        }

        for(var reward : rewards) {
            long count = reward.count;
            long bonusCount = (long)(reward.count * resDungeon.rewardBonusPercent);
            player.categoryFilter.add(reward.type, reward.rewardId, count);
            items.add(TBuilderOf.buildOf(reward.type, reward.rewardId, count, bonusCount));
        }

        out.mergeFrom(TBuilderOf.buildOf(model));

        player.topic.publish(new EventPlayerClearDungeon(dataId));

        return StatusCode.SUCCESS;
    }

    private void clearCooperativeRaid(long raidId, long score, int ymd) {
        // 서버 점수 합산
        SRedis.INSTANCE.content.cooperativeRaidScore.add(raidId, ymd, score);

        // 보상을 위한 플레이어 점수 추가
        SRedis.INSTANCE.content.cooperativeRaidScore.addPlayer(raidId, ymd, player.getId(),score);
    }

    private void clearCompetitiveRaid(long raidId, long score, int ymd) {
        // 랭킹 추가
        SRedis.INSTANCE.rank.competitiveRank.addOrUpdateScore(raidId, ymd, player.getId(), score);

        // 아바타 정보 추가
        var model = RMPlayerCompetitiveRaid.of(player.getId(), player.avatar.getEquipAvatarId(), player.getName());
        SRedis.INSTANCE.rank.competitiveRank.addAsync(raidId, ymd, player.getId(), model);
    }

    public List<TCompetitiveRaidDungeonPlayerRankInfo> getRankingList(long dataId, int start_rank) {
        var scores = SRedis.INSTANCE.rank.competitiveRank.entries(dataId, UtcZoneDateTime.getYmd(), start_rank, 10);
        if(Objects.isNull(scores) || scores.isEmpty()) {
            return null;
        }

        var resultList = new ArrayList<TCompetitiveRaidDungeonPlayerRankInfo>();
        int rank = start_rank;
        for(var score : scores) {
            ++rank;
            long playerId = Long.parseLong(score.getKey());
            var bucket = SRedis.INSTANCE.rank.competitiveRank.getCached(dataId, UtcZoneDateTime.getYmd(), playerId);
            if(Objects.isNull(bucket)) {
                logger.error("competitive raid player info fail - playerId({}), raidDungeonId({})", playerId, dataId);
                continue;
            }

            var model = JsonConverter.of(bucket, RMPlayerCompetitiveRaid.class);
            long playerScore =   score.getValue();

            resultList.add(TBuilderOf.buildOfRankInfo(model.name, playerScore, rank));
        }

        return resultList;
    }
}
