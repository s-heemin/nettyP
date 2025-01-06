package org.supercat.growstone.rules;

import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.StatusCode;

import java.util.List;
import java.util.Objects;

public final class FarmRules {
    public static long computePlant() {
        double random = SRandomUtils.random() * GameData.FARM.NORMAL_SEED_TOTAL_WEIGHT;
        long plantId = 0;
        for (var entry : GameData.FARM.NORMAL_SEEDS_PLANT_TABLE.entrySet()) {
            random -= entry.getValue();
            if (random > 0.0d) {
                continue;
            }
            plantId = entry.getKey();
            break;
        }

        return plantId;
    }

    public static int reviewSlotIndexes(List<Integer> slotIndexes) {
        for (var slotIndex : slotIndexes) {
            if (slotIndex < 0 || slotIndex >= GameData.FARM.MAX_PLANT_FIELD_COUNT) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        return StatusCode.SUCCESS;
    }

    public static int computeFarmLevel(long exp) {
        var resFarm = ResourceManager.INSTANCE.farm.getFarmByExp(exp);
        if (Objects.isNull(resFarm)) {
            return 1;
        }

        return (int) resFarm.id;
    }

    public static String convertToHistortyData(Object... arguments) {
        return JsonConverter.to(arguments);
    }
}
