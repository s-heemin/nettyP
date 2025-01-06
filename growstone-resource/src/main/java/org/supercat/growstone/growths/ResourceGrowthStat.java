package org.supercat.growstone.growths;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class ResourceGrowthStat {
    public final ImmutableMap<Integer, Float> valueByLevel;

    public ResourceGrowthStat(Element e, ZStat.Type type, float lastValue) {
        var minLevel = XMLHelper.getAttributeInt(e, "MinLevel", 1);
        var maxLevel = XMLHelper.getAttributeInt(e, "MaxLevel", 1);
        var interval = XMLHelper.getAttributeFloat(e, "Increment", 0);
        var temp = new HashMap<Integer, Float>();
        var addedValue = lastValue;
        for (int i = minLevel; i <= maxLevel; i++) {
            addedValue += interval ;
            temp.put(i, addedValue);
        }

        valueByLevel = ImmutableSortedMap.copyOf(temp);
    }
}
