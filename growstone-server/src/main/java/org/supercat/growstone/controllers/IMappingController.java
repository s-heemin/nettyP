package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.Packet;
import com.supercat.growstone.network.messages.PacketType;
import org.supercat.growstone.player.WorldPlayer;

import java.util.Map;
import java.util.function.Consumer;

public interface IMappingController {
    void setPlayer(WorldPlayer player);

    Map<PacketType, Consumer<Packet>> handlers();
}
