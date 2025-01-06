package org.supercat.growstone.digging;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceDiggingReward extends ResourceBase {
    public final ZTier.Type tier;
    public final int maxRatio;
    public final ImmutableList<ResourceReward> rewards;
    public ResourceDiggingReward(Element e) {
        super(e);
        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);

        var l = new ArrayList<ResourceReward>();
        int tempMaxRatio = 0;
        var tempRewards = e.getChild("Rewards");
        if(Objects.nonNull(tempRewards)) {
            for(var reward : tempRewards.getChildren("Reward")) {
                var addReward = new ResourceReward(reward);
                tempMaxRatio += addReward.ratio;
                l.add(addReward);
            }
        }

        this.maxRatio = tempMaxRatio;
        this.rewards = ImmutableList.copyOf(l);
    }
}
