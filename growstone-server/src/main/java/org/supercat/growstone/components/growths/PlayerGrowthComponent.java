package org.supercat.growstone.components.growths;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.GrowthRules;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerGrowthComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerGrowthComponent.class);

    private WorldPlayer player;
    public ConcurrentHashMap<Long, PlayerGrowth> models;
    public HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();
    public HashMap<ZStat.Type, Double> cacheOwnedStats = new HashMap<>();

    public PlayerGrowthComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        models = SDB.dbContext.growth.getByPlayerId(player.getId()).stream()
                .collect(Collectors.toConcurrentMap(x -> x.growth_id, x -> new PlayerGrowth(player, x),
                        (existing, replacement) -> existing,
                        ConcurrentHashMap::new));

        refresh();
    }


    public List<TGrowth> getTGrowths() {
        return models.values().stream().
                map(x -> x.model).
                map(TBuilderOf::buildOf).
                collect(Collectors.toList());
    }

    public TGrowth getTGrowth(long dataId) {
        var model = models.get(dataId);
        if (Objects.isNull(model)) {
            return null;
        }

        return TBuilderOf.buildOf(model.model);
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheOwnedStats);
    }

    public PlayerGrowth getGrowth(long growthId) {
        return models.get(growthId);
    }

    public int levelUp(long growthId, TMaterial material, TGrowth.Builder out) {
        var resGrowth = ResourceManager.INSTANCE.growth.get(growthId);
        if (Objects.isNull(resGrowth)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var growth = models.get(growthId);
        if (growth == null) {
            return StatusCode.INVALID_GROWTH;
        }

        if (Objects.isNull(material)) {
            return StatusCode.INVALID_REQUEST;
        }

        // 재료 체크
        int status = GrowthRules.reviewLevelUp(resGrowth.tier, resGrowth.type, growth.model.level, growth.model.limit_break_level, material);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        long assetCount = player.itemBag.getItemCount(material.getId());
        if (assetCount < material.getCount()) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        // 재화 차감
        status = player.categoryFilter.use(ZCategory.Type.ITEM, material.getId(), material.getCount());
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        ++growth.model.level;

        // 디비 정보 변경
        growth.markAsChanged();

        save();

        out.mergeFrom(TBuilderOf.buildOf(growth.model));

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public int promote(List<TMaterial> promoteTargets, List<TGrowth> results) {
        if (Objects.isNull(promoteTargets) || promoteTargets.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        promoteTargets = GrowthRules.aggregateMaterials(promoteTargets);
        for (var target : promoteTargets) {
            var resGrowth = ResourceManager.INSTANCE.growth.get(target.getId());
            if (Objects.isNull(resGrowth)) {
                logger.error("invalid growth id - playerId({}), growthId({})", player.getId(), target.getId());
                return StatusCode.INVALID_RESOURCE;
            }

            // 타겟 체크
            var model = models.get(target.getId());
            if (Objects.isNull(model)) {
                logger.error("not exist growth id - playerId({}), growthId({})", player.getId(), target.getId());
                return StatusCode.INVALID_GROWTH;
            }

            // 승급 가능할지 체크
            int status = GrowthRules.reviewPromote(resGrowth.tier, resGrowth.type, model.model.promote_level, target);
            if (!StatusCode.isSuccess(status)) {
                return status;
            }

            if (model.model.count < target.getCount()) {
                return StatusCode.NOT_ENOUGH_MATERIAL;
            }
        }

        for (var target : promoteTargets) {
            var model = models.get(target.getId());

            // 재료 소모
            use(target.getId(), target.getCount());

            // 메모리 변경
            ++model.model.promote_level;

            // 디비 변경
            model.markAsChanged();

            results.add(TBuilderOf.buildOf(model.model));
        }

        save();

        // 스탯 refresh
        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public int limitBreak(List<TMaterial> targets, List<TGrowth> outs) {
        if (Objects.isNull(targets)) {
            return StatusCode.INVALID_REQUEST;
        }

        for (var target : targets) {
            var resGrowth = ResourceManager.INSTANCE.growth.get(target.getId());
            if (Objects.isNull(resGrowth)) {
                return StatusCode.INVALID_RESOURCE;
            }

            var model = models.get(resGrowth.id);
            if (Objects.isNull(model)) {
                return StatusCode.INVALID_GROWTH;
            }

            int result = GrowthRules.reviewLimitBreak(resGrowth.tier, resGrowth.type, model.model.limit_break_level, model.model.promote_level, target);
            if (!StatusCode.isSuccess(result)) {
                return result;
            }

            if (model.model.count < target.getCount()) {
                return StatusCode.NOT_ENOUGH_MATERIAL;
            }
        }

        for (var target : targets) {
            var model = models.get(target.getId());
            if (Objects.isNull(model)) {
                return StatusCode.INVALID_GROWTH;
            }

            // 재료 소모
            use(model.model.growth_id, target.getCount());

            // 메모리 변경
            ++model.model.limit_break_level;

            // 디비 변경
            model.markAsChanged();

            outs.add(TBuilderOf.buildOf(model.model));
        }

        save();

        // 스탯 refresh
        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public void save() {
        models.values().forEach(PlayerGrowth::save);
    }

    private void refresh() {
        if (models.isEmpty()) {
            return;
        }

        cacheStats.clear();
        cacheOwnedStats.clear();
        models.values().forEach(this::computeOwnedStats);
        GrowthRules.computeStats(cacheStats, cacheOwnedStats);
    }

    // 성장 첫 획득했을때
    public int add(long growthId, long count, boolean isNotify) {
        var resGrowth = ResourceManager.INSTANCE.growth.get(growthId);
        if (Objects.isNull(resGrowth)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var growth = models.get(growthId);
        if (Objects.nonNull(growth)) {
            addExistingGrowth(growth, count, isNotify);
            return StatusCode.SUCCESS;
        }

        growth = PlayerGrowth.of(player, growthId, resGrowth.type);
        growth.model.count = Math.max(0, count - 1);
        growth.markAsChanged();

        models.put(growthId, growth);
        computeOwnedStats(growth);

        SDB.dbContext.growth.save(growth.model);

        player.stat.statsNotify();

        if (isNotify) {
            notifyPlayer(growth);
        }

        return StatusCode.SUCCESS;
    }

    private void addExistingGrowth(PlayerGrowth growth, long count, boolean isNotify) {
        growth.model.count += count;

        SDB.dbContext.growth.save(growth.model);

        if (isNotify) {
            notifyPlayer(growth);
        }
    }
    public int use(long dataId, long count) {
        var growth = models.get(dataId);
        if (Objects.isNull(growth)) {
            return StatusCode.INVALID_GROWTH;
        }

        if (growth.model.count < count) {
            return StatusCode.NOT_ENOUGH_GROWTH;
        }

        growth.model.count = Math.max(0, growth.model.count - count);

        SDB.dbContext.growth.save(growth.model);

        player.sendPacket(0, ZGrowthNotify.newBuilder()
                .addGrowths(TBuilderOf.buildOf(growth.model)));

        return StatusCode.SUCCESS;
    }

    private void computeOwnedStats(PlayerGrowth growth) {
        growth.calculateStats().forEach((stat, value) -> {
            cacheOwnedStats.compute(stat, (k, v) -> Objects.isNull(v) ? value : v + value);
        });

    }

    private void notifyPlayer(PlayerGrowth growth) {
        player.sendPacket(0, ZGrowthNotify.newBuilder()
                .addAllGrowths(List.of(TBuilderOf.buildOf(growth.model))));
    }

    public void clearForCheat() {
        player.preset.clearForCheat();

        models.clear();

        cacheStats.clear();

        cacheOwnedStats.clear();

        player.sendPacket(0L, ZGrowthNotify.newBuilder()
                .addAllGrowths(getTGrowths()));

        player.stat.statsNotify();

        SDB.dbContext.growth.clearForCheat(player.getId());
    }

    public void upgradeAllForCheat() {
        for(var growth : models.values()) {
            var resGrowth = ResourceManager.INSTANCE.growth.get(growth.model.growth_id);
            if (Objects.isNull(resGrowth)) {
                continue;
            }

            var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(resGrowth.type, resGrowth.tier);
            if (Objects.isNull(resGrowthUpgrade)) {
                continue;
            }

            for(var upgrade : resGrowthUpgrade.levelUpgrades.entrySet()) {
                if(upgrade.getKey() == ZGrowthStatTarget.Type.LEVEL) {
                    growth.model.level = upgrade.getValue().maxUpgradeLevel;
                } else if(upgrade.getKey() == ZGrowthStatTarget.Type.PROMOTE) {
                    growth.model.promote_level = upgrade.getValue().maxUpgradeLevel;
                } else if(upgrade.getKey() == ZGrowthStatTarget.Type.LIMIT_BREAK) {
                    growth.model.limit_break_level = upgrade.getValue().maxUpgradeLevel;
                }
            }
            growth.markAsChanged();
        }

        save();

        refresh();
    }
}
