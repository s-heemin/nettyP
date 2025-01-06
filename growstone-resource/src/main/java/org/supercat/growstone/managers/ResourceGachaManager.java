package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.gachas.ResourceGacha;
import org.supercat.growstone.gachas.ResourceGachaGroup;
import org.supercat.growstone.gachas.ResourceGachaLevelGroup;
import org.supercat.growstone.gachas.ResourceGachaPickUp;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceGachaManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceGachaManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceGacha> gachas;
    private final ImmutableMap<Long, ResourceGachaGroup> gachaGroups;
    private final ImmutableMap<Long, ResourceGachaLevelGroup> gachaLevelGroups;
    private final ImmutableMap<Long, ResourceGachaPickUp> gachaPickUps;


    public static ResourceGachaManager of(ResourceContext ctx) {
        return new ResourceGachaManager(ctx);
    }
    private ResourceGachaManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.gachas = load(ResourceGacha::new, ctx.absolutePathBy(ResourceFile.GACHA), "Gacha");
        this.gachaGroups = load(ResourceGachaGroup::new, ctx.absolutePathBy(ResourceFile.GACHA_GROUPS), "GachaGroup");
        this.gachaLevelGroups = load(ResourceGachaLevelGroup::new, ctx.absolutePathBy(ResourceFile.GACHA_LEVEL_GROUPS), "GachaLevelGroup");
        this.gachaPickUps = load(ResourceGachaPickUp::new, ctx.absolutePathBy(ResourceFile.GACHA_PICK_UPS), "GachaPickUp");
    }

    public ResourceGacha getGacha(long id) {
        return gachas.get(id);
    }
    public ResourceGachaGroup getGachaGroup(long id) {
        return gachaGroups.get(id);
    }
    public ResourceGachaLevelGroup getGachaLevelGroup(long id) {
        return gachaLevelGroups.get(id);
    }
    public ResourceGachaPickUp getGachaPickUp(long id) {
        return gachaPickUps.get(id);
    }

    public boolean verify() {
        if(!checkGachaItem()) {
            return false;
        }

        if(!checkGachaGroup()) {
            return false;
        }

        if(!checkGachaLevel()) {
            return false;
        }

        if(!checkGachaPickUpItem()) {
            return false;
        }

        return true;
    }

    public boolean checkGachaItem() {
        var errors = new ArrayList<String>();
        for(var gacha : gachas.values()) {
            for(var reward : gacha.addItems) {
                if(!checkReward(ctx, reward.type, reward.dataId, 1)) {
                    errors.add(String.format("gacha reward is invalid - gachaId ({%d}), category({%d}), dataId({%d})"
                            , gacha.id, reward.type.getNumber(), reward.dataId));
                }
            }

            if(gacha.maxRatio <= 0) {
                errors.add(String.format("invalid max ratio - gachaId(%d), maxRatio(%d)", gacha.id, gacha.maxRatio));
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
    public boolean checkGachaGroup() {
        var errors = new ArrayList<String>();
        for(var gachaGroup : gachaGroups.values()) {
            for(var ids : gachaGroup.gachas.values()) {
                var resGacha = ctx.gacha.getGacha(ids);
                if(Objects.isNull(resGacha)) {
                    errors.add(String.format("gacha id invalid, gachaGroupId(%d), gachaId(%d)", gachaGroup.id, ids));
                }
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    public boolean checkGachaLevel() {
        var errors = new ArrayList<String>();
        for(var gachaLevel : gachaLevelGroups.values()) {
           for(var kv : gachaLevel.levelByExp.entrySet()) {
               int level = kv.getKey();
               long exp = kv.getValue();

                if(level <= 0) {
                     errors.add(String.format("invalid level - gachaLevelId(%d), level(%d)", gachaLevel.id, level));
                }

                if(exp <= 0) {
                     errors.add(String.format("invalid exp - gachaLevelId(%d), exp(%d)", gachaLevel.id, exp));
                }
           }

        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    public boolean checkGachaPickUpItem() {
        var errors = new ArrayList<String>();
        for(var pickUp : gachaPickUps.values()) {
            for(var reward : pickUp.pickUpRewards.entrySet()) {
                int point = reward.getKey();
                var resReward = reward.getValue();
                if(point <= 0) {
                    errors.add(String.format("invalid point - gachaPickUpId(%d), point(%d)", pickUp.id, point));
                }

                if(!checkReward(ctx, resReward.type, resReward.dataId, resReward.count)) {
                    errors.add(String.format("gacha pickUp reward is invalid - gachaPickUp ({%d}), category({%d}), dataId({%d}), count({%d})"
                            , pickUp.id, resReward.type.getNumber(), resReward.dataId, resReward.count));
                }
            }

        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
