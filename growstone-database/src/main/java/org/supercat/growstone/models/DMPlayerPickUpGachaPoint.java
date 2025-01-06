package org.supercat.growstone.models;

import com.google.common.collect.ImmutableList;
import org.supercat.growstone.JsonConverter;

import java.time.Instant;
import java.util.List;

public class DMPlayerPickUpGachaPoint {
    public long id;
    public long player_id;
    public long shop_data_id;
    public int point;
    public String rewards;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerPickUpGachaPoint of(long player_id, long shopDataId) {
        var model = new DMPlayerPickUpGachaPoint();
        model.player_id = player_id;
        model.shop_data_id = shopDataId;
        model.point = 0;
        model.rewards = JsonConverter.to(List.of());
        return model;
    }
}
