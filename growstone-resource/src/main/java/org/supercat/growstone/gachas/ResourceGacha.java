package org.supercat.growstone.gachas;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZCategory;
import io.opencensus.resource.Resource;
import jdk.jfr.Category;
import org.jdom2.Element;
import org.json.XML;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.ArrayList;

public class ResourceGacha extends ResourceBase {
    public final int maxRatio;
    public final ImmutableList<ResourceAddRatioItem> addItems;

    public class ResourceAddRatioItem {
        public final ZCategory.Type type;
        public final long dataId;
        public int ratio;
        public ResourceAddRatioItem(Element e) {
            this.type = XMLHelper.getAttributeEnum(e, "Type", ZCategory.Type.NONE);
            this.dataId = XMLHelper.getAttributeLong(e, "ID", 0);
            this.ratio = XMLHelper.getAttributeInt(e, "Ratio", 0);
        }
    }
    public ResourceGacha(Element e) {
        super(e);

        var tempItems = new ArrayList<ResourceAddRatioItem>();
        var tempMaxRatio = 0;
        var temp = e.getChildren("Add");
        for(var addItem : temp) {
            var item = new ResourceAddRatioItem(addItem);
            tempItems.add(item);
            tempMaxRatio += item.ratio;
        }

        this.addItems = ImmutableList.copyOf(tempItems);
        this.maxRatio = tempMaxRatio;
    }
}
