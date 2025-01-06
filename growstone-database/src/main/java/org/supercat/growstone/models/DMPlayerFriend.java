package org.supercat.growstone.models;

import java.time.Instant;

public class DMPlayerFriend {
    public long id;
    public long player_id;
    public long target_player_id;
    public int state;
    public Instant receive_gift_expire_at;
    public Instant send_gift_expire_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerFriend of(long player_id, long target_player_id, int state) {
        var model = new DMPlayerFriend();
        model.player_id = player_id;
        model.target_player_id = target_player_id;
        model.state = state;
        model.receive_gift_expire_at = Instant.now().minus(1, java.time.temporal.ChronoUnit.DAYS);
        model.send_gift_expire_at = Instant.now().minus(1, java.time.temporal.ChronoUnit.DAYS);
        return model;
    }
}
