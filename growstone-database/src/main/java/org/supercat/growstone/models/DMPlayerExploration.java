package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerExploration {
    public long id;
    public long player_id;
    public int level;
    public int exp;
    public int auto;
    public String quests;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerExploration of(long player_id) {
        var model = new DMPlayerExploration();
        model.player_id = player_id;
        model.level = 1;
        model.exp = 0;
        model.auto = 0;

        return model;
    }

    public boolean isAuto() {
        return auto == 1;
    }

    public void setAuto(boolean auto) {
        this.auto = auto ? 1 : 0;
    }
}
