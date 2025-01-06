package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFarm {
    public int id;
    public long player_id;
    public long exp;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerFarm of(long player_id) {
        var model = new DMPlayerFarm();
        model.player_id = player_id;
        model.exp = 0L;
        return model;
    }
}
