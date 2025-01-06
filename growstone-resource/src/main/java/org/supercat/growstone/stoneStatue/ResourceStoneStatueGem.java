package org.supercat.growstone.stoneStatue;

import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;

import java.util.HashMap;

public class ResourceStoneStatueGem extends ResourceBase {
    public final ImmutableMap<Long, ResourceStoneStatueGemStatGroup> statGroup;

    public ResourceStoneStatueGem(Element e) {
        super(e);

        var tempStatGroup = new HashMap<Long, ResourceStoneStatueGemStatGroup>();
        var eStatGroups = e.getChildren("StatGroup");
        var beforeMaxLevel = 0;
        for (var eStatGroup : eStatGroups) {
            var resStoneStatueGemStatGroup = new ResourceStoneStatueGemStatGroup(eStatGroup, beforeMaxLevel);
            tempStatGroup.put(resStoneStatueGemStatGroup.id, resStoneStatueGemStatGroup);
            beforeMaxLevel += resStoneStatueGemStatGroup.maxLevel;
        }

        this.statGroup = ImmutableMap.copyOf(tempStatGroup);
    }
}
