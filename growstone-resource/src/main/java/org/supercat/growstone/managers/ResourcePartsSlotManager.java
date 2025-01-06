package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZInterval;
import com.supercat.growstone.network.messages.ZPartsSlot;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.collections.ResourceCollection;
import org.supercat.growstone.growths.ResourcePartsSlot;

import java.util.ArrayList;
import java.util.Objects;

public class ResourcePartsSlotManager  implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourcePartsSlotManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<ZPartsSlot.Type, ResourcePartsSlot> partsSlots;

    public static ResourcePartsSlotManager of(ResourceContext ctx) {
        return new ResourcePartsSlotManager(ctx);
    }
    private ResourcePartsSlotManager(ResourceContext ctx) {
        this.ctx = ctx;
        var temp = load(ResourcePartsSlot::new, ctx.absolutePathBy(ResourceFile.PARTS_SLOT), "PartsSlot");;
        this.partsSlots = ImmutableMap.copyOf(temp.entrySet().stream()
                .collect(ImmutableMap.toImmutableMap(x -> x.getValue().type, x -> x.getValue())));
    }

    public ResourcePartsSlot get(ZPartsSlot.Type type) {
        return partsSlots.get(type);
    }

    public boolean verify() {
        if(!checkPartsSlot()) {
            return false;
        }

        return true;
    }

    private boolean checkPartsSlot() {
        var errors = new ArrayList<String>();
        var duplicate = new ArrayList<ZPartsSlot.Type>();

        for(var partsSlot : partsSlots.values()) {
            if(duplicate.contains(partsSlot.type)) {
                errors.add(String.format("partsSlot type is duplicate - partsSlotId(%d)", partsSlot.id));
            }

            if(partsSlot.type == ZPartsSlot.Type.NONE) {
                errors.add(String.format("partsSlot type is invalid - partsSlotId(%d)", partsSlot.id));
            }

            if(partsSlot.addPercent < 0) {
                errors.add(String.format("partsSlot addPercent is invalid - partsSlotId(%d)", partsSlot.id));
            }

            if(partsSlot.material.type == ZInterval.Type.NONE) {
                errors.add(String.format("partsSlot material type is invalid - partsSlotId(%d)", partsSlot.id));
            }

            if(partsSlot.material.count <= 0) {
                errors.add(String.format("partsSlot material count is invalid - partsSlotId(%d)", partsSlot.id));
            }

            var resItem = ctx.item.getItem(partsSlot.material.itemId);
            if(Objects.isNull(resItem)) {
                errors.add(String.format("partsSlot material itemId is invalid - partsSlotId(%d)", partsSlot.id));
            }

            duplicate.add(partsSlot.type);
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
