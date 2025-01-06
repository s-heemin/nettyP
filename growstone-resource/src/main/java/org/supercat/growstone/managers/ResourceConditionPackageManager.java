package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.conditionPacakages.ResourceConditionPackage;

import java.util.*;

public class ResourceConditionPackageManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceConditionPackageManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceConditionPackage> conditions;
    public static ResourceConditionPackageManager of(ResourceContext ctx) {
        return new ResourceConditionPackageManager(ctx);
    }
    private ResourceConditionPackageManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.conditions = load(ResourceConditionPackage::new, ctx.absolutePathBy(ResourceFile.CONDITION_PACKAGES), "ConditionalPackage");
    }

    public Collection<ResourceConditionPackage> getAllByType(ZCondition.Type type) {
        return conditions.values().stream().filter(x -> x.type == type).toList();
    }

    public ResourceConditionPackage get(long id) {
        return conditions.get(id);
    }
    public boolean verify() {
        if(!checkCondition()) {
            return false;
        }

        return true;
    }

    private boolean checkCondition() {
        var errors = new ArrayList<String>();
        for(var condition : conditions.values()) {
          if(condition.type == ZCondition.Type.NONE) {
                errors.add(String.format("invalid condition type - id(%d)", condition.id));
          }

          if(condition.shopItemId > 0) {
              var resShop = ctx.shop.get(condition.shopItemId);
              if(Objects.isNull(resShop)) {
                    errors.add(String.format("invalid shop id - id(%d)", condition.shopItemId));
              }
          }

          for(var reward : condition.rewards) {
                if(reward.count <= 0) {
                        errors.add(String.format("invalid reward count - id(%d), rewardId(%d)", condition.id, reward.rewardId));
                }

              if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                  errors.add(String.format("condition package reward is invalid - conditionPackageId ({%d}), category({%d}), dataId({%d}), count({%d})"
                          , condition.id, reward.type.getNumber(), reward.rewardId, reward.count));
              }
          }

        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
