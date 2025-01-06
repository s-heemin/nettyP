import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZPayment;
import com.supercat.growstone.network.messages.ZShop;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.containers.Material;
import org.supercat.growstone.shops.ResourcePayment;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class PlayerShopComponentTests extends BaseServerTests {

    @Test
    void gachaTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resGachaShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.GACHA)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resGachaShop);

        var resNormalShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.NORMAL)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resNormalShop);

        var l = new ArrayList<TContentReward>();
        int status = player.shop.gacha(-1, 1, false, l);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        status = player.shop.gacha(resNormalShop.id, 1, false, l);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.shop.gacha(resGachaShop.id, 1, false, l);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        int tryCount = resGachaShop.tryGachaCount.stream().findAny().get();
        status = player.shop.gacha(resGachaShop.id, tryCount, false, l);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_PAYMENT, status);

        var payment = resGachaShop.payments.stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(payment);

        var category = payment.type == ZPayment.Type.ITEM ? ZCategory.Type.ITEM : ZCategory.Type.CURRENCY;
        status = player.categoryFilter.add(category, payment.dataId, payment.count);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.shop.gacha(resGachaShop.id, tryCount, false, l);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var model = player.shop.getTGacha(resGachaShop.id);
        Assertions.assertNotNull(model);
        Assertions.assertEquals(tryCount, model.getExp());
    }

    @Test
    void gachaLevelUpTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resGachaShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.GACHA)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resGachaShop);

        var l = new ArrayList<TContentReward>();
        int tryCount = resGachaShop.tryGachaCount.stream().findAny().get();
        var payment = resGachaShop.payments.stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(payment);

        var model = player.shop.getGachaModelByTest(resGachaShop.id);
        Assertions.assertNotNull(model);

        var resGachaLevel = ResourceManager.INSTANCE.gacha.getGachaLevelGroup(model.index);
        Assertions.assertNotNull(resGachaLevel);

        var exp = resGachaLevel.levelByExp.get(model.level);
        Assertions.assertNotNull(exp);

        var needTryCount = exp;
        while(needTryCount > 0) {
            var category = payment.type == ZPayment.Type.ITEM ? ZCategory.Type.ITEM : ZCategory.Type.CURRENCY;
            int status = player.categoryFilter.add(category, payment.dataId, payment.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.shop.gacha(resGachaShop.id, tryCount, false, l);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            needTryCount -= tryCount;
        }

    }

    @Test
    void pickUpGachaTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resGachaShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.PICKUP_GACHA)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resGachaShop);

        var l = new ArrayList<TContentReward>();
        int tryCount = resGachaShop.tryGachaCount.stream().findAny().get();
        var payment = resGachaShop.payments.stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(payment);

        var model = player.shop.getPickUpGachaModelByTest(resGachaShop.id);
        Assertions.assertNotNull(model);

        var resPickUpGacha = ResourceManager.INSTANCE.gacha.getGachaPickUp(resGachaShop.gachaPickUpId);
        Assertions.assertNotNull(resPickUpGacha);
        var rewards = resPickUpGacha.pickUpRewards.values().stream().collect(Collectors.toList());
        Collections.shuffle(rewards);
        var reward = rewards.stream().findAny().get();

        var needTryCount = reward.point;
        while(needTryCount > 0) {
            var category = payment.type == ZPayment.Type.ITEM ? ZCategory.Type.ITEM : ZCategory.Type.CURRENCY;
            int status = player.categoryFilter.add(category, payment.dataId, payment.count);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            status = player.shop.gacha(resGachaShop.id, tryCount, false, l);
            Assertions.assertEquals(StatusCode.SUCCESS, status);

            needTryCount -= tryCount;
        }

        int status = player.shop.getPickUpReward(resGachaShop.id, reward.point);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void viewAdvertiseTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.GACHA)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resShop);

        var l = new ArrayList<TContentReward>();
        int status = player.shop.viewAdvertise(resShop.id, l);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        int nowYmd = UtcZoneDateTime.getYmd();
        var model = player.shop.getGachaModelByTest(resShop.id);
        Assertions.assertNotNull(model);
        Assertions.assertEquals(1, model.ad_view_count);
        Assertions.assertEquals(nowYmd, model.reset_ymd);
        Assertions.assertEquals(resShop.adViewGachaMinCount + 1, model.gacha_max_count );

        for(int i = 0; i < resShop.dailyViewMaxCount - 1; i++) {
            status = player.shop.viewAdvertise(resShop.id, l);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        status = player.shop.viewAdvertise(resShop.id, l);
        Assertions.assertEquals(StatusCode.ADVERTISE_VIEW_COUNT_EXCEEDED, status);
    }

    @Test
    void buyContinueTypeTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var now = Instant.now();
        var resShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.CONTINUE)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resShop);

        var resGachaShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.GACHA)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resGachaShop);
        var l = new ArrayList<TContentReward>();
        int status = player.shop.purchaseShopItem(-1,1, now, l);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        status = player.shop.purchaseShopItem(resGachaShop.id, 1, now, l);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.shop.purchaseShopItem(resShop.id, resShop.maxBuyCount + 1, now, l);
        Assertions.assertEquals(StatusCode.EXCEED_ONCE_PURCHASE_LIMIT, status);

        status = player.shop.purchaseShopItem(resShop.id, 1, now, l);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_PAYMENT, status);

        var payment = resShop.payments.stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(payment);

        var category = payment.type == ZPayment.Type.ITEM ? ZCategory.Type.ITEM : ZCategory.Type.CURRENCY;
        status = player.categoryFilter.add(category, payment.dataId, payment.count);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.shop.purchaseShopItem(resShop.id, 1, now, l);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void buyContinueTypeFailTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var now = Instant.now();
        var resShop = ResourceManager.INSTANCE.shop.getAll().stream()
                .filter(x -> x.type == ZShop.Type.CONTINUE)
                .filter(x -> x.continueStepId == 2)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resShop);
        var l = new ArrayList<TContentReward>();

        // 순차적으로 진행되지 않아 실패가 되어야함.
        int status = player.shop.purchaseShopItem(resShop.id, 1, now, l);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);
    }
}
