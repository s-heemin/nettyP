package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerDailyDungeon {
    public long id;
    public long player_id;
    public long dungeon_data_id;
    public int stage;
    public int remain_ticket_count;
    public int remain_view_ad_count;
    public int reset_ymd;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerDailyDungeon of(long player_id, long dungeon_data_id) {
        var model = new DMPlayerDailyDungeon();
        model.player_id = player_id;
        model.dungeon_data_id = dungeon_data_id;
        model.stage = 1;
        model.remain_ticket_count = 0;
        model.remain_view_ad_count = 0;
        model.reset_ymd = 0;

        return model;
    }
}
