package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZCategory;

public class Material {
    public ZCategory.Type type;
    public long dataId;
    public long count;

    public Material(ZCategory.Type type, long dataId, long count) {
        setMaterial(type, dataId, count);
    }

    public void setMaterial(ZCategory.Type type, long dataId, long count) {
        this.type = type;
        this.dataId = dataId;
        this.count = count;
    }
}
