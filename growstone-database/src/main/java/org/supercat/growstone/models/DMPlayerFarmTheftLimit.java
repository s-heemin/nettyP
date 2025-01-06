package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFarmTheftLimit {
    public int id;
    public long player_id;
    public int ymd;
    public int count;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerFarmTheftLimit of(long player_id, int ymd) {
        var model = new DMPlayerFarmTheftLimit();
        model.player_id = player_id;
        model.ymd = ymd;
        model.count = 0;

        return model;
    }
}
