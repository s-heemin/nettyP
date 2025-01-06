package org.supercat.growstone.models;

import java.time.Instant;

public class DMEvent {
    public long id;
    public long event_data_id;
    public Instant start_at;
    public Instant end_at;
    public Instant display_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMEvent of(long eventDataId, Instant startAt, Instant endAt, Instant displayAt) {
        var model = new DMEvent();
        model.event_data_id = eventDataId;
        model.start_at = startAt;
        model.end_at = endAt;
        model.display_at = displayAt;
        return model;
    }
}
