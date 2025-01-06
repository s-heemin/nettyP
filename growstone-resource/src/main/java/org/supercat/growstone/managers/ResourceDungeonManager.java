package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import jdk.jfr.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.dungeons.ResourceDungeon;
import org.supercat.growstone.stats.ResourceStatGrowth;
import org.supercat.growstone.types.DungeonMode;
import org.supercat.growstone.types.DungeonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResourceDungeonManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceDungeonManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceDungeon> dungeons;
    public static ResourceDungeonManager of(ResourceContext ctx) {
        return new ResourceDungeonManager(ctx);
    }

    private ResourceDungeonManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.dungeons = load(ResourceDungeon::new, ctx.absolutePathBy(ResourceFile.DUNGEONS), "Dungeon");
    }

    public ResourceDungeon get(long id) {
        return dungeons.get(id);
    }

    public List<ResourceDungeon> getAll() {
        return dungeons.values().stream().toList();
    }

    public List<ResourceDungeon> getAllRaidDungeonByMode(DungeonMode mode) {
        return dungeons.values().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == mode)
                .collect(Collectors.toList());
    }
    public boolean verify() {
        if (!checkDungeon()) {
            return false;
        }

        return true;
    }

    public boolean checkDungeon() {
        var errors = new ArrayList<String>();
        errors.forEach(logger::error);
        for(var resDungeon : dungeons.values()) {
            var resMap = ctx.map.get(resDungeon.mapId);
            if(Objects.isNull(resMap)) {
                errors.add(String.format("map not found, dungeonId: %d, mapId: %d", resDungeon.id, resDungeon.mapId));
            }
            if(resDungeon.mode == DungeonMode.NONE) {
                errors.add(String.format("dungeon mode is NONE, dungeonId: %d", resDungeon.id));
            }

            if(resDungeon.type == DungeonType.NONE) {
                errors.add(String.format("dungeon type is NONE, dungeonId: %d", resDungeon.id));
            }

            if(Objects.nonNull(resDungeon.ticket)) {
                var resItem = ctx.item.getItem(resDungeon.ticket.ticketId);
                if(Objects.isNull(resItem)) {
                    errors.add(String.format("ticket item not found, dungeonId: %d, itemId: %d", resDungeon.id, resDungeon.ticket.ticketId));
                }

                if(resDungeon.ticket.ticketCount <= 0) {
                    errors.add(String.format("ticket count is invalid, dungeonId: %d, count: %d", resDungeon.id, resDungeon.ticket.ticketCount));
                }
            }

            if(Objects.nonNull(resDungeon.clearRewardsByStage)) {
                for(var entry : resDungeon.clearRewardsByStage.entrySet()) {
                    var stage = entry.getKey();
                    var rewards = entry.getValue();
                    if(Objects.isNull(rewards) || rewards.isEmpty()) {
                        errors.add(String.format("clear reward is empty, dungeonId: %d, stage: %d", resDungeon.id, stage));
                    }

                    checkRewards(resDungeon.id, rewards, errors);
                }
            }

            if(Objects.nonNull(resDungeon.soloRankRewards)) {
                for(var reward : resDungeon.soloRankRewards.values()) {
                    checkRewards(resDungeon.id, reward, errors);
                }
            }

            if(Objects.nonNull(resDungeon.serverTotalPointRewards)) {
                for(var reward : resDungeon.serverTotalPointRewards.entrySet()) {
                    if(reward.getKey() <= 0) {
                        errors.add(String.format("server total point is invalid, dungeonId: %d, point: %d", resDungeon.id, reward.getKey()));
                    }

                    checkRewards(resDungeon.id, reward.getValue(), errors);
                }
            }

        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private void checkRewards(long dungeonId, List<ResourceReward> rewards, List<String> errors) {
        for(var reward : rewards) {
            if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                errors.add(String.format("dungeon reward is invalid - dungeonId ({%d}), category({%d}), dataId({%d}), count({%d})"
                        , dungeonId, reward.type.getNumber(), reward.rewardId, reward.count));
            }
        }
    }
}
