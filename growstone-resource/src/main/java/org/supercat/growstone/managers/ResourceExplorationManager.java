package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.exploration.ResourceExplorationLevel;
import org.supercat.growstone.exploration.ResourceExplorationTier;

import java.util.ArrayList;
import java.util.HashMap;

public final class ResourceExplorationManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceExplorationManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<ZTier.Type, ResourceExplorationTier> tiers; // ZTier.Type
    private final ImmutableMap<Integer, ResourceExplorationLevel> levels; // Exploration Level


    public static ResourceExplorationManager of(ResourceContext ctx) {
        return new ResourceExplorationManager(ctx);
    }

    private ResourceExplorationManager(ResourceContext ctx) {
        this.ctx = ctx;

        this.tiers = loadTires();
        this.levels = loadLevels();
    }

    public ResourceExplorationTier getTier(ZTier.Type tier) {
        return tiers.get(tier);
    }

    public ResourceExplorationLevel getLevel(int level) {
        return levels.get(level);
    }

    public int getMaxLevel() {
        return levels.keySet().stream().max(Integer::compareTo).orElse(0);
    }

    @Override
    public boolean verify() {
        if (!checkTierProbability()) {
            return false;
        }

        if (!checkTierReward()) {
            return false;
        }

        return true;
    }

    private boolean checkTierProbability() {
        var errors = new ArrayList<String>();
        for (var resLevel : levels.values()) {
            if (resLevel.totalTierProbability != 10000) {
                errors.add("Level " + resLevel.level + " totalTierProbability " +
                        resLevel.totalTierProbability + " is not 10000");
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkTierReward() {
        var errors = new ArrayList<String>();
        for (var resTier : tiers.values()) {
            for (var reward : resTier.rewardItems) {
                if (!checkReward(ctx, reward.type, reward.id, 1)) {
                    errors.add(String.format("tier is invalid - tier ({%d}), category({%d}), dataId({%d})"
                            , resTier.tier.getNumber(), reward.type.getNumber(), reward.id));
                }

                for (var rewardCount : reward.rewardCounts.values()) {
                    if (rewardCount.minCount <= 0 || rewardCount.maxCount <= 0) {
                        errors.add(String.format("tier reward count is invalid - tier ({%d})"
                                , resTier.tier.getNumber()));
                    }

                    if (rewardCount.minCount > rewardCount.maxCount) {
                        errors.add(String.format("tier reward min count over max count is invalid - tier ({%d})"
                                , resTier.tier.getNumber()));
                    }
                }
            }
        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private ImmutableMap<ZTier.Type, ResourceExplorationTier> loadTires() {
        var absolutePaths = ctx.absolutePathBy(ResourceFile.EXPLORATION_TIERS);
        var elements = XMLHelper.loadAll(absolutePaths, "Exploration");
        var tempMap = new HashMap<ZTier.Type, ResourceExplorationTier>();
        for (var element : elements) {
            var resTier = new ResourceExplorationTier(element);
            tempMap.put(resTier.tier, resTier);
        }

        return ImmutableMap.copyOf(tempMap);
    }

    private ImmutableMap<Integer, ResourceExplorationLevel> loadLevels() {
        var absolutePaths = ctx.absolutePathBy(ResourceFile.EXPLORATION_LEVELS);
        var elements = XMLHelper.loadAll(absolutePaths, "Exploration");
        var tempMap = new HashMap<Integer, ResourceExplorationLevel>();
        for (var element : elements) {
            var resLevel = new ResourceExplorationLevel(element);
            tempMap.put(resLevel.level, resLevel);
        }

        return ImmutableMap.copyOf(tempMap);
    }
}
