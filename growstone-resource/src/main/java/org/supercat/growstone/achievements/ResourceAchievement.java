package org.supercat.growstone.achievements;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZCondition;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.types.ResetType;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceAchievement extends ResourceBase {
    public final ResetType resetType;
    public final ZCondition.Type type;
    public final boolean visible;
    public final ImmutableList<ResourceReward> rewards;
    public final Condition condition;
    public class Condition {
        public final long param1;
        public final long param2;
        public final ZCategory.Type type;
        public Condition(Element e) {
            this.param1 = XMLHelper.getAttributeLong(e, "Param1", 0);
            this.param2 = XMLHelper.getAttributeLong(e, "Param2", 0);
            this.type = XMLHelper.getAttributeEnum(e, "Type", ZCategory.Type.NONE);
        }
    }
    public ResourceAchievement(Element e) {
        super(e);
        this.resetType = XMLHelper.getChildEnum(e, "ResetType", ResetType.NONE);
        this.type = XMLHelper.getChildEnum(e, "Type", ZCondition.Type.NONE);
        this.visible = XMLHelper.getChildBoolean(e, "Visible", true);

        var tempCondition = e.getChild("Complete");
        if(Objects.nonNull(tempCondition)) {
            this.condition = new Condition(tempCondition);
        } else {
            this.condition = null;
        }

        var tempRewards = new ArrayList<ResourceReward>();
        var tempElement = e.getChild("Rewards");
        if(Objects.nonNull(tempElement)) {
            for (var rewardElement : tempElement.getChildren("Reward")) {
                tempRewards.add(new ResourceReward(rewardElement));
            }
        }

        this.rewards = ImmutableList.copyOf(tempRewards);
    }
}
