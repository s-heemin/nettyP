package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerRaidDungeon {
    public long id;
    public long player_id;
    public long raid_data_id;
    public long score;
    public int remain_ticket_count;
    public int remain_view_ad_count;
    public int reset_ymd;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerRaidDungeon of(long player_id, long raid_data_id) {
        var model = new DMPlayerRaidDungeon();
        model.player_id = player_id;
        model.raid_data_id = raid_data_id;
        model.score = 0;
        model.remain_ticket_count = 0;
        model.remain_view_ad_count = 0;
        model.reset_ymd = 0;

        return model;
    }
}
