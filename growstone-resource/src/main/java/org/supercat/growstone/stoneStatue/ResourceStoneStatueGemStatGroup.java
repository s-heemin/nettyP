package org.supercat.growstone.stoneStatue;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.Map;

public class ResourceStoneStatueGemStatGroup {
    public final long id;
    public final int maxLevel;
    public final int beforeMaxLevel;
    public final int price;
    public final long statRatioGroupId;
    public final ImmutableMap<ZStat.Type, Integer> stats;
    public final ImmutableMap<ZStat.Type, Integer> maxStats;

    public ResourceStoneStatueGemStatGroup(Element e, int beforeMaxLevel) {
        this.id = XMLHelper.getAttributeLong(e, "ID", 0);
        this.maxLevel = XMLHelper.getAttributeInt(e, "MaxLevel", 0);
        this.price = XMLHelper.getAttributeInt(e, "Price", 0);
        this.statRatioGroupId = XMLHelper.getChildLong(e, "StatRatioGroupID", 0);
        this.beforeMaxLevel = beforeMaxLevel;

        this.stats = ImmutableMap.copyOf(
                e.getChildren("Stat")
                        .stream()
                        .collect(ImmutableMap.toImmutableMap(
                                v -> XMLHelper.getAttributeEnum(v, "Key", ZStat.Type.NONE),
                                v -> XMLHelper.getAttributeInt(v, "Increment", 0))));

        this.maxStats = ImmutableMap.copyOf(
                stats.entrySet()
                        .stream()
                        .collect(ImmutableMap.toImmutableMap(
                                Map.Entry::getKey,
                                v -> v.getValue() * maxLevel)
                        ));
    }
}
