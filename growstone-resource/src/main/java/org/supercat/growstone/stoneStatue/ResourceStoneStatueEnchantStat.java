package org.supercat.growstone.stoneStatue;

import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceStoneStatueEnchantStat {
    public final ZStat.Type type;
    public final int min;
    public final int max;
    public final int increment;

    public ResourceStoneStatueEnchantStat(Element e) {
        this.type = XMLHelper.getAttributeEnum(e, "Type", ZStat.Type.NONE);
        this.min = XMLHelper.getAttributeInt(e, "Min", Integer.MAX_VALUE);
        this.max = XMLHelper.getAttributeInt(e, "Max", Integer.MIN_VALUE);
        this.increment = XMLHelper.getAttributeInt(e, "Increment", 0);
    }
}
