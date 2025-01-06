package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZCategory;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceReward {
    public final ZCategory.Type type;
    public final long rewardId;
    public final long count;
    public final int ratio;
    public ResourceReward(ZCategory.Type type, long rewardId, long count) {
        this.type = type;
        this.rewardId = rewardId;
        this.count = count;
        this.ratio = 0;
    }
    public ResourceReward(Element e) {
        this.type = XMLHelper.getAttributeEnum(e, "Type", ZCategory.Type.NONE);
        this.rewardId = XMLHelper.getAttributeLong(e, "ID", 0);
        this.count = XMLHelper.getAttributeLong(e, "Count", 0);
        this.ratio = XMLHelper.getAttributeInt(e, "Ratio", 0);
    }
}
