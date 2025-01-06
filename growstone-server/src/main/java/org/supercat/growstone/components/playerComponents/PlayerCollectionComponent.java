package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TCollection;
import com.supercat.growstone.network.messages.TCollectionLevelUp;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.containers.CollectionKey;
import org.supercat.growstone.models.DMPlayerCollection;
import org.supercat.growstone.rules.CollectionRules;
import org.supercat.growstone.setups.SDB;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerCollectionComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerCollectionComponent.class);
    private WorldPlayer player;
    private ConcurrentHashMap<CollectionKey, DMPlayerCollection> models = new ConcurrentHashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();

    public PlayerCollectionComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var collections = SDB.dbContext.collection.getByPlayerId(player.getId());
        if (Objects.isNull(models)) {
            return;
        }

        for(var collection : collections) {
            var type = ZResource.Type.forNumber(collection.type);
            if(Objects.isNull(type)) {
                logger.error("invalid collection type:({}), playerId:({})", collection.type, player.getId());
                continue;
            }

            models.put(new CollectionKey(type, collection.collection_id), collection);
        }

        refresh();
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheStats);
    }
    public void refresh() {
        cacheStats.clear();
        for(var model : models.values()) {
            CollectionRules.computeStats(model.collection_id, model.level, cacheStats);
        }
    }
    public List<TCollection> getCollections() {
        return models.values().stream()
                .map(TBuilderOf::buildOf)
                .collect(Collectors.toList());
    }
    public DMPlayerCollection getOrDefault(ZResource.Type type, long collectionId) {
        return models.getOrDefault(new CollectionKey(type, collectionId), DMPlayerCollection.of(player.getId(), type.getNumber(), collectionId));
    }
    public int levelUp(List<TCollectionLevelUp> targets, List<TCollection> outCollections) {
        if(!CollectionRules.isValidTargetList(targets)) {
            return StatusCode.INVALID_REQUEST;
        }

        for(var target : targets) {
            var collectionId = target.getDataId();
            var type = target.getType();
            var targetLevel = target.getTargetLevel();

            var model = getOrDefault(type, collectionId);
            var resCollection = ResourceManager.INSTANCE.collection.get(collectionId);
            if(Objects.isNull(resCollection)) {
                return StatusCode.INVALID_RESOURCE;
            }

            int status = CollectionRules.reviewLevelUp(resCollection, type, model.level, targetLevel);
            if(!StatusCode.isSuccess(status)) {
                return status;
            }

            // 해당 도감에 속한 장비들이 유효한 레벨값인지 체크
            for(var condition : resCollection.conditions.values()) {
                var growth = player.growth.getGrowth(condition.growthId);
                if(Objects.isNull(growth)) {
                    logger.error("invalid growthId:({}), playerId:({})", condition.growthId, player.getId());
                    return StatusCode.INVALID_GROWTH;
                }

                if(!CollectionRules.isValidLevel(condition, growth.model.level, targetLevel)) {
                    return StatusCode.NOT_ENOUGH_COLLECTION_LEVEL_UP;
                }
            }
        }


        // 여기까지 왔을때는 도감 레벨을 올릴 수 있는 상태이다.
        for(var target : targets) {
            var collectionId = target.getDataId();
            var type = target.getType();
            var targetLevel = target.getTargetLevel();

            var model = getOrDefault(type, collectionId);
            model.level = targetLevel;
            SDB.dbContext.collection.save(model);

            models.put(new CollectionKey(type, collectionId), model);

            outCollections.add(TBuilderOf.buildOf(model));
        }

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public void allCompleteByCheat() {
        var collections = ResourceManager.INSTANCE.collection.getAll();
        for(var collection : collections) {
            var model = getOrDefault(collection.type, collection.id);
            model.level = collection.maxLevel;
            SDB.dbContext.collection.save(model);
        }
    }

    public void allClearByCheat() {
        for(var model : models.values()) {
            model.level = 0;
            SDB.dbContext.collection.save(model);
        }
    }
}
