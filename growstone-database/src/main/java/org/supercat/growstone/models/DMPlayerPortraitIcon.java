package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerPortraitIcon {
    public long id;
    public long player_id;
    public long icon_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerPortraitIcon of(long player_id, long icon_id) {
        var model = new DMPlayerPortraitIcon();
        model.player_id = player_id;
        model.icon_id = icon_id;

        return model;
    }
}
