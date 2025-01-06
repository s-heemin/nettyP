package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.achievements.ResourceAchievement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResourceAchievementManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceAchievementManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceAchievement> achievements;
    public static ResourceAchievementManager of(ResourceContext ctx) {
        return new ResourceAchievementManager(ctx);
    }

    private ResourceAchievementManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.achievements = load(ResourceAchievement::new, ctx.absolutePathBy(ResourceFile.ACHIEVEMENTS), "Achievement");
    }

    public ResourceAchievement get(long id) {
        return achievements.get(id);
    }

    public List<ResourceAchievement> getAllByType(ZCondition.Type type) {
        return achievements.values().stream()
                .filter(x -> x.type == type)
                .collect(Collectors.toList());
    }
    public boolean verify() {
        if(!checkAchievement()) {
            return false;
        }

        return true;
    }

    public boolean checkAchievement() {
        var errors = new ArrayList<String>();
        for(var achievement : achievements.values()) {
            if(achievement.type == ZCondition.Type.NONE) {
                errors.add(String.format("achievement type is none - achievementId(%d)", achievement.id));
            }

            if(Objects.isNull(achievement.condition)) {
                errors.add(String.format("achievement condition is null - achievementId(%d)", achievement.id));
            }

            for(var reward : achievement.rewards) {
                if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                    errors.add(String.format("reward is invalid - achievementId(%d), rewardId(%d), rewardType(%d), rewardCount(%d)",
                            achievement.id, reward.rewardId, reward.type.getNumber(), reward.count));
                }
            }

            if(achievement.type == ZCondition.Type.GET_ITEM || achievement.type == ZCondition.Type.USE_ITEM) {
                if(achievement.condition.type == ZCategory.Type.NONE) {
                    errors.add(String.format("achievement condition type is none - achievementId(%d)", achievement.id));
                }
            } else {
                if(achievement.condition.type != ZCategory.Type.NONE) {
                    errors.add(String.format("achievement condition type is not none - achievementId(%d)", achievement.id));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

}
