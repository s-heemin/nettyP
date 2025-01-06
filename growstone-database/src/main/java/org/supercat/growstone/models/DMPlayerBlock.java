package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerBlock {
    public long id;
    public long player_id;
    public long target_player_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerBlock of(long player_id, long target_player_id) {
        var model = new DMPlayerBlock();
        model.player_id = player_id;
        model.target_player_id = target_player_id;
        return model;
    }
}
