package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerDiggingUpgrade {
    public long id;
    public long player_id;
    public int type;
    public int level;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerDiggingUpgrade of(long player_id, int type) {
        var model = new DMPlayerDiggingUpgrade();
        model.player_id = player_id;
        model.type = type;
        model.level = 0;
        return model;
    }
}
