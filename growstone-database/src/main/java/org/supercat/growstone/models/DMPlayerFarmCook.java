package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFarmCook {
    public long id;
    public long player_id;
    public int level;
    public String slots;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerFarmCook of(long player_id) {
        var model = new DMPlayerFarmCook();
        model.player_id = player_id;
        model.level = 1;

        return model;
    }
}
