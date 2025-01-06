package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerConditionPackage {
    public long id;
    public long player_id;
    public long package_id;
    public boolean is_complete;
    public Instant expire_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerConditionPackage of(long player_id, long package_id) {
        var model = new DMPlayerConditionPackage();
        model.player_id = player_id;
        model.package_id = package_id;
        model.is_complete = false;
        return model;
    }
}
