package org.supercat.growstone.conditionPacakages;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZCondition;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceConditionPackage extends ResourceBase {
    public final ZCondition.Type type;
    public final boolean isEnable;
    public final long shopItemId;
    public final long openDuration;
    public final List<ResourceReward> rewards;
    public final completeCondition completeCondition;
    public class completeCondition {
        public final long param1;
        public final long param2;

        public completeCondition(Element e) {
            this.param1 = XMLHelper.getAttributeLong(e, "Param1", 0);
            this.param2 = XMLHelper.getAttributeLong(e, "Param2", 0);
        }
    }

    public ResourceConditionPackage(Element e) {
        super(e);

        this.type = XMLHelper.getChildEnum(e, "Type", ZCondition.Type.NONE);
        this.isEnable = XMLHelper.getChildBoolean(e, "Enable", true);
        this.shopItemId = XMLHelper.getChildLong(e, "ShopItemId", 0);
        this.openDuration = XMLHelper.getChildLong(e, "OpenTime", 0);

        var tempComplete = e.getChild("Complete");
        if(Objects.nonNull(tempComplete)) {
            this.completeCondition = new completeCondition(tempComplete);
        } else {
            this.completeCondition = null;
        }

        var tempRewards = new ArrayList<ResourceReward>();
        var tempElements = e.getChildren("OpenReward");
        for(var ele : tempElements) {
            tempRewards.add(new ResourceReward(ele));
        }

        rewards = ImmutableList.copyOf(tempRewards);
    }
}
