package org.supercat.growstone.shops;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCondition;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceShopPass extends ResourceBase {
    public final boolean visible;
    public final long shopItemId;
    public final ZCondition.Type condition;
    public final ImmutableMap<Integer, Step> steps;

    public class Step {
        public final int step;
        public final int param1;
        public final int param2;

        public final ImmutableList<ResourceReward> freeRewards;
        public final ImmutableList<ResourceReward> paidRewards;
        public Step(Element e) {
            this.step = XMLHelper.getAttributeInt(e, "Key", 0);
            this.param1 = XMLHelper.getAttributeInt(e, "Param1", 0);
            this.param2 = XMLHelper.getAttributeInt(e, "Param2", 0);

            var l = new ArrayList<ResourceReward>();
            var tempFreeRewards = e.getChildren("FreeReward");
            for(var freeReward : tempFreeRewards) {
                l.add(new ResourceReward(freeReward));
            }

            freeRewards = ImmutableList.copyOf(l);

            l.clear();
            var tempPaidRewards = e.getChildren("PayReward");
            for(var paidReward : tempPaidRewards) {
                l.add(new ResourceReward(paidReward));
            }

            paidRewards = ImmutableList.copyOf(l);
        }
    }
    public ResourceShopPass(Element e) {
        super(e);

        this.visible = XMLHelper.getChildBoolean(e, "Visible", false);
        this.condition = XMLHelper.getChildEnum(e, "RewardCondition", ZCondition.Type.NONE);
        this.shopItemId = XMLHelper.getChildLong(e, "ShopItemID", 0);

        var temp = new HashMap<Integer, Step>();
        var tempSteps = e.getChildren("Step");
        for(var step : tempSteps) {
            var s = new Step(step);
            temp.put(s.step, s);
        }

        steps = ImmutableMap.copyOf(temp);
    }
}
