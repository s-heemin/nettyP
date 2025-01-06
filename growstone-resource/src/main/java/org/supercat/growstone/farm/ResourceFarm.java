package org.supercat.growstone.farm;

import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourceFarm extends ResourceBase {
    public final long requiredExp;
    public final int fieldCount;
    public final float increaseHarvest;
    public final float reduceHarvestTime;

    public ResourceFarm(Element e) {
        super(e);
        this.requiredExp = XMLHelper.getChildLong(e, "RequiredExp", 0);
        this.fieldCount = XMLHelper.getChildInt(e, "FieldCount", 0);
        this.increaseHarvest = XMLHelper.getChildFloat(e, "IncreaseHarvest", 0f);
        this.reduceHarvestTime = XMLHelper.getChildFloat(e, "ReduceHarvestTime", 0f);
    }
}
