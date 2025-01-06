package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerStoneStatue {
    public long id;
    public long player_id;
    public int enchant_level;
    public int enchant_exp;
    public int enchant_safe_grade;
    public long avatar_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerStoneStatue of(long player_id, int enchant_level, int enchant_exp, int enchant_safe_grade, long avatar_id) {
        var model = new DMPlayerStoneStatue();
        model.player_id = player_id;
        model.enchant_level = enchant_level;
        model.enchant_exp = enchant_exp;
        model.enchant_safe_grade = enchant_safe_grade;
        model.avatar_id = avatar_id;

        return model;
    }
}
