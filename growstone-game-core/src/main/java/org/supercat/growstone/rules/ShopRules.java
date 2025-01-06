package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.*;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.containers.ContentReward;
import org.supercat.growstone.models.DMPlayerBuyShopItem;
import org.supercat.growstone.shops.ResourceShopItem;
import org.supercat.growstone.shops.ResourceShopPass;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ShopRules {
    private static Set<ZShop.Type> GACHA_TYPES = Set.of(ZShop.Type.GACHA, ZShop.Type.PICKUP_GACHA);
    private static Set<ZShop.Type> BUY_TYPE = Set.of(ZShop.Type.NORMAL, ZShop.Type.CONTINUE, ZShop.Type.CONDITION, ZShop.Type.REMOVE_AD);


    public static List<ContentReward> getShopPassRewards(ResourceShopPass.Step stepInfo, int selectStep, ZShopPassReward.Type type,
                                                         int last_free_reward_step, int last_paid_reward_step, boolean isEnableGetPaid) {
        var rewards = new ArrayList<ContentReward>();
        if(type == ZShopPassReward.Type.FREE) {
            for(int i = last_free_reward_step + 1; i <= selectStep; i++) {
                var step = stepInfo.freeRewards.get(i);
                if(Objects.isNull(step)) {
                    continue;
                }
                rewards.add(ContentReward.of(step.type, step.rewardId, step.count));
            }
        } else if (type == ZShopPassReward.Type.PAID) {
            for(int i = last_paid_reward_step + 1; i <= selectStep; i++) {
                var step = stepInfo.paidRewards.get(i);
                if(Objects.isNull(step)) {
                    continue;
                }
                rewards.add(ContentReward.of(step.type, step.rewardId, step.count));
            }
        } else {
            for(int i = last_free_reward_step + 1; i <= selectStep; i++) {
                var step = stepInfo.freeRewards.get(i);
                if(Objects.isNull(step)) {
                    continue;
                }
                rewards.add(ContentReward.of(step.type, step.rewardId, step.count));
            }

            if(isEnableGetPaid) {
                for(int i = last_paid_reward_step + 1; i <= selectStep; i++) {
                    var step = stepInfo.paidRewards.get(i);
                    if(Objects.isNull(step)) {
                        continue;
                    }
                    rewards.add(ContentReward.of(step.type, step.rewardId, step.count));
                }
            }
        }

        return rewards;
    }
    public static ArrayList<ContentReward> computeDuplicateRewards(List<ContentReward> rewards) {
        var newRewards = new ArrayList<ContentReward>();
        for (var reward : rewards) {
            var found = newRewards.stream().filter(x -> x.type == reward.type && x.dataId == reward.dataId).findFirst();
            if (found.isPresent()) {
                found.get().count += reward.count;
            } else {
                newRewards.add(reward);
            }
        }
        return newRewards;
    }

    public static List<Integer> getRewards(String rewards) {
        var l = JsonConverter.of(rewards, Integer[].class);
        if(Objects.isNull(l)) {
            return List.of();
        }

        return Arrays.stream(l).collect(Collectors.toList());
    }

    public static boolean isGachaType(ZShop.Type type) {
        return GACHA_TYPES.contains(type);
    }

    public static boolean isBuyShopType(ZShop.Type type) {
        return BUY_TYPE.contains(type);
    }
}
