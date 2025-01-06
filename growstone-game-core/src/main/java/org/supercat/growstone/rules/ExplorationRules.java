package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.ZTier;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.exploration.ResourceExplorationLevel;
import org.supercat.growstone.exploration.ResourceExplorationTier;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplorationRules {
    public static ZTier.Type randomTier(ResourceExplorationLevel resLevel) {
        if (Objects.isNull(resLevel)) {
            return ZTier.Type.NONE;
        }

        var probability = SRandomUtils.nextInt(0, resLevel.totalTierProbability);
        for (var entry : resLevel.tierProbability.entrySet()) {
            if (probability < entry.getKey()) {
                return entry.getValue();
            }
        }

        return ZTier.Type.NONE;
    }

    public static long computeStartUntilAt(int elapsedTimeUnit) {
        return Instant.now().toEpochMilli() + elapsedTimeUnit * 60 * 1000;
    }

    public static long computeCommercialUntilAt(long untilAt) {
        return Math.max(untilAt - GameData.EXPLORATION.CommercialTimeUnitMS, Instant.now().toEpochMilli());
    }

    public static long computeAcceleratorUntilAt(long untilAt, long count) {
        return Math.max(untilAt - (GameData.EXPLORATION.AcceleratorTimeUnitMS * count), Instant.now().toEpochMilli());
    }

    public static int randomElapsedTimeUnit(ResourceExplorationTier resTier) {
        if (Objects.isNull(resTier)) {
            return 0;
        }

        return SRandomUtils.nextIntEnd(resTier.minElapsedTime / GameData.EXPLORATION.ElapsedTimeUnit,
                resTier.maxElapsedTime / GameData.EXPLORATION.ElapsedTimeUnit) * GameData.EXPLORATION.ElapsedTimeUnit;
    }

    public static TContentReward randomReward(ResourceExplorationTier resTier, int level) {
        var reward = TContentReward.newBuilder();
        if (Objects.isNull(resTier)) {
            return reward.build();
        }

        var resRewardItem = resTier.rewardItems.get(SRandomUtils.nextInt(resTier.rewardItems.size()));
        if (Objects.isNull(resRewardItem)) {
            return reward.build();
        }

        var resRewardCount = resRewardItem.rewardCounts.get(level);
        if (Objects.isNull(resRewardCount)) {
            return reward.build();
        }

        reward.setCategory(resRewardItem.type);
        reward.setDataId(resRewardItem.id);
        reward.setCount(SRandomUtils.nextLongEnd(resRewardCount.minCount, resRewardCount.maxCount));

        return reward.build();
    }

    public static List<ZTier.Type> randomNeedPetTiers() {
        var results = new ArrayList<ZTier.Type>();

        int randomCount = SRandomUtils.nextIntEnd(1, GameData.EXPLORATION.MaxNeedPetTierCount);
        for (int i = 0; i < randomCount; ++i) {
            results.add(ZTier.Type.forNumber(SRandomUtils.nextIntEnd(ZTier.Type.COMMON_VALUE, ZTier.Type.IMMORTAL_VALUE)));
        }
        return results;
    }

    public static int needAchieve(List<ZTier.Type> needPetTierList, List<Long> petIdList) {
        int achieveCount = 0;
        for (var petId : petIdList) {
            var resPet = ResourceManager.INSTANCE.growth.get(petId);
            for (var needPetTier : needPetTierList) {
                if (resPet.tier == needPetTier) {
                    achieveCount++;
                    break;
                }
            }
        }

        return Math.min(achieveCount, needPetTierList.size());
    }

    public static boolean hasSamePet(List<Long> petIds) {
        var petIdSet = new ArrayList<Long>();
        for (var petId : petIds) {
            if (petIdSet.contains(petId)) {
                return true;
            }
            petIdSet.add(petId);
        }

        return false;
    }

    public static long computeAchiveRewardCount(List<ZTier.Type> needPetTierList, List<Long> petIdList, long count) {
        final int percent = 100;
        final int achieveCount = needAchieve(needPetTierList, petIdList);
        final int increaseRewardPercent = GameData.EXPLORATION.IncreaseRewardPercent * achieveCount;
        return (count * (percent + increaseRewardPercent)) / percent;
    }
}