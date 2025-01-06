package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZStat;
import com.supercat.growstone.network.messages.ZTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueEnchant;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueEnchantRatioGroup;

import java.util.ArrayList;
import java.util.Objects;

public class ResourceStoneStatueEnchantManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceStoneStatueEnchantManager.class);
    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceStoneStatueEnchant> stoneEnchants;
    private final ImmutableMap<Long, ResourceStoneStatueEnchantRatioGroup> stoneEnchantRatioGroups;

    public static ResourceStoneStatueEnchantManager of(ResourceContext ctx) {
        return new ResourceStoneStatueEnchantManager(ctx);
    }

    private ResourceStoneStatueEnchantManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.stoneEnchants = load(ResourceStoneStatueEnchant::new, ctx.absolutePathBy(ResourceFile.STONE_STATUE_ENCHANTS), "StoneStatueEnchant");
        this.stoneEnchantRatioGroups = load(ResourceStoneStatueEnchantRatioGroup::new, ctx.absolutePathBy(ResourceFile.STONE_STATUE_ENCHANT_RATIO_GROUP), "StoneStatueEnchantRatioGroup");
    }

    public ResourceStoneStatueEnchant getStoneEnchants(long id) {
        return stoneEnchants.get(id);
    }

    public ResourceStoneStatueEnchant getStoneStatueEnchantByTier(ZTier.Type tier) {
        return stoneEnchants.values().stream()
                .filter(x -> x.tier == tier)
                .findFirst()
                .orElse(null);
    }

    public ResourceStoneStatueEnchantRatioGroup getRatioGroup(long id) {
        return stoneEnchantRatioGroups.get(id);
    }

    @Override
    public boolean verify() {
        if (stoneEnchants.isEmpty()) {
            return false;
        }

        if (stoneEnchantRatioGroups.isEmpty()) {
            return false;
        }

        if (!checkEnchantStat()) {
            return false;
        }

        if (!checkEnchantRatioGroup()) {
            return false;
        }

        if (!checkStoneStatueGameData()) {
            return false;
        }

        return true;
    }

    private boolean checkEnchantStat() {
        var errors = new ArrayList<String>();

        var distinctCount = stoneEnchants.values().stream()
                .map(x -> x.tier)
                .distinct()
                .count();
        if (distinctCount != stoneEnchants.size()) {
            errors.add(String.format("duplicate tier count - (%d)", distinctCount));
        }

        for (var enchant : stoneEnchants.values()) {
            if (enchant.tier == ZTier.Type.NONE) {
                errors.add(String.format("enchant tier is none - enchantId(%d)", enchant.id));
                continue;
            }

            for (var stat : enchant.enchants.values()) {
                if (stat.type == ZStat.Type.NONE) {
                    errors.add(String.format("enchant stat type is none - enchantId(%d)", enchant.id));
                    continue;
                }

                if (stat.min > stat.max) {
                    errors.add(String.format("enchant stat min is greater than max - enchantId(%d)", enchant.id));
                }
            }
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }

    private boolean checkEnchantRatioGroup() {
        var errors = new ArrayList<String>();

        for (var ratioGroup : stoneEnchantRatioGroups.values()) {
            if (ratioGroup.enchantRatios.isEmpty()) {
                errors.add(String.format("enchant ratio group is empty - ratioGroupId(%d)", ratioGroup.id));
                continue;
            }

            for (var ratio : ratioGroup.enchantRatios.values()) {
                if (ratio.tier == ZTier.Type.NONE) {
                    errors.add(String.format("enchant ratio tier is none - ratioGroupId(%d)", ratioGroup.id));
                    continue;
                }

                if (ratio.ratio < 0) {
                    errors.add(String.format("enchant ratio less than zero - ratioGroupId(%d)", ratioGroup.id));
                }
            }
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }

    private boolean checkStoneStatueGameData() {
        var errors = new ArrayList<String>();

        var resItem = ctx.item.getItem(GameData.STONE_STATUE.ENCHANT_CURRENCY_ID);
        if (Objects.isNull(resItem)) {
            errors.add(String.format("invalid enchant currency id - currencyId(%d)", GameData.STONE_STATUE.ENCHANT_CURRENCY_ID));
        }

        resItem = ctx.item.getItem(GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID);
        if (Objects.isNull(resItem)) {
            errors.add(String.format("invalid gem upgrade currency id - currencyId(%d)", GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID));
        }

        // TODO: 아바타 기본 외형 체크 필요


        return errors.isEmpty();
    }
}
