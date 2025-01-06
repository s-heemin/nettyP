package org.supercat.growstone.components;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TShopPass;
import com.supercat.growstone.network.messages.ZShopPassReward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SLog;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.containers.ContentReward;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.models.DMPlayerShopPass;
import org.supercat.growstone.rules.ShopRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.shops.ResourceShopPass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerShopPassComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerShopComponent.class);

    private WorldPlayer player;
    private LoadingCache<Long, DMPlayerShopPass> cache;

    public PlayerShopPassComponent(WorldPlayer player) {
        this.player = player;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
    }

    private DMPlayerShopPass load(long shopPassId) {
        return SDB.dbContext.shopPass.getOrDefault(player.getId(), shopPassId);
    }

    public DMPlayerShopPass getOrCreate(long shopPassId) {
        try {
            return cache.getUnchecked(shopPassId);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    public TShopPass getTShopPass(long shopPassId) {
        return TBuilderOf.buildOf(getOrCreate(shopPassId));
    }
    public void setOpenStep(long shopPassId, int step) {
        var model = getOrCreate(shopPassId);
        if(model.open_step >= step) {
            return;
        }

        model.open_step = step;

        SDB.dbContext.shopPass.save(model);
    }
    public void buyShopPass(long shopDataId) {
        var resShopPass = ResourceManager.INSTANCE.shopPass.get(shopDataId);
        if(Objects.isNull(resShopPass) || !resShopPass.visible) {
            logger.error("invalid shopPass - playerId({}), shopDataId({})", player.getId(), shopDataId);
            return;
        }

        var model = getOrCreate(shopDataId);
        if(model.is_paid) {
            logger.error("already paid - playerId({}), shopDataId({})", player.getId(), shopDataId);
            return;
        }

        model.is_paid = true;

        SDB.dbContext.shopPass.updatePaid(model);
    }
    public int getRewards(long shopPassId, int step, ZShopPassReward.Type type, List<TContentReward> outRewards) {
        if(type == ZShopPassReward.Type.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        var resShopPass = ResourceManager.INSTANCE.shopPass.get(shopPassId);
        if (Objects.isNull(resShopPass) || !resShopPass.visible) {
            return StatusCode.INVALID_RESOURCE;
        }

        var stepInfo = resShopPass.steps.get(step);
        if (Objects.isNull(stepInfo)) {
            logger.error("invalid step - playerId({}), shopPassId({}), step({})", player.getId(), shopPassId, step);
            return StatusCode.INVALID_REQUEST;
        }

        // 샵 모델 로드
        var model = getOrCreate(shopPassId);
        if(step > model.open_step || (!model.is_paid && type == ZShopPassReward.Type.PAID)) {
            return StatusCode.INVALID_REQUEST;
        }

        var rewards = ShopRules.getShopPassRewards(stepInfo, step, type, model.last_free_reward_step, model.last_paid_reward_step, model.is_paid);
        for(var reward : rewards) {
            int status = player.categoryFilter.add(reward.type,  reward.dataId, reward.count);
            if(!StatusCode.isSuccess(status)) {
                logger.error("add reward fail - playerId({}), shopPassId({}), step({}), type({}), reward({})", player.getId(), shopPassId, step, type, reward);
                continue;
            }
            outRewards.add(TBuilderOf.buildOf(reward.type, reward.dataId, reward.count, 0));
        }

        if(type == ZShopPassReward.Type.FREE) {
            model.last_free_reward_step = step;
        } else if(type == ZShopPassReward.Type.PAID){
            model.last_paid_reward_step = step;
        } else {
            model.last_free_reward_step = step;
            model.last_paid_reward_step = step;
        }

        SDB.dbContext.shopPass.updateRewards(model);

        return StatusCode.SUCCESS;
    }

}
