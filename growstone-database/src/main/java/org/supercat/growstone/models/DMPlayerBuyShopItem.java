package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerBuyShopItem {
    public long id;
    public long player_id;
    public long shop_data_id;
    public long count;
    public int buy_reset_day;
    public int ad_view_count;
    public int ad_reset_day;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerBuyShopItem of(long player_id, long shop_data_id, long count) {
        var model = new DMPlayerBuyShopItem();
        model.player_id = player_id;
        model.shop_data_id = shop_data_id;
        model.count = count;
        model.buy_reset_day = 0;
        model.ad_reset_day = 0;
        model.ad_view_count = 0;
        return model;

    }
}
