package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerCollection {
    public long id;
    public long player_id;
    public int type;
    public long collection_id;
    public int level;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerCollection of(long player_id, int type, long collection_id) {
        var model = new DMPlayerCollection();
        model.player_id = player_id;
        model.type = type;
        model.collection_id = collection_id;
        model.level = 0;

        return model;
    }
}
