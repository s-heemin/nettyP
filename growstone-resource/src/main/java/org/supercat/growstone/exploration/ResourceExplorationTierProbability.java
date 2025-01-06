package org.supercat.growstone.exploration;

import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceExplorationTierProbability {
    final public ZTier.Type tier;
    final public int probability;

    public ResourceExplorationTierProbability(Element e) {
        this.tier = XMLHelper.getAttributeEnum(e, "Tier", ZTier.Type.NONE);
        this.probability = XMLHelper.getAttributeInt(e, "Probability", 0);
    }
}
