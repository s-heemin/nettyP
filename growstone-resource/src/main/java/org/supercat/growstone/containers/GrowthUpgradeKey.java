package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZGrowth;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZTier;

import java.util.Objects;

public class GrowthUpgradeKey {
    public ZGrowth.Type type;
    public ZTier.Type tier;

    public GrowthUpgradeKey(ZGrowth.Type type, ZTier.Type tier) {
        this.type = type;
        this.tier = tier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowthUpgradeKey key = (GrowthUpgradeKey) o;
        return this.type == key.type  && this.tier == key.tier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.tier);
    }
}
