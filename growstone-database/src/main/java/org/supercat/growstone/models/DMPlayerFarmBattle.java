package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFarmBattle {
    public int id;
    public long player_id;
    public long target_player_id;
    public String data;
    public Instant created_at;

    public static DMPlayerFarmBattle of(long player_id, long target_player_id, String data) {
        var model = new DMPlayerFarmBattle();
        model.player_id = player_id;
        model.target_player_id = target_player_id;
        model.data = data;
        return model;
    }
}
