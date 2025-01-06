package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.WorldSession;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WorldController implements IMappingController {

    private static final Logger logger = LoggerFactory.getLogger(WorldController.class);

    private final WorldSession worldSession;

    private WorldPlayer player;

    public WorldController(WorldSession session) {
        worldSession = session;
    }


    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();
        handlers.put(PacketType.CheatRequest, this::handle_CheatCommand);

        // 스테이지 저장
        handlers.put(PacketType.StageClearSaveRequest, this::handle_StageClearSaveRequest);

        // 이름 변경
        handlers.put(PacketType.ChangePlayerNameRequest, this::handle_ChangePlayerNameRequest);

        // 초상화 아이콘 변경
        handlers.put(PacketType.ChangePortraitIconRequest, this::handle_ChangePortraitIconRequest);

        // 아바타 변경
        handlers.put(PacketType.ChangeEquipAvatarRequest, this::handle_ChangeEquipAvatarRequest);

        // 캐릭터 성장 스탯
        handlers.put(PacketType.CharacterStatGrowthRequest, this::handle_CharacterStatGrowthRequest);
        handlers.put(PacketType.CharacterStatGrowthLevelUpRequest, this::handle_CharacterStatGrowthLevelUpRequest);
        // 스탯 전달
        handlers.put(PacketType.StatsRequest, this::handle_StatsRequest);

        // 메일 관련
        handlers.put(PacketType.MailRequest, this::handle_MailRequest);
        handlers.put(PacketType.MailReadRequest, this::handle_MailReadRequest);

        // 아이템 동기화
        handlers.put(PacketType.SyncItemCountRequest, this::handle_SyncItemCountRequest);

        handlers.put(PacketType.StageBossGaugeOnRequest, this::handle_StageBossGaugeOnRequest);

        handlers.put(PacketType.AttackPowerRankRequest, this::handle_AttackPowerRankRequest);

        handlers.put(PacketType.GetPlayerDetailInfoRequest, this::handle_GetPlayerDetailInfoRequest);
        handlers.put(PacketType.PlayerConditionPackageInfoRequest, this::handle_PlayerConditionPackageInfoRequest);


        handlers.put(PacketType.DuelRequest, this::handle_DuelRequest);
        handlers.put(PacketType.PlayerDuelHistoriesRequest, this::handle_PlayerDuelHistoriesRequest);
        handlers.put(PacketType.PlayerDuelHistorySaveRequest, this::handle_PlayerDuelHistorySaveRequest);

        handlers.put(PacketType.PlayerAchievementRequest, this::handle_PlayerAchievementRequest);
        handlers.put(PacketType.PlayerAchievementRewardRequest, this::handle_PlayerAchievementRewardRequest);

        handlers.put(PacketType.PlayerQuestRequest, this::handle_PlayerQuestRequest);
        handlers.put(PacketType.PlayerQuestRewardRequest, this::handle_PlayerQuestRewardRequest);

        handlers.put(PacketType.PlayerAdvertiseInfoRequest, this::handle_PlayerAdvertiseInfoRequest);

        return handlers;
    }

    public void handle_CheatCommand(Packet packet) {
        var request = packet.getCheatRequest();

        int status = player.cheat.cheatShell(request.getCommand());
        player.sendPacket(packet.getId(), ZCheatResponse.newBuilder()
                .setStatus(status)
                .setCommand(request.getCommand())
        );
    }

    private void handle_StageClearSaveRequest(Packet packet) {
        var request = packet.getStageClearSaveRequest();
        var nextStage = TNextStageInfo.newBuilder();
        int status = player.saveStage(request.getStageGroup(), request.getStage(), nextStage);
        player.sendPacket(packet.getId(), ZStageClearSaveResponse.newBuilder()
                .setStatus(status)
                .setNextStageInfo(nextStage));
    }

    private void handle_ChangePlayerNameRequest(Packet packet) {
        var request = packet.getChangePlayerNameRequest();
        var response = ZChangePlayerNameResponse.newBuilder();
        int status = player.changeName(request.getName(), response);
        player.sendPacket(packet.getId(), response
                .setStatus(status));
    }

    private void handle_ChangePortraitIconRequest(Packet packet) {
        var request = packet.getChangePortraitIconRequest();
        int status = player.portraitIcon.changePortraitIcon(request.getPortraitIconId());
        player.sendPacket(packet.getId(), ZChangePortraitIconResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_ChangeEquipAvatarRequest(Packet packet) {
        var request = packet.getChangeEquipAvatarRequest();
        int status = player.avatar.changeAvatar(request.getAvatarId());
        player.sendPacket(packet.getId(), ZChangeEquipAvatarResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_StatsRequest(Packet packet) {
       player.sendPacket(packet.getId(), ZStatsResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllStats(player.stat.getTStats()));
    }

    private void handle_MailRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZMailResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllMails(player.mail.getMails(Instant.now())));
    }

    private void handle_MailReadRequest(Packet packet) {
        var request = packet.getMailReadRequest();
        var resultMails = new ArrayList<TMail>();
        player.mail.readMails(request.getMailIdsList(), resultMails);
        player.sendPacket(packet.getId(), ZMailReadResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllMail(resultMails));
    }

    private void handle_SyncItemCountRequest(Packet packet) {
        var request = packet.getSyncItemCountRequest();
        player.categoryFilter.syncItem(request.getNeedSyncItemsList());
        player.sendPacket(packet.getId(), ZSyncItemCountResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }

    private void handle_CharacterStatGrowthRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZCharacterStatGrowthResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setStatGrowthPage(player.statGrowth.getTStatGrowthPage()));
    }

    private void handle_CharacterStatGrowthLevelUpRequest(Packet packet) {
        var request = packet.getCharacterStatGrowthLevelUpRequest();
        var outStatGrowthPage = TStatGrowthPage.newBuilder();
        int status = player.statGrowth.levelUp(request.getLevelCount(), request.getTargetType(), outStatGrowthPage);
        player.sendPacket(packet.getId(), ZCharacterStatGrowthLevelUpResponse.newBuilder()
                .setStatus(status)
                .setStatGrowthPage(outStatGrowthPage));
    }

    private void handle_StageBossGaugeOnRequest(Packet packet) {
        player.saveOnBossGauge();
        player.sendPacket(packet.getId(), ZStageBossGaugeOnResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }

    private void handle_AttackPowerRankRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZAttackPowerRankResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setMyRank(World.INSTANCE.worldRank.getPlayerRankInfo(player.getId()))
                .addAllPlayers(World.INSTANCE.worldRank.getRanks()));
    }

    private void handle_GetPlayerDetailInfoRequest(Packet packet) {
        var request = packet.getGetPlayerDetailInfoRequest();
        player.sendPacket(packet.getId(), ZGetPlayerDetailInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setPlayerDetailInfo(World.INSTANCE.worldPlayers.getPlayerDetailInfo(request.getPlayerId())));
    }

    private void handle_PlayerConditionPackageInfoRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZPlayerConditionPackageInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllConditionPackages(player.conditionPackage.getTConditionPackage()));
    }

    private void handle_DuelRequest(Packet packet) {
        var request = packet.getDuelRequest();
        var response = ZDuelResponse.newBuilder();
        int status = World.INSTANCE.worldDuel.getDuelPlayerInfo(request.getPlayerId(), response);
        player.sendPacket(packet.getId(), response
                .setStatus(status));
    }

    private void handle_PlayerDuelHistoriesRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZPlayerDuelHistoriesResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllHistories(World.INSTANCE.worldDuel.getAllHistories(player.getId())));
    }

    private void handle_PlayerDuelHistorySaveRequest(Packet packet) {
        var request = packet.getPlayerDuelHistorySaveRequest();
        var history = World.INSTANCE.worldDuel.addDuelHistory(player.getId(), request.getHistory());
        player.sendPacket(packet.getId(), ZPlayerDuelHistorySaveResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setResult(history));
    }

    private void handle_PlayerAchievementRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZPlayerAchievementResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setAchievements(player.achievement.getTAchievements()));
    }

    private void handle_PlayerAchievementRewardRequest(Packet packet) {
        var request = packet.getPlayerAchievementRewardRequest();
        var out = new ArrayList<TContentReward>();
        player.achievement.reward(request.getAchievementIdList(), out);
        player.sendPacket(packet.getId(), ZPlayerAchievementRewardResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllRewards(out));
    }

    private void handle_PlayerQuestRequest(Packet packet) {
        player.sendPacket(packet.getId(), ZPlayerQuestResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setQuest(player.quest.getTQuest()));
    }

    private void handle_PlayerQuestRewardRequest(Packet packet) {
        var outRewards = new ArrayList<TContentReward>();
        player.quest.reward(outRewards);
        player.sendPacket(packet.getId(), ZPlayerQuestRewardResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllRewards(outRewards));
    }

    private void handle_PlayerAdvertiseInfoRequest(Packet packet) {
        var types = packet.getPlayerAdvertiseInfoRequest().getTypesList();

        player.sendPacket(packet.getId(), ZPlayerAdvertiseInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllAdvertiseInfos(player.advertise.getAdvertiseInfos(types)));
    }
}