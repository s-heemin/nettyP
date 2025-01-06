package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.TCollectionLevelUp;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZStat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.collections.CollectionCondition;
import org.supercat.growstone.collections.ResourceCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionRules {
    private static final Logger logger = LoggerFactory.getLogger(CollectionRules.class);

    public static int reviewLevelUp(ResourceCollection resCollection, ZResource.Type type, int level, int targetLevel) {
        if(type == ZResource.Type.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        // 리소스 타입이 같아야 한다.
        if(resCollection.type != type) {
            logger.error("invalid collection type:({}), collectionId:({})", type, resCollection.id);
            return StatusCode.INVALID_REQUEST;
        }

        // 타겟 레벨이 최대 레벨을 넘어서면 안된다.
        if(targetLevel > resCollection.maxLevel) {
            return StatusCode.INVALID_REQUEST;
        }

        // 타겟 레벨은 현재 레벨에서 1을 더한 값이어야한다.
        if(level + 1 != targetLevel) {
            return StatusCode.INVALID_REQUEST;
        }

        return StatusCode.SUCCESS;
    }

    public static boolean isValidLevel(CollectionCondition condition, int level, int targetLevel) {
        var needLevel = condition.condition.get(targetLevel);
        if(Objects.isNull(needLevel)) {
            return false;
        }

        return level >= needLevel;
    }

    public static boolean isValidTargetList(List<TCollectionLevelUp> targets) {
        var l = targets.stream()
                .map(x -> x.getDataId())
                .collect(Collectors.toSet());
        return l.size() == targets.size();
    }

    public static void computeStats(long collectionId, int level, HashMap<ZStat.Type, Double> stats) {
        var resCollection = ResourceManager.INSTANCE.collection.get(collectionId);
        if(Objects.isNull(resCollection)) {
            return;
        }

        var type = resCollection.stat;
        var value = resCollection.statValues.getOrDefault(level, 0f);

        stats.compute(type, (k, v) -> Objects.isNull(v) ? value : v + value);
    }
}
