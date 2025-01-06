package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerTower {
    public long id;
    public long player_id;
    public long tower_data_id;
    public int stage;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerTower of(long player_id, long tower_data_id) {
        var model = new DMPlayerTower();
        model.player_id = player_id;
        model.tower_data_id = tower_data_id;
        model.stage = 1;
        return model;
    }
}
