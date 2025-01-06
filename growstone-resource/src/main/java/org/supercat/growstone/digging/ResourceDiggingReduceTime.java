package org.supercat.growstone.digging;

import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceDiggingReduceTime extends ResourceBase {
    public final long itemId;
    public final long count;
    public final float reducePercent;
    public ResourceDiggingReduceTime(Element e) {
        super(e);
        this.itemId = XMLHelper.getChildLong(e, "ItemID", 0);
        this.count = XMLHelper.getChildLong(e, "Count", 0);
        var temp = XMLHelper.getChildInt(e, "ReduceTimePer", 0);
        this.reducePercent = temp / 100.0f;
    }
}
