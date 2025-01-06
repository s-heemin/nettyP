package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZStat;

public class ResourceStat {
    public final ZStat.Type type;
    public final double value;

    public ResourceStat(ZStat.Type type, double value) {
        this.type = type;
        this.value = value;
    }
}
