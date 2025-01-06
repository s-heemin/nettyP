package org.supercat.growstone.models;

import org.supercat.growstone.JsonConverter;

import java.time.Instant;
import java.util.List;

public class DMPlayerDailyContent {
    public long id;
    public long player_id;
    public int type;
    public int progress;
    public int state;
    public String rewards;
    public long last_updated_date;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerDailyContent of(long player_id, int type) {
        var model = new DMPlayerDailyContent();
        model.player_id = player_id;
        model.type = type;
        model.progress = 0;
        model.rewards = JsonConverter.to(List.of());
        model.last_updated_date = 0;
        return model;
    }
}
