package org.supercat.growstone.gachas;

import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class ResourceGachaLevelGroup extends ResourceBase {
    public final ImmutableMap<Integer, Long> levelByExp;
    public ResourceGachaLevelGroup(Element e) {
        super(e);

        var tempLevelByExp = new HashMap<Integer, Long>();
        var temp = e.getChildren("Level");
        for(var ele : temp) {
            var level = XMLHelper.getAttributeInt(ele, "Key", 0);
            var exp = XMLHelper.getAttributeLong(ele, "Exp", 0);
            tempLevelByExp.put(level, exp);
        }

        this.levelByExp = ImmutableMap.copyOf(tempLevelByExp);
    }
}
