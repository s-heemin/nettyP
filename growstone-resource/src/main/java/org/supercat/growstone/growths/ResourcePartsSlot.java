package org.supercat.growstone.growths;

import com.supercat.growstone.network.messages.ZPartsSlot;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

public class ResourcePartsSlot extends ResourceBase {
    private static final Logger logger = LoggerFactory.getLogger(ResourcePartsSlot.class);

    public final ZPartsSlot.Type type;
    public final float addPercent;
    public final ResourceGrowthMaterial material;
    public ResourcePartsSlot(Element e) {
        super(e);
        this.type = XMLHelper.getChildEnum(e, "Type", ZPartsSlot.Type.NONE);
        this.addPercent = XMLHelper.getChildFloat(e, "AddPercent", 1);
        this.material = new ResourceGrowthMaterial(e.getChild("Material"));
    }
}
