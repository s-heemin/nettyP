package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.TChatHistory;
import com.supercat.growstone.network.messages.ZChat;
import com.supercat.growstone.network.messages.ZChatNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Server;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.World;
import org.supercat.growstone.models.DMWorldChat;
import org.supercat.growstone.models.DMWorldClanChat;
import org.supercat.growstone.models.DMWorldWhisper;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorldChatComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldChatComponent.class);

    public void chat(WorldPlayer player, ZChat.Type type, String message, long targetPlayerId) {
        if(type == ZChat.Type.WORLD) {
            var model = DMWorldChat.of(Server.channelId, player.getId(), player.getName(), player.getPortraitIcon(), message);
            SDB.dbContext.worldChat.insert(model);

            sendChat(type, model.name, message, player.getPortraitIcon(), Instant.now(), 0);
        } else if(type == ZChat.Type.CLAN) {
            if(player.getClanId() <= 0) {
                return;
            }

            var model = DMWorldClanChat.of(Server.channelId, player.getId(), player.getClanId(), player.getName(), player.getPortraitIcon(), message);
            SDB.dbContext.worldClanChat.insert(model);

            sendChat(type, model.name, message, player.getPortraitIcon(), Instant.now(), model.clan_id);
        } else if (type == ZChat.Type.WHISPER) {
            if (targetPlayerId <= 0) {
                return;
            }

            var model = DMWorldWhisper.of(Server.channelId, targetPlayerId, player.getId(),
                    player.getName(), player.getPortraitIcon(), message);
            SDB.dbContext.worldWhisper.insert(model);
            sendWhisper(model);
        }
    }

    private void sendWhisper(DMWorldWhisper model) {
        var player = World.INSTANCE.worldPlayers.getPlayer(model.receive_player_id);
        if(Objects.isNull(player)) {
            return;
        }

        player.sendPacket(0L, ZChatNotify.newBuilder()
                .setSourceName(model.sender_name)
                .setText(model.text)
                .setSourcePortraitId(model.sender_portrait_icon)
                .setTime(model.created_at.getEpochSecond()));
    }

    private void sendChat(ZChat.Type type, String name, String text, long playerPortraitIconId, Instant createAt, long clanId) {
        if(type == ZChat.Type.WORLD) {
            World.INSTANCE.worldPlayers.getPlayers().forEach(player -> {
                player.sendPacket(0L, ZChatNotify.newBuilder()
                        .setSourceName(name)
                        .setText(text)
                        .setSourcePortraitId(playerPortraitIconId)
                        .setTime(createAt.getEpochSecond()));
            });
        } else if(type == ZChat.Type.CLAN) {
            var clan = World.INSTANCE.worldClan.getClan(clanId);
            if(Objects.isNull(clan)) {
                return;
            }

            var members = clan.getMembers();
            for(var member : members) {
                var player = World.INSTANCE.worldPlayers.getPlayer(member.player_id);
                if(Objects.isNull(player)) {
                    continue;
                }

                player.sendPacket(0L, ZChatNotify.newBuilder()
                        .setSourceName(name)
                        .setText(text)
                        .setSourcePortraitId(playerPortraitIconId)
                        .setTime(createAt.getEpochSecond()));
            }
        }
    }

    public List<TChatHistory> getChatHistories(ZChat.Type type, WorldPlayer player, long targetId) {
        var now = Instant.now();
        var results = new ArrayList<TChatHistory>();
        if(type == ZChat.Type.WORLD) {
            results.addAll(SDB.dbContext.worldChat.getAll(Server.channelId, now).stream()
                    .map(x -> TBuilderOf.buildOf(x.player_id, x.name, x.portrait_icon, x.text, x.created_at))
                    .collect(Collectors.toList()));
        } else if(type == ZChat.Type.CLAN) {
            results.addAll(SDB.dbContext.worldClanChat.getAll(Server.channelId, targetId, now).stream()
                    .map(x -> TBuilderOf.buildOf(x.player_id, x.name, x.portrait_icon, x.text, x.created_at))
                    .collect(Collectors.toList()));
        } else if(type == ZChat.Type.WHISPER) {
            results.addAll(SDB.dbContext.worldWhisper.getAll(Server.channelId, player.getId(), targetId, now).stream()
                    .map(x -> TBuilderOf.buildOf(x.sender_player_id, x.sender_name, x.sender_portrait_icon, x.text, x.created_at))
                    .collect(Collectors.toList()));
        }

        return results;
    }
}
