package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CollectionController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public CollectionController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.CollectionRequest, this::handle_CollectionRequest);
        handlers.put(PacketType.CollectionLevelUpRequest, this::handle_CollectionLevelUpRequest);

        return handlers;
    }

    private void handle_CollectionRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZCollectionResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllCollections(player.collection.getCollections()));
    }

    private void handle_CollectionLevelUpRequest(Packet packet) {
        var request = packet.getCollectionLevelUpRequest();
        var outCollection = new ArrayList<TCollection>();
        int status = player.collection.levelUp(request.getTargetsList(), outCollection);
        worldSession.sendPacket(packet.getId(),
                ZCollectionLevelUpResponse.newBuilder()
                        .setStatus(status)
                        .addAllCollections(outCollection));
    }

}
