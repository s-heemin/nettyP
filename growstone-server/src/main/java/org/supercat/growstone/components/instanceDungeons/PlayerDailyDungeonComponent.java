package org.supercat.growstone.components.instanceDungeons;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TDailyDungeon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.events.EventPlayerClearDungeon;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.models.DMPlayerDailyDungeon;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.DungeonType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerDailyDungeonComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDailyDungeonComponent.class);
    private WorldPlayer player;
    private Map<Long, DMPlayerDailyDungeon> dailyDungeons = new HashMap<>();

    public PlayerDailyDungeonComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        dailyDungeons = SDB.dbContext.dailyDungeon.getByPlayerId(player.getId()).stream()
                .collect(Collectors.toMap(x -> x.dungeon_data_id, x -> x));
    }

    public List<TDailyDungeon> getTDailyDungeon() {
        int nowYmd = UtcZoneDateTime.getYmd();
        return dailyDungeons.values().stream()
                .filter(x -> x.reset_ymd == nowYmd)
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }

    public DMPlayerDailyDungeon getOrCreate(long dungeonDataId) {
        var model = dailyDungeons.get(dungeonDataId);
        if (Objects.isNull(model)) {
            model = DMPlayerDailyDungeon.of(player.getId(), dungeonDataId);
            dailyDungeons.put(dungeonDataId, model);
        }

        int nowYmd = UtcZoneDateTime.getYmd();
        if (model.reset_ymd != nowYmd) {
            reset(model, nowYmd);
        }

        return model;
    }

    private void reset(DMPlayerDailyDungeon model, int nowYmd) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(model.dungeon_data_id);
        if (Objects.isNull(resDungeon)) {
            logger.error("daily dungeon model reset fail - playerId({}), dungeonId({})", player.getId(), model.dungeon_data_id);
            return;
        }

        model.reset_ymd = nowYmd;
        model.remain_view_ad_count = resDungeon.adViewMaxCount;
        if (Objects.nonNull(resDungeon.ticket) && model.remain_ticket_count < resDungeon.ticket.ticketCount) {
            model.remain_ticket_count = resDungeon.ticket.ticketCount;
        }

        SDB.dbContext.dailyDungeon.save(model);
    }

    public int isEnableStartDungeon(long dataId, int stage) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if (Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (resDungeon.type != DungeonType.DAILY) {
            return StatusCode.INVALID_REQUEST;
        }

        if (stage <= 0 || stage > resDungeon.clearRewardsByStage.size()) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if (stage > model.stage) {
            return StatusCode.NOT_VALID_STAGE;
        }

        if (model.remain_ticket_count <= 0) {
            return StatusCode.NOT_ENOUGH_DUNGEON_TICKET;
        }

        return StatusCode.SUCCESS;
    }

    public int viewAdvertise(long dataId, int stage, TDailyDungeon.Builder out) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if (Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (!DungeonType.UseTicketDungeonTypes.contains(resDungeon.type)) {
            return StatusCode.INVALID_REQUEST;
        }

        if (stage <= 0 || stage > resDungeon.clearRewardsByStage.size()) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if (model.remain_view_ad_count <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        --model.remain_view_ad_count;
        ++model.remain_ticket_count;

        SDB.dbContext.dailyDungeon.save(model);

        out.mergeFrom(TBuilderOf.buildOf(model));

        return StatusCode.SUCCESS;
    }

    public int clearDungeon(long dataId, int stage, int use_dungeon_ticket_count, TDailyDungeon.Builder out, List<TContentReward> items) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if (Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (DungeonType.DAILY != resDungeon.type) {
            return StatusCode.INVALID_REQUEST;
        }

        var rewards = resDungeon.clearRewardsByStage.get(stage);
        if (Objects.isNull(rewards)) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if (stage > model.stage) {
            return StatusCode.INVALID_REQUEST;
        }

        // 스테이지보다 낮은 단계이면 소탕권 사용으로 체크한다.
        boolean isSweep = model.stage > stage;

        if (model.remain_ticket_count <= 0 || model.remain_ticket_count < use_dungeon_ticket_count) {
            return StatusCode.NOT_ENOUGH_DUNGEON_TICKET;
        }

        model.remain_ticket_count -= use_dungeon_ticket_count;

        if(!isSweep) {
            var resNextStage = resDungeon.clearRewardsByStage.get(stage + 1);
            if(Objects.nonNull(resNextStage)) {
                ++model.stage;
            }
        }

        for (var reward : rewards) {
            long count = reward.count * use_dungeon_ticket_count;
            long bonusCount = (long) (reward.count * resDungeon.rewardBonusPercent) * use_dungeon_ticket_count;
            player.categoryFilter.add(reward.type, reward.rewardId, count + bonusCount);
            items.add(TBuilderOf.buildOf(reward.type, reward.rewardId, count, bonusCount));
        }

        SDB.dbContext.dailyDungeon.save(model);

        out.mergeFrom(TBuilderOf.buildOf(model));

        player.topic.publish(new EventPlayerClearDungeon(dataId));

        return StatusCode.SUCCESS;
    }
}
