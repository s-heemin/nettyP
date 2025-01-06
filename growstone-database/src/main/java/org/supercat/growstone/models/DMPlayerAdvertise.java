package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerAdvertise {
    public long id;
    public long player_id;
    public int type;
    public int view_count;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerAdvertise of(long player_id, int type) {
        var model = new DMPlayerAdvertise();
        model.player_id = player_id;
        model.type = type;
        model.view_count = 0;

        return model;
    }
}
