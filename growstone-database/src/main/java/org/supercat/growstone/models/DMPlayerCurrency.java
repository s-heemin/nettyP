package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerCurrency {
    public long id;
    public long player_id;
    public long gold;
    public long free_ruby;
    public long paid_ruby;
    public long free_diamond;
    public long paid_diamond;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerCurrency of(long playerId, long gold, long freeRuby, long paidRuby, long diamond, long paidDiamond) {
        var model = new DMPlayerCurrency();
        model.gold = gold;
        model.player_id = playerId;
        model.free_ruby = freeRuby;
        model.paid_ruby = paidRuby;
        model.free_diamond = diamond;
        model.paid_diamond = paidDiamond;

        return model;
    }
}
