package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.events.EventPlayerStatGrowthLevelUp;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerStatGrowth;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.rules.StatGrowthRules;
import org.supercat.growstone.rules.StatRules;
import org.supercat.growstone.setups.SDB;

import java.util.*;

public class PlayerStatGrowthComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerStatGrowthComponent.class);
    private WorldPlayer player;
    private HashMap<ZStat.Type, DMPlayerStatGrowth> models = new HashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();

    public PlayerStatGrowthComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var temps = SDB.dbContext.statGrowth.getByPlayerId(player.getId());
        if (temps.isEmpty()) {
            return;
        }

        for (var stat : temps) {
            var statType = NetEnumRules.ofStat(stat.stat);
            if (statType == ZStat.Type.NONE) {
                logger.error("invalid stat type:({}), playerID:({})", stat.stat, player.getId());
                continue;
            }

            models.put(statType, stat);
        }
    }

    public TStatGrowthPage getTStatGrowthPage() {
        long page = 0;
        // 모두 페이지는 같다.
        var l = new ArrayList<TStatGrowth>();
        for (var model : models.values()) {
            page = model.page;
            l.add(TBuilderOf.buildOf(model));
        }

        return TStatGrowthPage.newBuilder()
                .addAllStatGrowths(l)
                .setPage(page)
                .build();
    }

    private void refresh() {
        cacheStats.clear();

        StatGrowthRules.computeStatGrowthStats(cacheStats, models);
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheStats);
    }

    public DMPlayerStatGrowth getOrCreate(ZStat.Type type) {
        ;
        return models.computeIfAbsent(type, k -> DMPlayerStatGrowth.of(player.getId(), 1, 0, type.getNumber()));
    }

    public int levelUp(int levelUpCount, ZStat.Type type, TStatGrowthPage.Builder outStatGrowthPage) {
        if (levelUpCount <= 0) {
            return StatusCode.FAIL;
        }

        var model = models.getOrDefault(type, DMPlayerStatGrowth.of(player.getId(), 1, 0, type.getNumber()));
        var resStatGrowth = ResourceManager.INSTANCE.statGrowth.get(model.page);
        if (Objects.isNull(resStatGrowth)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (model.level + levelUpCount > resStatGrowth.maxLevel) {
            return StatusCode.INVALID_REQUEST;
        }

        // 소모할 재화
        long needCost = 0;
        int afterLevel = model.level;
        for (int i = 0; i < levelUpCount; i++) {
            var price = resStatGrowth.prices.get(afterLevel);
            if (Objects.isNull(price)) {
                return StatusCode.INVALID_RESOURCE;
            }

            needCost += price;
            ++afterLevel;
        }

        var cost = player.currency.getGold();
        if (cost < needCost) {
            return StatusCode.NOT_ENOUGH_MATERIAL;
        }

        // 소모
        int status = player.currency.useCurrency(ZResource.Type.GOLD, needCost);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 메모리 변경
        model.level = afterLevel;
        models.put(type, model);

        // 모두 레벨이 만렙이 되었을 경우
        final int maxLevel = resStatGrowth.maxLevel;
        boolean isPageUp = true;
        for (var statInfo : resStatGrowth.stats.entrySet()) {
            var stat = statInfo.getKey();
            var statModel = models.get(stat);
            if (Objects.isNull(statModel) || statModel.level != maxLevel) {
                isPageUp = false;
                break;
            }
        }

        if (isPageUp) {
            long newPage = model.page + 1;
            var newResStatGrowth = ResourceManager.INSTANCE.statGrowth.get(newPage);
            if (Objects.nonNull(newResStatGrowth)) {
                for (var statModel : models.values()) {
                    statModel.page = newPage;
                    statModel.level = 0;
                    models.put(NetEnumRules.ofStat(statModel.stat), statModel);
                    SDB.dbContext.statGrowth.save(statModel);
                }
            }
        }

        SDB.dbContext.statGrowth.save(model);

        outStatGrowthPage.mergeFrom(getTStatGrowthPage().toBuilder().build());

        player.stat.statsNotify();

        player.topic.publish(new EventPlayerStatGrowthLevelUp(type, levelUpCount));

        return StatusCode.SUCCESS;
    }

    public void clearForCheat() {
        models.clear();
        refresh();
    }
}
