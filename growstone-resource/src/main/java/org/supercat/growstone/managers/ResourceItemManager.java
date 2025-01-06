package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZTier;
import org.supercat.growstone.items.ResourceItem;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ResourceItemManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceItemManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceItem> items;

    public static ResourceItemManager of(ResourceContext ctx) {
        return new ResourceItemManager(ctx);
    }
    private ResourceItemManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.items = load(ResourceItem::new, ctx.absolutePathBy(ResourceFile.ITEMS), "Item");
    }

    public ResourceItem getItem(long id) {
        return items.get(id);
    }
    public List<ResourceItem> getAll() {
        return items.values().stream().toList();
    }

    public List<ResourceItem> getAllByCategory(ZCategory.Type category) {
        return items.values().stream()
                .filter(x -> x.category == category)
                .toList();
    }
    @Override
    public boolean verify() {
        if(!checkItemCategory()) {
            return false;
        }

        if(!checkDuplicateReward()) {
            return false;
        }

        return true;
    }

    private boolean checkItemCategory() {
        var errors = new ArrayList<String>();
        for (var resItem : items.values()) {
            if(resItem.id == 100) {
                continue;
            }

            if(resItem.category == ZCategory.Type.NONE) {
                errors.add("category is NONE - " + resItem.id);
            }

            if(resItem.type == ZResource.Type.NONE) {
                errors.add("type is NONE - " + resItem.id);
            }
        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkDuplicateReward() {
        var errors = new ArrayList<String>();
        for (var item : items.values()) {
            if(Objects.isNull(item.duplicateReward)) {
                continue;
            }

            if(!checkReward(ctx, item.duplicateReward.type, item.duplicateReward.rewardId, item.duplicateReward.count)) {
                errors.add(String.format("item duplicateReward is invalid - itemId ({%d}), category({%d}), dataId({%d}), count({%d})"
                        , item.id, item.duplicateReward.type.getNumber(), item.duplicateReward.rewardId, item.duplicateReward.count));
            }
        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
