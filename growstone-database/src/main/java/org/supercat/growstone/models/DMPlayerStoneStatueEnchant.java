package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerStoneStatueEnchant {
    public long id;
    public long player_id;
    public int order_id;
    public String data;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerStoneStatueEnchant of(long player_id, int order_id, String data) {
        var model = new DMPlayerStoneStatueEnchant();
        model.player_id = player_id;
        model.order_id = order_id;
        model.data = data;

        return model;
    }
}
