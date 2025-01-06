package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerDigging {
    public long id;
    public long player_id;
    public int index;
    public int tier;
    public boolean is_digging;
    public Instant complete_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerDigging of(long player_id, int index) {
        var model = new DMPlayerDigging();
        model.player_id = player_id;
        model.index = index;
        model.is_digging = false;
        model.complete_at = Instant.now();
        return model;
    }

    public int compareTo(DMPlayerDigging other) {
        return Integer.compare(this.index, other.index);
    }
}
