package org.supercat.growstone.digging;

import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceDiggingZoneCount extends ResourceBase {
    public final long itemId;
    public final long count;
    public final int addZoneCount;
    public ResourceDiggingZoneCount(Element e) {
        super(e);
        this.itemId = XMLHelper.getChildLong(e, "ItemID", 0);
        this.count = XMLHelper.getChildLong(e, "Count", 0);
        this.addZoneCount = XMLHelper.getChildInt(e, "AddZoneCount", 0);
    }
}
