package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.player.WorldPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChatController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public ChatController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.ChatRequest, this::handle_ChatRequest);
        handlers.put(PacketType.WhisperRequest, this::handle_WhisperRequest);
        handlers.put(PacketType.ChatHistoriesRequest, this::handle_ChatHistoriesRequest);


        return handlers;
    }

    private void handle_ChatRequest(Packet packet) {
        var request = packet.getChatRequest();
        var chat = request.getChat();
        World.INSTANCE.chat.chat(player, chat.getType(), chat.getText(), 0);

        worldSession.sendPacket(packet.getId(), ZChatResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }

    private void handle_WhisperRequest(Packet packet) {
        var request = packet.getWhisperRequest();
        var chat = request.getWhisper();
        World.INSTANCE.chat.chat(player, ZChat.Type.WHISPER, chat.getText(), chat.getTargetPlayerId());
        worldSession.sendPacket(packet.getId(), ZWhisperResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }

    private void handle_ChatHistoriesRequest(Packet packet) {
        var request = packet.getChatHistoriesRequest();
        var results = World.INSTANCE.chat.getChatHistories(request.getType(), player, request.getTargetId());
        worldSession.sendPacket(packet.getId(), ZChatHistoriesResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllChats(results));
    }



}
