package org.supercat.growstone.models;

import com.google.api.client.json.Json;
import com.google.common.collect.ImmutableList;
import org.supercat.growstone.JsonConverter;

import java.time.Instant;
import java.util.List;

public class DMPlayerEvent {
    public long id;
    public long player_id;
    public long event_id;
    public long event_data_id;
    public int progress;
    public int state;
    public String rewards;
    public long last_updated_date;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerEvent of(long player_id, long event_id, long event_data_id, int state) {
        var model = new DMPlayerEvent();
        model.player_id = player_id;
        model.event_id = event_id;
        model.event_data_id = event_data_id;
        model.progress = 0;
        model.state = state;
        model.rewards = JsonConverter.to(List.of());
        model.last_updated_date = 0;
        return model;
    }
}
