package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.quests.ResourceQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResourceQuestManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceQuestManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Integer, ResourceQuest> quests;

    public static ResourceQuestManager of(ResourceContext ctx) {
        return new ResourceQuestManager(ctx);
    }

    private ResourceQuestManager(ResourceContext ctx) {
        this.ctx = ctx;
        var temp = load(ResourceQuest::new, ctx.absolutePathBy(ResourceFile.QUESTS), "Quest");
        this.quests = ImmutableMap.copyOf(temp.values().stream()
                .collect(Collectors.toMap(x -> x.step, x -> x)));
    }

    public ResourceQuest getByStep(int step) {
        return quests.get(step);
    }

    public int questSize() {
        return quests.size();
    }
    public List<ResourceQuest> getAll() {
        return new ArrayList<>(quests.values());
    }
    public boolean verify() {
        if(!checkQuest()) {
            return false;
        }

        return true;
    }

    private boolean checkQuest() {
        var errors = new ArrayList<String>();
        for(var quest : quests.values()) {
            if(quest.type == ZCondition.Type.NONE) {
                errors.add(String.format("quest type is none - questId(%d)", quest.id));
            }

            if(Objects.isNull(quest.condition)) {
                errors.add(String.format("quest condition is null - questId(%d)", quest.id));
            }

            for(var reward : quest.rewards) {
                if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                    errors.add(String.format("reward is invalid - questId(%d), rewardId(%d), rewardType(%d), rewardCount(%d)",
                            quest.id, reward.rewardId, reward.type.getNumber(), reward.count));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
