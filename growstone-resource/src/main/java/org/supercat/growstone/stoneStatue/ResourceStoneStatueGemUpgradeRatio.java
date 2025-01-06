package org.supercat.growstone.stoneStatue;

import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceStoneStatueGemUpgradeRatio extends ResourceBase {
    public final ImmutableMap<Integer, Integer> upgradeRatios;

    public ResourceStoneStatueGemUpgradeRatio(Element e) {
        super(e);

        this.upgradeRatios = ImmutableMap.copyOf(
                e.getChildren("StatRatio")
                        .stream()
                        .collect(ImmutableMap.toImmutableMap(
                                v -> XMLHelper.getAttributeInt(v, "Level", 0),
                                v -> XMLHelper.getAttributeInt(v, "Ratio", 0))));
    }
}
