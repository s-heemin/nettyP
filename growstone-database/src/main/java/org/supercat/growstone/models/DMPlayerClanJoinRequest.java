package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerClanJoinRequest {
    public long id;
    public long player_id;
    public long clan_id;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerClanJoinRequest of(long playerId, long clanId) {
        var model = new DMPlayerClanJoinRequest();
        model.player_id = playerId;
        model.clan_id = clanId;

        return model;
    }
}
