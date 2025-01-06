package org.supercat.growstone.models;

import java.time.Instant;

public class DMClan {
    public long id;
    public int level;
    public int exp;
    public String name;
    public long master_player_id;
    public int rank;
    public String notice;
    public String introduction;
    public int join_type;
    public int state;
    public Instant master_last_logout_at;
    public Instant last_change_name_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMClan of(long master_player_id, String name, int state, int joinType) {
        var model = new DMClan();
        model.master_player_id = master_player_id;
        model.name = name;
        model.level = 1;
        model.exp = 0;
        model.rank = 0;
        model.notice = "";
        model.introduction = "";
        model.join_type = joinType;
        model.state = state;

        model.last_change_name_at = Instant.now();
        model.master_last_logout_at = Instant.now();
        return model;
    }

}

