package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameDataStoneStatue {
    private static final Logger logger = LoggerFactory.getLogger(GameDataStoneStatue.class);

    public final int ENCHANT_MAX_LEVEL;
    public final long ENCHANT_CURRENCY_ID;
    public final int ENCHANT_COST;
    public final int ENCHANT_LOCK_INCREASE_AMOUNT;
    public final ImmutableMap<Integer, Integer> ENCHANT_EXP_PER_LEVEL; // TODO: Integer, Long 확인 필요
    public final ImmutableMap<Integer, Integer> ENCHANT_SLOT_OPEN_LEVEL;
    public final long GEM_UPGRADE_CURRENCY_ID;
    public final long DEFAULT_STONE_STATUE_AVATAR_ID;

    public static GameDataStoneStatue ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataStoneStatue(loader);
    }

    private GameDataStoneStatue(GameDataLoader loader) {
        ENCHANT_MAX_LEVEL = loader.getInt("EnchantMaxLevel");
        ENCHANT_CURRENCY_ID = loader.getLong("EnchantCurrencyId");
        ENCHANT_COST = loader.getInt("EnchantCost");
        ENCHANT_LOCK_INCREASE_AMOUNT = loader.getInt("EnchantLockIncreaseAmount");

        var tempExpPerLevel = new HashMap<Integer, Integer>();
        for (var kv : loader.getDict("EnchantExpPerLevel").entrySet()) {
            tempExpPerLevel.put(Integer.parseInt(kv.getKey()), Integer.parseInt(kv.getValue()));
        }
        ENCHANT_EXP_PER_LEVEL = ImmutableMap.copyOf(tempExpPerLevel);

        var tempSlotOpenLevel = new HashMap<Integer, Integer>();
        for (var kv : loader.getDict("EnchantSlotOpenLevel").entrySet()) {
            tempSlotOpenLevel.put(Integer.parseInt(kv.getKey()), Integer.parseInt(kv.getValue()));
        }
        ENCHANT_SLOT_OPEN_LEVEL = ImmutableMap.copyOf(tempSlotOpenLevel);

        GEM_UPGRADE_CURRENCY_ID = loader.getLong("GemUpgradeCurrencyId");

        DEFAULT_STONE_STATUE_AVATAR_ID = loader.getLong("DefaultStoneStatueAvatarId");
    }

    public boolean verify() {
        var errors = new ArrayList<String>();

        // check enchant level
        if (Objects.isNull(ENCHANT_EXP_PER_LEVEL.getOrDefault(ENCHANT_MAX_LEVEL, null))) {
            errors.add("ENCHANT_MAX_LEVEL is not in ENCHANT_EXP_PER_LEVEL");
        }

        errors.forEach(logger::error);

        return errors.isEmpty();
    }
}
