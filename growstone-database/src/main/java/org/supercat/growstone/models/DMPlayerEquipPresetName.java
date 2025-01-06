package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerEquipPresetName {
    public long id;
    public long player_id;
    public int index;
    public String name;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerEquipPresetName of(long player_id, int index, String name) {
        var model = new DMPlayerEquipPresetName();
        model.player_id = player_id;
        model.index = index;
        model.name = name;
        return model;
    }
}
