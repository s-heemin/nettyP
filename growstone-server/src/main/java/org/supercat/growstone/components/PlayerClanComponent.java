package org.supercat.growstone.components;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.Out;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.World;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMClanMember;
import org.supercat.growstone.models.DMPlayerClanJoinRequest;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.ClanRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.worldComponents.WorldClan;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerClanComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerClanComponent.class);
    private WorldPlayer player;
    private DMClanMember model;
    private ConcurrentHashMap<Long, DMPlayerClanJoinRequest> clanJoinRequests = new ConcurrentHashMap<>();

    public PlayerClanComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_JOIN_CLAN_REQUEST, this::handle_Event_PLAYER_JOIN_CLAN_REQUEST)
                .on(EventType.PLAYER_JOIN_CLAN, this::handle_Event_PLAYER_JOIN_CLAN)
                .on(EventType.PLAYER_CREATE_CLAN, this::handle_Event_PLAYER_CREATE_CLAN)
                .on(EventType.PLAYER_LEAVE_CLAN, this::handle_Event_PLAYER_LEAVE_CLAN)
                .on(EventType.PLAYER_EXPULSION_CLAN, this::handle_Event_PLAYER_EXPULSION_CLAN)

        );
    }

    public DMClanMember getOrDefault() {
        if (Objects.nonNull(model)) {
            return model;
        }

        model = SDB.dbContext.clanMember.getByPlayerId(player.getId());
        if (Objects.nonNull(model)) {
            return model;
        }

        return DMClanMember.of(player.getId(), player.getClanId(), ZClanMember.Role.NONE.getNumber());
    }

    public void saveLogoutAt() {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return;
        }

        if (clan.getRole(player.getId()) == ZClanMember.Role.LEADER) {
            clan.saveMasterLogoutAt();
        }
    }

    private void handle_Event_PLAYER_EXPULSION_CLAN(EventPlayerClanMemberExpulsion event) {
        if (!player.isJoinClan()) {
            return;
        }

        if (player.getClanId() != event.clanId) {
            logger.error("player clan id and explusion clan id is different - playerId({}), clanId({}), leaveClanId({})", player.getId(), player.getClanId(), event.clanId);
            return;
        }

        clearMemberInfo();

        player.setClanId(0);

        player.sendPacket(0, ZClanMemberExpulsionNotify.newBuilder());
    }

    private void handle_Event_PLAYER_LEAVE_CLAN(EventPlayerLeaveClan event) {
        if (!player.isJoinClan()) {
            return;
        }

        if (player.getId() != event.clanId) {
            logger.error("player clan id and leave clan id is different - playerId({}), clanId({}), leaveClanId({})", player.getId(), player.getClanId(), event.clanId);
            return;
        }

        clearMemberInfo();

        player.setClanId(0);

        player.sendPacket(0, ZClanMemberLeaveNotify.newBuilder());
    }

    private void clearMemberInfo() {
        var member = getOrDefault();
        if (member.role == ZClanMember.Role.NONE.getNumber()) {
            return;
        }

        member.clan_id = 0;
        member.role = ZClanMember.Role.NONE.getNumber();
        member.accumulate_contribution = 0;
        member.donate_count = 0;
        member.donate_reset_ymd = 0;
        member.penalty_end_at = Instant.now().plusSeconds(GameData.CLAN.CLAN_PENALTY_SECOND);

        SDB.dbContext.clanMember.save(member);
    }

    private void handle_Event_PLAYER_JOIN_CLAN_REQUEST(EventPlayerJoinClanRequest event) {
        long packetId = event.packetId;
        long clanId = event.clanId;
        var wc = World.INSTANCE.worldClan.getClan(clanId);
        if (Objects.isNull(wc)) {
            player.sendPacket(packetId, ZPlayerClanJoinResponse.newBuilder()
                    .setStatus(StatusCode.NOT_FOUND_CLAN));
            return;
        }

        var member = getOrDefault();
        if(member.penalty_end_at.isAfter(Instant.now())) {
            player.sendPacket(packetId, ZPlayerClanJoinResponse.newBuilder()
                    .setStatus(StatusCode.CLAN_PENALTY));
            return;
        }

        if (clanJoinRequests.size() > GameData.CLAN.CLAN_JOIN_REQUEST_MAX_COUNT) {
            player.sendPacket(packetId, ZPlayerClanJoinResponse.newBuilder()
                    .setStatus(StatusCode.CLAN_JOIN_REQUEST_FULL));
            return;
        }

        if (getJoinRequests().containsKey(clanId)) {
            player.sendPacket(packetId, ZPlayerClanJoinResponse.newBuilder()
                    .setStatus(StatusCode.ALREADY_JOIN_CLAN));
            return;
        }

        wc.addEvent(new EventRequestJoinClan(packetId, player.getId(), wc.getClanId(), ZClanMember.Role.MEMBER));
    }

    public void insertJoinRequestList(long clanId) {
        var model = DMPlayerClanJoinRequest.of(player.getId(), clanId);
        SDB.dbContext.clanJoinRequest.insert(model);
        clanJoinRequests.put(clanId, model);
    }

    private void handle_Event_PLAYER_JOIN_CLAN(EventPlayerJoinClan event) {
        long clanId = event.clanId;
        player.setClanId(clanId);

        // 클랜에 가입했으니 가입신청서들은 다 날려줘야한다.
        SDB.dbContext.clanJoinRequest.deleteByPlayerId(player.getId());
        clanJoinRequests.clear();
        //클랜에 가입했으니 가입되었다고 notice를 날려준다.
        //클랜이 없을 순 없으니 일단 널 체크 해준다.

        // 무조건 세팅이 되어 있다.
        model = SDB.dbContext.clanMember.getByPlayerId(player.getId());

        var clan = getClan();
        if (Objects.nonNull(clan)) {
            player.sendPacket(0, ZPlayerClanJoinNotify.newBuilder()
                    .setClan(clan.getTClan(true))
                    .setMember(clan.getTClanMember(player.getId())));
        }
    }

    private void handle_Event_PLAYER_CREATE_CLAN(EventPlayerCreateClan event) {
        long packetId = event.packetId;
        if (player.isJoinClan()) {
            player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                    .setStatus(StatusCode.ALREADY_JOIN_CLAN));
            return;
        }

        var member = SDB.dbContext.clanMember.getByPlayerId(player.getId());
        if (Objects.nonNull(member)) {
            if (Instant.now().isBefore(member.penalty_end_at)) {
                player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                        .setStatus(StatusCode.CLAN_PENALTY));
                return;
            }
        }

        long hasRuby = player.currency.getRuby();
        if (GameData.CLAN.CLAN_CREATE_NEED_AMOUNT > hasRuby) {
            player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                    .setStatus(StatusCode.NOT_ENOUGH_CURRENCY));
            return;
        }

        var out = Out.of(0L);
        int status = World.INSTANCE.worldClan.createClan(player.getId(), event.clanName, out);
        if (!StatusCode.isSuccess(status)) {
            player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                    .setStatus(status));
            return;
        }

        // 재화 소모
        status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.CLAN.CLAN_CREATE_NEED_ITEM_DATA_ID, GameData.CLAN.CLAN_CREATE_NEED_AMOUNT);
        if (!StatusCode.isSuccess(status)) {
            // 재화가 소모되지 않았을때 차라리 나중에 재화를 뺐는게 더 효율적인것 같다.
            logger.error("player create clan cost fail - playerId({}), clanId({})", player.getId(), player.getClanId());
            return;
        }

        // 가입했으니 클랜 가입 신청서는 다 날린다.
        SDB.dbContext.clanJoinRequest.deleteByPlayerId(player.getId());
        clanJoinRequests.clear();

        player.setClanId(out.get());

        var clan = getClan();
        if (Objects.isNull(clan)) {
            player.setClanId(0);
            player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                    .setStatus(StatusCode.NOT_FOUND_CLAN));
            return;
        }

        var tMember = clan.getTClanMember(player.getId());
        var tClan = clan.getTClan(true);

        // 무조건 세팅이 되어 있다.
        model = SDB.dbContext.clanMember.getByPlayerId(player.getId());

        player.sendPacket(packetId, ZCreateClanResponse.newBuilder()
                .setStatus(status)
                .setClan(tClan).
                setMember(tMember));
    }


    private ConcurrentHashMap<Long, DMPlayerClanJoinRequest> getJoinRequests() {
        if (Objects.nonNull(clanJoinRequests)) {
            return clanJoinRequests;
        }

        clanJoinRequests = SDB.dbContext.clanJoinRequest.getByPlayerId(player.getId()).stream()
                .collect(Collectors.toMap(x -> x.clan_id, x -> x, (x, y) -> x, ConcurrentHashMap::new));
        return clanJoinRequests;
    }

    public void removeJoinRequests(long clanId) {
        clanJoinRequests.remove(clanId);
    }

    public List<TClanJoinRequest> getTClanJoinRequestList() {
        var requests = getJoinRequests();
        if (Objects.isNull(requests)) {
            return List.of();
        }

        var l = new ArrayList<TClanJoinRequest>();
        for (var request : requests.values()) {
            var wc = World.INSTANCE.worldClan.getClan(request.clan_id);
            if (Objects.isNull(wc)) {
                logger.error("clan not found - clanId({})", request.clan_id);
                continue;
            }

            var model = wc.getModel();
            l.add(TClanJoinRequest.newBuilder()
                    .setClanName(model.name)
                    .setClanMemberCount(wc.memberSize())
                    .setClanId(model.id)
                    .setClanLevel(model.level)
                    .setRank(wc.getClanRank()).build());
        }

        return l;
    }

    private WorldClan getClan() {
        if (!player.isJoinClan()) {
            return null;
        }

        return World.INSTANCE.worldClan.getClan(player.getClanId());
    }

    public List<TClan> getRecommendedClans(String includeName) {
        if (player.isJoinClan()) {
            return List.of();
        }

        var joinRequests = getJoinRequests();
        if (joinRequests.isEmpty()) {
            return List.of();
        }

        var clans = World.INSTANCE.worldClan.getClans();
        Collections.shuffle(clans);
        return clans.stream()
                .filter(x -> !joinRequests.containsKey(x.getClanId()))
                .filter(x -> x.getName().contains(includeName))
                .filter(x -> x.getModel().state == ZClan.State.ACTIVE.getNumber())
                .limit(GameData.CLAN.CLAN_RECOMMEND_LIST_COUNT)
                .map(x -> x.getTClan(false))
                .collect(Collectors.toList());
    }

    public TClan getTClan(long clanId) {
        var wc = World.INSTANCE.worldClan.getClan(clanId);
        if (Objects.isNull(wc)) {
            return TClan.newBuilder().build();
        }

        return wc.getTClan(player.getClanId() == clanId);
    }

    public int changeNotice(String notice, long packetId) {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        var member = getOrDefault();
        var status = ClanRules.reviewClanText(notice, member.role);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        clan.addEvent(new EventChangeClanNotice(notice, player.getId(), packetId));
        return StatusCode.SUCCESS;
    }

    public int changeIntroduction(String introduction, ZClanJoin.Type joinType, long packetId) {
        if (Objects.isNull(joinType) || joinType == ZClanJoin.Type.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        var member = getOrDefault();
        var status = ClanRules.reviewClanText(introduction, member.role);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        clan.addEvent(new EventChangeClanIntroduction(introduction, joinType, player.getId(), packetId));
        return StatusCode.SUCCESS;
    }

    public int changeClanRole(long targetPlayerId, ZClanMember.Role role) {
        if (Objects.isNull(role) || role == ZClanMember.Role.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        if (targetPlayerId == player.getId()) {
            return StatusCode.INVALID_REQUEST;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        var myClanRole = clan.getRole(player.getId());
        if (myClanRole != ZClanMember.Role.LEADER) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        int status = clan.changeClanMemberRole(targetPlayerId, role);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(targetPlayerId);
        if (Objects.nonNull(targetPlayer)) {
            targetPlayer.sendPacket(0, ZClanMemberRoleChangeNotify.newBuilder()
                    .setRole(role));
        }

        return StatusCode.SUCCESS;
    }

    public int clanJoinRequestApply(long requestId, boolean isAccept, long packetId) {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        if (!ClanRules.isClanActiveRole(clan.getRole(player.getId()).getNumber())) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        clan.addEvent(new EventReplyJoinClanRequest(requestId, player.getId(), isAccept, packetId));

        return StatusCode.SUCCESS;
    }

    public int getClanJoinRequests(List<TClanJoinRequestPlayer> l) {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        var role = clan.getRole(player.getId());
        if (role != ZClanMember.Role.LEADER) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        var requests = SDB.dbContext.clanJoinRequest.getByClanId(clan.getClanId());
        for (var request : requests) {
            var player = World.INSTANCE.worldPlayers.getPlayer(request.player_id);
            if (Objects.isNull(player)) {
                var offline = SDB.dbContext.player.get(request.player_id);
                if (Objects.isNull(offline)) {
                    continue;
                }

                l.add(TClanJoinRequestPlayer.newBuilder()
                        .setRequestId(request.id)
                        .setPortraitId(offline.portrait_id)
                        .setPlayerName(offline.name)
                        .setLevel(offline.level)
                        .setAttackPower(offline.attack_power)
                        .build());
                continue;
            }

            l.add(TClanJoinRequestPlayer.newBuilder()
                    .setRequestId(request.id)
                    .setPortraitId(player.getPortraitIcon())
                    .setPlayerName(player.getName())
                    .setLevel(player.getLevel())
                    .setAttackPower(player.getAttackPower())
                    .build());
        }

        return StatusCode.SUCCESS;
    }

    public int clanNameChange(String name, long packetId) {
        int status = ClanRules.checkClanName(name);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        if (clan.getRole(player.getId()) != ZClanMember.Role.LEADER) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        long hasRuby = player.currency.getRuby();
        if (GameData.CLAN.NAME_CHANGE_RUBY > hasRuby) {
            return StatusCode.NOT_ENOUGH_CURRENCY;
        }

        clan.addEvent(new EventChangeClanName(player.getId(), name, packetId));

        return StatusCode.SUCCESS;
    }

    public int clanMemberLeave() {
        if (!player.isJoinClan()) {
            return StatusCode.NOT_JOIN_CLAN;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        // 문파장이 탈퇴하면 어떻게됨?
        int status = clan.clanMemberLeave(player.getId());
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 마스터는 탈퇴하면 안된다.
        if (clan.getRole(player.getId()) == ZClanMember.Role.LEADER) {
            return StatusCode.INVALID_REQUEST;
        }

        clearMemberInfo();

        player.setClanId(0);

        SDB.dbContext.player.clearClanIdByPlayerId(player.getId(), 0);

        return StatusCode.SUCCESS;
    }

    public int clanMemberExpulsion(long targetPlayerId, long packetId) {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_JOIN_CLAN;
        }

        var myClanRole = clan.getRole(player.getId());
        if (myClanRole != ZClanMember.Role.LEADER) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        clan.addEvent(new EventClanMemberExpulsion(player.getId(), targetPlayerId, packetId));

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(targetPlayerId);
        if (Objects.nonNull(targetPlayer)) {
            targetPlayer.sendPacket(0, ZClanMemberExpulsionResponse.newBuilder());
        }

        return StatusCode.SUCCESS;
    }

    public int donate(List<TContentReward> outResults) {
        if (!player.isJoinClan()) {
            return StatusCode.NOT_JOIN_CLAN;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        var member = getOrDefault();
        if (member.role == ZClanMember.Role.NONE.getNumber()) {
            return StatusCode.INVALID_REQUEST;
        }

        int nowYmd = UtcZoneDateTime.getYmd();
        if (member.donate_reset_ymd != nowYmd) {
            member.donate_count = 0;
            member.donate_reset_ymd = nowYmd;
        }

        if (member.donate_count >= GameData.CLAN.CLAN_DAILY_CONTRIBUTE_MAX_COUNT) {
            return StatusCode.DAILY_CONTRIBUTE_COUNT_FULL;
        }

        var donation = GameData.CLAN.DONATIONS.get(++member.donate_count);
        if (Objects.isNull(donation)) {
            return StatusCode.INVALID_REQUEST;
        }

        // 루비 확인
        long hasRuby = player.currency.getRuby();
        if (donation.cost > hasRuby) {
            return StatusCode.NOT_ENOUGH_CURRENCY;
        }

        // 루비 소모
        int status = player.currency.useCurrency(ZResource.Type.FREE_RUBY, donation.cost);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        for (var reward : donation.rewards) {
            status = player.categoryFilter.add(reward.type, reward.rewardId, reward.count);
            if (!StatusCode.isSuccess(status)) {
                logger.error("player clan contribute reward get fail - playerId({}) type({}) rewardId({}) count({})", player.getId(), reward.type, reward.rewardId, reward.count);
            }

            outResults.add(TContentReward.newBuilder()
                    .setCategory(reward.type)
                    .setDataId(reward.rewardId)
                    .setCount(reward.count)
                    .build());
        }

        member.accumulate_contribution += 1;

        SDB.dbContext.clanMember.updateContribute(member);

        // 클랜에 이벤트 던지기
        clan.addEvent(new EventClanContribute());

        return StatusCode.SUCCESS;
    }

    public List<TClanMember> getTClanMemberList() {
        var clan = getClan();
        if (Objects.isNull(clan)) {
            return List.of();
        }

        var members = clan.getMembers();
        var l = new ArrayList<TClanMember>();
        for (var member : members) {
            var mp = World.INSTANCE.worldPlayers.getPlayer(member.player_id);
            if (Objects.isNull(mp)) {
                var offline = SDB.dbContext.player.get(member.player_id);
                if (Objects.isNull(offline)) {
                    continue;
                }
                l.add(TClanMember.newBuilder()
                        .setPortraitId(offline.portrait_id)
                        .setName(offline.name)
                        .setLevel(offline.level)
                        .setAttackPower(offline.attack_power)
                        .setRole(ClanRules.ofRole(member.role))
                        .setContribution(member.donate_count)
                        .build());
                continue;
            }

            l.add(TClanMember.newBuilder()
                    .setPortraitId(mp.getPortraitIcon())
                    .setName(mp.getName())
                    .setLevel(mp.getLevel())
                    .setAttackPower(mp.getAttackPower())
                    .setRole(ClanRules.ofRole(member.role))
                    .setContribution(member.donate_count)
                    .build());
        }

        return l;
    }

    public int clanDissolution() {
        if (!player.isJoinClan()) {
            return StatusCode.NOT_JOIN_CLAN;
        }

        var clan = getClan();
        if (Objects.isNull(clan)) {
            return StatusCode.NOT_FOUND_CLAN;
        }

        if (clan.getRole(player.getId()) != ZClanMember.Role.LEADER) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        clan.addEvent(new EventClanDissolution(player.getId()));

        return StatusCode.SUCCESS;
    }
}
