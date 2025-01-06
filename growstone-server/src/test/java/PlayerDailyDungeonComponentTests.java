import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TDailyDungeon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.types.DungeonType;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerDailyDungeonComponentTests extends BaseServerTests {

    @Test
    void dailyDungeonInfo() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resDailyDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.DAILY)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resDailyDungeon);

        var model = player.dailyDungeon.getOrCreate(resDailyDungeon.id);
        Assertions.assertNotNull(model);

        var l = player.dailyDungeon.getTDailyDungeon();
        Assertions.assertNotNull(l);
        Assertions.assertEquals(1, l.size());

        int nowYmd = UtcZoneDateTime.getYmd();
        for(var info : l) {
            Assertions.assertEquals(resDailyDungeon.id, info.getId());
            Assertions.assertEquals(resDailyDungeon.ticket.ticketCount, info.getRemainTicketCount());
            Assertions.assertEquals(resDailyDungeon.adViewMaxCount, info.getRemainAdViewCount());
            Assertions.assertEquals(nowYmd, model.reset_ymd);
            Assertions.assertEquals(1, info.getStage());
        }
    }
    @Test
    void dailyDungeonStartTest() {
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

        int status = player.dailyDungeon.isEnableStartDungeon(0, 0);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE,status);

        // 잘못된 일일던전ID
        status = player.dailyDungeon.isEnableStartDungeon(resRaidDungeon.id, 0);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST,status);

        // 잘못된 일일던전스테이지ID
        int stageSize = resDailyDungeon.clearRewardsByStage.size();
        status = player.dailyDungeon.isEnableStartDungeon(resDailyDungeon.id, stageSize + 1);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST,status);

        // 레이드던전 시작
        status = player.dailyDungeon.isEnableStartDungeon(resDailyDungeon.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 시작을 해도 티켓이 없어지면 안된다.
        var model = player.dailyDungeon.getOrCreate(resDailyDungeon.id);
        Assertions.assertEquals(resDailyDungeon.ticket.ticketCount, model.remain_ticket_count);
    }

    @Test
    void dailyDungeonViewAdvertiseTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resDailyDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.DAILY)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resDailyDungeon);

        var resTower =  ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        var model = player.dailyDungeon.getOrCreate(resDailyDungeon.id);
        Assertions.assertEquals(resDailyDungeon.ticket.ticketCount, model.remain_ticket_count);
        Assertions.assertEquals(resDailyDungeon.adViewMaxCount, model.remain_view_ad_count);

        var out = TDailyDungeon.newBuilder();
        int status = player.dailyDungeon.viewAdvertise(0, 0, out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        status = player.dailyDungeon.viewAdvertise(resTower.id, 0, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        model.remain_view_ad_count = 0;

        // 광고를 볼 수 없을때
        status = player.dailyDungeon.viewAdvertise(resTower.id, resTower.clearRewardsByStage.size(), out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        final int ticketCount = model.remain_ticket_count;
        ++model.remain_view_ad_count;

        status = player.dailyDungeon.viewAdvertise(resDailyDungeon.id, 1, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(ticketCount + 1, model.remain_ticket_count);
        Assertions.assertEquals(0, model.remain_view_ad_count);
    }

    @Test
    void clearDailyDungeonTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resDailyDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.DAILY)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resDailyDungeon);
        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        int nowYmd = UtcZoneDateTime.getYmd();
        var model = player.dailyDungeon.getOrCreate(resDailyDungeon.id);
        Assertions.assertEquals(resDailyDungeon.id, model.dungeon_data_id);
        Assertions.assertEquals(resDailyDungeon.ticket.ticketCount, model.remain_ticket_count);
        Assertions.assertEquals(resDailyDungeon.adViewMaxCount, model.remain_view_ad_count);
        Assertions.assertEquals(1, model.stage);
        Assertions.assertEquals(nowYmd, model.reset_ymd);


        var out = TDailyDungeon.newBuilder();
        var rewards = new ArrayList<TContentReward>();
        int status = player.dailyDungeon.clearDungeon(resTower.id, 1, 1,out, rewards);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 잘못된 스테이지
        status = player.dailyDungeon.clearDungeon(resDailyDungeon.id, -1, 1,out, rewards);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        model.remain_ticket_count = 0;
        status = player.dailyDungeon.clearDungeon(resDailyDungeon.id, 1, 1,out, rewards);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_DUNGEON_TICKET, status);

        model.remain_ticket_count = 1;
        final int currentStage = model.stage;
        status = player.dailyDungeon.clearDungeon(resDailyDungeon.id, 1, 1,out, rewards);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resNextStage = resDailyDungeon.clearRewardsByStage.get(model.stage + 1);
        if(Objects.isNull(resNextStage)) {
            Assertions.assertEquals(currentStage , model.stage);
        } else {
            Assertions.assertEquals(currentStage + 1 , model.stage);
        }

        Assertions.assertEquals(0, model.remain_ticket_count);

        var resReward = resDailyDungeon.clearRewardsByStage.get(currentStage);
        Assertions.assertNotNull(resReward);

        for(var reward : resReward) {
            var count = player.categoryFilter.getMaterial(reward.type, reward.rewardId);
            Assertions.assertEquals(reward.count + reward.count * resDailyDungeon.rewardBonusPercent, count);
        }
    }
}
