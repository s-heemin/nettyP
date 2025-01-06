package org.supercat.growstone.components.playerComponents;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.ZDiggingUpgrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.SLog;
import org.supercat.growstone.models.DMPlayerDiggingUpgrade;
import org.supercat.growstone.setups.SDB;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PlayerDiggingUpgrade {
    private static final Logger logger = LoggerFactory.getLogger(PlayerDiggingUpgrade.class);

    private WorldPlayer player;
    private LoadingCache<ZDiggingUpgrade.Type, DMPlayerDiggingUpgrade> cache;
    public PlayerDiggingUpgrade(WorldPlayer player) {
        this.player = player;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
    }

    private DMPlayerDiggingUpgrade load(ZDiggingUpgrade.Type type) {
        var model = SDB.dbContext.diggingUpgrade.getByType(player.getId(), type.getNumber());
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerDiggingUpgrade.of(player.getId(), type.getNumber());
        return model;
    }

    public DMPlayerDiggingUpgrade getOrCreate(ZDiggingUpgrade.Type type) {
        try {
            return cache.getUnchecked(type);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

}
