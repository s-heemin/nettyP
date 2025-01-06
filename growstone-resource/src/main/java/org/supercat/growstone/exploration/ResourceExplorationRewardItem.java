package org.supercat.growstone.exploration;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class ResourceExplorationRewardItem {
    final public ZCategory.Type type;
    final public int id;
    final public ImmutableMap<Integer, ResourceExplorationRewardCount> rewardCounts;

    public ResourceExplorationRewardItem(Element e) {
        this.type = XMLHelper.getAttributeEnum(e, "Type", ZCategory.Type.NONE);
        this.id = XMLHelper.getAttributeInt(e, "ID", 0);

        var eRewardCounts = e.getChildren("RewardCount");
        var tempRewardCounts = new HashMap<Integer, ResourceExplorationRewardCount>();
        for (var rewardCount : eRewardCounts) {
            var resExplorationRewardCount = new ResourceExplorationRewardCount(rewardCount);
            tempRewardCounts.put(resExplorationRewardCount.level, resExplorationRewardCount);
        }

        rewardCounts = ImmutableMap.copyOf(tempRewardCounts);
    }
}
