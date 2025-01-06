package org.supercat.growstone;

import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

public class ResourceBase {
    public final long id;
    private final String name;

    public ResourceBase(Element e) {
        long tempId = XMLHelper.getAttributeLong(e, "ID", Long.MIN_VALUE);
        this.id = Integer.MIN_VALUE != tempId ? tempId : XMLHelper.getAttributeLong(e, "ID", Long.MIN_VALUE);
        this.name = XMLHelper.getChildText(e, "Name", "");
    }
}
