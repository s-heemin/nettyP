package org.supercat.growstone.jsons;

public class JMPlayerItem {
    public int dataId;
    public long count;

    public static JMPlayerItem of(int dataId, long count) {
        var model = new JMPlayerItem();
        model.dataId = dataId;
        model.count = count;
        return model;
    }

}
