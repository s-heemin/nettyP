package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerGrowth {
    public long id;
    public long player_id;
    public long growth_id;
    public long count;
    public int type;
    public int level;
    public int promote_level;
    public int limit_break_level;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerGrowth of(long player_id, long growth_id, int type) {
        var model = new DMPlayerGrowth();
        model.player_id = player_id;
        model.growth_id = growth_id;
        model.count = 1;
        model.type = type;
        model.level = 0;
        model.promote_level = 0;
        model.limit_break_level = 0;
        return model;
    }
}
