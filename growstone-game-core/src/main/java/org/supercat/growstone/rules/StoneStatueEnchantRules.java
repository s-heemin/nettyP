package org.supercat.growstone.rules;

import com.google.common.base.Strings;
import com.supercat.growstone.network.messages.ZStat;
import com.supercat.growstone.network.messages.ZTier;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.jsons.JMStoneStatueEnchantSlot;
import org.supercat.growstone.models.DMPlayerStoneStatueEnchant;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueEnchantRatio;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueEnchantRatioGroup;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueEnchantStat;

import java.util.*;

public final class StoneStatueEnchantRules {
    public static List<JMStoneStatueEnchantSlot> tryEnchant(int level, DMPlayerStoneStatueEnchant preset) {
        var resStoneStatueRatioGroup = ResourceManager.INSTANCE.stoneStatueEnchant.getRatioGroup(level);
        if (Objects.isNull(resStoneStatueRatioGroup)) {
            return null;
        }

        var enchantSlotList = JsonConverter.of(preset.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(enchantSlotList)) {
            return null;
        }

        var resultList = new ArrayList<JMStoneStatueEnchantSlot>();
        for (var slot : enchantSlotList) {
            if (slot.isLocked) {
                resultList.add(slot);
                continue;
            }

            var resStoneStatueEnchantRatio = runEnchantTier(resStoneStatueRatioGroup);
            if (Objects.isNull(resStoneStatueEnchantRatio)) {
                return null;
            }

            var newTier = resStoneStatueEnchantRatio.tier;
            var resStoneStatueEnchantStat = runEnchantStat(newTier);
            if (Objects.isNull(resStoneStatueEnchantStat)) {
                return null;
            }

            var min = Math.max(resStoneStatueEnchantStat.min, resStoneStatueEnchantStat.min + (level * resStoneStatueEnchantStat.increment));
            var max = Math.max(resStoneStatueEnchantStat.max, resStoneStatueEnchantStat.max + (level * resStoneStatueEnchantStat.increment));
            var randStatValue = SRandomUtils.nextInt(min, max);
            var newStat = JMStoneStatueEnchantSlot.of(slot.slotId, slot.isLocked, newTier, resStoneStatueEnchantStat.type, randStatValue);
            resultList.add(newStat);
        }

        return resultList;
    }

    public static ResourceStoneStatueEnchantRatio runEnchantTier(ResourceStoneStatueEnchantRatioGroup resEnchantRatioGroup) {
        var totalRatio = resEnchantRatioGroup.enchantRatios.values().stream().mapToInt(x -> x.ratio).sum();
        var randTierRatio = SRandomUtils.nextIntEnd(1, totalRatio);
        for (var value : resEnchantRatioGroup.enchantRatios.values()) {
            randTierRatio -= value.ratio;
            if (randTierRatio <= 0) {
                return value;
            }
        }

        return null;
    }

    public static ResourceStoneStatueEnchantStat runEnchantStat(ZTier.Type tier) {
        var resStoneStatueEnchant = ResourceManager.INSTANCE.stoneStatueEnchant.getStoneStatueEnchantByTier(tier);
        if (Objects.isNull(resStoneStatueEnchant)) {
            return null;
        }

        var totalSize = resStoneStatueEnchant.enchants.size();
        if (totalSize <= 0) {
            return null;
        }

        var randIndex = SRandomUtils.nextInt(0, totalSize);
        var index = 0;
        for (var value : resStoneStatueEnchant.enchants.values()) {
            if (index == randIndex) {
                return value;
            }
            index++;
        }

        return null;
    }

    public static String addEnchantSlot(int level, String data) {
        if (Strings.isNullOrEmpty(data)) {
            return data;
        }

        var slotId = GameData.STONE_STATUE.ENCHANT_SLOT_OPEN_LEVEL.getOrDefault(level, -1);
        if (slotId <= 0) {
            return data;
        }

        var slotList = JsonConverter.of(data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(slotList)) {
            return data;
        }

        // 이미 오픈되어있는 슬롯을 또 오픈할 경우
        var existCount = Arrays.stream(slotList)
                .filter(x -> x.slotId == slotId)
                .count();
        if (existCount > 0) {
            return data;
        }

        var newSlot = JMStoneStatueEnchantSlot.of(slotId, false, ZTier.Type.NONE, ZStat.Type.NONE, 0);
        var resultList = new ArrayList<>(List.of(slotList));
        resultList.add(newSlot);

        return JsonConverter.to(resultList);
    }

    public static void computeStats(HashMap<ZStat.Type, Double> stats, DMPlayerStoneStatueEnchant model) {
        var slotList = JsonConverter.of(model.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(slotList)) {
            return;
        }

        for (var slot : slotList) {
            if (slot.statId == ZStat.Type.NONE) {
                continue;
            }

            stats.compute(slot.statId, (k, v) -> Objects.isNull(v) ? slot.statValue : v + slot.statValue);
        }
    }
}
