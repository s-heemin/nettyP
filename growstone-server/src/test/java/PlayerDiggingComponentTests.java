import com.supercat.growstone.network.messages.TDigging;
import com.supercat.growstone.network.messages.ZDiggingUpgrade;
import com.supercat.growstone.network.messages.ZDiggingUpgradeResponse;
import com.supercat.growstone.network.messages.ZUseAcceleratorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerDiggingComponentTests extends BaseServerTests {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDiggingComponentTests.class);

    @Test
    void upgradeDiggingZoneTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var type = ZDiggingUpgrade.Type.ZONE;
        var response = ZDiggingUpgradeResponse.newBuilder();
        int status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.NONE, response);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var model = player.digging.getUpgradeModelForCheat(type);
        Assertions.assertNotNull(model);

        var resZones = ResourceManager.INSTANCE.digging.getZones();
        for(var resZone : resZones) {
            if(model.level == GameData.DIGGING.diggingZoneMaxLevel) {
                status = player.digging.upgradeDigging(type, response);
                Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
                break;
            }
            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

            status = player.itemBag.add(resZone.itemId, resZone.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(resZone.id, model.level);
            long itemCount = player.itemBag.getItemCount(resZone.id);
            Assertions.assertEquals(0, itemCount);        }

    }

    @Test
    void upgradeDiggingSpoonTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var type = ZDiggingUpgrade.Type.SPOON;
        var response = ZDiggingUpgradeResponse.newBuilder();
        int status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.NONE, response);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var model = player.digging.getUpgradeModelForCheat(type);
        Assertions.assertNotNull(model);

        var resSpoons = ResourceManager.INSTANCE.digging.getSpoons();
        for(var resSpoon : resSpoons) {
            if(model.level == GameData.DIGGING.diggingSpoonMaxLevel) {
                status = player.digging.upgradeDigging(type, response);
                Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
                break;
            }

            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

            status = player.itemBag.add(resSpoon.itemId, resSpoon.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(resSpoon.id, model.level);
            long itemCount = player.itemBag.getItemCount(resSpoon.id);
            Assertions.assertEquals(0, itemCount);
        }

    }

    @Test
    void upgradeDiggingTierTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var type = ZDiggingUpgrade.Type.TIER;
        var response = ZDiggingUpgradeResponse.newBuilder();
        int status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.NONE, response);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var model = player.digging.getUpgradeModelForCheat(type);
        Assertions.assertNotNull(model);

        var resTiers = ResourceManager.INSTANCE.digging.getTiers();
        for(var resTier : resTiers) {
            if(model.level == GameData.DIGGING.diggingTierMaxLevel) {
                status = player.digging.upgradeDigging(type, response);
                Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
                break;
            }
            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

            status = player.itemBag.add(resTier.itemId, resTier.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(resTier.id, model.level);
            long itemCount = player.itemBag.getItemCount(resTier.id);
            Assertions.assertEquals(0, itemCount);
        }

    }

    @Test
    void upgradeDiggingTimeTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var type = ZDiggingUpgrade.Type.TIME;
        var response = ZDiggingUpgradeResponse.newBuilder();
        int status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.NONE, response);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var model = player.digging.getUpgradeModelForCheat(type);
        Assertions.assertNotNull(model);

        var resReduceTimes = ResourceManager.INSTANCE.digging.getReduceTimes();
        for(var resTime : resReduceTimes) {
            if(model.level == GameData.DIGGING.diggingReduceTimeMaxLevel) {
                status = player.digging.upgradeDigging(type, response);
                Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
                break;
            }
            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

            status = player.itemBag.add(resTime.itemId, resTime.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.digging.upgradeDigging(type, response);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
            Assertions.assertEquals(resTime.id, model.level);

            long itemCount = player.itemBag.getItemCount(resTime.id);
            Assertions.assertEquals(0, itemCount);
        }
    }

    @Test
    void diggingTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var now = Instant.now();
        var tDiggings = new ArrayList<TDigging>();
        int status = player.digging.digging(List.of(100), tDiggings, now);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var diggings = new ArrayList<Integer>();
        diggings.add(0);
        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = player.digging.getOrCreate(0);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
    }

    @Test
    void MultiDiggingTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var tDiggings = new ArrayList<TDigging>();
        var diggings = new ArrayList<Integer>();
        var upgradeModel = player.digging.getUpgradeModelForCheat(ZDiggingUpgrade.Type.ZONE);
        var resZone = ResourceManager.INSTANCE.digging.getZone(upgradeModel.level);
        int maxZoneCount = Objects.isNull(resZone) ? GameData.DIGGING.diggingDefaultZoneCount : GameData.DIGGING.diggingDefaultZoneCount + resZone.addZoneCount;
        for(int i = 0; i < maxZoneCount; ++i) {
            diggings.add(i);
        }

        var now = Instant.now();
        int status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        for(int i = 0; i < maxZoneCount; ++i) {
            var model = player.digging.getOrCreate(i);
            Assertions.assertNotNull(model);
            Assertions.assertTrue(model.is_digging);
        }
    }

    @Test
    void diggingForReduceTimeTypeUpgradeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var tDiggings = new ArrayList<TDigging>();
        var diggings = new ArrayList<Integer>();

        diggings.add(0);
        int status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var now = Instant.now();
        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = player.digging.getOrCreate(0);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
        Assertions.assertEquals(now.plusSeconds(GameData.DIGGING.diggingDefaultSecond).getEpochSecond(),
                model.complete_at.getEpochSecond());

        var upgradeModel = player.digging.getUpgradeModelForCheat(ZDiggingUpgrade.Type.TIME);
        Assertions.assertNotNull(upgradeModel);

        var resReduceTime = ResourceManager.INSTANCE.digging.getReduceTime(upgradeModel.level + 1);
        Assertions.assertNotNull(resReduceTime);

        status = player.itemBag.add(resReduceTime.itemId, resReduceTime.count);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.TIME, ZDiggingUpgradeResponse.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        diggings.clear();
        diggings.add(1);

        status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        model = player.digging.getOrCreate(1);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
        Assertions.assertEquals(now.plusSeconds((int)(GameData.DIGGING.diggingDefaultSecond * (1 - resReduceTime.reducePercent))).getEpochSecond(),
                model.complete_at.getEpochSecond());
    }

    @Test
    void diggingForZoneTypeZoneUpgradeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var tDiggings = new ArrayList<TDigging>();
        var diggings = new ArrayList<Integer>();

        diggings.add(0);
        var upgradeModel = player.digging.getUpgradeModelForCheat(ZDiggingUpgrade.Type.ZONE);
        var resZone = ResourceManager.INSTANCE.digging.getZone(upgradeModel.level);
        int maxZoneCount = Objects.isNull(resZone) ? GameData.DIGGING.diggingDefaultZoneCount : GameData.DIGGING.diggingDefaultZoneCount + resZone.addZoneCount;
        diggings.add(maxZoneCount);

        var now = Instant.now();
        int status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        diggings.clear();
        diggings.add(0);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        resZone = ResourceManager.INSTANCE.digging.getZone(upgradeModel.level + 1);
        Assertions.assertNotNull(resZone);

        status = player.itemBag.add(resZone.itemId, resZone.count);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.upgradeDigging(ZDiggingUpgrade.Type.ZONE, ZDiggingUpgradeResponse.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        diggings.add(maxZoneCount);
        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.DIGGING_NOT_FINISH, status);

        diggings.clear();
        diggings.add(1);
        diggings.add(maxZoneCount);

        status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = player.digging.getOrCreate(1);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
        model = player.digging.getOrCreate(maxZoneCount);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
    }

    @Test
    void useAcceleratorTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var tDiggings = new ArrayList<TDigging>();
        var diggings = new ArrayList<Integer>();
        diggings.add(0);

        int status = player.itemBag.add(GameData.DIGGING.spoonId, diggings.size());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var now = Instant.now();
        status = player.digging.digging(diggings, tDiggings, now);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = player.digging.getOrCreate(0);
        Assertions.assertNotNull(model);
        Assertions.assertTrue(model.is_digging);
        Assertions.assertEquals(now.plusSeconds(GameData.DIGGING.diggingDefaultSecond).getEpochSecond(),
                model.complete_at.getEpochSecond());
        final long beforeCompleteAt = model.complete_at.getEpochSecond();

        var out = ZUseAcceleratorResponse.newBuilder();
        status = player.digging.useAccelerator(0, 1, false, now, out);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_ITEM, status);

        status = player.itemBag.add(GameData.PLAYER.acceleratorItemId, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.digging.useAccelerator(0, 1, false, now, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(beforeCompleteAt - GameData.PLAYER.acceleratorSecond, model.complete_at.getEpochSecond());
        long count = player.itemBag.getItemCount(GameData.PLAYER.acceleratorItemId);
        Assertions.assertEquals(0, count);

        status = player.digging.useAccelerator(0, 2, true, now, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.digging.useAccelerator(0, 1, true, now, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        count = player.itemBag.getItemCount(GameData.PLAYER.acceleratorItemId);
        Assertions.assertEquals(0, count);

        for(int i = 0; i < GameData.PLAYER.acceleratorDayMaxCountByCommercial - 1; i++) {
            status = player.digging.useAccelerator(0, 1, true, now, out);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        status = player.digging.useAccelerator(0, 1, true, now, out);
        Assertions.assertEquals(StatusCode.FAIL, status);
    }

    @Test
    void viewCommercialTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        int status = player.itemBag.add(GameData.DIGGING.spoonId, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 스푼이 남아있으면 광고를 볼 수 없다.
        status = player.digging.viewAdvertise();
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.itemBag.use(GameData.DIGGING.spoonId, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        // 광고를 보게되면 하루에 받을 수 있는 스푼수를 받는다.
        status = player.digging.viewAdvertise();
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(player.itemBag.getItemCount(GameData.DIGGING.spoonId), GameData.DIGGING.diggingDefaultSpoonCount);
    }
}
