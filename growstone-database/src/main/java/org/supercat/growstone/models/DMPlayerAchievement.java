package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerAchievement {
    public long id;
    public long player_id;
    public byte[] data;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerAchievement of(long player_id, byte[] data) {
        var model = new DMPlayerAchievement();
        model.player_id = player_id;
        model.data = data;
        return model;
    }
}
