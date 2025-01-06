package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZCategory;

public class ContentReward {
    public ZCategory.Type type;
    public long dataId;
    public long count;

    public static ContentReward of(ZCategory.Type type, long dataId, long count) {
        ContentReward model = new ContentReward();
        model.type = type;
        model.dataId = dataId;
        model.count = count;
        return model;
    }
}
