package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.avatars.ResourceAvatar;
import org.supercat.growstone.stats.ResourceStatGrowth;

import java.util.ArrayList;
import java.util.List;

public class ResourceStatGrowthManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceStatGrowthManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceStatGrowth> statGrowth;
    public static ResourceStatGrowthManager of(ResourceContext ctx) {
        return new ResourceStatGrowthManager(ctx);
    }

    private ResourceStatGrowthManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.statGrowth = load(ResourceStatGrowth::new, ctx.absolutePathBy(ResourceFile.STAT_GROWTH), "StatGrowth");
    }

    public ResourceStatGrowth get(long id) {
        return statGrowth.get(id);
    }

    public List<ResourceStatGrowth> getAll() {
        return statGrowth.values().stream().toList();
    }

    public boolean verify() {
        if (!checkReward()) {
            return false;
        }

        return true;
    }

    public boolean checkReward() {
        var errors = new ArrayList<String>();
        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
