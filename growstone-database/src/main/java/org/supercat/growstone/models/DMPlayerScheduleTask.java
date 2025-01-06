package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerScheduleTask {
    public long id;
    public long player_id;
    public int type;
    public int reset_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerScheduleTask of(long player_id, int type, int reset_at) {
        var model = new DMPlayerScheduleTask();
        model.player_id = player_id;
        model.type = type;
        model.reset_at = reset_at;

        return model;
    }
}
