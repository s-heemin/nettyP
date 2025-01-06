package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.stages.ResourceStageGroup;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceStageGroupManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceStageGroupManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceStageGroup> stageGroups;

    public static ResourceStageGroupManager of(ResourceContext ctx) {
        return new ResourceStageGroupManager(ctx);
    }

    private ResourceStageGroupManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.stageGroups = load(ResourceStageGroup::new, ctx.absolutePathBy(ResourceFile.STAGE_GROUPS), "StageGroup");
    }

    public ResourceStageGroup get(long id) {
        return stageGroups.get(id);
    }

    public boolean verify() {
        if (!checkStages()) {
            return false;
        }

        return true;
    }

    private boolean checkStages() {
        var errors = new ArrayList<String>();

        for (var stageGroup : stageGroups.values()) {
            if (stageGroup.stages.isEmpty()) {
                errors.add(String.format("stageGroup has no stages - stageGroupId(%d)", stageGroup.id));
            }

            for (var stage : stageGroup.stages.values()) {
                var resMap = ctx.map.get(stage.mapId);
                if (Objects.isNull(resMap)) {
                    errors.add(String.format("stage has invalid mapId - stageGroupId(%d), mapId(%d)", stageGroup.id, stage.mapId));
                }

                var resDropTable = ctx.dropTable.get(stage.fieldDrop.id);
                if (Objects.isNull(resDropTable)) {
                    errors.add(String.format("stage has invalid fieldDropId - stageGroupId(%d), fieldDropId(%d)", stageGroup.id, stage.fieldDrop.id));
                }

                if(stage.clearRewards.isEmpty()) {
                    errors.add(String.format("stage has no clearRewards - stageGroupId(%d)", stageGroup.id));
                }

                for(var clearReward : stage.clearRewards) {
                    if(!checkReward(ctx, clearReward.type, clearReward.rewardId, clearReward.count)) {
                        errors.add(String.format("stage clear reward is invalid - stageGroupId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                , stageGroup.id, clearReward.type.getNumber(), clearReward.rewardId, clearReward.count));
                    }
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
