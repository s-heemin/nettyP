package org.supercat.growstone.stats;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.supercat.growstone.network.messages.ZStat;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;

import java.util.HashMap;

public class ResourceStatGrowth extends ResourceBase {
    public final ImmutableMap<Integer, Integer> prices;
    public final int maxLevel;
    public final ImmutableMap<ZStat.Type, Integer> stats;
    public ResourceStatGrowth(Element e) {
        super(e);
        this.maxLevel = XMLHelper.getAttributeInt(e, "MaxLevel", 0);
        int tempIncrease = XMLHelper.getAttributeInt(e, "PriceIncrease", 0);
        int tempBasePrice = XMLHelper.getAttributeInt(e, "PriceBase", 0);
        var tempPrices = new HashMap<Integer, Integer>();

        for(int i = 0 ; i< this.maxLevel; i++) {
            int price = tempBasePrice + i * tempIncrease;
            tempPrices.put(i, price);
        }

        this.prices = ImmutableMap.copyOf(tempPrices);

        var tempStats = new HashMap<ZStat.Type, Integer>();
        var temp = e.getChildren("Stat");
        if (temp != null) {
            for (var element : temp) {
                var stat =  XMLHelper.getAttributeEnum(element, "Key", ZStat.Type.NONE);
                var value = XMLHelper.getAttributeInt(element, "Increment", 0);
                tempStats.put(stat, value);
            }
        }

        stats = ImmutableMap.copyOf(tempStats);
    }
}
