package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerStatGrowth {
    public long id;
    public long player_id;
    public long page;
    public int level;
    public int stat;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerStatGrowth of(long player_id, long page, int level, int stat) {
        var model = new DMPlayerStatGrowth();
        model.player_id = player_id;
        model.page = page;
        model.level = level;
        model.stat = stat;

        return model;
    }
}
