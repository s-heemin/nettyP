package org.supercat.growstone.exploration;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.HashSet;

public class ResourceExplorationTier {
    final public ZTier.Type tier;
    final public int minElapsedTime;
    final public int maxElapsedTime;
    final public int earnExp;
    final public ImmutableList<ResourceExplorationRewardItem> rewardItems;

    public ResourceExplorationTier(Element e) {
        this.tier = XMLHelper.getAttributeEnum(e, "Tier", ZTier.Type.NONE);
        this.minElapsedTime = XMLHelper.getAttributeInt(e, "MinElapsedTime", 0);
        this.maxElapsedTime = XMLHelper.getAttributeInt(e, "MaxElapsedTime", 0);
        this.earnExp = XMLHelper.getAttributeInt(e, "EarnExp", 0);

        var eRewardItems = e.getChildren("RewardItem");
        var tempSet = new HashSet<ResourceExplorationRewardItem>();
        for (var rewardItem : eRewardItems) {
            var resExplorationRewardItem = new ResourceExplorationRewardItem(rewardItem);
            tempSet.add(resExplorationRewardItem);
        }
        this.rewardItems = ImmutableList.copyOf(tempSet);
    }
}
