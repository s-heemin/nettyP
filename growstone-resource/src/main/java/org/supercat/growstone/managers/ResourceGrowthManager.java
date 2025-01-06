package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.containers.GrowthUpgradeKey;
import org.supercat.growstone.growths.ResourceGrowth;
import org.supercat.growstone.growths.ResourceGrowthUpgrade;
import org.supercat.growstone.growths.ResourcePartsSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceGrowthManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceGrowthManager.class);

    public static ResourceGrowthManager of(ResourceContext ctx) {
        return new ResourceGrowthManager(ctx);
    }
    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceGrowth> growth;
    private final ImmutableMap<GrowthUpgradeKey, ResourceGrowthUpgrade> growthUpgrade;
    private ResourceGrowthManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.growth = load(ResourceGrowth::new, ctx.absolutePathBy(ResourceFile.GROWTHS), "Growth");

        var temp = load(ResourceGrowthUpgrade::new, ctx.absolutePathBy(ResourceFile.GROWTH_UPGRADE), "GrowthUpgrade");
        this.growthUpgrade = ImmutableMap.copyOf(temp.entrySet().stream()
                .collect(ImmutableMap.toImmutableMap(x -> new GrowthUpgradeKey(x.getValue().type, x.getValue().tier), x -> x.getValue())));
    }

    public ResourceGrowth get(Long id) {
        return growth.get(id);
    }
    public List<ResourceGrowth> getAll() {
        return new ArrayList<>(growth.values());
    }
    public ResourceGrowthUpgrade get(ZGrowth.Type type, ZTier.Type tier) {
        return growthUpgrade.get(new GrowthUpgradeKey(type, tier));
    }
    public boolean verify() {
        if(!checkStats()) {
            return false;
        }

        if(!checkUpgradeLevel()) {
            return false;
        }

        if(!checkUpgradeMaterial()) {
            return false;
        }

        return true;
    }

    private boolean checkStats() {
        var errors = new ArrayList<String>();

        for(var resGrowth : growth.values()) {
            if(resGrowth.category == ZCategory.Type.NONE) {
                errors.add("category is invalid - id : " + resGrowth.id);
            }

            if(resGrowth.tier == ZTier.Type.NONE) {
                errors.add("tier is invalid - " + resGrowth.id);
            }

            for(var equipOption : resGrowth.equipStats.entrySet()) {
                var targetType = equipOption.getKey();
                var valuesByStat = equipOption.getValue();
                if(targetType == ZGrowthStatTarget.Type.NONE) {
                    errors.add("growth target is invalid - " + resGrowth.id);
                }

                for(var kv : valuesByStat.entrySet()) {
                    var stat = kv.getKey();
                    if(stat == ZStat.Type.NONE) {
                        errors.add("stat is invalid - " + resGrowth.id);
                    }

                    int beforeLevel = 0;
                    var growthStats = kv.getValue();
                    for(var level : growthStats.valueByLevel.keySet()) {
                        if(level - beforeLevel != 1) {
                            errors.add("level is invalid - " + resGrowth.id);
                        }
                        beforeLevel = level;
                    }
                }
            }

            for(var equipOption : resGrowth.ownedStats.entrySet()) {
                var targetType = equipOption.getKey();
                var valuesByStat = equipOption.getValue();
                if(targetType == ZGrowthStatTarget.Type.NONE) {
                    errors.add("growth target is invalid - " + resGrowth.id);
                }

                for(var kv : valuesByStat.entrySet()) {
                    var stat = kv.getKey();
                    if(stat == ZStat.Type.NONE) {
                        errors.add("stat is invalid - " + resGrowth.id);
                    }

                    int beforeLevel = 0;
                    var growthStats = kv.getValue();
                    for(var level : growthStats.valueByLevel.keySet()) {
                        if(level - beforeLevel != 1) {
                            errors.add("level is invalid - " + resGrowth.id);
                        }
                        beforeLevel = level;
                    }
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkUpgradeMaterial() {
        var errors = new ArrayList<String>();
        for(var growthUpgrade : growthUpgrade.values()) {
            if(growthUpgrade.tier == ZTier.Type.NONE) {
                errors.add(String.format("tier is invalid - id : %d", growthUpgrade.id));
            }

            if(growthUpgrade.type == ZGrowth.Type.NONE) {
                errors.add(String.format("type is invalid - id : %d", growthUpgrade.id));
            }

            for(var kv : growthUpgrade.materials.entrySet()) {
              var resMat = kv.getValue();

                if(resMat.type == ZInterval.Type.NONE) {
                    errors.add(String.format("material interval type is invalid - id : %d", growthUpgrade.id));
                }

                if(resMat.count <= 0) {
                    errors.add(String.format("material count is invalid - id : %d", growthUpgrade.id));
                }

                if(resMat.intervalValue <= 0) {
                    errors.add(String.format("material interval value is invalid - id : %d", growthUpgrade.id));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
    private boolean checkUpgradeLevel() {
        var errors = new ArrayList<String>();
        for(var growthUpgrade : growthUpgrade.values()) {
            for(var kv : growthUpgrade.levelUpgrades.entrySet()) {
                var type = kv.getKey();
                var resLevel = kv.getValue();

                if(resLevel.maxUpgradeLevel <= 0) {
                    errors.add(String.format("max upgrade level is invalid - id : %d", growthUpgrade.id));
                }

                if(type == ZGrowthStatTarget.Type.LEVEL) {
                    if(resLevel.intervalValue <= 0) {
                        errors.add(String.format("interval value is invalid - id : %d", growthUpgrade.id));
                    }
                } else {
                    if(resLevel.intervalValue > 0) {
                        errors.add(String.format("interval value is invalid - id : %d", growthUpgrade.id));
                    }
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
