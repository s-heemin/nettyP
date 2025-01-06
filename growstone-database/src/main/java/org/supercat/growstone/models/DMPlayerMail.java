package org.supercat.growstone.models;

import org.supercat.growstone.JsonConverter;

import java.time.Instant;
import java.util.List;

public class DMPlayerMail {
    public long id;
    public long player_id;
    public int mail_type;
    public String rewards;
    public boolean is_read;
    public String sender;
    public Instant expire_at;
    public Instant updated_at;
    public Instant created_at;

    public static DMPlayerMail of(long player_id, int mail_type, String sender ,String rewards, Instant expire_at) {
        var model = new DMPlayerMail();
        model.player_id = player_id;
        model.mail_type = mail_type;
        model.rewards = rewards;
        model.is_read = false;
        model.sender = sender;
        model.expire_at = expire_at;
        return model;
    }

    public static DMPlayerMail of(long player_id, int mail_type, String sender , Instant expire_at) {
        var model = new DMPlayerMail();
        model.player_id = player_id;
        model.mail_type = mail_type;
        model.rewards = JsonConverter.to(List.of());
        model.is_read = false;
        model.sender = sender;
        model.expire_at = expire_at;
        return model;
    }
}
