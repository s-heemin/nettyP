package org.supercat.growstone.gachas;

import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class ResourceGachaGroup extends ResourceBase {
    public final ImmutableMap<Integer, Long> gachas;
    public ResourceGachaGroup(Element e) {
        super(e);

        var tempGachas = new HashMap<Integer, Long>();
        var temp = e.getChildren("Gacha");
        for(var gacha : temp) {
            var level = XMLHelper.getAttributeInt(gacha, "Level", 0);
            var gachaId = XMLHelper.getAttributeLong(gacha, "ID", 0);
            tempGachas.put(level, gachaId);
        }

        this.gachas = ImmutableMap.copyOf(tempGachas);
    }
}
