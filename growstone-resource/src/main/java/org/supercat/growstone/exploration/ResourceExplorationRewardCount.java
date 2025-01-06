package org.supercat.growstone.exploration;

import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceExplorationRewardCount {
    final public int level;
    final public int minCount;
    final public int maxCount;

    public ResourceExplorationRewardCount(Element e) {
        this.level = XMLHelper.getAttributeInt(e, "Level", 0);
        this.minCount = XMLHelper.getAttributeInt(e, "MinCount", 0);
        this.maxCount = XMLHelper.getAttributeInt(e, "MaxCount", 0);
    }
}
