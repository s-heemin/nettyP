package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerStoneStatueAvatar {
    public long id;
    public long player_id;
    public long avatar_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerStoneStatueAvatar of(long player_id, long avatar_id) {
        var model = new DMPlayerStoneStatueAvatar();
        model.player_id = player_id;
        model.avatar_id = avatar_id;

        return model;
    }
}
