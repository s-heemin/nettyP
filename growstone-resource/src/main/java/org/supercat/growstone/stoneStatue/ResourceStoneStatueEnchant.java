package org.supercat.growstone.stoneStatue;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZStat;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceStoneStatueEnchant extends ResourceBase {
    public final ZTier.Type tier;
    public final ImmutableMap<ZStat.Type, ResourceStoneStatueEnchantStat> enchants;

    public ResourceStoneStatueEnchant(Element e) {
        super(e);

        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);
        this.enchants = ImmutableMap.copyOf(
                e.getChildren("EnchantStat")
                        .stream()
                        .map(ResourceStoneStatueEnchantStat::new)
                        .collect(ImmutableMap.toImmutableMap(v -> v.type, v -> v)));
    }
}
