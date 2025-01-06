package org.supercat.growstone.avatars;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.containers.ResourceStat;
import org.supercat.growstone.growths.ResourceGrowthOption;
import org.supercat.growstone.growths.ResourceGrowthStats;

import java.util.HashMap;
import java.util.Objects;

public class ResourceAvatar extends ResourceBase {
    public final ZCategory.Type category;
    public final ZResource.Type type;
    public final ZTier.Type tier;
    public final ResourceReward duplicateReward;
    public final ImmutableMap<ZStat.Type, Double> stats;

    public ResourceAvatar(Element e) {
        super(e);
        this.category = XMLHelper.getChildEnum(e, "Category", ZCategory.Type.NONE);
        this.type = XMLHelper.getChildEnum(e, "Type", ZResource.Type.NONE);
        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);

        var eDuplicate = e.getChild("DuplicateReward");
        duplicateReward = Objects.nonNull(eDuplicate) ? new ResourceReward(eDuplicate.getChild("Reward")) : null;

        var temp = new HashMap<ZStat.Type, Double>();
        var ownedOption = e.getChild("OwnedOption");
        if(Objects.nonNull(ownedOption)) {
            var elements = ownedOption.getChildren("Stat");
            if(Objects.nonNull(elements)) {
                for(var element : elements) {
                    var stat = XMLHelper.getAttributeEnum(element, "Type", ZStat.Type.NONE);
                    var value = XMLHelper.getAttributeDouble(element, "Value", 0);

                    temp.compute(stat, (k, v) -> Objects.isNull(v) ? value : v + value);
                }
            }
        }
        stats = ImmutableMap.copyOf(temp);
    }
}
