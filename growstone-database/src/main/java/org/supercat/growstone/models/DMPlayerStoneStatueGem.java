package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerStoneStatueGem {
    public long id;
    public long player_id;
    public long gem_id;
    public int gem_level;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerStoneStatueGem of(long player_id, long gem_id, int gem_level) {
        var model = new DMPlayerStoneStatueGem();
        model.player_id = player_id;
        model.gem_id = gem_id;
        model.gem_level = gem_level;

        return model;
    }
}
