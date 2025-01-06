package org.supercat.growstone.growths;

import com.supercat.growstone.network.messages.ZInterval;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceGrowthMaterial {
    public long itemId;
    public int count;
    public ZInterval.Type type;
    public float intervalValue;

    public ResourceGrowthMaterial(Element e) {
        this.itemId = XMLHelper.getAttributeLong(e, "ItemId", 0);
        this.count = XMLHelper.getAttributeInt(e, "Count", 0);
        this.type = XMLHelper.getAttributeEnum(e, "Type", ZInterval.Type.NONE);
        final int div =  type ==  ZInterval.Type.ADD ? 1 : 100;
        this.intervalValue = XMLHelper.getAttributeFloat(e, "Value", 0) / div;
    }

}
