package org.supercat.growstone.stages;

import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;
import java.util.List;

public class ResourceStageGroup extends ResourceBase {
    public final ImmutableMap<Integer, ResourceStage> stages;
    public final long nextStageGroupId;
    public ResourceStageGroup(Element e) {
        super(e);

        var tempStages = new HashMap<Integer, ResourceStage>();
        for(var ele : e.getChildren("Stage")) {
            int id = XMLHelper.getAttributeInt(ele, "ID", 0);
            var stage = new ResourceStage(ele);

            tempStages.put(id, stage);
        }

        stages = ImmutableMap.copyOf(tempStages);
        nextStageGroupId = XMLHelper.getChildLong(e, "NextStageGroup", 0);
    }
}
