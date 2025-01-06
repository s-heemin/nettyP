package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.shops.ResourceShopPass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ResourceShopPassManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceShopPassManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceShopPass> shopPasses;

    public static ResourceShopPassManager of(ResourceContext ctx) {
        return new ResourceShopPassManager(ctx);
    }

    private ResourceShopPassManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.shopPasses = load(ResourceShopPass::new, ctx.absolutePathBy(ResourceFile.SHOP_PASSES), "ShopPass");
    }

    public ResourceShopPass get(long id) {
        return shopPasses.get(id);
    }
    public Collection<ResourceShopPass> getAllByType(ZCondition.Type type) {
        return shopPasses.values().stream()
                .filter(x -> x.condition == type && x.visible)
                .toList();
    }
    public boolean verify() {
        if (!checkShopPass()) {
            return false;
        }

        if(!checkShopPassLevelUpCondition()) {
            return false;
        }

        if(!checkShopPassClearStageCondition()) {
            return false;
        }

        return true;
    }

    private boolean checkShopPass() {
        var errors = new ArrayList<String>();
        for (var shopPass : shopPasses.values()) {
            if (shopPass.condition == ZCondition.Type.NONE) {
                errors.add(String.format("shopPass condition is invalid - shopPassId(%d)", shopPass.id));
            }

            var resShopItem = ctx.shop.get(shopPass.shopItemId);
            if (Objects.isNull(resShopItem)) {
                errors.add(String.format("shopPass has invalid shopItemId - sopPassId(%d), shopItemId(%d)", shopPass.id, shopPass.shopItemId));
            }

            for (var step : shopPass.steps.values()) {

                for (var freeReward : step.freeRewards) {
                    if(!checkReward(ctx, freeReward.type, freeReward.rewardId, freeReward.count)) {
                        errors.add(String.format("free shop pass reward is invalid - shopPassId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                , shopPass.id, freeReward.type.getNumber(), freeReward.rewardId, freeReward.count));
                    }
                }

                for (var paidReward : step.paidRewards) {
                    if(!checkReward(ctx, paidReward.type, paidReward.rewardId, paidReward.count)) {
                        errors.add(String.format("paid shop pass reward is invalid - shopPassId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                , shopPass.id, paidReward.type.getNumber(), paidReward.rewardId, paidReward.count));
                    }
                }
            }
        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkShopPassLevelUpCondition() {
        var errors = new ArrayList<String>();
        for (var shopPass : shopPasses.values()) {
            if (shopPass.condition != ZCondition.Type.LEVEL_UP) {
                continue;
            }

            int beforeLevel = 0;
            for(var step : shopPass.steps.values()) {
                if(beforeLevel >= step.param1) {
                    errors.add(String.format("shopPass has invalid levelUpCondition - shopPassId(%d), step(%d)", shopPass.id, step.step));
                }
                beforeLevel = step.param1;
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkShopPassClearStageCondition() {
        var errors = new ArrayList<String>();
        for (var shopPass : shopPasses.values()) {
            if (shopPass.condition != ZCondition.Type.CLEAR_STAGE) {
                continue;
            }

            int beforeStage = 0;
            for(var step : shopPass.steps.values()) {
                if(beforeStage >= step.param2) {
                    errors.add(String.format("shopPass has invalid ClearStageCondition - shopPassId(%d), step(%d)", shopPass.id, step.step));
                }

                beforeStage = step.param2;
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}