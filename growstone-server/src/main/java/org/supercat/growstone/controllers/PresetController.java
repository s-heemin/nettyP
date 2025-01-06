package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PresetController implements IMappingController {

    private static final Logger logger = LoggerFactory.getLogger(PresetController.class);

    private final WorldSession worldSession;

    private WorldPlayer player;

    public PresetController(WorldSession session) {
        worldSession = session;
    }


    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.EquipPresetRequest, this::handle_EquipPresetRequest);
        handlers.put(PacketType.EquipPresetEquipRequest, this::handle_EquipPresetEquipRequest);
        handlers.put(PacketType.EquipPresetUnEquipRequest, this::handle_EquipPresetUnEquipRequest);
        handlers.put(PacketType.EquipPresetNameChangeRequest, this::handle_EquipPresetNameChangeRequest);
        handlers.put(PacketType.EquipPresetIndexChangeRequest, this::handle_EquipPresetIndexChangeRequest);
        return handlers;
    }

    private void handle_EquipPresetRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZEquipPresetResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllPresets(player.preset.getTPresets()));
    }

    private void handle_EquipPresetEquipRequest(Packet packet) {
        var request = packet.getEquipPresetEquipRequest();
        var out = TEquipPresetsByType.newBuilder();
        int status = player.preset.presetEquip(request.getPresetIndex(), request.getType(), request.getEquipList(), out);
        player.sendPacket(packet.getId(), ZEquipPresetEquipResponse.newBuilder()
                .setStatus(status)
                .setEquips(out));
    }

    private void handle_EquipPresetUnEquipRequest(Packet packet) {
        var request = packet.getEquipPresetUnEquipRequest();
        var out = TEquipPresetsByType.newBuilder();
        int status = player.preset.presetUnEquip(request.getPresetIndex(), request.getType(), request.getIndex(), out);
        player.sendPacket(packet.getId(), ZEquipPresetUnEquipResponse.newBuilder()
                .setStatus(status)
                .setEquips(out));
    }
    private void handle_EquipPresetNameChangeRequest(Packet packet) {
        var request = packet.getEquipPresetNameChangeRequest();
        var out = TEquipPresetName.newBuilder();
        int status = player.preset.presetNameChange(request.getIndex(), request.getName(), out);
        player.sendPacket(packet.getId(), ZEquipPresetNameChangeResponse.newBuilder()
                .setStatus(status)
                .setName(out));
    }

    private void handle_EquipPresetIndexChangeRequest(Packet packet) {
        var request = packet.getEquipPresetIndexChangeRequest();
        int status = player.preset.presetIndexChange(request.getIndex());
        player.sendPacket(packet.getId(), ZEquipPresetIndexChangeResponse.newBuilder()
                .setStatus(status));
    }

}
