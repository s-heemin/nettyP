package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZCondition;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMPlayerStatGrowth;

import java.util.HashMap;
import java.util.Objects;

public class StatGrowthRules {
    private static final Logger logger = LoggerFactory.getLogger(StatGrowthRules.class);

    public static int reviewStatGrowth(int page, HashMap<Integer, HashMap<ZStat.Type, DMPlayerStatGrowth>> models) {
        if (page <= 1) {
            return StatusCode.SUCCESS;
        }

        // 페이지가 2이상일 경우에는 이전 페이지들이 모두 만렙이 되어야 한다.
        for (int i = page - 1; i > 0; i--) {
            var resStatGrowth = ResourceManager.INSTANCE.statGrowth.get(i);
            if (Objects.isNull(resStatGrowth)) {
                return StatusCode.INVALID_RESOURCE;
            }

            var stats = models.get(i);
            if (Objects.isNull(stats)) {
                return StatusCode.INVALID_REQUEST;
            }

            for (var statInfo : resStatGrowth.stats.entrySet()) {
                var stat = statInfo.getKey();
                var model = stats.get(stat);
                if (Objects.isNull(model) || model.level != resStatGrowth.maxLevel) {
                    return StatusCode.INVALID_REQUEST;
                }
            }
        }


        return StatusCode.SUCCESS;
    }

    public static void computeStatGrowthStats(HashMap<ZStat.Type, Double> stats, HashMap<ZStat.Type, DMPlayerStatGrowth> models) {
        for (var kv : models.entrySet()) {
            var stat = kv.getKey();
            var model = kv.getValue();

            for (int i = 1; i <= model.page; i++) {
                var resStatGrowth = ResourceManager.INSTANCE.statGrowth.get(i);
                if (Objects.isNull(resStatGrowth)) {
                    logger.error("invalid stat growth page:({})", i);
                    continue;
                }

                var value = resStatGrowth.stats.get(stat);
                if (Objects.isNull(value)) {
                    logger.error("invalid stat growth type:({})", stat.getNumber());
                    continue;
                }

                int level = model.level;
                if (model.page != i) {
                    level = resStatGrowth.maxLevel;
                }

                if (level <= 0) {
                    continue;
                }

                var addValue = value * level;
                stats.compute(stat, (k, v) -> Objects.isNull(v) ? addValue : v + addValue);
            }
        }
    }

    public static ZCondition.Type getClearType(ZStat.Type type) {
        if (type == ZStat.Type.BASE_ATTACK) {
            return ZCondition.Type.STATGROWTH_BASE_ATTACK;
        } else if (type == ZStat.Type.BASE_DEFENSE) {
            return ZCondition.Type.STATGROWTH_BASE_DEFENSE;
        } else if (type == ZStat.Type.BASE_HP) {
            return ZCondition.Type.STATGROWTH_BASE_HP;
        }
        return ZCondition.Type.NONE;
    }
}
