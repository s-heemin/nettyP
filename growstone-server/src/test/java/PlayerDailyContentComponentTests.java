import com.supercat.growstone.network.messages.ZDailyContent;
import com.supercat.growstone.network.messages.ZReward;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.rules.RewardRules;
import org.supercat.growstone.setups.SDB;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayerDailyContentComponentTests extends BaseServerTests {
    @Test
    public void dailyContentAttendanceTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);
        var resDailyContent = ResourceManager.INSTANCE.dailyContent.getByType(ZDailyContent.Type.ATTENDANCE);
        Assertions.assertNotNull(resDailyContent);

        var now = Instant.now();
        awaitFunc(player, 3);

        var model = player.dailyContent.getOrCreate(ZDailyContent.Type.ATTENDANCE);
        Assertions.assertNotNull(model);
        Assertions.assertEquals(1, model.progress);
        Assertions.assertEquals(model.state, ZReward.State.CAN_REWARD.getNumber());
        Assertions.assertEquals(UtcZoneDateTime.getYmd(), model.last_updated_date);

        int status = player.dailyContent.getReward(ZDailyContent.Type.ATTENDANCE, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(model.state, ZReward.State.GOT_REWARD.getNumber());
        Assertions.assertEquals(RewardRules.getReward(model.rewards).size(), 1);

        status = player.dailyContent.getReward(ZDailyContent.Type.ATTENDANCE, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 1주일간 보상 받을 수 있는지
        for(int i = 2; i <= resDailyContent.dailyRewards.size(); i++) {
            var bef = now.minus(Duration.ofDays(1));
            model.last_updated_date = UtcZoneDateTime.getYmd(bef);

            awaitFunc(player, 3);

            status = player.dailyContent.getReward(ZDailyContent.Type.ATTENDANCE, now, new ArrayList<>());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(i, model.progress);
            Assertions.assertEquals(model.state, ZReward.State.GOT_REWARD.getNumber());
            Assertions.assertEquals(UtcZoneDateTime.getYmd(), model.last_updated_date);
            Assertions.assertEquals(RewardRules.getReward(model.rewards).size(), i);
        }

        // 1주일이 지나면 리셋이 되는지
        player.dailyContent.resetForCheat(ZDailyContent.Type.ATTENDANCE, UtcZoneDateTime.getYmd(UtcZoneDateTime.getPlusDay(1)));
        awaitFunc(player, 3);

        Assertions.assertEquals(1, model.progress);
        Assertions.assertEquals(model.state, ZReward.State.CAN_REWARD.getNumber());
        Assertions.assertEquals(UtcZoneDateTime.getYmd(), model.last_updated_date);
        Assertions.assertEquals(RewardRules.getReward(model.rewards).size(), 0);

        // 1주일이 지난 후 보상 리셋이 되어 보상이 수령 되는지
        status = player.dailyContent.getReward(ZDailyContent.Type.ATTENDANCE, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(1, model.progress);
        Assertions.assertEquals(model.state, ZReward.State.GOT_REWARD.getNumber());
        Assertions.assertEquals(UtcZoneDateTime.getYmd(), model.last_updated_date);
        Assertions.assertEquals(RewardRules.getReward(model.rewards).size(), 1);

        // DB 체크
        var newModel = SDB.dbContext.dailyContent.getByType(player.getId(), ZDailyContent.Type.ATTENDANCE.getNumber());
        Assertions.assertNotNull(newModel);
        Assertions.assertEquals(1, newModel.progress);
        Assertions.assertEquals(newModel.state, ZReward.State.GOT_REWARD.getNumber());
        Assertions.assertEquals(UtcZoneDateTime.getYmd(), newModel.last_updated_date);
        Assertions.assertEquals(RewardRules.getReward(newModel.rewards).size(), 1);


        // 7일 출석체크 했지만 보상을 받지 않았을때 한번에 일괄 획득이 가능한지 체크
        model.progress = resDailyContent.dailyRewards.size();
        model.state = ZReward.State.CAN_REWARD.getNumber();

        status = player.dailyContent.getReward(ZDailyContent.Type.ATTENDANCE, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(resDailyContent.dailyRewards.size(), model.progress);
        Assertions.assertEquals(model.state, ZReward.State.GOT_REWARD.getNumber());
        Assertions.assertEquals(UtcZoneDateTime.getYmd(), model.last_updated_date);
        Assertions.assertEquals(RewardRules.getReward(model.rewards).size(), resDailyContent.dailyRewards.size());
    }

    @Test
    public void dailyContentStonePressTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resDailyContent = ResourceManager.INSTANCE.dailyContent.getByType(ZDailyContent.Type.STONE_PRESS);
        Assertions.assertNotNull(resDailyContent);

        var now = Instant.now();

        var model = player.dailyContent.getOrCreate(ZDailyContent.Type.STONE_PRESS);
        int status = player.dailyContent.getReward(ZDailyContent.Type.STONE_PRESS, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(model.progress, 1);
        Assertions.assertEquals(model.last_updated_date, now.getEpochSecond());

        status = player.dailyContent.getReward(ZDailyContent.Type.STONE_PRESS, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.DAILY_CONTENT_COOL_TIME_NOT_YET, status);
        awaitFunc(player, (int) resDailyContent.coolTimeSecond);

        for(int i = 0; i < resDailyContent.dailyCount - 1; i++) {
            now = Instant.now();
            status = player.dailyContent.getReward(ZDailyContent.Type.STONE_PRESS, now, new ArrayList<>());
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(model.progress, i + 2);
            awaitFunc(player, (int) resDailyContent.coolTimeSecond);
        }

        now = Instant.now();
        status = player.dailyContent.getReward(ZDailyContent.Type.STONE_PRESS, now, new ArrayList<>());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var aft1Day = now.plus(Duration.ofDays(1));
        status = player.dailyContent.getReward(ZDailyContent.Type.STONE_PRESS, aft1Day, new ArrayList<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(model.progress, 1);
    }

    private void awaitFunc(org.supercat.growstone.player.WorldPlayer player, int second) {
        var untilAt = Instant.now().plusSeconds(second);
        Awaitility.await()
                .pollInSameThread()
                .timeout(1, TimeUnit.HOURS)
                .pollDelay(1 / 60, TimeUnit.SECONDS)
                .until(() -> {
                    var tempNow = Instant.now();
                    player.update();
                    return tempNow.isAfter(untilAt);
                });
    }

}
