package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueGem;
import org.supercat.growstone.stoneStatue.ResourceStoneStatueGemUpgradeRatio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceStoneStatueGemManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceStoneStatueGemManager.class);
    public static final long MAIN_STONE_GEM_INDEX = 0; // 메인 보석 번호
    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceStoneStatueGem> stoneGems;
    private final ImmutableMap<Long, ResourceStoneStatueGemUpgradeRatio> stoneGemUpgradeRatios;
    private final ResourceStoneStatueGem mainStoneGem;
    private final int mainStoneMaxLevel;

    public static ResourceStoneStatueGemManager of(ResourceContext ctx) {
        return new ResourceStoneStatueGemManager(ctx);
    }

    private ResourceStoneStatueGemManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.stoneGems = load(ResourceStoneStatueGem::new, ctx.absolutePathBy(ResourceFile.STONE_STATUE_GEMS), "StoneStatueGem");
        this.stoneGemUpgradeRatios = load(ResourceStoneStatueGemUpgradeRatio::new, ctx.absolutePathBy(ResourceFile.STONE_STATUE_GEM_UPGRADE_RATIO), "StoneStatueGemUpgradeRatioGroup");
        this.mainStoneGem = loadMainStoneGem();
        this.mainStoneMaxLevel = loadMainStoneGemMaxLevel();
    }

    public List<ResourceStoneStatueGem> getStoneStatueGems() {
        return new ArrayList<>(stoneGems.values());
    }

    public ResourceStoneStatueGem getStoneStatueGem(long id) {
        return stoneGems.get(id);
    }

    public ResourceStoneStatueGem getMainStoneGem() {
        return mainStoneGem;
    }

    public int getMainStoneMaxLevel() {
        return mainStoneMaxLevel;
    }

    public ResourceStoneStatueGemUpgradeRatio getStoneStatueGemUpgradeRatio(long id) {
        return stoneGemUpgradeRatios.get(id);
    }

    public long getMainStoneGemID() {
        return MAIN_STONE_GEM_INDEX;
    }

    private ResourceStoneStatueGem loadMainStoneGem() {
        return stoneGems.getOrDefault(MAIN_STONE_GEM_INDEX, null);
    }

    private int loadMainStoneGemMaxLevel() {
        return mainStoneGem.statGroup.keySet().stream().max(Long::compareTo).orElse(0L).intValue();
    }

    @Override
    public boolean verify() {
        if (stoneGems.isEmpty()) {
            return false;
        }

        if (stoneGemUpgradeRatios.isEmpty()) {
            return false;
        }

        if (Objects.isNull(mainStoneGem)) {
            return false;
        }

        if (!checkStoneMainGem()) {
            return false;
        }

        if (!checkStoneGem()) {
            return false;
        }

        if (!checkStoneGemUpgradeRatio()) {
            return false;
        }

        return true;
    }

    private boolean checkStoneMainGem() {
        var errors = new ArrayList<String>();

        if (mainStoneGem.statGroup.isEmpty()) {
            errors.add("main stone gem stat group is empty");
        }

        for (var value : mainStoneGem.statGroup.values()) {
            if (value.id != mainStoneMaxLevel && value.price <= 0) {
                errors.add("main stone gem price is invalid");
                continue;
            }

            for (var entry : value.stats.entrySet()) {
                if (entry.getKey() == ZStat.Type.NONE) {
                    errors.add("main stone gem invalid stat type");
                    continue;
                }

                if (entry.getValue() <= 0) {
                    errors.add("main stone gem invalid stat value");
                }
            }
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }

    private boolean checkStoneGem() {
        var errors = new ArrayList<String>();

        for (var groups : stoneGems.values()) {
            if (groups.id == MAIN_STONE_GEM_INDEX) {
                // 메인 보석은 checkStoneMainGem 에서 검사
                continue;
            }

            if (groups.statGroup.isEmpty()) {
                errors.add(String.format("stat group is empty - groupId(%d)", groups.id));
                continue;
            }

            int gemMaxLevel = groups.statGroup.keySet().stream().max(Long::compareTo).orElse(0L).intValue();
            if (mainStoneMaxLevel != gemMaxLevel) {
                errors.add(String.format("max level statGroup count diff - Id(%d)", groups.id));
                continue;
            }

            for (var group : groups.statGroup.values()) {
                if (group.maxLevel <= 0) {
                    errors.add(String.format("max level is invalid - groupId(%d)", groups.id));
                    continue;
                }

                if (group.price <= 0) {
                    errors.add(String.format("price is invalid - groupId(%d)", groups.id));
                    continue;
                }

                for (var entry : group.stats.entrySet()) {
                    if (entry.getKey() == ZStat.Type.NONE) {
                        errors.add(String.format("invalid stat type - groupId(%d)", groups.id));
                        continue;
                    }

                    if (entry.getValue() <= 0) {
                        errors.add(String.format("invalid stat value - groupId(%d)", groups.id));
                        continue;
                    }

                    var resEnchantRatio = stoneGemUpgradeRatios.getOrDefault(group.statRatioGroupId, null);
                    if (Objects.isNull(resEnchantRatio)) {
                        errors.add(String.format("invalid stat ratio group - groupId(%d)", groups.id));
                    }
                }
            }
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }

    private boolean checkStoneGemUpgradeRatio() {
        var errors = new ArrayList<String>();

        for (var group : stoneGemUpgradeRatios.values()) {
            for (var ratio : group.upgradeRatios.values()) {
                if (ratio < 0) {
                    errors.add(String.format("invalid upgrade ratio - groupId(%d)", group.id));
                    continue;
                }

                if (ratio > 100) {
                    errors.add(String.format("invalid upgrade ratio - groupId(%d)", group.id));
                }
            }
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }
}
