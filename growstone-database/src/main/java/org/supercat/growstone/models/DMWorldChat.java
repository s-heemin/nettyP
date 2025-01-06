package org.supercat.growstone.models;

import java.time.Instant;

public class DMWorldChat {
    public long id;
    public long channel_id;
    public long player_id;
    public String name;
    public long portrait_icon;
    public String text;
    public Instant created_at;

    public static DMWorldChat of(long channelId, long playerId, String name, long portraitIcon, String text) {
        DMWorldChat chat = new DMWorldChat();
        chat.channel_id = channelId;
        chat.player_id = playerId;
        chat.name = name;
        chat.portrait_icon = portraitIcon;
        chat.text = text;
        return chat;
    }
}
