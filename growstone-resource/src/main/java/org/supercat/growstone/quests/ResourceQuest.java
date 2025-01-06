package org.supercat.growstone.quests;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZCondition;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceQuest extends ResourceBase {
    public final int step;
    public final ZCondition.Type type;
    public final ImmutableList<ResourceReward> rewards;
    public final Condition condition;

    public class Condition {
        public final long param1;
        public final long param2;

        public Condition(Element e) {
            this.param1 = XMLHelper.getAttributeLong(e, "Param1", 0);
            this.param2 = XMLHelper.getAttributeLong(e, "Param2", 0);
        }
    }

    public ResourceQuest(Element e) {
        super(e);
        this.step = XMLHelper.getChildInt(e, "Step", 0);
        this.type = XMLHelper.getChildEnum(e, "Type", ZCondition.Type.NONE);

        var tempCondition = e.getChild("Complete");
        if (Objects.nonNull(tempCondition)) {
            this.condition = new Condition(tempCondition);
        } else {
            this.condition = null;
        }

        var tempRewards = new ArrayList<ResourceReward>();
        for (var rewardElement : e.getChildren("Reward")) {
            tempRewards.add(new ResourceReward(rewardElement));
        }

        this.rewards = ImmutableList.copyOf(tempRewards);
    }
}
