package org.supercat.growstone.drops;

import com.google.common.collect.ImmutableList;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;

public class ResourceDropTable extends ResourceBase {
    public final ImmutableList<ResourceReward> rewards;
    public ResourceDropTable(Element e) {
        super(e);
        var temp = new ArrayList<ResourceReward>();
        for(var reward : e.getChildren("Reward")) {
            temp.add(new ResourceReward(reward));
        }

        this.rewards = ImmutableList.copyOf(temp);
    }
}
