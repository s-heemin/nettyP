package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class GameDataLevel {
    private final ImmutableMap<Integer, Integer> levels;
    public static GameDataLevel ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataLevel(loader);
    }

    private GameDataLevel(GameDataLoader loader) {
        Map<Integer, Integer> tempLevel = new HashMap<>();
        loader.getDict("Levels").forEach(
                (k, v) -> tempLevel.put(Integer.valueOf(k), Integer.valueOf(v))
        );
        levels = ImmutableMap.copyOf(tempLevel);
    }

    public int getMaxLevel() {
        return levels.size();
    }

    public int getNeedExp(int level) {
        return levels.getOrDefault(level, 0);
    }
}
