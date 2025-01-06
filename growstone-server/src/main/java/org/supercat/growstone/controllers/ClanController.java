package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.events.EventPlayerCreateClan;
import org.supercat.growstone.events.EventPlayerJoinClanRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ClanController implements IMappingController {
    private static final Logger logger = LoggerFactory.getLogger(ClanController.class);

    private final WorldSession worldSession;
    private WorldPlayer player;

    public ClanController(WorldSession session) {
        worldSession = session;
    }

    @Override
    public void setPlayer(WorldPlayer player) {
        this.player = player;
    }

    public Map<PacketType, Consumer<Packet>> handlers() {
        var handlers = new HashMap<PacketType, Consumer<Packet>>();

        handlers.put(PacketType.CreateClanRequest, this::handle_CreateClanRequest);
        handlers.put(PacketType.PlayerClanJoinRequestListRequest, this::handle_PlayerClanJoinRequestListRequest);
        handlers.put(PacketType.PlayerClanJoinRequest, this::handle_PlayerClanJoinRequest);
        handlers.put(PacketType.ClanMemberListRequest, this::handle_ClanMemberListRequest);
        handlers.put(PacketType.GetClanRecommendListRequest, this::handle_GetClanRecommendListRequest);
        handlers.put(PacketType.ClanNoticeChangeRequest, this::handle_ClanNoticeChangeRequest);
        handlers.put(PacketType.ClanIntroductionChangeRequest, this::handle_ClanIntroductionChangeRequest);
        handlers.put(PacketType.ClanInfoRequest, this::handle_ClanInfoRequest);
        handlers.put(PacketType.ClanMemberRoleChangeRequest, this::handle_ClanMemberRoleChangeRequest);
        handlers.put(PacketType.ClanMemberExpulsionRequest, this::handle_ClanMemberExpulsionRequest);
        handlers.put(PacketType.ClanMemberLeaveRequest, this::handle_ClanMemberLeaveRequest);
        handlers.put(PacketType.ClanNameChangeRequest, this::handle_ClanNameChangeRequest);
        handlers.put(PacketType.ClanJoinRequestListRequest, this::handle_ClanJoinRequestListRequest);
        handlers.put(PacketType.ClanJoinRequestReplyRequest, this::handle_ClanJoinRequestReplyRequest);
        handlers.put(PacketType.PlayerDonateClanRequest, this::handle_PlayerDonateClanRequest);
        handlers.put(PacketType.PlayerClanDissolutionRequest, this::handle_PlayerClanDissolution);
        return handlers;
    }

    private void handle_CreateClanRequest(Packet packet) {
        var request = packet.getCreateClanRequest();
        player.topic.publish(new EventPlayerCreateClan(request.getClanName(), packet.getId()));
    }


    private void handle_PlayerClanJoinRequestListRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZPlayerClanJoinRequestListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllRequests(player.clan.getTClanJoinRequestList()));
    }

    private void handle_PlayerClanJoinRequest(Packet packet) {
        var request = packet.getPlayerClanJoinRequest();
        player.topic.publish(new EventPlayerJoinClanRequest(request.getClanId(), packet.getId()));
    }

    private void handle_ClanMemberListRequest(Packet packet) {
        worldSession.sendPacket(packet.getId(), ZClanMemberListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllMembers(player.clan.getTClanMemberList()));
    }

    private void handle_GetClanRecommendListRequest(Packet packet) {
        var request = packet.getGetClanRecommendListRequest();
        worldSession.sendPacket(packet.getId(), ZGetClanRecommendListResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllClans(player.clan.getRecommendedClans(request.getIncludeName())));
    }

    private void handle_ClanNoticeChangeRequest(Packet packet) {
        var request = packet.getClanNoticeChangeRequest();
        var status = player.clan.changeNotice(request.getNotice(), packet.getId());
        if (!StatusCode.isSuccess(status)) {
            worldSession.sendPacket(packet.getId(), ZClanNoticeChangeResponse.newBuilder()
                    .setStatus(status));
        }
    }

    private void handle_ClanIntroductionChangeRequest(Packet packet) {
        var request = packet.getClanIntroductionChangeRequest();
        var status = player.clan.changeIntroduction(request.getIntroduction(), request.getType(), packet.getId());
        if (!StatusCode.isSuccess(status)) {
            worldSession.sendPacket(packet.getId(), ZClanIntroductionChangeResponse.newBuilder()
                    .setStatus(status));
        }
    }

    private void handle_ClanInfoRequest(Packet packet) {
        var request = packet.getClanInfoRequest();
        worldSession.sendPacket(packet.getId(), ZClanInfoResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setClan(player.clan.getTClan(request.getClanId())));
    }

    private void handle_ClanMemberRoleChangeRequest(Packet packet) {
        var request = packet.getClanMemberRoleChangeRequest();
        var status = player.clan.changeClanRole(request.getMemberId(), request.getRole());
        if (!StatusCode.isSuccess(status)) {
            worldSession.sendPacket(packet.getId(), ZClanMemberRoleChangeResponse.newBuilder()
                    .setStatus(status));
        }
    }

    private void handle_ClanMemberExpulsionRequest(Packet packet) {
        var request = packet.getClanMemberExpulsionRequest();
        var status = player.clan.clanMemberExpulsion(request.getMemberId(), packet.getId());
        if (!StatusCode.isSuccess(status)) {
            worldSession.sendPacket(packet.getId(), ZClanMemberExpulsionResponse.newBuilder()
                    .setStatus(status));
        }
    }

    private void handle_ClanMemberLeaveRequest(Packet packet) {
        var status = player.clan.clanMemberLeave();
        worldSession.sendPacket(packet.getId(), ZClanMemberLeaveResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_ClanNameChangeRequest(Packet packet) {
        var request = packet.getClanNameChangeRequest();
        var status = player.clan.clanNameChange(request.getName(), packet.getId());
        if (!StatusCode.isSuccess(status)) {
            worldSession.sendPacket(packet.getId(), ZClanNameChangeResponse.newBuilder()
                    .setStatus(status));
        }
    }

    private void handle_ClanJoinRequestListRequest(Packet packet) {
        var l = new ArrayList<TClanJoinRequestPlayer>();
        int status = player.clan.getClanJoinRequests(l);
        worldSession.sendPacket(packet.getId(), ZClanJoinRequestListResponse.newBuilder()
                .setStatus(status)
                .addAllRequests(l));
    }

    private void handle_ClanJoinRequestReplyRequest(Packet packet) {
        var request = packet.getClanJoinRequestReplyRequest();
        int status = player.clan.clanJoinRequestApply(request.getRequestId(), request.getIsAccept(), packet.getId());
        worldSession.sendPacket(packet.getId(), ZClanJoinRequestReplyResponse.newBuilder()
                .setStatus(status));
    }

    private void handle_PlayerDonateClanRequest(Packet packet) {
        var outResult = new ArrayList<TContentReward>();
        int status = player.clan.donate(outResult);
        worldSession.sendPacket(packet.getId(), ZPlayerDonateClanResponse.newBuilder()
                .setStatus(status));
    }
    private void handle_PlayerClanDissolution(Packet packet) {
        int status = player.clan.clanDissolution();
        worldSession.sendPacket(packet.getId(), ZPlayerDonateClanResponse.newBuilder()
                .setStatus(status));
    }



}
