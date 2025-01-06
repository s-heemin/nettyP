package org.supercat.growstone.managers;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZPayment;
import com.supercat.growstone.network.messages.ZShop;
import io.reactivex.rxjava3.internal.operators.observable.ObservableJoin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.shops.ResourceShopItem;

import java.util.*;

public class ResourceShopManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceShopManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceShopItem> shopItems;
    private final ImmutableMap<Integer, ImmutableMap<Integer, ResourceShopItem>> continueItems;

    public static ResourceShopManager of(ResourceContext ctx) {
        return new ResourceShopManager(ctx);
    }

    private ResourceShopManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.shopItems = load(ResourceShopItem::new, ctx.absolutePathBy(ResourceFile.SHOP_ITEMS), "ShopItem");
        this.continueItems = setContinueItems();
    }

    private ImmutableMap<Integer, ImmutableMap<Integer, ResourceShopItem>> setContinueItems() {
        Map<Integer, Map<Integer, ResourceShopItem>> groupedItems = new HashMap<>();

        for (var shopItem : shopItems.values()) {
            if (shopItem.type != ZShop.Type.CONTINUE) {
                continue;
            }

            groupedItems.computeIfAbsent(shopItem.continueGroupId, k -> new HashMap<>())
                    .put(shopItem.continueStepId, shopItem);
        }

        var immutableGroupedItems = new HashMap<Integer, ImmutableMap<Integer, ResourceShopItem>>();

        for (var entry : groupedItems.entrySet()) {
            immutableGroupedItems.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }

        return ImmutableMap.copyOf(immutableGroupedItems);
    }

    public ResourceShopItem getStepItem(int groupId, int stepId) {
        var groupItems = continueItems.get(groupId);
        if (Objects.isNull(groupItems)) {
            return null;
        }

        return groupItems.get(stepId);
    }

    public ResourceShopItem get(long id) {
        return shopItems.get(id);
    }

    public Collection<ResourceShopItem> getAll() {
        return shopItems.values();
    }

    public boolean verify() {
        if (!checkShopItems()) {
            return false;
        }

        return true;
    }

    private boolean checkShopItems() {
        var errors = new ArrayList<String>();

        for (var shopItem : shopItems.values()) {
            if (shopItem.type == ZShop.Type.NONE) {
                errors.add(String.format("shopItem type is invalid - shopItemId(%d)", shopItem.id));
            }

            if (shopItem.type != ZShop.Type.GACHA && shopItem.type != ZShop.Type.PICKUP_GACHA) {
                if (shopItem.maxBuyCount <= 0) {
                    errors.add(String.format("shopItem maxBuyCount is invalid - shopItemId(%d)", shopItem.id));
                }
            } else {
                if (shopItem.gachaGroupId <= 0) {
                    errors.add(String.format("shopItem gachaGroupId is invalid - shopItemId(%d)", shopItem.id));
                }
            }
            for (var pm : shopItem.payments) {
                if (pm.type == ZPayment.Type.ITEM || pm.type == ZPayment.Type.CURRENCY) {
                    var resItem = ctx.item.getItem(pm.dataId);
                    if (Objects.isNull(resItem)) {
                        errors.add(String.format("shopItem payment rewardId is invalid - shopItemId(%d)", shopItem.id));
                    }
                } else if (pm.type == ZPayment.Type.STORE_RECEIPT) {
                    if (Strings.isNullOrEmpty(pm.productId)) {
                        errors.add(String.format("shopItem payment productId is invalid - shopItemId(%d)", shopItem.id));
                    }
                } else if(pm.type == ZPayment.Type.AD) {
                    if(Objects.isNull(shopItem.buyLimit)) {
                        errors.add(String.format("shopItem payment ad is invalid - shopItemId(%d)", shopItem.id));
                    }
                }
                else {
                    errors.add(String.format("shopItem payment type is invalid - shopItemId(%d)", shopItem.id));
                }
            }

            for (var reward : shopItem.addRewards) {
                if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                    errors.add(String.format("shop reward is invalid - shopItem ({%d}), category({%d}), dataId({%d}), count({%d})"
                            , shopItem.id, reward.type.getNumber(), reward.rewardId, reward.count));
                }
            }

            if (shopItem.type == ZShop.Type.CONTINUE) {
                if (Objects.nonNull(shopItem.buyLimit)) {
                    errors.add(String.format("buyLimit is invalid - shopItemId(%d)", shopItem.id));
                }
                if (shopItem.continueGroupId <= 0) {
                    errors.add(String.format("continueGroupId is invalid - shopItemId(%d)", shopItem.id));
                }

                if (shopItem.continueStepId <= 0) {
                    errors.add(String.format("continueStepId is invalid - shopItemId(%d)", shopItem.id));
                }
            } else if (shopItem.type == ZShop.Type.CONDITION) {
                var resConditionPackage = ctx.conditionPackage.get(shopItem.conditionPackageId);
                if (Objects.isNull(resConditionPackage)) {
                    errors.add(String.format("conditionPackageId is invalid - shopItemId(%d)", shopItem.id));
                }
            } else if(shopItem.type == ZShop.Type.SHOPPASS) {
                if(!shopItem.addRewards.isEmpty()) {
                    errors.add(String.format("addRewards is not null - shopItemId(%d)", shopItem.id));
                }

                var resShopPass = ctx.shopPass.get(shopItem.shopPassId);
                if(Objects.isNull(resShopPass)) {
                    errors.add(String.format("shopPassId is invalid - shopItemId(%d)", shopItem.id));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

}
