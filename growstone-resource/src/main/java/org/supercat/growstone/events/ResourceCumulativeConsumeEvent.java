package org.supercat.growstone.events;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceCumulativeConsumeEvent extends ResourceEvent {
    public final ImmutableMap<Integer, ImmutableList<ResourceReward>> rewards;
    public final ImmutableList<Long> exceptShopIds;

    public ResourceCumulativeConsumeEvent(Element e) {
        super(e);
        var tempRewards = new HashMap<Integer, ImmutableList<ResourceReward>>();
        for(var ele : e.getChildren("SpendAmount")) {
            int count = XMLHelper.getAttributeInt(ele, "Key", 0);
            var temps = new ArrayList<ResourceReward>();
            for(var reward : ele.getChildren("Reward")) {
                temps.add(new ResourceReward(reward));
            }

            tempRewards.put(count, ImmutableList.copyOf(temps));
        }

        this.rewards = ImmutableMap.copyOf(tempRewards);

        var tempExcepts = new ArrayList<Long>();
        for(var ele : e.getChildren("ExceptShopItems")) {
            tempExcepts.add(XMLHelper.getChildLong(ele, "ShopItemID", 0));
        }
        exceptShopIds = ImmutableList.copyOf(tempExcepts);
    }
}
