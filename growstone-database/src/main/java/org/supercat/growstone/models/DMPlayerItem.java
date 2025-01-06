package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerItem {
    public long id;
    public long player_id;
    public long item_id;
    public long item_data_id;
    public long count;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerItem of(long player_id, long item_data_id, long count) {
        var model = new DMPlayerItem();
        model.player_id = player_id;
        model.item_data_id = item_data_id;
        model.count = count;
        return model;
    }
}
