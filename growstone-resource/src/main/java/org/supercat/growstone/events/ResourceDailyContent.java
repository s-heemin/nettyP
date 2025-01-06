package org.supercat.growstone.events;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZDailyContent;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ResourceDailyContent extends ResourceBase {
    public final ZDailyContent.Type type;
    public final boolean visible;
    public final int dailyCount;
    public final long coolTimeSecond;
    public final ImmutableList<ResourceReward> gachaRewards;
    public final ImmutableMap<Integer, ResourceReward> dailyRewards;
    public ResourceDailyContent(Element e) {
        super(e);
        this.type = XMLHelper.getChildEnum(e, "Type", ZDailyContent.Type.NONE);
        this.visible = XMLHelper.getChildBoolean(e, "Visible", false);
        this.dailyCount = XMLHelper.getChildInt(e, "DailyCount", 1);
        this.coolTimeSecond = XMLHelper.getChildLong(e, "CoolTimeSecond", 0);

        var tempGachaRewards = new ArrayList<ResourceReward>();
        var gachaRewardChild = e.getChild( "GachaRewardTable");
        if(Objects.nonNull(gachaRewardChild)) {
            for(var reward : gachaRewardChild.getChildren("Reward")) {
                tempGachaRewards.add(new ResourceReward(reward));
            }
        }

        gachaRewards = ImmutableList.copyOf(tempGachaRewards);

        var tempDailyRewards = new HashMap<Integer, ResourceReward>();
        var dailyRewardChild = e.getChild("DailyRewardTable");
        if(Objects.nonNull(dailyRewardChild)) {
            for(var reward : dailyRewardChild.getChildren("Reward")) {
                int key = XMLHelper.getAttributeInt(reward, "Key", 0);
                var rewardModel = new ResourceReward(reward);
                tempDailyRewards.put(key, rewardModel);
            }
        }

        dailyRewards = ImmutableMap.copyOf(tempDailyRewards);
    }

    public int getMaxRatio() {
        return gachaRewards.stream().mapToInt(r -> r.ratio).sum();
    }
}
