package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerAvatar {
    public long id;
    public long player_id;
    public long avatar_id;
    public boolean isEquip;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerAvatar of(long player_id, long avatar_id) {
        var model = new DMPlayerAvatar();
        model.player_id = player_id;
        model.avatar_id = avatar_id;
        model.isEquip = false;

        return model;
    }
}
