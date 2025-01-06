package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.models.DMPlayerStoneStatueGem;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class StoneStatueGemRules {
    static final Logger logger = LoggerFactory.getLogger(StoneStatueGemRules.class);
    // 일반 보석 스텟 계산 처리
    public static void computeStats(HashMap<ZStat.Type, Double> stats, List<DMPlayerStoneStatueGem> models) {
        var mainStoneGemModel = models.stream()
                .filter(x -> x.gem_id == ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID())
                .findAny()
                .orElse(null);
        if (Objects.isNull(mainStoneGemModel)) {
            logger.error("mainStoneGemModel is null");
            return;
        }

        for (var model : models) {
            var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGem(model.gem_id);
            if (Objects.isNull(resStoneStatueGem)) {
                continue;
            }

            for (var entry : resStoneStatueGem.statGroup.entrySet()) {
                if (mainStoneGemModel.gem_level < entry.getKey()) {
                    continue;
                }

                var value = entry.getValue();
                if (model.gem_id != mainStoneGemModel.gem_id &&
                        mainStoneGemModel.gem_level > entry.getKey()) {
                    for (var statEntry : value.maxStats.entrySet()) {
                        stats.compute(statEntry.getKey(), (k, v) -> Objects.isNull(v) ? statEntry.getValue() : v + statEntry.getValue());
                    }
                    continue;
                }

                for (var statEntry : value.stats.entrySet()) {
                    stats.compute(statEntry.getKey(), (k, v) -> Objects.isNull(v) ? statEntry.getValue() * (model.gem_level - value.beforeMaxLevel) : v + (statEntry.getValue() * (model.gem_level - value.beforeMaxLevel)));
                }
            }
        }
    }

    // 메인 보석 전용 스텟 계산처리
    public static void computeStats(int gemLevel, HashMap<ZStat.Type, Double> stats) {
        var resMainStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGem();
        if (Objects.isNull(resMainStoneStatueGem)) {
            return;
        }

        for (var entry : resMainStoneStatueGem.statGroup.entrySet()) {
            if (gemLevel < entry.getKey()) {
                break;
            }

            var value = entry.getValue();
            for (var statEntry : value.stats.entrySet()) {
                stats.compute(statEntry.getKey(), (k, v) -> Objects.isNull(v) ? statEntry.getValue() : v + statEntry.getValue());
            }
        }
    }

    public static boolean runUpgrade(long ratioGroupId, int gemLevel) {
        var resGemUpgradeRatio = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGemUpgradeRatio(ratioGroupId);
        if (Objects.isNull(resGemUpgradeRatio)) {
            return false;
        }

        var totalRatio = 100;
        var successRatio = resGemUpgradeRatio.upgradeRatios.getOrDefault(gemLevel, -1);
        if (successRatio < 0) {
            return false;
        }

        var rand = SRandomUtils.nextIntEnd(1, totalRatio);

        return rand <= successRatio;
    }
}
