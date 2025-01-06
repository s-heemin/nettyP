package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZDiggingUpgrade;
import com.supercat.growstone.network.messages.ZTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.digging.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceDiggingManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceDiggingManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceDiggingReduceTime> reduceTimes;
    private final ImmutableMap<Long, ResourceDiggingTiers> tiers;
    private final ImmutableMap<Long, ResourceDiggingZoneCount> zones;
    private final ImmutableMap<Long, ResourceDiggingSpoonCount> spoons;
    private final ImmutableMap<ZTier.Type, ResourceDiggingReward> rewards;
    public static ResourceDiggingManager of(ResourceContext ctx) {
        return new ResourceDiggingManager(ctx);
    }

    private ResourceDiggingManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.reduceTimes = load(ResourceDiggingReduceTime::new, ctx.absolutePathBy(ResourceFile.DIGGING_REDUCE_TIMES), "DiggingReduceTime");
        this.tiers = load(ResourceDiggingTiers::new, ctx.absolutePathBy(ResourceFile.DIGGING_TIERS), "DiggingTier");
        this.zones = load(ResourceDiggingZoneCount::new, ctx.absolutePathBy(ResourceFile.DIGGING_ZONE_COUNT), "DiggingZoneCount");
        this.spoons = load(ResourceDiggingSpoonCount::new, ctx.absolutePathBy(ResourceFile.DIGGING_SPOON_COUNT), "DiggingSpoonCount");
        var tReward = load(ResourceDiggingReward::new, ctx.absolutePathBy(ResourceFile.DIGGING_REWARDS), "DiggingReward");
        this.rewards = tReward.values().stream().collect(ImmutableMap.toImmutableMap(x -> x.tier, x -> x));
    }


    @SuppressWarnings("unchecked")

    public <T extends ResourceBase> T get(ZDiggingUpgrade.Type type, int level) {
        switch (type) {
            case TIME:
                return (T) getReduceTime(level);
            case TIER:
                return (T) getTier(level);
            case ZONE:
                return (T) getZone(level);
            case SPOON:
                return (T) getSpoon(level);
            default:
                return null;
        }
    }




    public ResourceDiggingReduceTime getReduceTime(long id) {
        return reduceTimes.get(id);
    }

    public ResourceDiggingTiers getTier(long id) {
        return tiers.get(id);
    }

    public ResourceDiggingZoneCount getZone(long id) {
        return zones.get(id);
    }

    public ResourceDiggingSpoonCount getSpoon(long id) {
        return spoons.get(id);
    }

    public ResourceDiggingReward getReward(ZTier.Type tier) {
        return rewards.get(tier);
    }

    public List<ResourceDiggingSpoonCount> getSpoons() {
        return new ArrayList<>(spoons.values());
    }

    public List<ResourceDiggingZoneCount> getZones() {
        return new ArrayList<>(zones.values());
    }

    public List<ResourceDiggingTiers> getTiers() {
        return new ArrayList<>(tiers.values());
    }

    public List<ResourceDiggingReduceTime> getReduceTimes() {
        return new ArrayList<>(reduceTimes.values());
    }

    public boolean verify() {
        if(!checkReduceTime()) {
            return false;
        }

        if(!checkTier()) {
            return false;
        }

        if(!checkZone()) {
            return false;
        }

        if(!checkSpoon()) {
            return false;
        }

        if(!checkReward()) {
            return false;
        }

        return true;
    }

    private boolean checkReduceTime() {
        var errors = new ArrayList<String>();

        for(var reduceTime : reduceTimes.values()) {
            var resItem = ctx.item.getItem(reduceTime.itemId);
            if(Objects.isNull(resItem)) {
                errors.add(String.format("item not found - ({%d})", reduceTime.itemId));
            }

            if(reduceTime.count <= 0) {
                errors.add(String.format("count is invalid - ({%d})", reduceTime.count));
            }

            if(reduceTime.reducePercent <= 0) {
                errors.add(String.format("reducePercent is invalid - ({%f})", reduceTime.reducePercent));
            }
        }

        if(GameData.DIGGING.diggingReduceTimeMaxLevel != reduceTimes.size()) {
            errors.add(String.format("reduceTime max level is invalid - (%d)", reduceTimes.size()));
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkTier() {
        var errors = new ArrayList<String>();

        for(var tier : tiers.values()) {
            var resItem = ctx.item.getItem(tier.itemId);
            if(Objects.isNull(resItem)) {
                errors.add(String.format("item not found - ({%d})", tier.id));
            }

            if(tier.count <= 0) {
                errors.add(String.format("count is invalid - ({%d})", tier.id));
            }

            int maxRatio = 0;
            for(var ratio : tier.tiers) {
                if(ratio.ratio <= 0) {
                    errors.add(String.format("ratio is invalid - ({%d})", tier.id));
                }

                maxRatio += ratio.ratio;
                if(ratio.tier == ZTier.Type.NONE) {
                    errors.add(String.format("ratio tier is invalid"));
                }
            }

            if(maxRatio != tier.maxRatio) {
                errors.add(String.format("maxRatio is invalid - ({%d})", tier.id));
            }
        }


        if(GameData.DIGGING.diggingTierMaxLevel != tiers.size()) {
            errors.add(String.format("tiers max level is invalid - (%d)", tiers.size()));
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkZone() {
        var errors = new ArrayList<String>();

        for(var zone : zones.values()) {
            var resItem = ctx.item.getItem(zone.itemId);
            if(Objects.isNull(resItem)) {
                errors.add(String.format("item not found - ({%d})", zone.id));
            }

            if(zone.count <= 0) {
                errors.add(String.format("count is invalid - ({%d})", zone.id));
            }

            if(zone.addZoneCount <= 0) {
                errors.add(String.format("maxZoneCount is invalid - ({%d})", zone.id));
            }
        }

        if(GameData.DIGGING.diggingZoneMaxLevel != zones.size()) {
            errors.add(String.format("zones max level is invalid - (%d)", zones.size()));
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkSpoon() {
        var errors = new ArrayList<String>();

        for(var spoon : spoons.values()) {
            var resItem = ctx.item.getItem(spoon.itemId);
            if(Objects.isNull(resItem)) {
                errors.add(String.format("item not found - ({%d})", spoon.id));
            }

            if(spoon.count <= 0) {
                errors.add(String.format("count is invalid - ({%d})", spoon.id));
            }

            if(spoon.addSpoonCount <= 0) {
                errors.add(String.format("maxSpoonCount is invalid - ({%d})", spoon.id));
            }
        }

        if(GameData.DIGGING.diggingSpoonMaxLevel != spoons.size()) {
            errors.add(String.format("spoons max level is invalid - (%d)", spoons.size()));
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkReward() {
        var errors = new ArrayList<String>();

        for(var reward : rewards.values()) {
            if(reward.tier == ZTier.Type.NONE) {
                errors.add(String.format("tier is invalid - ({%d})", reward.id));
            }

            int maxRatio = 0;
            for(var rewardItem : reward.rewards) {
                if(rewardItem.count <= 0) {
                    errors.add(String.format("count is invalid - ({%d})", reward.id));
                }

                if(rewardItem.ratio <= 0) {
                    errors.add(String.format("ratio is invalid - ({%d})", reward.id));
                }

                if(!checkReward(ctx, rewardItem.type, rewardItem.rewardId, rewardItem.count)) {
                    errors.add(String.format("digging reward is invalid - diggingId ({%d}), category({%d}), dataId({%d}), count({%d})"
                            , reward.id, rewardItem.type.getNumber(), rewardItem.rewardId, rewardItem.count));
                }

                maxRatio += rewardItem.ratio;
            }

            if(maxRatio != reward.maxRatio) {
                errors.add(String.format("maxRatio is invalid - ({%d})", reward.id));
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
