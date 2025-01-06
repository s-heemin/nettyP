package org.supercat.growstone.rules;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.containers.GrowthUpgradeKey;
import org.supercat.growstone.containers.LevelUpResult;
import org.supercat.growstone.growths.ResourceGrowth;
import org.supercat.growstone.growths.ResourceGrowthMaterial;
import org.supercat.growstone.growths.ResourceGrowthStats;
import org.supercat.growstone.jsons.JMPlayerPartsSlot;
import org.supercat.growstone.models.DMPlayerGrowth;

import java.util.*;
import java.util.stream.Collectors;

public class GrowthRules {

    public static List<TMaterial> aggregateMaterials(List<TMaterial> materials) {
        return materials.stream().collect(
                Collectors.groupingBy(TMaterial::getId, Collectors.summingLong(TMaterial::getCount)))
                .entrySet().stream()
                .map(x -> TMaterial.newBuilder()
                .setId(x.getKey())
                .setCount(x.getValue()).build())
                .collect(Collectors.toList());

    }

    public static void calculateStats(ImmutableMap<ZGrowthStatTarget.Type, HashMap<ZStat.Type, ResourceGrowthStats>> resGrowthStats,
                                                             ZGrowthStatTarget.Type targetType, int targetLevel, float addPartsSlotPercent, HashMap<ZStat.Type, Double> outStats) {

        var statsByTarget = resGrowthStats.get(targetType);
        if(Objects.isNull(statsByTarget)) {
            return;
        }

        for(var kv : statsByTarget.entrySet()) {
            var statType = kv.getKey();
            var valuesByLevel = kv.getValue();

            if(targetLevel <= 0) {
                //레벨이 0인 경우는 디폴트 값을 넣어준다.
                computeStats(outStats, statType, valuesByLevel.defaultValue * addPartsSlotPercent);
                continue;
            }

            var value = valuesByLevel.valueByLevel.get(targetLevel);
            if(Objects.isNull(value)) {
                // 레벨이 0도 아니며, 해당 레벨의 스탯값이 없는 경우에는 max치를 넘은걸로 판단하여 최대 max레벨과 targetLevel의 차이에 interval을 곱해서 더해준다
                int lastLevel = valuesByLevel.lastLevel;
                var lastValue = valuesByLevel.valueByLevel.get(lastLevel);
                if(Objects.isNull(lastValue)) {
                    continue;
                }

                float addValue = (lastValue + valuesByLevel.lastIntervalValue * (targetLevel - lastLevel)) * addPartsSlotPercent;
                computeStats(outStats, statType, addValue);
                continue;
            }

            computeStats(outStats, statType, value);
        }
    }

    public static void computeStats(HashMap<ZStat.Type, Double> stats, HashMap<ZStat.Type, Double> addStats) {
        addStats.forEach((k, v) -> computeStats(stats, k, v));
    }
    private static void computeStats(HashMap<ZStat.Type, Double> stats, ZStat.Type type, double value) {
        stats.compute(type, (k, v) -> Objects.isNull(v) ? value :  v + value);
    }

    public static int reviewLevelUp(ZTier.Type tierType, ZGrowth.Type type,
                                    int currentLevel, int limitBreakLevel, TMaterial material) {
        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(type, tierType);
        if(Objects.isNull(resGrowthUpgrade)) {
            return StatusCode.INVALID_REQUEST;
        }

        var resGrowthUpgradeLevel = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.LEVEL);
        if(Objects.isNull(resGrowthUpgradeLevel)) {
            return StatusCode.INVALID_RESOURCE;
        }

        // 돌파 레벨값으로 인한 최대 레벨값이 변경되므로 비교를 해야한다.
        int defaultLevel = resGrowthUpgradeLevel.maxUpgradeLevel;
        int maxLevel = defaultLevel + resGrowthUpgradeLevel.intervalValue * limitBreakLevel;
        if(currentLevel >= maxLevel) {
            return StatusCode.ALREADY_MAX_LEVEL;
        }

        // 레벨당 필요한 재료의 수가 다르기 떄문에 계산이 필요하다
        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.LEVEL);
        if(Objects.isNull(resMaterial)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resMaterial.itemId != material.getId()) {
            return StatusCode.INVALID_GROWTH_MATERIAL_ID;
        }

        int needCount = computeMaterialCountByType(currentLevel, resMaterial);
        if(needCount != material.getCount()) {
            return StatusCode.INVALID_GROWTH_COUNT;
        }

        return StatusCode.SUCCESS;
    }

    public static int reviewPromote(ZTier.Type tierType, ZGrowth.Type type, int promoteLevel, TMaterial material) {
        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(type, tierType);
        if(Objects.isNull(resGrowthUpgrade)) {
            return StatusCode.INVALID_REQUEST;
        }

        var resGrowthUpgradePromote = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.PROMOTE);
        if(Objects.isNull(resGrowthUpgradePromote)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(promoteLevel >= resGrowthUpgradePromote.maxUpgradeLevel) {
            return StatusCode.ALREADY_MAX_LEVEL;
        }

        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.PROMOTE);
        if(Objects.isNull(resMaterial)) {
            return StatusCode.INVALID_RESOURCE;
        }

        int needCount = computeMaterialCountByType(promoteLevel, resMaterial);
        if(needCount != material.getCount()) {
            return StatusCode.INVALID_GROWTH_COUNT;
        }

        return StatusCode.SUCCESS;
    }

    public static int reviewLimitBreak(ZTier.Type tierType, ZGrowth.Type type, int limitBreakLevel, int promoteLevel, TMaterial material) {
        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(type, tierType);
        if(Objects.isNull(resGrowthUpgrade)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var resGrowthUpgradeLimitBreak = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.LIMIT_BREAK);
        if(Objects.isNull(resGrowthUpgradeLimitBreak)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(limitBreakLevel >= resGrowthUpgradeLimitBreak.maxUpgradeLevel) {
            return StatusCode.ALREADY_MAX_LEVEL;
        }

        var resGrowthUpgradePromote = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.PROMOTE);
        if(Objects.isNull(resGrowthUpgradePromote)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(promoteLevel != resGrowthUpgradePromote.maxUpgradeLevel) {
            return StatusCode.NOT_ENOUGH_PROMOTE_LEVEL;
        }

        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.LIMIT_BREAK);
        if(Objects.isNull(resMaterial)) {
            return StatusCode.INVALID_RESOURCE;
        }

        int needCount = computeMaterialCountByType(limitBreakLevel, resMaterial);
        if(needCount != material.getCount()) {
            return StatusCode.INVALID_GROWTH_COUNT;
        }

        return StatusCode.SUCCESS;
    }

    public static int computeMaterialCountByType(int level, ResourceGrowthMaterial resMaterial) {
        float baseCount = resMaterial.count;
        var type = resMaterial.type;
        if(type == ZInterval.Type.PERCENT) {
            for(int i = 0; i < level; i++) {
                baseCount *= (1 + resMaterial.intervalValue);
            }
        } else if(type == ZInterval.Type.ADD) {
            baseCount += (resMaterial.intervalValue * level);
        }

        return (int) baseCount;
    }

    public static int computePartsSlotLevelMaterialCounts(int target_level, ResourceGrowthMaterial resMaterial) {
        return computeMaterialCountByType(target_level, resMaterial);
    }
}
