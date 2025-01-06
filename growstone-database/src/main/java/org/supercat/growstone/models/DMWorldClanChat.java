package org.supercat.growstone.models;

import java.time.Instant;

public class DMWorldClanChat {
    public long id;
    public long channel_id;
    public long player_id;
    public long  clan_id;
    public String name;
    public long portrait_icon;
    public String text;
    public Instant created_at;

    public static DMWorldClanChat of(long channelId, long playerId, long clanId, String name, long portraitIcon, String text) {
        DMWorldClanChat chat = new DMWorldClanChat();
        chat.channel_id = channelId;
        chat.player_id = playerId;
        chat.clan_id = clanId;
        chat.name = name;
        chat.portrait_icon = portraitIcon;
        chat.text = text;
        return chat;
    }
}
