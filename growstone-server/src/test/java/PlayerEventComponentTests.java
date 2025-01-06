import com.supercat.growstone.network.messages.*;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.World;
import org.supercat.growstone.components.playerEventComponents.PlayerEventCumulativeConsume;
import org.supercat.growstone.events.EventPlayerBuyShopItemUseDiamond;
import org.supercat.growstone.player.WorldPlayer;

import org.supercat.growstone.components.playerEventComponents.PlayerEventAttendance;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.ResourceAttendanceEvent;
import org.supercat.growstone.models.DMEvent;
import org.supercat.growstone.rules.EventRules;
import org.supercat.growstone.rules.RewardRules;
import org.supercat.growstone.setups.SDB;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerEventComponentTests extends BaseServerTests {
    @Test
    public void playerAttendanceEventTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resEvent = ResourceManager.INSTANCE.event.getAllByType(ZEvent.Type.ATTENDANCE).stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resEvent);

        var testEvent = SDB.dbContext.worldEvent.getAllByActive();
        if (testEvent.isEmpty()) {
            testEvent.add(createAttendanceEvent(resEvent.id));
        }

        awaitFunc(player, 3);

        long eventId = testEvent.get(0).id;
        var model = player.event.getEvent(eventId);
        Assertions.assertNotNull(model);

        Assertions.assertTrue(model instanceof PlayerEventAttendance);
        var attendance = (PlayerEventAttendance) model;
        Assertions.assertNotNull(attendance);

        var outResult = new ArrayList<TContentReward>();
        int status = player.event.getReward(eventId, 0, TPlayerEvent.newBuilder(), outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(ZEventProgress.State.REWARDED.getNumber(), model.getModel().state);
        var l = EventRules.getAttendanceReward(model.getModel().rewards);
        Assertions.assertEquals(1, l.size());
        Assertions.assertEquals(1, l.get(0));

        var dm = SDB.dbContext.event.get(model.getModel().id);
        Assertions.assertNotNull(dm);
        Assertions.assertEquals(ZEventProgress.State.REWARDED.getNumber(), dm.state);
        Assertions.assertEquals(1, EventRules.getAttendanceReward(dm.rewards).size());

        ResourceAttendanceEvent resAttendance = (ResourceAttendanceEvent) resEvent;
        Assertions.assertNotNull(resAttendance);

        var now = Instant.now();
        int befProgress = model.getModel().progress;
        for(int i= 0; i < resAttendance.attendanceRewards.size(); i++) {
            model.getModel().last_updated_date = UtcZoneDateTime.getYmd(now.minus(Duration.ofDays(1)));
            attendance.attendance();

            if(resAttendance.attendanceRewards.size() - 1 == i) {
                // 기존에 1회 출석을 했기때문에 마지막은 출석이 되지 않아야 한다.
                Assertions.assertEquals(befProgress, model.getModel().progress);
            } else {
                Assertions.assertEquals(++befProgress, model.getModel().progress);
            }
        }
        Assertions.assertEquals(ZEventProgress.State.COMPLETE.getNumber(), model.getModel().state);

        // 모두 출석 후에 progress만큼 보상을 받을 수 있다.
        status = player.event.getReward(eventId, 0,  TPlayerEvent.newBuilder(), outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(ZEventProgress.State.REWARDED.getNumber(), model.getModel().state);
        Assertions.assertEquals(resAttendance.attendanceRewards.size(), EventRules.getAttendanceReward(model.getModel().rewards).size());
    }


    @Test
    @Timeout(value = 15 , unit = TimeUnit.MINUTES)
    public void cumulativeConsumeEventTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resEvent = ResourceManager.INSTANCE.event.getAllByType(ZEvent.Type.CUMULATIVE_CONSUMPTION_EVENT).stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resEvent);

        DMEvent testEvent = World.INSTANCE.event.getActiveEvents().stream()
                .filter(x -> x.event_data_id == resEvent.id)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(testEvent)) {
            testEvent = createAttendanceEvent(resEvent.id);
        }

        awaitFunc(player, 60);

        var eventId = testEvent.id;
        var eventModel = player.event.getEvent(eventId);
        Assertions.assertNotNull(eventModel);

        Assertions.assertTrue(eventModel instanceof PlayerEventCumulativeConsume);
        var cumulativeConsume = (PlayerEventCumulativeConsume) eventModel;
        Assertions.assertNotNull(cumulativeConsume);

        var model = cumulativeConsume.getModel();
        Assertions.assertEquals(0, model.progress);

        player.topic.publish(new EventPlayerBuyShopItemUseDiamond(10, 10));
        awaitFunc(player, 3);

        Assertions.assertEquals(10, model.progress);

        List<Integer> values = cumulativeConsume.resEvent.rewards.keySet().stream()
                .collect(Collectors.toList());

        int floorKey = values.stream().min(Integer::compareTo).orElse(0);
        int ceilKey = values.stream().max(Integer::compareTo).orElse(0);
        int middleKey = values.stream().filter(x -> x != floorKey && x != ceilKey).findFirst().orElse(0);

        player.topic.publish(new EventPlayerBuyShopItemUseDiamond(middleKey, 10));
        awaitFunc(player, 3);

        Assertions.assertEquals(10 + middleKey, model.progress);

        var outRewards = new ArrayList<TContentReward>();
        int status = player.event.getReward(eventId, middleKey,  TPlayerEvent.newBuilder(), outRewards);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var l = RewardRules.getReward(model.rewards);
        Assertions.assertEquals(1, l.size());
        Assertions.assertEquals(middleKey, l.get(0));

        status = player.event.getReward(eventId, middleKey,  TPlayerEvent.newBuilder(), outRewards);
        Assertions.assertEquals(StatusCode.ALREADY_GET_REWARD, status);

        status = player.event.getReward(eventId, ceilKey,  TPlayerEvent.newBuilder(), outRewards);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.event.getReward(eventId, floorKey,  TPlayerEvent.newBuilder(), outRewards);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        l = RewardRules.getReward(model.rewards);
        Assertions.assertEquals(2, l.size());

        var dbModel = SDB.dbContext.event.get(model.id);
        Assertions.assertNotNull(dbModel);
        Assertions.assertEquals(2, RewardRules.getReward(dbModel.rewards).size());
        Assertions.assertEquals(10 + middleKey, dbModel.progress);
    }

    @Test
    public void firstPurchaseEventTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resEvent = ResourceManager.INSTANCE.event.getAllByType(ZEvent.Type.FIRST_PURCHASE_ATTENDANCE).stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resEvent);

        DMEvent testEvent = World.INSTANCE.event.getActiveEvents().stream()
                .filter(x -> x.event_data_id == resEvent.id)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(testEvent)) {
            testEvent = createAttendanceEvent(resEvent.id);
        }

        awaitFunc(player, 3);

        long eventId = testEvent.id;
        var model = player.event.getEvent(eventId);
        Assertions.assertNotNull(model);

        Assertions.assertTrue(model instanceof PlayerEventAttendance);
        var attendance = (PlayerEventAttendance) model;
        Assertions.assertNotNull(attendance);

        var dm = model.getModel();
        attendance.attendance();
        Assertions.assertEquals(0, dm.progress);
        Assertions.assertEquals(ZEventProgress.State.NOT_STARTED.getNumber(), dm.state);

        int status = player.event.getReward(eventId, 0,  TPlayerEvent.newBuilder(), List.of());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 첫 다이아 구매를 진행하고나선 이벤트가 시작된다.
        player.topic.publish(new EventPlayerBuyShopItemUseDiamond(1, 10));
        awaitFunc(player, 3);

        Assertions.assertEquals(1, dm.progress);
        Assertions.assertEquals(ZEventProgress.State.COMPLETE.getNumber(), dm.state);
        var outResult = new ArrayList<TContentReward>();
        status = player.event.getReward(eventId, 0,  TPlayerEvent.newBuilder(), outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        int befProgress = model.getModel().progress;
        var now = Instant.now();
        for(int i= 0; i < attendance.resEvent.attendanceRewards.size(); i++) {
            model.getModel().last_updated_date = UtcZoneDateTime.getYmd(now.minus(Duration.ofDays(1)));
            attendance.attendance();

            if(attendance.resEvent.attendanceRewards.size() - 1 == i) {
                // 기존에 1회 출석을 했기때문에 마지막은 출석이 되지 않아야 한다.
                Assertions.assertEquals(befProgress, model.getModel().progress);
            } else {
                Assertions.assertEquals(++befProgress, model.getModel().progress);
            }
        }
        Assertions.assertEquals(ZEventProgress.State.COMPLETE.getNumber(), model.getModel().state);

        status = player.event.getReward(eventId, 0,  TPlayerEvent.newBuilder(), outResult);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(attendance.resEvent.attendanceRewards.size(), EventRules.getAttendanceReward(model.getModel().rewards).size());
    }

    private void awaitFunc(WorldPlayer player, int second) {
        var untilAt = Instant.now().plusSeconds(second);
        Awaitility.await()
                .pollInSameThread()
                .timeout(300, TimeUnit.SECONDS)
                .pollDelay(1 / 60, TimeUnit.SECONDS)
                .until(() -> {
                    var tempNow = Instant.now();
                    player.update();
                    return tempNow.isAfter(untilAt);
                });
    }

    private DMEvent createAttendanceEvent(long eventDataId) {
        var startAt = Instant.now();
        var endAt = startAt.plus(Duration.ofDays(99));
        var model = DMEvent.of(eventDataId, startAt, endAt, endAt);
        SDB.dbContext.worldEvent.insert(model);

        return model;
    }
}
