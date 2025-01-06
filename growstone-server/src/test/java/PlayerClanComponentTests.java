import com.supercat.growstone.network.messages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;
import org.supercat.growstone.events.EventPlayerJoinClanRequest;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class PlayerClanComponentTests extends BaseServerTests {
    @Test
    void createClanTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        // 클랜 생성 실패
        int status = TestPlayerUtils.createClan(player, false);
        Assertions.assertEquals(StatusCode.FAIL, status);

        // 클랜 생성 성공
        status = TestPlayerUtils.createClan(player, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 클랜 생성 성공
        var clanId = player.getClanId();
        Assertions.assertTrue(clanId > 0);

        // DB 확인
        // 클랜 체크
        var clanModel = SDB.dbContext.clan.getByMasterPlayerId(player.getId());
        Assertions.assertNotNull(clanModel);
        Assertions.assertEquals(clanId, clanModel.id);

        // 클랜 멤버 체크
        var memberModel = SDB.dbContext.clanMember.getByPlayerId(player.getId());
        Assertions.assertNotNull(memberModel);
        Assertions.assertEquals(clanId, memberModel.clan_id);

        var clan = World.INSTANCE.worldClan.getClan(clanId);
        Assertions.assertNotNull(clan);

        var tClan = clan.getTClan(true);
        Assertions.assertNotNull(tClan);
        Assertions.assertEquals(clanId, tClan.getClanId());
        Assertions.assertEquals(player.getName(), tClan.getMasterPlayerName());
        Assertions.assertEquals(String.valueOf(player.getId()), tClan.getClanName());
        Assertions.assertEquals(1, tClan.getClanLevel());
        Assertions.assertEquals(0, tClan.getClanExp());
        Assertions.assertEquals(ZClanJoin.Type.AUTOMATIC, tClan.getJoinType());
        Assertions.assertEquals(ZClan.State.ACTIVE, tClan.getState());
        Assertions.assertEquals(1, tClan.getMemberCount());
        Assertions.assertEquals(player.getAttackPower(), tClan.getTotalAttackPower());
    }

    @Test
    void joinAutomaticJoinTypeClanTest() {
        var masterPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(masterPlayer);

        // 클랜 생성 성공
        int status = TestPlayerUtils.createClan(masterPlayer, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 클랜원 가입 타입 변경
        status = masterPlayer.clan.changeIntroduction("", ZClanJoin.Type.AUTOMATIC, 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        TestPlayerUtils.awaitFunc(masterPlayer, 3);

        // 클랜원 생성
        var memberPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(memberPlayer);

        // 클랜 가입
        memberPlayer.topic.publish(new EventPlayerJoinClanRequest(0L, masterPlayer.getClanId()));
        TestPlayerUtils.awaitFunc(memberPlayer, 10);

        Assertions.assertTrue(memberPlayer.getClanId() > 0);
        var memberModel = SDB.dbContext.clanMember.getByPlayerId(memberPlayer.getId());
        Assertions.assertNotNull(memberModel);
        Assertions.assertEquals(memberPlayer.getClanId(), memberModel.clan_id);
    }

    @Test
    void joinApproveRequireTypeClanAcceptTest() {
        var masterPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(masterPlayer);

        // 클랜 생성 성공
        int status = TestPlayerUtils.createClan(masterPlayer, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 클랜원 가입 타입 변경
        status = masterPlayer.clan.changeIntroduction("", ZClanJoin.Type.APPROVAL_REQUIRED, 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        TestPlayerUtils.awaitFunc(masterPlayer, 3);

        // 클랜원 생성
        var memberPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(memberPlayer);

        // 클랜 가입
        memberPlayer.topic.publish(new EventPlayerJoinClanRequest(0L, masterPlayer.getClanId()));
        TestPlayerUtils.awaitFunc(memberPlayer, 10);
        Assertions.assertTrue(memberPlayer.getClanId() <= 0);

        var requestModels = SDB.dbContext.clanJoinRequest.getByClanId(masterPlayer.getClanId());
        Assertions.assertTrue(!requestModels.isEmpty());
        Assertions.assertEquals(1, requestModels.size());
        // 마스터가 클랜 가입 승인
        status = masterPlayer.clan.clanJoinRequestApply(requestModels.get(0).id, true, 0L);
        TestPlayerUtils.awaitFunc(memberPlayer, 10);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertTrue(memberPlayer.getClanId() > 0);

        // DB체크
        var memberModel = SDB.dbContext.clanMember.getByPlayerId(memberPlayer.getId());
        Assertions.assertNotNull(memberModel);
        Assertions.assertEquals(memberPlayer.getClanId(), memberModel.clan_id);
        Assertions.assertEquals(ZClanMember.Role.MEMBER.getNumber(), memberModel.role);

        var members = SDB.dbContext.clanMember.getAll(masterPlayer.getClanId());
        Assertions.assertEquals(2, members.size());

        var requestModel = SDB.dbContext.clanJoinRequest.getByPlayerIdAndClanId(memberPlayer.getId(), masterPlayer.getClanId());
        Assertions.assertNull(requestModel);
    }

    @Test
    void joinApproveRequireTypeClanRejectTest() {
        var masterPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(masterPlayer);

        // 재화 지급
        int status = masterPlayer.categoryFilter.add(ZCategory.Type.ITEM, GameData.CLAN.CLAN_CREATE_NEED_ITEM_DATA_ID, GameData.CLAN.CLAN_CREATE_NEED_AMOUNT);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 클랜 생성 성공
        status = TestPlayerUtils.createClan(masterPlayer, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 클랜원 가입 타입 변경
        status = masterPlayer.clan.changeIntroduction("", ZClanJoin.Type.APPROVAL_REQUIRED, 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        TestPlayerUtils.awaitFunc(masterPlayer, 3);

        // 클랜원 생성
        var memberPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(memberPlayer);

        // 클랜 가입
        memberPlayer.topic.publish(new EventPlayerJoinClanRequest(0L, masterPlayer.getClanId()));
        TestPlayerUtils.awaitFunc(memberPlayer, 10);
        Assertions.assertTrue(memberPlayer.getClanId() <= 0);

        var requestModels = SDB.dbContext.clanJoinRequest.getByClanId(masterPlayer.getClanId());
        Assertions.assertTrue(!requestModels.isEmpty());
        Assertions.assertEquals(1, requestModels.size());
        // 마스터가 클랜 가입 승인
        status = masterPlayer.clan.clanJoinRequestApply(requestModels.get(0).id, false, 0L);
        TestPlayerUtils.awaitFunc(memberPlayer, 10);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertTrue(memberPlayer.getClanId() <=0);
        // DB체크
        var memberModel = SDB.dbContext.clanMember.getByPlayerId(memberPlayer.getId());
        Assertions.assertNull(memberModel);
        var members = SDB.dbContext.clanMember.getAll(masterPlayer.getClanId());
        Assertions.assertEquals(1, members.size());
        var requestModel = SDB.dbContext.clanJoinRequest.getByPlayerIdAndClanId(memberPlayer.getId(), masterPlayer.getClanId());
        Assertions.assertNull(requestModel);
    }

    @Test
    void donationTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var outResult = new ArrayList<TContentReward>();
        int status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.NOT_JOIN_CLAN, status);

        status = TestPlayerUtils.createClan(player, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = SDB.dbContext.clanMember.getByPlayerId(player.getId());
        Assertions.assertNotNull(model);

        var donate = GameData.CLAN.DONATIONS.get(model.donate_count);
        Assertions.assertNotNull(donate);
        Assertions.assertEquals(1, model.donate_count);

        // 보상 체크
        for(var reward : donate.rewards) {
            var out = outResult.stream()
                    .filter(x -> x.getCategory() == reward.type && x.getDataId() == reward.rewardId && x.getCount() == reward.count)
                    .findFirst()
                    .orElse(null);
            Assertions.assertNotNull(out);
        }

        Assertions.assertEquals(donate.rewards.size(), outResult.size());
    }

    @Test
    void donationMaxCountTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var outResult = new ArrayList<TContentReward>();
        int status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.NOT_JOIN_CLAN, status);

        status = TestPlayerUtils.createClan(player, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_CURRENCY, status);

        var model = player.clan.getOrDefault();
        Assertions.assertNotNull(model);
        Assertions.assertNotEquals(ZClanMember.Role.NONE.getNumber(), model.role);
        Assertions.assertEquals(1, model.donate_count);

        for(int i = model.donate_count + 1; i <= GameData.CLAN.DONATIONS.size(); i++) {
            var donate = GameData.CLAN.DONATIONS.get(i);
            Assertions.assertNotNull(donate);

            status = player.categoryFilter.add(ZCategory.Type.ITEM, GameData.CLAN.DONATE_PAID_ITEM_DATA_ID, donate.cost);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.clan.donate(outResult);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        model = player.clan.getOrDefault();

        Assertions.assertEquals(5, model.donate_count);
        Assertions.assertEquals(5, model.accumulate_contribution);

        status = player.clan.donate(outResult);
        Assertions.assertEquals(StatusCode.DAILY_CONTRIBUTE_COUNT_FULL, status);
    }

    // 클랜 기부시 클랜 경험치 및 레벨 상승
    @Test
    void donateForClanGrowthTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var outResult = new ArrayList<TContentReward>();
        int status = TestPlayerUtils.createClan(player, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        AtomicLong needCost = new AtomicLong();
        GameData.CLAN.DONATIONS.values().forEach(x -> needCost.addAndGet(x.cost));
        for(int i = 0; i< GameData.CLAN.DONATIONS.size(); i++) {
            var donate = GameData.CLAN.DONATIONS.get(i + 1);
            Assertions.assertNotNull(donate);

            status = player.categoryFilter.add(ZCategory.Type.ITEM, GameData.CLAN.DONATE_PAID_ITEM_DATA_ID, donate.cost);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.clan.donate(outResult);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        TestPlayerUtils.awaitFunc( 5);

        var clan = World.INSTANCE.worldClan.getClan(player.getClanId());
        var tClan = clan.getTClan(true);
        Assertions.assertNotNull(tClan);
        Assertions.assertEquals(1, tClan.getClanLevel());
        Assertions.assertEquals(5, tClan.getClanExp());

        var needExp = GameData.CLAN.NEED_EXP_BY_LEVEL.get(tClan.getClanLevel() + 1);
        Assertions.assertNotNull(needExp);

        long remainExp = needExp - tClan.getClanExp();
        Assertions.assertTrue(remainExp > 0);

        int needPlayerCount = (int)remainExp / GameData.CLAN.DONATIONS.size() + 1;
        for(int i = 0; i < needPlayerCount; i++) {
            var wp = TestPlayerUtils.of();
            Assertions.assertNotNull(wp);

            TestPlayerUtils.joinClan(wp, player.getClanId());

            status = wp.categoryFilter.add(ZCategory.Type.ITEM, GameData.CLAN.DONATE_PAID_ITEM_DATA_ID, needCost.get());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            for(int j = 0 ; j < GameData.CLAN.DONATIONS.size(); j++) {
                status = wp.clan.donate(outResult);
                Assertions.assertEquals(StatusCode.SUCCESS, status);
            }
        }

        TestPlayerUtils.awaitFunc( 5);

        tClan = clan.getTClan(true);
        Assertions.assertEquals(2, tClan.getClanLevel());
        Assertions.assertTrue(tClan.getClanExp() > 0);
    }

    // 클랜원 추방
    @Test
    void clanMemberExclusionTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        int status = player.clan.clanMemberExpulsion(player.getId(), 0L);
        Assertions.assertEquals(StatusCode.NOT_JOIN_CLAN, status);

        status = TestPlayerUtils.createClan(player, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var member = TestPlayerUtils.of();
        Assertions.assertNotNull(member);

        status = TestPlayerUtils.joinClan(member, player.getClanId());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var clanMembers = SDB.dbContext.clanMember.getAll(player.getClanId());
        Assertions.assertEquals(2, clanMembers.size());

        var clan = World.INSTANCE.worldClan.getClan(player.getClanId());
        var tClanMember = clan.getTClanMember(member.getId());
        Assertions.assertTrue(tClanMember.getName().equals(member.getName()));

        status = player.clan.clanMemberExpulsion(member.getId(), 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        TestPlayerUtils.awaitFunc(member, 3);

        tClanMember = clan.getTClanMember(member.getId());
        Assertions.assertFalse(tClanMember.getName().equals(member.getName()));

        var model = SDB.dbContext.clanMember.getByPlayerId(member.getId());
        Assertions.assertNotNull(model);
        Assertions.assertEquals(0, model.clan_id);

        clanMembers = SDB.dbContext.clanMember.getAll(player.getClanId());
        Assertions.assertEquals(1, clanMembers.size());
    }


    // 클랜 역할 위임
    @Test
    void clanRoleTest() {
        var master = TestPlayerUtils.of();
        Assertions.assertNotNull(master);

        int status = TestPlayerUtils.createClan(master, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var deputies = new ArrayList<WorldPlayer>();
        for(int i = 0 ; i < GameData.CLAN.DEPUTY_LEADER_COUNT; i++) {
            var player = TestPlayerUtils.of();
            deputies.add(player);

            status = TestPlayerUtils.joinClan(player, master.getClanId());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        var member = TestPlayerUtils.of();
        Assertions.assertNotNull(member);

        status = TestPlayerUtils.joinClan(member, master.getClanId());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 마스터가 아닌 유저가 위임할 경우
        status = member.clan.changeClanRole(member.getId(), ZClanMember.Role.NONE);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);
        status = member.clan.changeClanRole(member.getId(), ZClanMember.Role.DEPUTY_LEADER);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);
        status = member.clan.changeClanRole(deputies.get(0).getId(), ZClanMember.Role.DEPUTY_LEADER);
        Assertions.assertEquals(StatusCode.NOT_AVAILABLE_CLAN_ROLE, status);

        // 마스터가 위임 성공
        for(var deputy : deputies) {
            status = master.clan.changeClanRole(deputy.getId(), ZClanMember.Role.DEPUTY_LEADER);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        // 정해진 수 초과로 위임을 했을 경우
        status = master.clan.changeClanRole(member.getId(), ZClanMember.Role.DEPUTY_LEADER);
        Assertions.assertEquals(StatusCode.ALREADY_DEPUTY_MASTER_FULL, status);

        var members = SDB.dbContext.clanMember.getAll(master.getClanId());
        Assertions.assertEquals(2 + GameData.CLAN.DEPUTY_LEADER_COUNT, members.size());
        deputies.add(master);
        deputies.add(member);

        for(var cm : members) {
            if(cm.player_id == master.getId()) {
                Assertions.assertEquals(ZClanMember.Role.LEADER.getNumber(), cm.role);
            } else if(cm.player_id == member.getId()) {
                Assertions.assertEquals(ZClanMember.Role.MEMBER.getNumber(), cm.role);
            } else {
                Assertions.assertEquals(ZClanMember.Role.DEPUTY_LEADER.getNumber(), cm.role);
            }
        }
    }

    // 공지사항, 소개
    @Test
    void clanNoticeAndIntroductionTest() {
        var master = TestPlayerUtils.of();
        Assertions.assertNotNull(master);

        String notice = "abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0" +
                "abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0abcdefghijklmnnp0";

        int status = TestPlayerUtils.createClan(master, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var member = TestPlayerUtils.of();
        Assertions.assertNotNull(member);

        status = TestPlayerUtils.joinClan(member, master.getClanId());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = member.clan.changeNotice(notice, 0L);
        Assertions.assertEquals(StatusCode.NOT_AVAILABLE_CLAN_ROLE, status);

        status = master.clan.changeNotice(notice, 0L);
        Assertions.assertEquals(StatusCode.OVER_TEXT_LENGTH, status);

        notice = "정상이지롱";
        status = master.clan.changeNotice(notice, 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        TestPlayerUtils.awaitFunc(5);

        var clan = master.clan.getTClan(master.getClanId());
        Assertions.assertEquals(notice, clan.getNotice());

        // db 체크
        var model = SDB.dbContext.clan.get(master.getClanId());
        Assertions.assertNotNull(notice);
        Assertions.assertEquals(notice, model.notice);

        notice = "orientation";
        status = member.clan.changeIntroduction(notice, ZClanJoin.Type.AUTOMATIC, 0L);
        Assertions.assertEquals(StatusCode.NOT_AVAILABLE_CLAN_ROLE, status);

        status = master.clan.changeIntroduction(notice, ZClanJoin.Type.AUTOMATIC, 0L);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        TestPlayerUtils.awaitFunc(5);

        // db 체크
        model = SDB.dbContext.clan.get(master.getClanId());
        Assertions.assertNotNull(notice);
        Assertions.assertEquals(notice, model.introduction);
    }

    // 문파장 접속하지 않았을때 위임이 되는지 체크
    @Test
    void changeClanMaster() {
        var master = TestPlayerUtils.of();
        Assertions.assertNotNull(master);

        int status = TestPlayerUtils.createClan(master, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var member = TestPlayerUtils.of();
        Assertions.assertNotNull(member);

        status = TestPlayerUtils.joinClan(member, master.getClanId());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = master.clan.changeClanRole(member.getId(), ZClanMember.Role.DEPUTY_LEADER);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var clan = World.INSTANCE.worldClan.getClan(master.getClanId());
        Assertions.assertNotNull(clan);

        clan.getModel().master_last_logout_at = Instant.now().minus(GameData.CLAN.CLAN_MASTER_DISCONNECT_DAY + 1, ChronoUnit.DAYS);

        World.INSTANCE.worldPlayers.removeWorldPlayer(master.getId());

        TestPlayerUtils.awaitFunc(10);

        var newMaster = clan.getTClanMember(member.getId());
        Assertions.assertNotNull(newMaster);
        Assertions.assertEquals(ZClanMember.Role.LEADER, newMaster.getRole());

        var members = SDB.dbContext.clanMember.getAll(master.getClanId());
        Assertions.assertEquals(1, members.size());

        var befMasterModel = SDB.dbContext.player.get(master.getId());
        Assertions.assertNotNull(befMasterModel);

        var updateClan = SDB.dbContext.clan.get(befMasterModel.clan_id);
        Assertions.assertEquals(0L, befMasterModel.clan_id);
        Assertions.assertNull(updateClan);

        updateClan = SDB.dbContext.clan.get(member.getClanId());
        Assertions.assertNotNull(updateClan);
        Assertions.assertEquals(updateClan.master_player_id, member.getId());
    }

    @Test
    void dissolutionClanTest() {
        var master = TestPlayerUtils.of();
        Assertions.assertNotNull(master);

        int status = TestPlayerUtils.createClan(master, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var clan = World.INSTANCE.worldClan.getClan(master.getClanId());
        Assertions.assertNotNull(clan);

        var l = new ArrayList<WorldPlayer>();
        for(int i = 0; i < 8 ; i++) {
            var player = TestPlayerUtils.of();
            l.add(player);
            status = TestPlayerUtils.joinClan(player, master.getClanId());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        status = master.clan.clanDissolution();
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        TestPlayerUtils.awaitFunc(5);

        Assertions.assertEquals(ZClan.State.DISSOLUTION.getNumber(), clan.getTClan(true).getState().getNumber());
        Assertions.assertEquals(0, clan.getTClan(true).getMemberCount());

        Assertions.assertEquals(0L, master.getClanId());

        // 새로운 클랜 생성
        var newMaterPlayer = TestPlayerUtils.of();
        Assertions.assertNotNull(newMaterPlayer);
        status = TestPlayerUtils.createClan(newMaterPlayer, true);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        for(var player : l) {
            Assertions.assertEquals(0L, player.getClanId());
            status = TestPlayerUtils.joinClan(player, newMaterPlayer.getClanId());
            Assertions.assertEquals(StatusCode.FAIL, status);
        }
    }
}
