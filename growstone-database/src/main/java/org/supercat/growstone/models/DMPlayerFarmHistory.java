package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFarmHistory {
    public int id;
    public long player_id;
    public int type;
    public String data;
    public Instant created_at;

    public static DMPlayerFarmHistory of(long player_id, int type, String data) {
        var model = new DMPlayerFarmHistory();
        model.player_id = player_id;
        model.type = type;
        model.data = data;
        return model;
    }
}
