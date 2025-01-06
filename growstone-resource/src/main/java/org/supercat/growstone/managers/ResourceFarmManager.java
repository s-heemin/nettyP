package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.IResourceManageable;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.ResourceFile;
import org.supercat.growstone.farm.ResourceFarm;
import org.supercat.growstone.farm.ResourceFarmPlant;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class ResourceFarmManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceFarmManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceFarm> farms;
    private final ImmutableMap<Long, ResourceFarmPlant> farmPlants;
    public final ImmutableSortedMap<Long, ResourceFarm> farmByExp;

    public static ResourceFarmManager of(ResourceContext ctx) {
        return new ResourceFarmManager(ctx);
    }

    private ResourceFarmManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.farms = load(ResourceFarm::new, ctx.absolutePathBy(ResourceFile.FARMS), "Farm");
        this.farmPlants = load(ResourceFarmPlant::new, ctx.absolutePathBy(ResourceFile.FARM_PLANTS), "FarmPlant");
        var tempFarmByExp = new TreeMap<Long, ResourceFarm>();
        for (var res : farms.values()) {
            long exp = res.requiredExp;
            tempFarmByExp.put(exp, res);
        }
        this.farmByExp = ImmutableSortedMap.copyOf(tempFarmByExp);
    }

    public ResourceFarm getFarm(int id) {
        return farms.get(id);
    }

    public ResourceFarm getFarmByExp(long exp) {
        var res = farmByExp.floorEntry(exp);
        if (Objects.isNull(res)) {
            return null;
        }

        return res.getValue();
    }

    public ResourceFarmPlant getFarmPlant(long id) {
        return farmPlants.get(id);
    }

    public boolean verify() {
        if (!checkSeedPlantTable()) {
            return false;
        }

        if (!checkSteal()) {
            return false;
        }

        return true;
    }

    private boolean checkSeedPlantTable() {
        var errors = new ArrayList<String>();
        for (var id : GameData.FARM.NORMAL_SEEDS_PLANT_TABLE.keySet()) {
            var res = farmPlants.get(id);
            if (Objects.isNull(res)) {
                errors.add(String.format("NormalSeedsPlantTable is invalid - id(%d)", id));
            }
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkSteal() {
        return true;
    }
}
