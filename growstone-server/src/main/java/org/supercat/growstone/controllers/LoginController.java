package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LoginController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public LoginController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.LoginRequest, this::handle_LoginRequest);
        handlers.put(PacketType.LogoutRequest, this::handle_LogoutRequest);
        handlers.put(PacketType.Ping, this::handle_Ping);
        return handlers;
    }

    private void handle_Ping(Packet packet) {
        this.player.setLastPingAt();
        worldSession.sendPacket(packet.getId(), ZPing.newBuilder());
    }

    private void handle_LoginRequest(Packet packet) {
       worldSession.login(packet.getId(), packet.getLoginRequest());
    }

    private void handle_LogoutRequest(Packet packet) {
        player.logout(false);

        worldSession.sendPacket(packet.getId(), ZLogoutResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setType(ZLogout.Type.DISCONNECT));
    }
}
