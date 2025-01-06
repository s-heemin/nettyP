package org.supercat.growstone.growths;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.GrowthLevelIntervalUpgrade;

import java.util.HashMap;

public class ResourceGrowthUpgrade extends ResourceBase {
    private static final Logger logger = LoggerFactory.getLogger(ResourceGrowthUpgrade.class);

    public final ZGrowth.Type type;
    public final ZTier.Type tier;
    public final ImmutableMap<ZGrowthStatTarget.Type, GrowthLevelIntervalUpgrade> levelUpgrades;
    public final ImmutableMap<ZGrowthStatTarget.Type, ResourceGrowthMaterial> materials;
    public ResourceGrowthUpgrade(Element e) {
        super(e);
        this.type = XMLHelper.getChildEnum(e, "Type", ZGrowth.Type.NONE);
        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);
        var tempMaterials = new HashMap<ZGrowthStatTarget.Type, ResourceGrowthMaterial>();
        var tempLevelUpgrades = new HashMap<ZGrowthStatTarget.Type, GrowthLevelIntervalUpgrade>();
        // 레벨 상승 재료
        var levelElement = e.getChild("Level");
        setMaterials(levelElement, ZGrowthStatTarget.Type.LEVEL, tempMaterials, tempLevelUpgrades);

        // 승급 재료
        var promoteElement = e.getChild("Promote");
        setMaterials(promoteElement, ZGrowthStatTarget.Type.PROMOTE, tempMaterials, tempLevelUpgrades);

        // 한계 돌파 재료
        var limitBreakElement = e.getChild("LimitBreak");
        setMaterials(limitBreakElement, ZGrowthStatTarget.Type.LIMIT_BREAK, tempMaterials, tempLevelUpgrades);

        materials = ImmutableMap.copyOf(tempMaterials);
        levelUpgrades = ImmutableMap.copyOf(tempLevelUpgrades);
    }

    private void setMaterials(Element e, ZGrowthStatTarget.Type type,
                              HashMap<ZGrowthStatTarget.Type, ResourceGrowthMaterial> tempMaterials,
                              HashMap<ZGrowthStatTarget.Type, GrowthLevelIntervalUpgrade> tempLevelUpgrades) {
        var maxUpgradeLevel = XMLHelper.getAttributeInt(e, "MaxUpgradeLevel", 0);
        var intervalValue = XMLHelper.getAttributeInt(e, "AddMaxForLimitBreak", 0);
        tempLevelUpgrades.put(type, new GrowthLevelIntervalUpgrade(maxUpgradeLevel, intervalValue));
        tempMaterials.put(type, new ResourceGrowthMaterial(e.getChild("Material")));
    }
}