package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.stages.ResourceMap;

public class ResourceMapManager  implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourcePartsSlotManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceMap> maps;

    public static ResourceMapManager of(ResourceContext ctx) {
        return new ResourceMapManager(ctx);
    }
    private ResourceMapManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.maps = load(ResourceMap::new, ctx.absolutePathBy(ResourceFile.MAPS), "Map");
    }

    public ResourceMap get(long id) {
        return maps.get(id);
    }

    public boolean verify() {

        return true;
    }

}
