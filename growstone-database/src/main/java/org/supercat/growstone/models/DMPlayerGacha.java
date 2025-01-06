package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerGacha {
    public long id;
    public long player_id;
    public long index;
    public int level;
    public long exp;
    public int gacha_max_count;
    public int reset_ymd;
    public int ad_view_count;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerGacha of(long player_id, long index) {
        var model = new DMPlayerGacha();
        model.player_id = player_id;
        model.index = index;
        model.level = 1;
        model.exp = 0;
        model.gacha_max_count = 0;
        model.reset_ymd = 0;
        model.ad_view_count = 0;
        return model;
    }
}
