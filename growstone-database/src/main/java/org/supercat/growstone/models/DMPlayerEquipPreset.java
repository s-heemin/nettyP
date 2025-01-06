package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerEquipPreset {
    public long id;
    public long player_id;
    public int type;
    public int preset_index;
    public int equip_index;
    public long data_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerEquipPreset of(long player_id, int type, int preset_index, int equip_index, long data_id) {
        var model = new DMPlayerEquipPreset();
        model.player_id = player_id;
        model.type = type;
        model.preset_index = preset_index;
        model.equip_index = equip_index;
        model.data_id = data_id;;

        return model;
    }
}
