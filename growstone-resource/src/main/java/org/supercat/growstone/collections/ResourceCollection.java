package org.supercat.growstone.collections;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZStat;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.json.XML;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceCollection extends ResourceBase {
    public final ZResource.Type type;
    public final int maxLevel;
    public final ZStat.Type stat;
    public final ImmutableMap<Integer, Float> statValues;
    public final ImmutableMap<Long, CollectionCondition> conditions;
    public ResourceCollection(Element e) {
        super(e);
        this.type = XMLHelper.getChildEnum(e, "Type", ZResource.Type.NONE);
        this.maxLevel = XMLHelper.getChildInt(e, "MaxLevel", 0);

        var temp = e.getChild("Stat");
        this.stat = XMLHelper.getAttributeEnum(temp, "Key", ZStat.Type.NONE);
        var values = Arrays.stream(StringUtils.split(XMLHelper.getChildText(e, "Stat", StringUtils.EMPTY), ","))
                .filter(x -> !Strings.isNullOrEmpty(x))
                .map(x -> Float.parseFloat(x.trim())).sorted().collect(Collectors.toList());
        var tempValues = new HashMap<Integer, Float>();
        for(int i = 1 ; i <= maxLevel; i++) {
            tempValues.put(i, values.get(i - 1));
        }

        this.statValues = ImmutableMap.copyOf(tempValues);

        var tc = new HashMap<Long, CollectionCondition>();
        var tempConditions = e.getChildren("Condition");
        for(var tempCondition : tempConditions) {
            var condition = new CollectionCondition(tempCondition, maxLevel);
            tc.put(condition.growthId, condition);
        }

        this.conditions = ImmutableMap.copyOf(tc);
    }
}
