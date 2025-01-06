package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerQuest {
    public long id;
    public long player_id;
    public int step;
    public long progress;
    public int state;
    public long next_stage_group_id_condition;
    public int next_stage_id_condition;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerQuest of(long player_id, int state) {
        var model = new DMPlayerQuest();
        model.player_id = player_id;
        model.step = 1;
        model.progress = 0;
        model.state = state;
        model.next_stage_group_id_condition = 1;
        model.next_stage_id_condition = 1;

        return model;
    }
}
