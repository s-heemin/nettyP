package org.supercat.growstone.growths;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZGrowthStatTarget;
import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.Collections;
import java.util.HashMap;

public class ResourceGrowthOption {
    public  static HashMap<ZGrowthStatTarget.Type, HashMap<ZStat.Type, ResourceGrowthStats>> ResourceGrowthOption(Element e) {
        var temp = new HashMap<ZGrowthStatTarget.Type, HashMap<ZStat.Type, ResourceGrowthStats>>();
        for (var element_sub : e.getChildren("Stat")) {
            var stat = XMLHelper.getAttributeEnum(element_sub, "Type", ZStat.Type.NONE);
            var defaultValue = XMLHelper.getAttributeFloat(element_sub, "Value", 0);
            var lastIntervalValue = XMLHelper.getAttributeFloat(element_sub, "LastValue", 0);
            var targetType = XMLHelper.getAttributeEnum(element_sub, "Target", ZGrowthStatTarget.Type.NONE);

            // key : stat, value : level, statValue - 스탯의 값을 레벨별로 저장
            var valuesByStat = temp.getOrDefault(targetType, new HashMap<>());

            // key : level, value : statValue - 레벨별 스탯의 값을 저장
            var valuesByLevel = new HashMap<Integer, Float>();
            float lastValue = defaultValue;
            for (var lv : element_sub.getChildren("Level")) {
                var stats = new ResourceGrowthStat(lv, stat, lastValue);
                lastValue = stats.valueByLevel.get(Collections.max(stats.valueByLevel.keySet()));
                valuesByLevel.putAll(stats.valueByLevel);
            }

            // 디폴트 값과 레벨별 스탯의 값을 저장 하고 있는 객체 생성
            var growthStats = new ResourceGrowthStats(defaultValue,lastIntervalValue, valuesByLevel);
            valuesByStat.put(stat, growthStats);
            temp.put(targetType, valuesByStat);
        }

        return temp;
    }
}