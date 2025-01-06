package org.supercat.growstone.components.instanceDungeons;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TTower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.events.EventPlayerClearDungeon;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerTower;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.DungeonType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerTowerComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerTowerComponent.class);
    private WorldPlayer player;
    private Map<Long, DMPlayerTower> towers = new HashMap<>();

    public PlayerTowerComponent(WorldPlayer player) {
        this.player = player;
    }
    public void initialize() {
        towers = SDB.dbContext.tower.getByPlayerId(player.getId()).stream()
                .collect(Collectors.toMap(x -> x.tower_data_id, x -> x));
    }
    public List<TTower> getTTower() {
        return towers.values().stream()
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }

    public DMPlayerTower getOrCreate(long towerId) {
        var model = towers.get(towerId);
        if (Objects.isNull(model)) {
            model = DMPlayerTower.of(player.getId(), towerId);
            towers.put(towerId, model);
        }

        return model;
    }

    public int isEnableStartTower(long dataId, int stage) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resDungeon.type != DungeonType.TOWER) {
            return StatusCode.INVALID_REQUEST;
        }

        if(stage <= 0 || stage > resDungeon.clearRewardsByStage.size()) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if(stage != model.stage) {
            return StatusCode.NOT_VALID_STAGE;
        }

        return StatusCode.SUCCESS;
    }

    public int clearTower(long dataId, int stage, TTower.Builder out, List<TContentReward> items) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(dataId);
        if(Objects.isNull(resDungeon)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resDungeon.type != DungeonType.TOWER) {
            return StatusCode.INVALID_REQUEST;
        }

        var rewards = resDungeon.clearRewardsByStage.get(stage);
        if(Objects.isNull(stage)) {
            return StatusCode.INVALID_REQUEST;
        }

        var model = getOrCreate(dataId);
        if(stage != model.stage) {
            return StatusCode.INVALID_REQUEST;
        }

        var resNextStage = resDungeon.clearRewardsByStage.get(stage + 1);
        if(Objects.nonNull(resNextStage)) {
            ++model.stage;
        }

        for(var reward : rewards) {
            long count = reward.count;
            long bonusCount = (long)(reward.count * resDungeon.rewardBonusPercent);
            player.categoryFilter.add(reward.type, reward.rewardId, count);
            items.add(TBuilderOf.buildOf(reward.type, reward.rewardId, count, bonusCount));
        }

        SDB.dbContext.tower.save(model);

        out.mergeFrom(TBuilderOf.buildOf(model));

        player.topic.publish(new EventPlayerClearDungeon(dataId));

        return StatusCode.SUCCESS;
    }
}
