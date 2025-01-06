package org.supercat.growstone.farm;

import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceFarmPlant extends ResourceBase {
    public final int growTime;
    public final int resultCount;
    public final long resultExp;
    public final int stealCount;
    public final int stealTime;

    public ResourceFarmPlant(Element e) {
        super(e);
        this.growTime = XMLHelper.getChildInt(e, "GrowTime", 0);
        this.resultCount = XMLHelper.getChildInt(e, "ResultCount", 0);
        this.resultExp = XMLHelper.getChildLong(e, "ResultExp", 0L);
        this.stealCount = XMLHelper.getChildInt(e, "StealCount", 0);
        this.stealTime = XMLHelper.getChildInt(e, "StealTime", 0);
    }
}
