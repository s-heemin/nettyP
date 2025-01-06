package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.ZPartsSlot;

public class JMPlayerPartsSlot {
    public ZPartsSlot.Type type;
    public int level;

    public static JMPlayerPartsSlot of(ZPartsSlot.Type type, int level) {
        var model = new JMPlayerPartsSlot();
        model.type = type;
        model.level = level;
        return model;
    }
}
