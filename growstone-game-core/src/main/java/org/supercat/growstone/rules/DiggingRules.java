package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.TMaterial;
import com.supercat.growstone.network.messages.ZDiggingUpgrade;
import com.supercat.growstone.network.messages.ZTier;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.digging.*;

import java.util.List;
import java.util.Objects;

public class DiggingRules {
    public static ResourceReward getRandomReward(ResourceDiggingReward reward) {
        int rand = SRandomUtils.nextIntEnd(0, reward.maxRatio);
        for(var resReward : reward.rewards) {
            rand -= resReward.ratio;
            if(rand <= 0) {
                return resReward;
            }
        }

        return null;
    }

    public static ZTier.Type getRandomTier(int level) {
        var resTier = ResourceManager.INSTANCE.digging.getTier(level);
        if(Objects.isNull(resTier)) {
            return ZTier.Type.COMMON;
        }

        int rand = SRandomUtils.nextIntEnd(0, resTier.maxRatio);
        for(var tier : resTier.tiers) {
            rand -= tier.ratio;
            if(rand <= 0) {
                return tier.tier;
            }
        }

        return ZTier.Type.COMMON;
    }
    public static float getReduceTimePercent(int level) {
        var resTime = ResourceManager.INSTANCE.digging.getReduceTime(level);
        if(Objects.isNull(resTime)) {
            return 0.0f;
        }

        return resTime.reducePercent;
    }
    public static int getMaxZoneCount(int level) {
        var resZone = ResourceManager.INSTANCE.digging.getZone(level);
        if(Objects.isNull(resZone)) {
            return GameData.DIGGING.diggingDefaultZoneCount;
        }

        return GameData.DIGGING.diggingDefaultZoneCount + resZone.addZoneCount;
    }
    public static boolean isValidIndexes(List<Integer> indexes, int maxCount) {
        return indexes.stream().allMatch(x -> x >= 0 && x < maxCount);
    }
    public static boolean isMaxLevel(ZDiggingUpgrade.Type type, int level) {
        switch (type) {
            case TIME:
                return GameData.DIGGING.diggingReduceTimeMaxLevel == level;
            case TIER:
                return GameData.DIGGING.diggingTierMaxLevel == level;
            case ZONE:
                return GameData.DIGGING.diggingZoneMaxLevel == level;
            case SPOON:
                return GameData.DIGGING.diggingSpoonMaxLevel == level;
            default:
                return false;
        }
    }

    public static TMaterial.Builder computeNeedCost(ZDiggingUpgrade.Type type, int level) {
        var resDigging = ResourceManager.INSTANCE.digging.get(type, level);
        if (Objects.isNull(resDigging)) {
            return null;
        }

        TMaterial.Builder builder = TMaterial.newBuilder();

        if (resDigging instanceof ResourceDiggingReduceTime resTime && type == ZDiggingUpgrade.Type.TIME) {
            return builder.setId(resTime.itemId).setCount(resTime.count);
        } else if (resDigging instanceof ResourceDiggingZoneCount resZone && type == ZDiggingUpgrade.Type.ZONE) {
            return builder.setId(resZone.itemId).setCount(resZone.count);
        } else if (resDigging instanceof ResourceDiggingSpoonCount resSpoon && type == ZDiggingUpgrade.Type.SPOON) {
            return builder.setId(resSpoon.itemId).setCount(resSpoon.count);
        } else if (resDigging instanceof ResourceDiggingTiers resTier && type == ZDiggingUpgrade.Type.TIER) {
            return builder.setId(resTier.itemId).setCount(resTier.count);
        } else {
            return null;
        }
    }
}
