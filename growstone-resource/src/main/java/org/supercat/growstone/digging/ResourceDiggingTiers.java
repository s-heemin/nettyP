package org.supercat.growstone.digging;

import com.google.common.collect.ImmutableList;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceDiggingTiers extends ResourceBase {
    public final long itemId;
    public final long count;
    public final int maxRatio;
    public final ImmutableList<Tier> tiers;
    public class Tier {
        public final ZTier.Type tier;
        public final int ratio;
        public Tier(Element e) {
            this.tier = XMLHelper.getAttributeEnum(e, "Tier", ZTier.Type.NONE);
            this.ratio = XMLHelper.getAttributeInt(e, "Ratio", 0);
        }
    }
    public ResourceDiggingTiers(Element e) {
        super(e);
        this.itemId = XMLHelper.getChildLong(e, "ItemID", 0);
        this.count = XMLHelper.getChildLong(e, "Count", 0);

        var l = new ArrayList<Tier>();
        int tempMaxRatio = 0;
        var tempTiers = e.getChild("Tiers");
        if(Objects.nonNull(tempTiers)) {
            for(var tier : tempTiers.getChildren("Tier")) {
                var resTier = new Tier(tier);
                tempMaxRatio += resTier.ratio;
                l.add(resTier);
            }
        }

        this.maxRatio = tempMaxRatio;
        this.tiers = ImmutableList.copyOf(l);

    }
}
