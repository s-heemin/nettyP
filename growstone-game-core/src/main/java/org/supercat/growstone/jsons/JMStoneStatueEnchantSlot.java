package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.ZStat;
import com.supercat.growstone.network.messages.ZTier;

public class JMStoneStatueEnchantSlot {
    public int slotId;
    public boolean isLocked;
    public ZTier.Type tier;
    public ZStat.Type statId;
    public double statValue;

    public static JMStoneStatueEnchantSlot of(int slotId, boolean isLocked, ZTier.Type tier, ZStat.Type statId, double statValue) {
        var jm = new JMStoneStatueEnchantSlot();
        jm.slotId = slotId;
        jm.isLocked = isLocked;
        jm.tier = tier;
        jm.statId = statId;
        jm.statValue = statValue;

        return jm;
    }
}
