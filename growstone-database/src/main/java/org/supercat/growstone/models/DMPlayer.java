package org.supercat.growstone.models;

import java.time.Duration;
import java.time.Instant;

public class DMPlayer {
    public long id;
    public long global_master_player_id;
    public long channel_id;
    public String name;
    public long portrait_id;
    public int level;
    public long exp;
    public long clan_id;
    public long attack_power;
    public long stage_group;
    public int stage;
    public boolean on_boss_gauge;
    public int preset_index;
    public int ban_status;
    public boolean remove_ad;
    public String friend_code;
    public Instant last_change_name_at;
    public Instant last_connected_at;
    public Instant last_disconnected_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayer of(long global_master_player_id, long channel_id) {
        var model = new DMPlayer();
        model.global_master_player_id = global_master_player_id;
        model.channel_id = channel_id;
        model.level = 1;
        model.exp = 0;
        model.stage_group = 1;
        model.stage = 1;
        model.preset_index = 1;
        model.clan_id = 0;
        model.last_change_name_at = Instant.now().minus(Duration.ofDays(1));
        model.last_connected_at = Instant.now();
        model.last_disconnected_at = Instant.now();

        return model;
    }
}
