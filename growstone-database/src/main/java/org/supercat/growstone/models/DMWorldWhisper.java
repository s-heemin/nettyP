package org.supercat.growstone.models;

import java.time.Instant;

public class DMWorldWhisper {
    public long id;
    public long channel_id;
    public long  sender_portrait_icon;
    public String sender_name;
    public long sender_player_id;
    public long receive_player_id;
    public String text;
    public Instant created_at;

    public static DMWorldWhisper of(long channel_id, long receive_player_id, long sender_player_id, String sender_name, long sender_portrait_icon, String text) {
        DMWorldWhisper chat = new DMWorldWhisper();
        chat.channel_id = channel_id;
        chat.receive_player_id = receive_player_id;
        chat.sender_player_id = sender_player_id;
        chat.sender_name = sender_name;
        chat.sender_portrait_icon = sender_portrait_icon;
        chat.text = text;
        return chat;
    }
}
