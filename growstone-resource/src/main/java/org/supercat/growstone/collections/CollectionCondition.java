package org.supercat.growstone.collections;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CollectionCondition {
    public final long growthId;
    public final ImmutableMap<Integer, Integer> condition;

    public CollectionCondition(Element e, int maxLevel) {
        this.growthId = XMLHelper.getAttributeLong(e, "GrowthID", 0);
        var values = Arrays.stream(StringUtils.split(XMLHelper.getAttributeText(e, "LevelList", StringUtils.EMPTY), ","))
                .filter(x -> !Strings.isNullOrEmpty(x))
                .map(x -> Integer.parseInt(x.trim()))
                .collect(Collectors.toList());
        Collections.sort(values);
        var tempValues = new HashMap<Integer, Integer>();
        for (int i = 1; i <= maxLevel; i++) {
            tempValues.put(i, values.get(i - 1));
        }

        condition = ImmutableMap.copyOf(tempValues);
    }
}
