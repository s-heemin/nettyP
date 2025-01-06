package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableMap;

public class GameDataFarm {
    public final int MAX_FREE_SEEDS;
    public final ImmutableMap<Long, Float> NORMAL_SEEDS_PLANT_TABLE;
    public final Double NORMAL_SEED_TOTAL_WEIGHT;
    public final int MAX_STEAL_PLAYER_COUNT;
    public final int BOOST_MINUTES;
    public final int MAX_PLANT_FIELD_COUNT;
    public final int UNLOCK_LEVEL_BATCH_SEED;
    public final int UNLOCK_LEVEL_BATCH_BOOST;
    public final int UNLOCK_LEVEL_BATCH_HARVEST;
    public final int SEED_ITEM_ID;
    public final int BOOST_ITEM_ID;
    public final int PLANT_ITEM_ID;
    public final int MAX_THEFT_ATTEMPTS_PER_PLANT;
    public final int FARM_HISTORTY_HOUR;
    public final int FARM_HISTORY_LIMIT_COUNT;
    public final int MAX_OTHER_PLAYER_SEARCH_COUNT;
    public final int MAX_ENEMY_PLAYER_SEARCH_COUNT;
    public final int ENEMY_PLAYER_SEARCH_HOUR;
    public final int MAX_COMMERCIAL_SEED_ITEM_VIEW_COUNT;
    public final int MAX_COMMERCIAL_BOOST_VIEW_COUNT;
    public final int MAX_SETTLE_THEFT_COUNT;
    public final ImmutableMap<Integer, Long> FARM_COOK_EXP_TABLE;
    public final ImmutableMap<Integer, Long> FARM_COOK_BUFF_IDS;
    public final int FARM_COOK_COMMERCIAL_BUFF_DURATION;
    public final int MAX_STEAL_DAILY_COUNT; //하루 약탈 가능한 최대 횟수

    public static GameDataFarm ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataFarm(loader);
    }

    private GameDataFarm(GameDataLoader loader) {
        MAX_FREE_SEEDS = loader.getInt("MaxFreeSeeds");
        NORMAL_SEEDS_PLANT_TABLE = loader.getDict("NormalSeedsPlantTable")
                .entrySet()
                .stream()
                .collect(ImmutableMap.toImmutableMap(x -> Long.parseLong(x.getKey()), x -> Float.parseFloat(x.getValue())));

        double tempTotalWeight = 0.0d;
        for (float rate : NORMAL_SEEDS_PLANT_TABLE.values()) {
            tempTotalWeight += rate;
        }

        NORMAL_SEED_TOTAL_WEIGHT = tempTotalWeight;
        MAX_STEAL_PLAYER_COUNT = loader.getInt("MaxStealPlayerCount");
        MAX_PLANT_FIELD_COUNT = loader.getInt("MaxFieldCount");
        BOOST_MINUTES = loader.getInt("BoostMinutes");
        UNLOCK_LEVEL_BATCH_SEED = loader.getInt("UnlockLevel_BatchSeed");
        UNLOCK_LEVEL_BATCH_BOOST = loader.getInt("UnlockLevel_BatchBoost");
        UNLOCK_LEVEL_BATCH_HARVEST = loader.getInt("UnlockLevel_BatchHarvest");
        SEED_ITEM_ID = loader.getInt("SeedItemID");
        BOOST_ITEM_ID = loader.getInt("BoostItemID");
        PLANT_ITEM_ID = loader.getInt("PlantItemID");
        MAX_THEFT_ATTEMPTS_PER_PLANT = loader.getInt("MaxThiefAttemptsPerPlant");
        FARM_HISTORTY_HOUR = loader.getInt("FarmHistoryExpireHour");
        FARM_HISTORY_LIMIT_COUNT = loader.getInt("FarmHistoryLimitCount");
        MAX_OTHER_PLAYER_SEARCH_COUNT = loader.getInt("MaxOtherPlayerSearchCount");
        MAX_ENEMY_PLAYER_SEARCH_COUNT = loader.getInt("MaxEnemyPlayerSearchCount");
        ENEMY_PLAYER_SEARCH_HOUR = loader.getInt("EnemyPlayerSearchHour");
        MAX_COMMERCIAL_SEED_ITEM_VIEW_COUNT = loader.getInt("MaxCommercialSeedItemViewCount");
        MAX_COMMERCIAL_BOOST_VIEW_COUNT = loader.getInt("MaxCommercialBoostViewCount");
        FARM_COOK_EXP_TABLE = loader.getDict("FarmCookExpTable")
                .entrySet()
                .stream()
                .collect(ImmutableMap.toImmutableMap(x -> Integer.parseInt(x.getKey()), x -> Long.parseLong(x.getValue())));

        FARM_COOK_BUFF_IDS = loader.getDict("FarmCookBuffIDs")
                .entrySet()
                .stream()
                .collect(ImmutableMap.toImmutableMap(x -> Integer.parseInt(x.getKey()), x -> Long.parseLong(x.getValue())));

        FARM_COOK_COMMERCIAL_BUFF_DURATION = loader.getInt("FarmCookCommercialBuffDuration");
        MAX_STEAL_DAILY_COUNT = loader.getInt("MaxStealDailyCount");

        MAX_SETTLE_THEFT_COUNT = 1000;
    }

    public long getCookExp(int level) {
        return FARM_COOK_EXP_TABLE.getOrDefault(level, 0L);
    }
}
