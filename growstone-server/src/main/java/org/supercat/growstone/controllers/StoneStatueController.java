package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Out;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.player.WorldPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StoneStatueController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(StoneStatueController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public StoneStatueController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();
        handlers.put(PacketType.StoneStatueInfoRequest, this::handle_StoneStatueInfoRequest);
        handlers.put(PacketType.StoneStatueEnchantSlotLockRequest, this::handle_StoneStatueEnchantSlotLockRequest);
        handlers.put(PacketType.StoneStatueEnchantChangeSafeGradeRequest, this::handle_StoneStatueEnchantChangeSafeGradeRequest);
        handlers.put(PacketType.StoneStatueEnchantRequest, this::handle_StoneStatueEnchantRequest);
        handlers.put(PacketType.StoneStatueGemLevelUpRequest, this::handle_StoneStatueGemLevelUpRequest);
        handlers.put(PacketType.StoneStatueGemLimitLevelUpRequest, this::handle_StoneStatueGemLimitLevelUpRequest);
        handlers.put(PacketType.StoneStatueAvatarEquipRequest, this::handle_StoneStatueAvatarEquipRequest);

        return handlers;
    }

    private void handle_StoneStatueInfoRequest(Packet packet) {
        var p = packet.getStoneStatueInfoRequest();

        var tStoneStatue = player.stoneStatue.getTStoneStatue();
        var tStoneStatueEnchants = player.stoneStatueEnchant.getAllTStoneStatueEnchant();
        var stoneStatueAvatarList = player.stoneStatueAvatar.getStoneStatueAvatarIds();
        var stoneStatueGems = player.stoneStatueGem.getAllTStoneStatueGem();

        worldSession.sendPacket(packet.getId(), ZStoneStatueInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setStoneStatue(tStoneStatue)
                .addAllStoneStatueEnchants(tStoneStatueEnchants)
                .addAllStoneStatueAvatarIds(stoneStatueAvatarList)
                .addAllStoneStatueGems(stoneStatueGems));
    }

    private void handle_StoneStatueEnchantSlotLockRequest(Packet packet) {
        var p = packet.getStoneStatueEnchantSlotLockRequest();

        Out<TStoneStatueEnchant> outEnchant = Out.of(null);
        var status = player.stoneStatueEnchant.lockEnchantSlot(p.getSlotId(), p.getIsLocked(), outEnchant);
        var builder = ZStoneStatueEnchantSlotLockResponse.newBuilder()
                .setStatus(status);

        if (StatusCode.isSuccess(status)) {
            builder.setStoneStatueEnchant(outEnchant.get());
        }

        worldSession.sendPacket(packet.getId(), builder);
    }

    private void handle_StoneStatueEnchantChangeSafeGradeRequest(Packet packet) {
        var p = packet.getStoneStatueEnchantChangeSafeGradeRequest();

        var status = player.stoneStatue.changeSafeGrade(p.getTier());
        worldSession.sendPacket(packet.getId(), ZStoneStatueEnchantChangeSafeGradeResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_StoneStatueEnchantRequest(Packet packet) {
        var p = packet.getStoneStatueEnchantRequest();

        Out<TStoneStatueEnchant> outEnchant = Out.of(null);
        Out<Integer> outLevel = Out.of(0);
        Out<Integer> outExp = Out.of(0);
        var status = player.stoneStatueEnchant.enchant(outEnchant, outLevel, outExp);
        var builder = ZStoneStatueEnchantResponse.newBuilder()
                .setStatus(status);
        if (StatusCode.isSuccess(status)) {
            builder.setStoneStatueEnchant(outEnchant.get());
            builder.setEnchantLevel(outLevel.get());
            builder.setEnchantExp(outExp.get());
        }

        worldSession.sendPacket(packet.getId(), builder);
    }

    private void handle_StoneStatueGemLevelUpRequest(Packet packet) {
        var p = packet.getStoneStatueGemLevelUpRequest();

        Out<TStoneStatueGem> outGem = Out.of(null);
        var status = player.stoneStatueGem.tryGemLevelUp(p.getGemId(), outGem);
        var builder = ZStoneStatueGemLevelUpResponse.newBuilder()
                .setStatus(status);
        if (StatusCode.isSuccess(status)) {
            builder.setGem(outGem.get());
        }

        worldSession.sendPacket(packet.getId(), builder);
    }

    private void handle_StoneStatueGemLimitLevelUpRequest(Packet packet) {
        var p = packet.getStoneStatueGemLimitLevelUpRequest();

        Out<Integer> outGemLevel = Out.of(0);
        var status = player.stoneStatueGem.tryGemLimitLevelUp(outGemLevel);

        var builder = ZStoneStatueGemLimitLevelUpResponse.newBuilder()
                .setStatus(status);

        if (StatusCode.isSuccess(status)) {
            builder.setLevel(outGemLevel.get());
        }

        worldSession.sendPacket(packet.getId(), builder);
    }

    public void handle_StoneStatueAvatarEquipRequest(Packet packet) {
        var p = packet.getStoneStatueAvatarEquipRequest();

        var status = player.stoneStatue.equipAvatar(p.getAvatarId());
        worldSession.sendPacket(packet.getId(), ZStoneStatueAvatarEquipResponse.newBuilder()
                .setStatus(status));
    }
}
