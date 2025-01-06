package org.supercat.growstone.exploration;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZTier;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;

import java.util.TreeMap;

public class ResourceExplorationLevel {
    final public int level;
    final public int nextLevelExp;
    final public ImmutableMap<Integer, ZTier.Type> tierProbability;

    final public int totalTierProbability;

    public ResourceExplorationLevel(Element e) {
        this.level = XMLHelper.getAttributeInt(e, "Level", 0);
        this.nextLevelExp = XMLHelper.getAttributeInt(e, "NextLevelExp", 0);

        var eTierProbabilities = e.getChildren("TierProbability");
        var tempTreeMap = new TreeMap<Integer, ZTier.Type>();
        var tempTotalTierProbability = 0;
        for (var eTierProbability : eTierProbabilities) {
            var resExplorationTierProbability = new ResourceExplorationTierProbability(eTierProbability);
            tempTotalTierProbability += resExplorationTierProbability.probability;
            tempTreeMap.put(tempTotalTierProbability, resExplorationTierProbability.tier);
        }
        tierProbability = ImmutableMap.copyOf(tempTreeMap);

        totalTierProbability = tempTotalTierProbability;
    }


}
