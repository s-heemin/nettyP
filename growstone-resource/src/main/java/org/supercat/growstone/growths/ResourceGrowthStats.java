package org.supercat.growstone.growths;

import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;

public class ResourceGrowthStats {
    public final float defaultValue;
    public final float lastIntervalValue; // valueByLevel에 마지막 레벨보다 레벨이 높다면 그 편차만큼 곱해서 더해준다.
    public final int lastLevel;
    public final ImmutableMap<Integer, Float> valueByLevel;

    public ResourceGrowthStats(float defaultValue, float lastIntervalValue, HashMap<Integer, Float> valueByLevel) {
        this.defaultValue = defaultValue;
        this.lastIntervalValue = lastIntervalValue;
        this.valueByLevel = ImmutableMap.copyOf(valueByLevel);
        this.lastLevel = Collections.max(valueByLevel.keySet());
    }
}
