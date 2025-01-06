package org.supercat.growstone.stoneStatue;

import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceStoneStatueEnchantRatio {
    public final ZTier.Type tier;
    public final int ratio;

    public ResourceStoneStatueEnchantRatio(Element e) {
        this.tier = XMLHelper.getAttributeEnum(e, "Tier", ZTier.Type.NONE);
        this.ratio = XMLHelper.getAttributeInt(e, "Ratio", 0);
    }
}
