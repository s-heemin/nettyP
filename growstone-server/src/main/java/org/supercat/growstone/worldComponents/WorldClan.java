package org.supercat.growstone.worldComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMClan;
import org.supercat.growstone.models.DMClanMember;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.rules.ClanRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WorldClan {
    private static final Logger logger = LoggerFactory.getLogger(WorldClan.class);
    private DMClan dmClan;
    private ConcurrentHashMap<Long, DMClanMember> members = new ConcurrentHashMap<>();
    private ArrayDeque<ClanEvent> eventQueue = new ArrayDeque<>();
    private final Map<ClanEventType, Consumer<? extends ClanEvent>> handlers = new HashMap<>();

    private WorldClan(DMClan clan) {
        this.dmClan = clan;
        registerHandlers();
        initialize();
    }

    private void initialize() {
        members = SDB.dbContext.clanMember.getAll(dmClan.id).stream()
                .collect(Collectors.toMap(x -> x.player_id, x -> x, (x, y) -> x, ConcurrentHashMap::new));
    }

    private void registerHandlers() {
        Map<ClanEventType, Consumer<? extends ClanEvent>> initialHandlers = new HashMap<>();
        initialHandlers.put(ClanEventType.REQUEST_JOIN_CLAN, event -> handle_RequestJoinClan((EventRequestJoinClan) event));
        initialHandlers.put(ClanEventType.CHANGE_NOTICE, event -> handle_ChangeClanNotice((EventChangeClanNotice) event));
        initialHandlers.put(ClanEventType.CHANGE_INTRODUCTION, event -> handle_ChangeClanIntroduction((EventChangeClanIntroduction) event));
        initialHandlers.put(ClanEventType.EXPULSION_CLAN_MEMBER, event -> handle_ClanMemberExpulsion((EventClanMemberExpulsion) event));
        initialHandlers.put(ClanEventType.CHANGE_CLAN_NAME, event -> handle_EventChangeClanName((EventChangeClanName) event));
        initialHandlers.put(ClanEventType.REPLY_JOIN_CLAN, event -> handle_EventReplyJoinClanRequest((EventReplyJoinClanRequest) event));
        initialHandlers.put(ClanEventType.CONTRIBUTION, event -> handle_EventClanContribute((EventClanContribute) event));
        initialHandlers.put(ClanEventType.CHANGE_CLAN_LEADER, event -> handle_EventChangeClanLeader((EventChangeClanMaster) event));
        initialHandlers.put(ClanEventType.DISSOLUTION, event -> handle_EventDissolution((EventClanDissolution) event));

        handlers.putAll(initialHandlers);
    }

    private void handleEvent(ClanEvent event) {
        Consumer<? extends ClanEvent> handler = handlers.get(event.type);
        if (handler != null) {
            @SuppressWarnings("unchecked")
            Consumer<ClanEvent> specificHandler = (Consumer<ClanEvent>) handler;
            specificHandler.accept(event);
        }
    }

    public <T extends ClanEvent> void addEvent(T event) {
        eventQueue.add(event);
    }

    public void update() {
        if (dmClan.state == ZClan.State.DISSOLUTION.getNumber()) {
            return;
        }

        ClanEvent event;
        while ((event = eventQueue.poll()) != null) {
            handleEvent(event);
        }

        // 클랜장 이전 체크
        if (Instant.now().isAfter(dmClan.master_last_logout_at.plus(GameData.CLAN.CLAN_MASTER_DISCONNECT_DAY, ChronoUnit.DAYS))) {
            addEvent(new EventChangeClanMaster());
        }
    }

    public void saveMasterLogoutAt() {
        dmClan.master_last_logout_at = Instant.now();
        SDB.dbContext.clan.updateMasterLastLogoutAt(dmClan);
    }

    public static WorldClan of(DMClan clan) {
        return new WorldClan(clan);
    }

    public String getName() {
        return dmClan.name;
    }

    public DMClan getModel() {
        return dmClan;
    }

    public int memberSize() {
        return members.size();
    }

    public long getClanId() {
        return dmClan.id;
    }

    public int getClanRank() {
        int nowYmd = UtcZoneDateTime.getYmd();
        return (int) SRedis.INSTANCE.rank.clanAttackRank.getRank(nowYmd, dmClan.id);
    }

    public TClan getTClan(boolean isClanMember) {
        var mp = SDB.dbContext.player.get(dmClan.master_player_id);
        if (Objects.isNull(mp)) {
            return TClan.newBuilder().build();
        }

        String masterName = mp.name;
        int memberCount = members.size();
        return TBuilderOf.buildOf(dmClan, masterName, memberCount, getClanRank(), isClanMember, getTotalAttack());
    }

    public TClanMember getTClanMember(long memberId) {
        var member = members.get(memberId);
        if (Objects.isNull(member)) {
            return TClanMember.newBuilder().build();
        }

        var wp = SDB.dbContext.player.get(member.player_id);
        if (Objects.isNull(wp)) {
            return TClanMember.newBuilder().build();
        }

        return TBuilderOf.buildOf(member, wp.name, wp.portrait_id, wp.level, wp.attack_power);
    }


    public boolean isAutomaticallyJoin() {
        return dmClan.join_type == ZClanJoin.Type.AUTOMATIC.getNumber();
    }

    public int isEnableJoinClan() {
        // 인원 체크
        var maxMemberSize = GameData.CLAN.MEMBER_COUNT_BY_LEVEL.get(dmClan.level);
        if (Objects.isNull(maxMemberSize)) {
            return StatusCode.INVALID_CLAN_JOIN_STATE;
        }

        if (members.size() >= maxMemberSize) {
            return StatusCode.CLAN_MEMBER_FULL;
        }

        if (!ClanRules.isEnableJoinClan(dmClan.state)) {
            return StatusCode.INVALID_CLAN_JOIN_STATE;
        }

        if (!ClanRules.IsEnableJoinClanType(dmClan.join_type)) {
            return StatusCode.INVALID_CLAN_JOIN_TYPE;
        }

        return StatusCode.SUCCESS;
    }

    private void handle_EventDissolution(EventClanDissolution event) {
        // 클랜원한테 메일을 날린다
        var expireAt = Instant.now().plus(365, ChronoUnit.DAYS);
        var type = ZMail.Type.DISSOLUTION_CLAN.getNumber();
        for (var member : members.values()) {
            var player = World.INSTANCE.worldPlayers.getPlayer(member.player_id);
            if (Objects.isNull(player)) {
                clearTargetMemberInfo(member);
                SDB.dbContext.player.clearClanIdByPlayerId(member.player_id, dmClan.id);
                SDB.dbContext.mail.insert(DMPlayerMail.of(member.player_id, type, "system", expireAt));
            } else {
                player.mail.insertMail(type, "system", expireAt);
                player.topic.publish(new EventPlayerClanMemberExpulsion(getClanId()));
            }
        }

        members.clear();

        // 클랜 정보 변경
        dmClan.state = ZClan.State.DISSOLUTION.getNumber();
        SDB.dbContext.clan.updateState(dmClan);
    }

    private void handle_EventChangeClanLeader(EventChangeClanMaster event) {
        // 접속해 있을 수도 있다.
        var masterPlayer = World.INSTANCE.worldPlayers.getPlayer(dmClan.master_player_id);
        if (Objects.nonNull(masterPlayer)) {
            dmClan.master_last_logout_at = Instant.now();
            return;
        }

        final long beforeMasterPlayerId = dmClan.master_player_id;
        var deputyLeaders = members.values().stream()
                .filter(x -> x.role == ZClanMember.Role.DEPUTY_LEADER.getNumber())
                .collect(Collectors.toList());
        if (!deputyLeaders.isEmpty()) {
            // 부 문파장이 존재한다면
            changeMaster(deputyLeaders);
        } else if (members.size() >= 2) {
            // 클랜원이 2명 이상이라면
            changeMaster(members.values().stream().filter(x -> x.player_id != dmClan.master_player_id).collect(Collectors.toList()));
        } else {
            // 클랜원이 문파장밖에 없다면
            // 그냥 해체를 해야한다.
            return;
        }

        // 기존 문파장에게 메일을 날린다.
        var model = DMPlayerMail.of(beforeMasterPlayerId, ZMail.Type.CHANGE_CLAN_LEADER.getNumber(),
                "system", Instant.now().plus(365, ChronoUnit.DAYS));
        SDB.dbContext.mail.insert(model);

        var member = members.get(beforeMasterPlayerId);
        if (Objects.nonNull(member)) {
            clearTargetMemberInfo(member);
        }

        members.remove(beforeMasterPlayerId);

        SDB.dbContext.player.clearClanIdByPlayerId(beforeMasterPlayerId, dmClan.id);
    }

    private void changeMaster(List<DMClanMember> members) {
        var maxContribute = members.stream().mapToLong(x -> x.accumulate_contribution).max().getAsLong();
        var target = members.stream().filter(x -> x.accumulate_contribution == maxContribute).findAny().get();

        var player = World.INSTANCE.worldPlayers.getPlayer(target.player_id);
        if (Objects.isNull(player)) {
            var model = SDB.dbContext.player.get(target.player_id);
            dmClan.master_last_logout_at = model.last_disconnected_at;
        } else {
            dmClan.master_last_logout_at = Instant.now();
        }

        target.role = ZClanMember.Role.LEADER.getNumber();
        dmClan.master_player_id = target.player_id;

        SDB.dbContext.clan.updateMaster(dmClan);
    }

    private void handle_EventClanContribute(EventClanContribute event) {
        var needExp = GameData.CLAN.NEED_EXP_BY_LEVEL.get(dmClan.level);
        if (Objects.isNull(needExp)) {
            logger.error("clan level up failed - clanId({})", dmClan.id);
            return;
        }

        dmClan.exp = Math.min(needExp, dmClan.exp + GameData.CLAN.EXP_BY_CONTRIBUTE);
        if (dmClan.exp >= needExp) {
            var nextLevel = GameData.CLAN.NEED_EXP_BY_LEVEL.get(dmClan.level + 1);
            if (Objects.nonNull(nextLevel)) {
                dmClan.exp = 0;
                dmClan.level++;
            }
        }

        int affected = SDB.dbContext.clan.updateClanExp(dmClan);
        if (affected <= 0) {
            logger.error("clan level up save failed - clanId({})", dmClan.id);
        }
    }


    private void handle_EventReplyJoinClanRequest(EventReplyJoinClanRequest event) {
        var wp = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        var member = members.get(event.playerId);
        if (Objects.isNull(member) && Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                    .setStatus(StatusCode.FAIL));
            return;
        }

        var requestModel = SDB.dbContext.clanJoinRequest.get(event.requestId);
        if (Objects.isNull(requestModel)) {
            wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                    .setStatus(StatusCode.CANCEL_REQUEST_JOIN_CLAN));
            return;
        }

        if (requestModel.clan_id != dmClan.id) {
            wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST_JOIN_CLAN));
            return;
        }

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(requestModel.player_id);
        if (event.isAccept) {
            var maxMemberSize = GameData.CLAN.MEMBER_COUNT_BY_LEVEL.get(dmClan.level);
            if (Objects.isNull(maxMemberSize)) {
                return;
            }

            if (members.size() >= maxMemberSize) {
                wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                        .setStatus(StatusCode.CLAN_MEMBER_FULL));
                return;
            }

            int status = World.INSTANCE.worldClan.joinClan(requestModel.player_id, this);
            if (!StatusCode.isSuccess(status)) {
                wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                        .setStatus(status));
                return;
            }

            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.topic.publish(new EventPlayerJoinClan(dmClan.id));
            }
        } else {
            SDB.dbContext.clanJoinRequest.deleteByPlayerIdAndClanId(requestModel.player_id, dmClan.id);
            if (Objects.nonNull(targetPlayer)) {
                targetPlayer.clan.removeJoinRequests(dmClan.id);
            }
        }

        if (Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanJoinRequestReplyResponse.newBuilder()
                    .setStatus(StatusCode.SUCCESS));
        }
    }

    private void handle_ChangeClanNotice(EventChangeClanNotice event) {
        var wp = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        var member = members.get(event.playerId);
        if (Objects.isNull(member) && Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanNoticeChangeResponse.newBuilder()
                    .setStatus(StatusCode.FAIL));
            return;
        }

        var status = ClanRules.reviewClanText(event.notice, member.role);
        if (!StatusCode.isSuccess(status)) {
            if (Objects.nonNull(wp)) {
                wp.sendPacket(event.packetId, ZClanNoticeChangeResponse.newBuilder()
                        .setStatus(status));
            }
            return;
        }

        dmClan.notice = event.notice;
        int affected = SDB.dbContext.clan.updateText(dmClan);
        if (affected <= 0) {
            logger.error("clan notice change failed - playerId({}), clanId({})", event.playerId, dmClan.id);
            return;
        }

        if (Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanNoticeChangeResponse.newBuilder()
                    .setStatus(StatusCode.SUCCESS));
        }
    }

    public ZClanMember.Role getRole(long playerId) {
        var member = members.get(playerId);
        if (Objects.isNull(member)) {
            return ZClanMember.Role.NONE;
        }

        return ZClanMember.Role.forNumber(member.role);
    }

    private int getDeputyLeaderCount() {
        return (int) members.values().stream().filter(x -> x.role == ZClanMember.Role.DEPUTY_LEADER.getNumber()).count();
    }

    public int changeClanMemberRole(long targetPlayerId, ZClanMember.Role role) {
        var targetMember = members.get(targetPlayerId);
        if (Objects.isNull(targetMember)) {
            return StatusCode.INVALID_CLAN_MEMBER;
        }

        if (role == ZClanMember.Role.DEPUTY_LEADER) {
            if (getDeputyLeaderCount() >= GameData.CLAN.DEPUTY_LEADER_COUNT) {
                return StatusCode.ALREADY_DEPUTY_MASTER_FULL;
            }
        }

        targetMember.role = role.getNumber();

        int affected = SDB.dbContext.clanMember.updateRole(targetMember);
        if (affected <= 0) {
            logger.error("clan member role change failed - playerId({}), clanId({})", targetPlayerId, dmClan.id);
            return StatusCode.FAIL;
        }


        return StatusCode.SUCCESS;
    }

    private void handle_EventChangeClanName(EventChangeClanName event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        var member = members.get(event.playerId);
        if (Objects.isNull(member) && Objects.nonNull(player)) {
            player.sendPacket(event.packetId, ZClanNameChangeResponse.newBuilder()
                    .setStatus(StatusCode.FAIL));
            return;
        }

        var now = Instant.now();
        if (now.isBefore(dmClan.last_change_name_at.plus(GameData.CLAN.NAME_CHANGE_EXPIRE_SECOND, ChronoUnit.SECONDS))) {
            player.sendPacket(event.packetId, ZClanNameChangeResponse.newBuilder()
                    .setStatus(StatusCode.NOT_ENOUGH_CHANGE_TIME));
            return;
        }

        dmClan.name = event.newName;
        int affected = SDB.dbContext.clan.updateName(dmClan);
        if (affected <= 0) {
            logger.error("clan name change failed - playerId({}), clanId({})", event.playerId, dmClan.id);
        }

        if (Objects.nonNull(player)) {
            player.sendPacket(event.packetId, ZClanNameChangeResponse.newBuilder()
                    .setStatus(StatusCode.SUCCESS));
        }
    }

    private void handle_ClanMemberExpulsion(EventClanMemberExpulsion event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        var member = members.get(event.playerId);
        if (Objects.isNull(member)) {
            player.sendPacket(event.packetId, ZClanMemberExpulsionResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_CLAN_MEMBER));
            return;
        }

        var targetMember = members.get(event.targetPlayerId);
        if (Objects.isNull(targetMember)) {
            player.sendPacket(event.packetId, ZClanMemberExpulsionResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_CLAN_MEMBER));
            return;
        }

        if (member.role != ZClanMember.Role.LEADER.getNumber()) {
            player.sendPacket(event.packetId, ZClanMemberExpulsionResponse.newBuilder()
                    .setStatus(StatusCode.NOT_AVAILABLE_CLAN_ROLE));
            return;
        }

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(event.targetPlayerId);
        if (Objects.nonNull(targetPlayer)) {
            targetPlayer.topic.publish(new EventPlayerClanMemberExpulsion(dmClan.id));
        } else {
            clearTargetMemberInfo(targetMember);
            SDB.dbContext.player.clearClanIdByPlayerId(event.targetPlayerId, dmClan.id);
        }

        members.remove(event.targetPlayerId);

        player.sendPacket(event.packetId, ZClanMemberExpulsionResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS));
    }

    private void clearTargetMemberInfo(DMClanMember member) {
        member.clan_id = 0;
        member.role = ZClanMember.Role.NONE.getNumber();
        member.accumulate_contribution = 0;
        member.donate_count = 0;
        member.donate_reset_ymd = 0;
        member.penalty_end_at = Instant.now().plusSeconds(GameData.CLAN.CLAN_PENALTY_SECOND);
        SDB.dbContext.clanMember.save(member);

    }

    private void handle_ChangeClanIntroduction(EventChangeClanIntroduction event) {
        var wp = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        var member = members.get(event.playerId);
        if (Objects.isNull(member) && Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanIntroductionChangeResponse.newBuilder()
                    .setStatus(StatusCode.FAIL));
        }

        var status = ClanRules.reviewClanText(event.introduction, member.role);
        if (!StatusCode.isSuccess(status)) {
            if (Objects.nonNull(wp)) {
                wp.sendPacket(event.packetId, ZClanIntroductionChangeResponse.newBuilder()
                        .setStatus(status));
            }
            return;
        }

        dmClan.introduction = event.introduction;
        dmClan.join_type = event.joinType.getNumber();

        int affected = SDB.dbContext.clan.updateText(dmClan);
        if (affected <= 0) {
            logger.error("clan notice change failed - playerId({}), clanId({})", event.playerId, dmClan.id);
        }

        if (Objects.nonNull(wp)) {
            wp.sendPacket(event.packetId, ZClanIntroductionChangeResponse.newBuilder()
                    .setStatus(StatusCode.SUCCESS));
        }
    }

    private void handle_RequestJoinClan(EventRequestJoinClan event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.nonNull(player) && player.isJoinClan()) {
            player.sendPacket(event.packetId, ZPlayerClanJoinResponse.newBuilder()
                    .setStatus(StatusCode.ALREADY_JOIN_CLAN));
        } else {
            var pm = SDB.dbContext.player.get(event.playerId);
            if (Objects.nonNull(pm) && pm.clan_id > 0) {
                return;
            }
        }

        int status = isEnableJoinClan();
        if (!StatusCode.isSuccess(status)) {
            if (Objects.nonNull(player)) {
                player.sendPacket(event.packetId, ZPlayerClanJoinResponse.newBuilder()
                        .setStatus(status));
            }

            return;
        }

        boolean isAuto = isAutomaticallyJoin();
        // 자동 가입일때 처리
        if (isAuto) {
            var model = SDB.dbContext.clanMember.getByPlayerId(event.playerId);
            if (Objects.isNull(model)) {
                model = DMClanMember.of(event.playerId, dmClan.id, event.role.getNumber());
            }
            int affected = SDB.dbContext.clanMember.save(model);
            if (affected <= 0) {
                logger.error("clan join request failed - playerId({}), clanId({})", event.playerId, dmClan.id);
            }

            members.put(event.playerId, model);
            if (Objects.nonNull(player)) {
                player.topic.publish(new EventPlayerJoinClan(dmClan.id));
                player.sendPacket(event.packetId, ZPlayerClanJoinResponse.newBuilder()
                        .setStatus(StatusCode.SUCCESS));
            }
        } else {
            if (Objects.nonNull(player)) {
                player.clan.insertJoinRequestList(dmClan.id);
                player.sendPacket(event.packetId, ZPlayerClanJoinResponse.newBuilder()
                        .setStatus(StatusCode.SUCCESS));
            }
        }
    }

    public void insertClanMember(long playerId, DMClanMember model) {
        members.put(playerId, model);
    }

    public void dailyReset() {
        int nowYmd = UtcZoneDateTime.getYmd();
        SRedis.INSTANCE.rank.clanAttackRank.addOrUpdateScore(nowYmd, dmClan.id, getTotalAttack());
    }

    public int clanMemberLeave(long playerId) {
        var member = members.get(playerId);
        if (Objects.isNull(member)) {
            return StatusCode.INVALID_CLAN_MEMBER;
        }

        members.remove(playerId);

        return StatusCode.SUCCESS;
    }

    public long getTotalAttack() {
        long totalAttack = 0;
        for (var model : members.values()) {
            var wp = World.INSTANCE.worldPlayers.getPlayer(model.player_id);
            if (Objects.isNull(wp)) {
                var offline = SDB.dbContext.player.get(model.player_id);
                if (Objects.isNull(offline)) {
                    continue;
                }
                totalAttack += offline.attack_power;
                continue;
            }
            totalAttack += wp.getAttackPower();
        }

        return totalAttack;
    }

    public List<DMClanMember> getMembers() {
        return new CopyOnWriteArrayList<>(members.values());
    }

}
