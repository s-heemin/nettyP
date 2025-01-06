package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerContinueShop {
    public long id;
    public long player_id;
    public int group_id;
    public int step_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerContinueShop of(long player_id, int group_id) {
        var model = new DMPlayerContinueShop();
        model.player_id = player_id;
        model.group_id = group_id;
        model.step_id = 1;
        return model;
    }
}
