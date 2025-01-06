package org.supercat.growstone.shops;

import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZPayment;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourcePayment {
    public final ZPayment.Type type;
    public final long dataId;
    public final long count;
    public final int order;
    public final String productId;
    public ResourcePayment(Element e) {
        this.type = XMLHelper.getAttributeEnum(e, "Type", ZPayment.Type.NONE);
        this.order = XMLHelper.getAttributeInt(e, "Order", 0);
        this.dataId = XMLHelper.getAttributeLong(e, "ID", 0);
        this.count = XMLHelper.getAttributeLong(e, "Count", 0);
        this.productId = XMLHelper.getAttributeText(e, "ProductID", "");
    }
}
