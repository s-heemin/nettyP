package org.supercat.growstone.growths;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ResourceGrowth extends ResourceBase {
    private static final Logger logger = LoggerFactory.getLogger(ResourceGrowth.class);

    public final ZCategory.Type category;
    public final ZGrowth.Type type;
    public final ZTier.Type tier;
    public final ZPartsSlot.Type partsSlotType;
    public final ImmutableMap<ZGrowthStatTarget.Type, HashMap<ZStat.Type, ResourceGrowthStats>> equipStats;
    public final ImmutableMap<ZGrowthStatTarget.Type, HashMap<ZStat.Type, ResourceGrowthStats>> ownedStats;
    public ResourceGrowth(Element e) {
        super(e);
        this.category = XMLHelper.getChildEnum(e, "Category", ZCategory.Type.NONE);
        this.type = XMLHelper.getChildEnum(e, "Type", ZGrowth.Type.NONE);
        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);
        this.partsSlotType = XMLHelper.getChildEnum(e, "PartsType", ZPartsSlot.Type.NONE);

        var equippedOption = e.getChild("EquippedOption");
        if(Objects.nonNull(equippedOption)) {
            var temp = ResourceGrowthOption.ResourceGrowthOption(equippedOption);
            this.equipStats = ImmutableMap.copyOf(temp);
        } else {
            this.equipStats = ImmutableMap.of();
        }

        var ownedOption = e.getChild("OwnedOption");
        if(Objects.nonNull(ownedOption)) {
            var temp = ResourceGrowthOption.ResourceGrowthOption(e.getChild("OwnedOption"));
            this.ownedStats = ImmutableMap.copyOf(temp);
        } else {
            this.ownedStats = ImmutableMap.of();
        }


    }
}
