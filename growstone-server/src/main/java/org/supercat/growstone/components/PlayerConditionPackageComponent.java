package org.supercat.growstone.components;

import com.supercat.growstone.network.messages.TConditionPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerConditionPackage;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerConditionPackageComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerConditionPackageComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<Long, DMPlayerConditionPackage> models;

    public PlayerConditionPackageComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        models = SDB.dbContext.conditionPackage.getAll(player.getId()).stream()
                .collect(Collectors.toMap(model -> model.package_id, model -> model, (a, b) -> a, ConcurrentHashMap::new));
    }

    public List<TConditionPackage> getTConditionPackage() {
        return models.values().stream()
                .filter(x -> !x.is_complete)
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }
    public DMPlayerConditionPackage get(long packageId) {
        return models.get(packageId);
    }

    public DMPlayerConditionPackage add(long packageId) {
        var resConditionPackage = ResourceManager.INSTANCE.conditionPackage.get(packageId);
        if(Objects.isNull(resConditionPackage)) {
            logger.error("invalid condition package id - id({})", packageId);
            return null;
        }

        var model = DMPlayerConditionPackage.of(player.getId(), packageId);

        model.expire_at = Instant.now().plusSeconds(resConditionPackage.openDuration);

        models.put(packageId, model);

        SDB.dbContext.conditionPackage.save(model);

        return model;
    }

    public void complete(long packageId) {
        var model = models.get(packageId);
        if(Objects.isNull(model)) {
            return;
        }

        model.is_complete = true;

        SDB.dbContext.conditionPackage.save(model);
    }
    public int isEnableComplete(long packageId) {
        var model = models.get(packageId);
        if(model == null) {
            return StatusCode.INVALID_REQUEST;
        }

        if(model.is_complete) {
            return StatusCode.ALREADY_COMPLETE_CONDITION_PACKAGE;
        }

        if(Instant.now().isAfter(model.expire_at)) {
            return StatusCode.ALREADY_EXPIRED_CONDITION_PACKAGE;
        }

        return StatusCode.SUCCESS;
    }
}
