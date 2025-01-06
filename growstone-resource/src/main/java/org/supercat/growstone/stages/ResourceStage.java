package org.supercat.growstone.stages;

import com.google.common.collect.ImmutableList;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;

public class ResourceStage {
    public final long mapId;
    public final DropTable fieldDrop;
    public final ImmutableList<ResourceReward> clearRewards;
    public final boolean isLastStage;

    public class DropTable {
        public final long id;
        public final float addPercent;

        public DropTable(Element e) {
            this.id = XMLHelper.getAttributeLong(e, "ID", 0);
            float temp = XMLHelper.getAttributeFloat(e, "AddCountPercent", 0.0f);
            this.addPercent = temp > 0.0f ? 1 + (temp / 100) : 0;
        }
    }
    public ResourceStage(Element e) {
        this.mapId = XMLHelper.getChildLong(e, "MapID", 0);
        this.fieldDrop = new DropTable(e.getChild("DropTable"));

        var tempClearRewards = new ArrayList<ResourceReward>();
        for(var reward : e.getChildren("ClearReward")) {
            var clearReward = new ResourceReward(reward.getChild("Reward"));
            tempClearRewards.add(clearReward);
        }
        this.clearRewards = ImmutableList.copyOf(tempClearRewards);

        this.isLastStage = XMLHelper.getChildBoolean(e, "IsLastStage", false);
    }
}
