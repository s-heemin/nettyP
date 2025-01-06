package org.supercat.growstone.gachas;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.supercat.growstone.network.messages.ZCategory;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;
import java.util.TreeMap;

public class ResourceGachaPickUp extends ResourceBase {
    public final ImmutableSortedMap<Integer, ResourcePickUpReward> pickUpRewards;

    public class ResourcePickUpReward {
        public final int point;
        public final ZCategory.Type type;
        public final long dataId;
        public final long count;

        public ResourcePickUpReward(Element e) {
            this.point = XMLHelper.getAttributeInt(e, "Point", 0);
            this.type = XMLHelper.getAttributeEnum(e, "Type", ZCategory.Type.NONE);
            this.dataId = XMLHelper.getAttributeLong(e, "ID", 0);
            this.count = XMLHelper.getAttributeLong(e, "Count", 0);
        }
    }
    public ResourceGachaPickUp(Element e) {
        super(e);

        var tempPickUps = new HashMap<Integer, ResourcePickUpReward>();
        var temp = e.getChildren("PickUpReward");
        for(var ele : temp) {
            var reward = new ResourcePickUpReward(ele);
            tempPickUps.put(reward.point, reward);
        }


        this.pickUpRewards = ImmutableSortedMap.copyOf(new TreeMap<>(tempPickUps));
    }
}
