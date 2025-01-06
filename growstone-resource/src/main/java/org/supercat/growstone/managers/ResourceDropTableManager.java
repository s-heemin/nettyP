package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.drops.ResourceDropTable;
import org.supercat.growstone.stages.ResourceStageGroup;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceDropTableManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceDropTableManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceDropTable> drops;

    public static ResourceDropTableManager of(ResourceContext ctx) {
        return new ResourceDropTableManager(ctx);
    }
    private ResourceDropTableManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.drops = load(ResourceDropTable::new, ctx.absolutePathBy(ResourceFile.DROPS), "DropTable");
    }

    public ResourceDropTable get(long id) {
        return drops.get(id);
    }

    public boolean verify() {
        if(!checkReward()) {
            return false;
        }

        return true;
    }

    public boolean checkReward() {
        var errors = new ArrayList<String>();
        for(var drop : drops.values()) {
          for(var reward : drop.rewards) {
              if(reward.count <= 0) {
                    errors.add(String.format("invalid reward count - dropId({%d}), rewardId({%d})", drop.id, reward.rewardId));
              }

              if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                  errors.add(String.format("drop reward is invalid - dropTableId ({%d}), category({%d}), dataId({%d}), count({%d})"
                          , drop.id, reward.type.getNumber(), reward.rewardId, reward.count));
              }
          }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }


}
