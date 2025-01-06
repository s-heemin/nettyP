package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Constants;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DungeonController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(DungeonController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public DungeonController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.DailyDungeonRequest, this::handle_DailyDungeonRequest);
        handlers.put(PacketType.DailyDungeonStartRequest, this::handle_DailyDungeonStartRequest);
        handlers.put(PacketType.DailyDungeonClearRequest, this::handle_DailyDungeonClearRequest);
        handlers.put(PacketType.DailyDungeonViewAdvertiseRequest, this::handle_DailyDungeonViewAdvertiseRequest);

        handlers.put(PacketType.RaidDungeonRequest, this::handle_RaidDungeonRequest);
        handlers.put(PacketType.RaidDungeonStartRequest, this::handle_RaidDungeonStartRequest);
        handlers.put(PacketType.RaidDungeonClearRequest, this::handle_RaidDungeonClearRequest);
        handlers.put(PacketType.RaidDungeonViewAdvertiseRequest, this::handle_RaidDungeonViewAdvertiseRequest);
        handlers.put(PacketType.CooperativeRaidDungeonRequest, this::handle_CooperativeRaidDungeonRequest);
        handlers.put(PacketType.CompetitiveRaidDungeonRequest, this::handle_CompetitiveRaidDungeonRequest);
        handlers.put(PacketType.CompetitiveRaidDungeonRankRequest, this::handle_CompetitiveRaidDungeonRankRequest);

        handlers.put(PacketType.TowerRequest, this::handle_TowerRequest);
        handlers.put(PacketType.TowerStartRequest, this::handle_TowerStartRequest);
        handlers.put(PacketType.TowerClearRequest, this::handle_TowerClearRequest);


        return handlers;
    }

    private void handle_DailyDungeonRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZDailyDungeonResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllDungeons(player.dailyDungeon.getTDailyDungeon()));
    }

    private void handle_DailyDungeonStartRequest(Packet packet) {
        var request = packet.getDailyDungeonStartRequest();
        int status = player.dailyDungeon.isEnableStartDungeon(request.getId(), request.getStageId());
        worldSession.sendPacket(packet.getId(), ZDailyDungeonStartResponse.newBuilder().setStatus(status));
    }

    private void handle_DailyDungeonClearRequest(Packet packet) {
        var request = packet.getDailyDungeonClearRequest();
        var rewards = new ArrayList<TContentReward>();
        var out = TDailyDungeon.newBuilder();
        int status = player.dailyDungeon.clearDungeon(request.getId(), request.getStageId(), request.getUseTicketCount(), out, rewards);
        worldSession.sendPacket(packet.getId(), ZDailyDungeonClearResponse.newBuilder()
                .setStatus(status)
                .setDailyDungeon(out)
                .addAllRewards(rewards));
    }

    private void handle_DailyDungeonViewAdvertiseRequest(Packet packet) {
        var request = packet.getDailyDungeonViewAdvertiseRequest();
        var out = TDailyDungeon.newBuilder();
        int status = player.dailyDungeon.viewAdvertise(request.getId(), request.getStageId(), out);
        worldSession.sendPacket(packet.getId(), ZDailyDungeonViewAdvertiseResponse.newBuilder()
                .setStatus(status)
                .setDailyDungeon(out));
    }

    private void handle_RaidDungeonRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZRaidDungeonResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllRaidDungeon(player.raidDungeon.getTRaidDungeon()));
    }

    private void handle_RaidDungeonStartRequest(Packet packet) {
        var request = packet.getRaidDungeonStartRequest();
        int status = player.raidDungeon.isEnableStartRaid(request.getId());
        worldSession.sendPacket(packet.getId(), ZRaidDungeonStartResponse.newBuilder().setStatus(status));
    }

    private void handle_RaidDungeonClearRequest(Packet packet) {
        var request = packet.getRaidDungeonClearRequest();
        var rewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();
        int status = player.raidDungeon.clearRaidDungeon(request.getId(), request.getStageId(), request.getScore(), rewards, out);
        worldSession.sendPacket(packet.getId(), ZRaidDungeonClearResponse.newBuilder()
                .setStatus(status)
                .setRaidDungeon(out)
                .addAllRewards(rewards));
    }

    private void handle_RaidDungeonViewAdvertiseRequest(Packet packet) {
        var request = packet.getRaidDungeonViewAdvertiseRequest();
        var out = TRaidDungeon.newBuilder();
        int status = player.raidDungeon.viewAdvertise(request.getId(), out);
        worldSession.sendPacket(packet.getId(), ZRaidDungeonViewAdvertiseResponse.newBuilder()
                .setStatus(status)
                .setRaidDungeon(out));
    }
    private void handle_CooperativeRaidDungeonRequest(Packet packet) {
       var request = packet.getCooperativeRaidDungeonRequest();
        worldSession.sendPacket(packet.getId(), ZCooperativeRaidDungeonResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setCooperativeRaidInfo(player.raidDungeon.getCooperativeRaidInfo(request.getId())));
    }

    private void handle_CompetitiveRaidDungeonRequest(Packet packet) {
        var request = packet.getCompetitiveRaidDungeonRequest();
        worldSession.sendPacket(packet.getId(), ZCompetitiveRaidDungeonResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setCompetitiveRaidInfo(player.raidDungeon.getCompetitiveRaidInfo(request.getId())));
    }

    private void handle_CompetitiveRaidDungeonRankRequest(Packet packet) {
        var request = packet.getCompetitiveRaidDungeonRankRequest();
        worldSession.sendPacket(packet.getId(), ZCompetitiveRaidDungeonRankResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllPlayers(player.raidDungeon.getRankingList(request.getDataId(), request.getStartRank()))
                .setNextRank(request.getStartRank() + Constants.NEXT_RAID_RANKING_COUNT));
    }

    private void handle_TowerRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZTowerResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllTower(player.tower.getTTower()));
    }

    private void handle_TowerStartRequest(Packet packet) {
        var request = packet.getTowerStartRequest();
        int status = player.tower.isEnableStartTower(request.getId(), request.getStageId());
        worldSession.sendPacket(packet.getId(), ZTowerStartResponse.newBuilder().setStatus(status));
    }

    private void handle_TowerClearRequest(Packet packet) {
       var request = packet.getTowerClearRequest();
        var rewards = new ArrayList<TContentReward>();
        var out = TTower.newBuilder();
        int status = player.tower.clearTower(request.getId(), request.getStageId(), out, rewards);
        worldSession.sendPacket(packet.getId(), ZTowerClearResponse.newBuilder()
                .setStatus(status)
                .setTower(out)
                .addAllRewards(rewards));
    }
}
