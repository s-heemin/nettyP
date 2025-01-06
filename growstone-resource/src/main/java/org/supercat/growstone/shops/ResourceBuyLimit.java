package org.supercat.growstone.shops;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZBuyReset;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.time.Instant;
import java.util.ArrayList;

public class ResourceBuyLimit {
    public final ZBuyReset.Type resetType;
    public final long maxBuyCount;
    public ResourceBuyLimit(Element e) {
        this.resetType = XMLHelper.getAttributeEnum(e, "ResetType", ZBuyReset.Type.NONE);
        this.maxBuyCount = XMLHelper.getAttributeLong(e, "MaxCount", 0);
    }
}
