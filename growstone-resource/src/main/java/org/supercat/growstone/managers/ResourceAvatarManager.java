package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.avatars.ResourceAvatar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceAvatarManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceAvatarManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceAvatar> avatars;

    public static ResourceAvatarManager of(ResourceContext ctx) {
        return new ResourceAvatarManager(ctx);
    }

    private ResourceAvatarManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.avatars = load(ResourceAvatar::new, ctx.absolutePathBy(ResourceFile.AVATARS), "Avatar");
    }

    public ResourceAvatar get(long id) {
        return avatars.get(id);
    }

    public List<ResourceAvatar> getAll() {
        return new ArrayList<>(avatars.values());
    }

    public boolean verify() {
        if (!checkReward()) {
            return false;
        }

        return true;
    }

    public boolean checkReward() {
        var errors = new ArrayList<String>();
        for (var avatar : avatars.values()) {
            if (avatar.category != ZCategory.Type.AVATAR &&
                    avatar.category != ZCategory.Type.AVATAR_STONE_STATUE) {
                errors.add(String.format("avatar category type is invalid", avatar.id));
            }

            if (avatar.type != ZResource.Type.AVATAR &&
                    avatar.type != ZResource.Type.AVATAR_STATUE) {
                errors.add(String.format("avatar type is invalid", avatar.id));
            }

            if (avatar.tier == ZTier.Type.NONE) {
                errors.add(String.format("avatar tier is invalid", avatar.id));
            }

            if (!checkReward(ctx, avatar.duplicateReward.type, avatar.duplicateReward.rewardId, avatar.duplicateReward.count)) {
                errors.add(String.format("avatar reward is invalid - avatarId ({%d}), category({%d}), dataId({%d}), count({%d})"
                        , avatar.id, avatar.duplicateReward.type.getNumber(), avatar.duplicateReward.rewardId, avatar.duplicateReward.count));
            }
        }
        errors.forEach(logger::error);
        return errors.isEmpty();
    }

}
