package org.supercat.growstone.components;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.EventPlayerPlayGacha;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.containers.ContentReward;
import org.supercat.growstone.containers.Material;
import org.supercat.growstone.gachas.ResourceGachaLevelGroup;
import org.supercat.growstone.models.DMPlayerBuyShopItem;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.models.DMPlayerPickUpGachaPoint;
import org.supercat.growstone.rules.MailRules;
import org.supercat.growstone.rules.ShopRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.shops.ResourcePayment;
import org.supercat.growstone.shops.ResourceShopItem;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerShopComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerShopComponent.class);

    private WorldPlayer player;
    private LoadingCache<Long, DMPlayerGacha> gachaCache;
    private LoadingCache<Long, DMPlayerPickUpGachaPoint> pickUpCache;
    private ConcurrentHashMap<Long, DMPlayerBuyShopItem> shopItems;

    public PlayerShopComponent(WorldPlayer player) {
        this.player = player;
        this.gachaCache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::loadGacha));

        this.pickUpCache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::loadPickUpPoint));

        shopItems = new ConcurrentHashMap<>();
    }

    public void initialize() {
        shopItems = SDB.dbContext.buyShopItem.getAll(player.getId()).stream()
                .collect(Collectors.toMap(x -> x.shop_data_id, x -> x, (x, y) -> x, ConcurrentHashMap::new));
    }

    public List<TBuyShopItem> getTBuyShopItems() {
        var result = new ArrayList<TBuyShopItem>();
        int nowYmd = UtcZoneDateTime.getYmd();
        for (var shopItem : shopItems.values()) {
            var resShopItem = ResourceManager.INSTANCE.shop.get(shopItem.shop_data_id);
            if (Objects.isNull(resShopItem)) {
                continue;
            }

            if (!resShopItem.visible) {
                continue;
            }

            if(nowYmd != shopItem.ad_reset_day) {
                shopItem.ad_view_count = 0;
                shopItem.ad_reset_day = nowYmd;
            }

            int resetDay = UtcZoneDateTime.getResetDay(resShopItem.buyLimit.resetType);
            if(resetDay != shopItem.buy_reset_day) {
                shopItem.count = 0;
                shopItem.buy_reset_day = resetDay;
            }

            result.add(TBuyShopItem.newBuilder()
                    .setShopDataId(shopItem.shop_data_id)
                    .setCount(shopItem.count)
                    .setAdViewCount(shopItem.ad_view_count)
                    .build());
        }

        return result;
    }

    public DMPlayerBuyShopItem getOrCreate(long shopDataId) {
        return shopItems.get(shopDataId);
    }

    private DMPlayerGacha loadGacha(long index) {
        return SDB.dbContext.gacha.getOrDefault(player.getId(), index);
    }

    public DMPlayerGacha getOrCreateGacha(long index) {
        try {
            return gachaCache.getUnchecked(index);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    private DMPlayerPickUpGachaPoint loadPickUpPoint(long shopDataId) {
        return SDB.dbContext.pickUpGacha.getOrDefault(player.getId(), shopDataId);
    }

    public DMPlayerPickUpGachaPoint getOrCreatePickUpPoint(long shopDataId) {
        try {
            return pickUpCache.getUnchecked(shopDataId);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    public TGacha getTGacha(long shopDataId) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            return TGacha.newBuilder().build();
        }

        var model = getOrCreateGacha(resShop.gachaLevelGroupId);
        return TBuilderOf.buildOf(model);
    }

    public TPickUpGacha getTPickUpGacha(long shopDataId) {
        var model = getOrCreatePickUpPoint(shopDataId);
        var rewards = ShopRules.getRewards(model.rewards);
        return TBuilderOf.buildOfPickUpGacha(model.point, rewards.stream().collect(Collectors.toList()));
    }

    public int purchaseShopItem(long shopDataId, long count, Instant now, List<TContentReward> outRewards) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            logger.error("invalid shop data - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_RESOURCE;
        }

        if (!ShopRules.isBuyShopType(resShop.type)) {
            return StatusCode.INVALID_REQUEST;

        }
        if (!resShop.visible) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (resShop.startTime.isAfter(now) || resShop.endTime.isBefore(now)) {
            return StatusCode.NOT_AVAILABLE_TIME;
        }

        if (resShop.maxBuyCount < count) {
            return StatusCode.EXCEED_ONCE_PURCHASE_LIMIT;
        }

        var model = getOrCreate(shopDataId);
        if(resShop.type != ZShop.Type.CONTINUE && Objects.isNull(model)) {
            model = DMPlayerBuyShopItem.of(player.getId(), shopDataId, 0);
        }

        if (Objects.nonNull(resShop.buyLimit)) {
            if(resShop.buyLimit.resetType != ZBuyReset.Type.NONE) {
                int resetDay = UtcZoneDateTime.getResetDay(resShop.buyLimit.resetType);
                if (resetDay <= 0) {
                    return StatusCode.INVALID_REQUEST;
                }

                if (resetDay != model.buy_reset_day) {
                    model.count = 0;
                    model.buy_reset_day = resetDay;
                }
            }

            if (model.count + count > resShop.buyLimit.maxBuyCount) {
                return StatusCode.EXCEED_PURCHASE_LIMIT;
            }
        }

        if (resShop.type == ZShop.Type.CONTINUE) {
            int status = reviewContinueType(resShop);
            if (!StatusCode.isSuccess(status)) {
                return status;
            }

            if(Objects.isNull(model)) {
                model = DMPlayerBuyShopItem.of(player.getId(), shopDataId, 0);
            }

        } else if (resShop.type == ZShop.Type.CONDITION) {
            int status = reviewConditionType(resShop);
            if (!StatusCode.isSuccess(status)) {
                return status;
            }
        }

        // 인앱 결제는 따로 처리를 해야한다.
        if (resShop.payments.stream()
                .anyMatch(x -> x.type == ZPayment.Type.STORE_RECEIPT)) {
            // TODO : 인앱 결제 필요
        } else if (resShop.payments.stream()
                .anyMatch(x -> x.type == ZPayment.Type.AD)) {
        } else {
            var material = isEnoughPayment(resShop.payments);
            if (Objects.isNull(material)) {
                logger.error("not enough payment - playerId ({}), shopDataId: ({})", player.getId(), resShop.id);
                return StatusCode.NOT_ENOUGH_PAYMENT;
            }

            int status = useGachaPayment(material);
            if (status != StatusCode.SUCCESS) {
                logger.error("use gacha payment failed - playerId ({}), shopDataId: ({})", player.getId(), resShop.id);
                return status;
            }
        }

        model.count += count;

        SDB.dbContext.buyShopItem.save(model);

        shopItems.put(shopDataId, model);

        var rewards = new ArrayList<ContentReward>();
        for (var resReward : resShop.addRewards) {
            rewards.add(ContentReward.of(resReward.type, resReward.rewardId, resReward.count));
        }

        if(!rewards.isEmpty()) {
            rewards = ShopRules.computeDuplicateRewards(rewards);
            sendRewards(rewards, resShop.isGetMail, resShop.id);

            rewards.stream().forEach(x -> outRewards.add(TContentReward.newBuilder()
                    .setCategory(x.type)
                    .setDataId(x.dataId)
                    .setCount(x.count)
                    .build()));
        }

        if (resShop.type == ZShop.Type.CONDITION) {
            player.conditionPackage.complete(resShop.conditionPackageId);
        } else if(resShop.type == ZShop.Type.SHOPPASS) {
            player.shopPass.buyShopPass(resShop.shopPassId);
        }

        return StatusCode.SUCCESS;
    }

    private int reviewConditionType(ResourceShopItem resShop) {
        var model = player.conditionPackage.get(resShop.conditionPackageId);
        if (Objects.isNull(model)) {
            return StatusCode.INVALID_REQUEST;
        }

        int status = player.conditionPackage.isEnableComplete(resShop.conditionPackageId);
        if (status != StatusCode.SUCCESS) {
            logger.error("invalid condition package - playerId ({}), shopDataId: ({})", player.getId(), resShop.id);
            return status;
        }

        return StatusCode.SUCCESS;
    }

    private int reviewContinueType(ResourceShopItem resShop) {
        if (resShop.continueStepId > 1) {
            var prevResShop = ResourceManager.INSTANCE.shop.getStepItem(resShop.continueGroupId, resShop.continueStepId - 1);
            if (Objects.isNull(prevResShop)) {
                return StatusCode.INVALID_RESOURCE;
            }

            var model = getOrCreate(prevResShop.id);
            if (Objects.isNull(model)) {
                return StatusCode.INVALID_RESOURCE;
            }
        }

        var model = getOrCreate(resShop.id);
        if (Objects.nonNull(model)) {
            return StatusCode.INVALID_REQUEST;
        }

        return StatusCode.SUCCESS;
    }


    private int gacha(long shopDataId, int gachaCount, List<TContentReward> rewards) {
        return gacha(shopDataId, gachaCount, true, rewards);
    }

    public int gacha(long shopDataId, int gachaCount, boolean isCommercial, List<TContentReward> outRewards) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            logger.error("invalid shop data - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_RESOURCE;
        }

        if (!ShopRules.isGachaType(resShop.type)) {
            logger.error("invalid shop type - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_REQUEST;
        }

        if (!resShop.tryGachaCount.contains(gachaCount) && !isCommercial) {
            logger.error("invalid gacha count - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_REQUEST;
        }

        var resGachaLevel = ResourceManager.INSTANCE.gacha.getGachaLevelGroup(resShop.gachaLevelGroupId);
        if (Objects.isNull(resGachaLevel)) {
            logger.error("invalid gacha level group - playerId ({}), gachaLevelGroupId: ({})", player.getId(), resShop.gachaLevelGroupId);
            return StatusCode.INVALID_RESOURCE;
        }

        var resGachaGroup = ResourceManager.INSTANCE.gacha.getGachaGroup(resShop.gachaGroupId);
        if (Objects.isNull(resGachaGroup)) {
            logger.error("invalid gacha group - playerId ({}), gachaGroupId: ({})", player.getId(), resShop.gachaGroupId);
            return StatusCode.INVALID_RESOURCE;
        }

        if(!isCommercial) {
            var material = isEnoughPayment(resShop.payments);
            if (Objects.isNull(material)) {
                logger.error("not enough payment - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
                return StatusCode.NOT_ENOUGH_PAYMENT;
            }

            // 여기까지 왔으면 뽑기를 할 수 있다.
            int status = useGachaPayment(material);
            if (status != StatusCode.SUCCESS) {
                logger.error("use gacha payment failed - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
                return status;
            }
        }

        var rewards = new ArrayList<ContentReward>();
        var model = getOrCreateGacha(resShop.gachaLevelGroupId);
        for (int i = 0; i < gachaCount; ++i) {
            var gachaId = resGachaGroup.gachas.get(model.level);
            var resGacha = ResourceManager.INSTANCE.gacha.getGacha(gachaId);
            if (Objects.isNull(resGacha)) {
                logger.error("invalid gacha - playerId ({}), gachaGroupId ({}), gachaId ({})", player.getId(), resGachaGroup.id, gachaId);
                return StatusCode.INVALID_RESOURCE;
            }

            int rand = SRandomUtils.nextIntEnd(0, resGacha.maxRatio);
            for (var ratio : resGacha.addItems) {
                rand -= ratio.ratio;
                if (rand <= 0) {
                    rewards.add(ContentReward.of(ratio.type, ratio.dataId, 1));
                    break;
                }
            }

            refreshExp(model, resGachaLevel);
        }

        //보상 처리
        rewards = ShopRules.computeDuplicateRewards(rewards);
        sendRewards(rewards, resShop.isGetMail, shopDataId);

        SDB.dbContext.gacha.save(model);

        // 만약 픽업가차라면 픽업포인트를 올려주어야한다.
        if (resShop.type == ZShop.Type.PICKUP_GACHA) {
            var pickUpModel = getOrCreatePickUpPoint(resShop.id);
            pickUpModel.point += gachaCount;
            SDB.dbContext.pickUpGacha.updatePoint(pickUpModel);
        }

        rewards.stream().forEach(x -> outRewards.add(TContentReward.newBuilder()
                .setCategory(x.type)
                .setDataId(x.dataId)
                .setCount(x.count)
                .build()));

        player.topic.publish(new EventPlayerPlayGacha(resShop.gachaLevelGroupId, gachaCount));

        return StatusCode.SUCCESS;
    }

    public int getPickUpReward(long shopDataId, long point) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            logger.error("invalid shop data - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_RESOURCE;
        }

        if (resShop.type != ZShop.Type.PICKUP_GACHA) {
            logger.error("invalid shop type - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_REQUEST;
        }

        var resPickUp = ResourceManager.INSTANCE.gacha.getGachaPickUp(resShop.gachaPickUpId);
        if (Objects.isNull(resPickUp)) {
            logger.error("invalid pickup gacha - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.INVALID_RESOURCE;
        }

        var pickUpModel = getOrCreatePickUpPoint(shopDataId);
        if (pickUpModel.point < point) {
            logger.error("not enough point - playerId ({}), shopDataId: ({})", player.getId(), shopDataId);
            return StatusCode.NOT_ENOUGH_PAYMENT;
        }

        var l = ShopRules.getRewards(pickUpModel.rewards);
        for (var reward : resPickUp.pickUpRewards.values()) {
            if (reward.point > pickUpModel.point) {
                continue;
            }

            if (l.contains(reward.point)) {
                continue;
            }

            int status = player.categoryFilter.add(reward.type, reward.dataId, reward.count);
            if (!StatusCode.isSuccess(status)) {
                logger.error("add pickup reward failed - playerId ({}), shopDataId ({}), rewardId({})", player.getId(), shopDataId, reward.dataId);
                continue;
            }

            l.add(reward.point);
        }

        pickUpModel.rewards = JsonConverter.to(l);

        SDB.dbContext.pickUpGacha.updateReward(pickUpModel);

        return StatusCode.SUCCESS;
    }

    public int viewAdvertise(long shopDataId, List<TContentReward> outRewards) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (resShop.type != ZShop.Type.GACHA && resShop.type != ZShop.Type.PICKUP_GACHA) {
            return purchaseShopItem(shopDataId, 1, Instant.now(), outRewards);
        }

        var model = getOrCreateGacha(resShop.gachaLevelGroupId);

        int nowYmd = UtcZoneDateTime.getYmd();
        if (nowYmd != model.reset_ymd) {
            model.reset_ymd = nowYmd;
            model.ad_view_count = 0;
        }

        if (model.ad_view_count >= resShop.dailyViewMaxCount) {
            return StatusCode.ADVERTISE_VIEW_COUNT_EXCEEDED;
        }

        if (model.gacha_max_count < resShop.adViewGachaMinCount) {
            model.gacha_max_count = resShop.adViewGachaMinCount;
        }

        int status = gacha(shopDataId, model.gacha_max_count, outRewards);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        if (model.gacha_max_count < resShop.adViewGachaMaxCount) {
            model.gacha_max_count += 1;
        }

        model.ad_view_count += 1;

        SDB.dbContext.gacha.updateGachaMaxCount(model);

        return StatusCode.SUCCESS;
    }

    public void refreshExp(DMPlayerGacha model, ResourceGachaLevelGroup resLevelGroup) {
        var needExp = resLevelGroup.levelByExp.get(model.level);
        if (Objects.isNull(resLevelGroup)) {
            ++model.exp;
            return;
        }


        if (model.exp + 1 < needExp) {
            model.exp += 1;
            return;
        }

        model.exp = 0;
        model.level += 1;
    }

    private void sendRewards(List<ContentReward> rewards, boolean isSendMail, long shopDataId) {
        if (isSendMail) {
            var jmRewards = MailRules.getJMPlayerMailRewardByContentReward(rewards);
            player.mail.insertMail(ZMail.Type.SHOP_GACHA_ITEM.getNumber(), "system", jmRewards, Instant.now().plus(Duration.ofDays(1)));
        } else {
            for (var reward : rewards) {
                int status = player.categoryFilter.add(reward.type, reward.dataId, reward.count);
                if (!StatusCode.isSuccess(status)) {
                    logger.error("add gacha reward failed - playerId ({}), shopDataId ({}), rewardId({})", player.getId(), shopDataId, reward.dataId);
                }
            }
        }

    }

    private int useGachaPayment(Material material) {
        return player.categoryFilter.use(material.type, material.dataId, material.count);
    }

    private Material isEnoughPayment(List<ResourcePayment> payments) {
        for (var payment : payments) {
            if (payment.type == ZPayment.Type.AD || payment.type == ZPayment.Type.STORE_RECEIPT) {
                continue;
            }

            var categoryType = payment.type == ZPayment.Type.ITEM ? ZCategory.Type.ITEM : ZCategory.Type.CURRENCY;
            long hasCount = player.categoryFilter.getMaterial(categoryType, payment.dataId);
            if (hasCount >= payment.count) {
                return new Material(categoryType, payment.dataId, payment.count);
            }
        }

        return null;
    }

    public DMPlayerGacha getGachaModelByTest(long shopDataId) {
        var resShop = ResourceManager.INSTANCE.shop.get(shopDataId);
        if (Objects.isNull(resShop)) {
            return null;
        }

        return getOrCreateGacha(resShop.gachaLevelGroupId);
    }

    public DMPlayerPickUpGachaPoint getPickUpGachaModelByTest(long shopDataId) {
        return getOrCreatePickUpPoint(shopDataId);
    }
}
