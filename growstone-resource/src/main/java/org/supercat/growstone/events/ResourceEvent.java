package org.supercat.growstone.events;

import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.types.ResetType;

public class ResourceEvent extends ResourceBase {
    public final ResetType resetType;
    public final ZEvent.Type type;
    public ResourceEvent(Element e) {
        super(e);
        this.resetType = XMLHelper.getChildEnum(e, "ResetType", ResetType.NONE);
        this.type = XMLHelper.getChildEnum(e, "Type", ZEvent.Type.NONE);
    }
}
