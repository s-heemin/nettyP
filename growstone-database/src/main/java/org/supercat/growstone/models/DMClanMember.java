package org.supercat.growstone.models;

import java.time.Instant;

public class DMClanMember {
    public long id;
    public long player_id;
    public long clan_id;
    public int role;;
    public int accumulate_contribution;
    public int donate_count;
    public int donate_reset_ymd;
    public Instant penalty_end_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMClanMember of(long player_id, long clan_id, int role) {
        var model = new DMClanMember();
        model.player_id = player_id;
        model.clan_id = clan_id;
        model.role = role;
        model.accumulate_contribution = 0;
        model.donate_count = 0;
        model.donate_reset_ymd = 0;
        model.penalty_end_at = Instant.now();

        return model;
    }

}
