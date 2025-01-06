import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TRaidDungeon;
import com.supercat.growstone.network.messages.ZCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.*;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.model.RMPlayerCompetitiveRaid;
import org.supercat.growstone.types.DungeonMode;
import org.supercat.growstone.types.DungeonType;

import java.util.*;

public class PlayerRaidDungeonComponentTests extends BaseServerTests {
    final long channelId = World.INSTANCE.model.id;
    @Test
    void raidDungeonInfo() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resRaid = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaid);

        var model = player.raidDungeon.getOrCreate(resRaid.id);
        Assertions.assertNotNull(model);

        var l = player.raidDungeon.getTRaidDungeon();
        Assertions.assertNotNull(l);
        Assertions.assertEquals(1, l.size());

        for(var info : l) {
            Assertions.assertEquals(resRaid.id, info.getId());
            Assertions.assertEquals(resRaid.ticket.ticketCount, info.getRemainTicketCount());
            Assertions.assertEquals(resRaid.adViewMaxCount, info.getRemainAdViewCount());
            Assertions.assertEquals(0, info.getScore());
        }
    }

    @Test
    void raidDungeonStartTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resDailyDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.DAILY)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resDailyDungeon);

        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        // 잘못된 레이드ID
        int status = player.raidDungeon.isEnableStartRaid(0);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE,status);

        // 잘못된 레이드 타입
        status = player.raidDungeon.isEnableStartRaid(resDailyDungeon.id);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST,status);

        var model = player.raidDungeon.getOrCreate(resRaidDungeon.id);
        model.remain_ticket_count = 0;

        status = player.raidDungeon.isEnableStartRaid(resRaidDungeon.id);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_DUNGEON_TICKET, status);

        model.remain_ticket_count = 1;
        status = player.raidDungeon.isEnableStartRaid(resRaidDungeon.id);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void raidDungeonViewAdvertiseTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        var model = player.raidDungeon.getOrCreate(resRaidDungeon.id);
        Assertions.assertEquals(resRaidDungeon.ticket.ticketCount, model.remain_ticket_count);
        Assertions.assertEquals(resRaidDungeon.adViewMaxCount, model.remain_view_ad_count);

        var out = TRaidDungeon.newBuilder();
        int status = player.raidDungeon.viewAdvertise(0,  out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        model.remain_view_ad_count = 0;

        status = player.raidDungeon.viewAdvertise(resRaidDungeon.id, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        final int ticketCount = model.remain_ticket_count;
        ++model.remain_view_ad_count;

        status = player.raidDungeon.viewAdvertise(resRaidDungeon.id, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(ticketCount + 1, model.remain_ticket_count);
        Assertions.assertEquals(0, model.remain_view_ad_count);
    }

    @Test
    void competitiveRaidDungeonTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COMPETITIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        SRedis.INSTANCE.rank.competitiveRank.clearForTest(resRaidDungeon.id, UtcZoneDateTime.getYmd());

        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();
        int status = player.raidDungeon.clearRaidDungeon(0, 0, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        // 잘못된 타입
        status = player.raidDungeon.clearRaidDungeon(resTower.id, 1, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 잘못된 스테이지
        status = player.raidDungeon.clearRaidDungeon(resTower.id, 0, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 첫 클리어
        status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, 5300, contentRewards, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resReward = resRaidDungeon.clearRewardsByStage.get(1);
        for(var reward : resReward) {
            if(reward.type == ZCategory.Type.ITEM) {
                Assertions.assertEquals(reward.count + reward.count * resRaidDungeon.rewardBonusPercent, player.itemBag.getItemCount(reward.rewardId));
            } else if (reward.type == ZCategory.Type.CURRENCY)  {
                var resItem = ResourceManager.INSTANCE.item.getItem(reward.rewardId);
                Assertions.assertEquals(reward.count, player.currency.getCurrency(resItem.type));
            } else {
                Assertions.fail();
            }
        }

        // 레디스 점수 및 랭킹 체크
        long avatarId = player.avatar.getEquipAvatarId();
        int nowYmd = UtcZoneDateTime.getYmd();
        long rank = SRedis.INSTANCE.rank.competitiveRank.getRank(resRaidDungeon.id, nowYmd, player.getId());
        Assertions.assertEquals(rank, 1);

        var registInfos = SRedis.INSTANCE.rank.competitiveRank.getCached(resRaidDungeon.id, nowYmd, player.getId());
        Assertions.assertNotNull(registInfos);

        var model = JsonConverter.of(registInfos, RMPlayerCompetitiveRaid.class);
        Assertions.assertEquals(avatarId, model.avatar_id);
        Assertions.assertEquals(player.getName(), model.name);
    }

    @Test
    void competitiveRaidDungeonMultiPlayerTest() {
        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COMPETITIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        SRedis.INSTANCE.rank.competitiveRank.clearForTest(resRaidDungeon.id, UtcZoneDateTime.getYmd());
        final int testPlayerCount = 100;
        var players = new ArrayList<WorldPlayer>();
        for(int i = 0 ; i < testPlayerCount; i++) {
            players.add(TestPlayerUtils.of());
        }

        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();

        var scoreList = new ArrayList<Long>();
        for(var player : players) {
            long score = SRandomUtils.nextInt(0, 10000000);
            scoreList.add(score);
            int status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, score, contentRewards, out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        Collections.sort(scoreList, Collections.reverseOrder());
        int rank = 0;
        var scores = SRedis.INSTANCE.rank.competitiveRank.entries(resRaidDungeon.id, UtcZoneDateTime.getYmd(), 0, testPlayerCount);
        for(var score : scores) {
            Assertions.assertEquals(score.getValue(), scoreList.get(rank));
            ++rank;
        }
    }

    @Test
    void cooperativeRaidDungeonTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COOPERATIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();
        int status = player.raidDungeon.clearRaidDungeon(0, 0, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        // 잘못된 타입
        status = player.raidDungeon.clearRaidDungeon(resTower.id, 1, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 잘못된 스테이지
        status = player.raidDungeon.clearRaidDungeon(resTower.id, 0, 0, contentRewards, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        int nowYmd = UtcZoneDateTime.getYmd();
        SRedis.INSTANCE.content.cooperativeRaidScore.clearForTest(resRaidDungeon.id, nowYmd);

        // 첫 클리어
        status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, 5300, contentRewards, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resReward = resRaidDungeon.clearRewardsByStage.get(1);
        for(var reward : resReward) {
            long count  = (long)(reward.count + reward.count * resRaidDungeon.rewardBonusPercent);
            if(reward.type == ZCategory.Type.ITEM) {
                Assertions.assertEquals(count, player.itemBag.getItemCount(reward.rewardId));
            } else if(reward.type == ZCategory.Type.CURRENCY){
                var resItem = ResourceManager.INSTANCE.item.getItem(reward.rewardId);
                Assertions.assertEquals(count, player.currency.getCurrency(resItem.type));
            } else {
                Assertions.fail();
            }
        }

        // 서버 점수 체크
        long accumulateScore = SRedis.INSTANCE.content.cooperativeRaidScore.get(resRaidDungeon.id, nowYmd);
        Assertions.assertEquals(accumulateScore, 5300);
    }

    @Test
    void cooperativeRaidDungeonMultiPlayerTest() {
        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COOPERATIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        int nowYmd = UtcZoneDateTime.getYmd();
        SRedis.INSTANCE.content.cooperativeRaidScore.clearForTest(resRaidDungeon.id, nowYmd);
        final int testPlayerCount = 100;
        var players = new ArrayList<WorldPlayer>();
        for(int i = 0 ; i < testPlayerCount; i++) {
            players.add(TestPlayerUtils.of());
        }

        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();

        long totalScore = 0;
        for(var player : players) {
            long score = SRandomUtils.nextInt(0, 10000000);
            totalScore += score;
            int status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, score, contentRewards, out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        long accumulateScore = SRedis.INSTANCE.content.cooperativeRaidScore.get(resRaidDungeon.id, nowYmd);
        Assertions.assertEquals(accumulateScore, totalScore);
    }

    @Test
    void cooperativeeRaidDungeonMultiPlayerGetRewardTest() {
        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COOPERATIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        int nowYmd = UtcZoneDateTime.getYmd();
        SRedis.INSTANCE.content.cooperativeRaidScore.clearForTest(resRaidDungeon.id, nowYmd);
        final int testPlayerCount = 100;
        var players = new ArrayList<WorldPlayer>();
        for(int i = 0 ; i < testPlayerCount; i++) {
            players.add(TestPlayerUtils.of());
        }

        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();
        long totalScore = 0;
        for(var player : players) {
            long score = SRandomUtils.nextInt(0, 10000000);
            totalScore += score;
            int status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, score, contentRewards, out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        long accumulateScore = SRedis.INSTANCE.content.cooperativeRaidScore.get(resRaidDungeon.id, nowYmd);
        Assertions.assertEquals(accumulateScore, totalScore);

        Assertions.assertTrue(World.INSTANCE.worldSchedule.dailyResetTaskByTest().giveCooperativeRaidDungeonRewardsForTest(nowYmd));
    }
    @Test
    void competitiveRaidDungeonMultiPlayerGetRewardTest() {
        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID && x.mode == DungeonMode.COMPETITIVE_RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        final int testPlayerCount = 100;
        var players = new ArrayList<WorldPlayer>();
        for(int i = 0 ; i < testPlayerCount; i++) {
            players.add(TestPlayerUtils.of());
        }
        int nowYmd = UtcZoneDateTime.getYmd();
        SRedis.INSTANCE.rank.competitiveRank.clearForTest(resRaidDungeon.id, nowYmd);
        var contentRewards = new ArrayList<TContentReward>();
        var out = TRaidDungeon.newBuilder();

        var scoreList = new ArrayList<Long>();
        for(var player : players) {
            long score = SRandomUtils.nextInt(0, 10000000);
            scoreList.add(score);
            int status = player.raidDungeon.clearRaidDungeon(resRaidDungeon.id, 1, score, contentRewards, out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        Collections.sort(scoreList, Collections.reverseOrder());
        int rank = 0;
        var scores = SRedis.INSTANCE.rank.competitiveRank.entries(resRaidDungeon.id, UtcZoneDateTime.getYmd(), 0, testPlayerCount);
        for(var score : scores) {
            Assertions.assertEquals(score.getValue(), scoreList.get(rank));
            ++rank;
        }
        Assertions.assertTrue(World.INSTANCE.worldSchedule.dailyResetTaskByTest().giveCompetitiveRaidDungeonRewardsForTest(nowYmd));
    }
}
