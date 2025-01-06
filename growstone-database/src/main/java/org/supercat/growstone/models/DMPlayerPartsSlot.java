package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerPartsSlot {
    public long id;
    public long player_id;
    public int type;
    public int level;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerPartsSlot of(long player_id, int type) {
        var model = new DMPlayerPartsSlot();
        model.player_id = player_id;
        model.type = type;
        return model;
    }
}
