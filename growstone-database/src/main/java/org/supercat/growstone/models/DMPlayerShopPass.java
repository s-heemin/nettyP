package org.supercat.growstone.models;

import com.google.common.collect.ImmutableList;
import org.supercat.growstone.JsonConverter;

import java.time.Instant;
import java.util.List;

public class DMPlayerShopPass {
    public long id;
    public long player_id;
    public long shop_pass_id;
    public int open_step;
    public boolean is_paid;
    public int last_free_reward_step;
    public int last_paid_reward_step;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerShopPass of(long player_id, long shopPassId) {
        var model = new DMPlayerShopPass();
        model.player_id = player_id;
        model.shop_pass_id = shopPassId;
        model.open_step = 1;
        model.is_paid = false;
        model.last_free_reward_step = 0;
        model.last_paid_reward_step = 0;

        return model;
    }
}
