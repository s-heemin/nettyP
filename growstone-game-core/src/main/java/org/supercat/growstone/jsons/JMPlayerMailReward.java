package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.ZCategory;

public class JMPlayerMailReward {
    public ZCategory.Type type;
    public long data_id;
    public long count;

    public static JMPlayerMailReward of(ZCategory.Type type, long data_id, long count) {
        var model = new JMPlayerMailReward();
        model.type = type;
        model.data_id = data_id;
        model.count = count;
        return model;
    }
}
