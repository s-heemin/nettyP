package org.supercat.growstone.stoneStatue;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;

public class ResourceStoneStatueEnchantRatioGroup extends ResourceBase {
    public final ImmutableMap<ZTier.Type, ResourceStoneStatueEnchantRatio> enchantRatios;

    public ResourceStoneStatueEnchantRatioGroup(Element e) {
        super(e);

        this.enchantRatios = ImmutableMap.copyOf(
                e.getChildren("StoneStatueEnchantRatio")
                        .stream()
                        .map(ResourceStoneStatueEnchantRatio::new)
                        .collect(ImmutableMap.toImmutableMap(v -> v.tier, v -> v)));
    }

}
