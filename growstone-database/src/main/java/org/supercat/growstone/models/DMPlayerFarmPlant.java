package org.supercat.growstone.models;

import org.supercat.growstone.GameZoneDateTime;

import java.time.Instant;

public class DMPlayerFarmPlant {
    public long id;
    public long player_id;
    public int slot_index;
    public int status;
    public long plant_id;
    public int theft_ymd;
    public long theft_player_id;
    public int theft_limit_count;
    public Instant seed_start_at;
    public Instant seed_end_at;
    public Instant theft_end_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerFarmPlant of(long player_id, int index, int status) {
        var model = new DMPlayerFarmPlant();
        model.player_id = player_id;
        model.slot_index = index;
        model.status = status;
        model.plant_id = 0L;
        model.theft_ymd = 0;
        model.theft_player_id = 0L;
        model.theft_limit_count = 0;
        model.seed_start_at = GameZoneDateTime.SAFE_INSTANT_MIN;
        model.seed_end_at = GameZoneDateTime.SAFE_INSTANT_MIN;
        model.theft_end_at = GameZoneDateTime.SAFE_INSTANT_MIN;
        return model;
    }
}
