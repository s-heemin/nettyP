package org.supercat.growstone.models;

import java.time.Instant;

public class DMWorldScheduleTask {
    public long id;
    public long channel_id;
    public int type;
    public int reset_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMWorldScheduleTask of(long channel_id, int type, int reset_key) {
        var model = new DMWorldScheduleTask();
        model.channel_id = channel_id;
        model.type = type;
        model.reset_at = reset_key;

        return model;
    }
}
