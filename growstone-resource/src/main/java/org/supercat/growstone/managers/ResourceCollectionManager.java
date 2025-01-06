package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZStat;
import io.opencensus.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.collections.ResourceCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceCollectionManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceCollectionManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceCollection> collections;

    public static ResourceCollectionManager of(ResourceContext ctx) {
        return new ResourceCollectionManager(ctx);
    }
    private ResourceCollectionManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.collections = load(ResourceCollection::new, ctx.absolutePathBy(ResourceFile.COLLECTION), "Collection");
    }

    public ResourceCollection get(long id) {
        return collections.get(id);
    }

    public List<ResourceCollection> getAll() {
        return collections.values().stream().toList();
    }

    public boolean verify() {
        if(!checkCollection()) {
            return false;
        }

        return true;
    }

    private boolean checkCollection() {
        var errors = new ArrayList<String>();

        for(var collection : collections.values()) {
            if(collection.type == ZResource.Type.NONE) {
                errors.add(String.format("collection type is invalid - collectionId(%d)", collection.id));
            }

            if(collection.stat == ZStat.Type.NONE) {
                errors.add(String.format("collection stat is invalid - collectionId(%d)", collection.id));
            }

            if(collection.maxLevel <= 0) {
                errors.add(String.format("collection maxLevel is invalid - collectionId(%d)", collection.id));
            }

            if(collection.statValues.size() != collection.maxLevel) {
                errors.add(String.format("collection statValues size is invalid - collectionId(%d)", collection.id));
            }

            for(var condition : collection.conditions.values()) {
                var resGrowth = ctx.growth.get(condition.growthId);
                if(Objects.isNull(resGrowth)) {
                    errors.add(String.format("collection condition growthId is invalid - collectionId(%d), growthId(%d)", collection.id, condition.growthId));
                }

                if(condition.condition.size() != collection.maxLevel) {
                    errors.add(String.format("collection condition size is invalid - collectionId(%d)", collection.id));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

}
