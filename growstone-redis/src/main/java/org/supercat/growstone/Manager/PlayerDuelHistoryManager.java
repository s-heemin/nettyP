package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalNotification;
import com.supercat.growstone.network.messages.TPlayerDuel;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.model.RMPlayerDuelHistory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerDuelHistoryManager {
    private RedissonClient redisson;
    private final String KEY = "DUEL_HISTORY:";
    private final String UNIQUE_KEY = "DUEL_HISTORY_ID";
    private final LoadingCache<String, RMap<String, String>> cached;

    public PlayerDuelHistoryManager(RedissonClient redisson) {
        this.redisson = redisson;
        this.cached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .removalListener(this::removal)
                .build(CacheLoader.from(this::loading));
    }

    private RMap<String, String> loading(String key) {
        RMap<String, String> ramp = redisson.getMap(key);
        ramp.expireAsync(14, TimeUnit.DAYS);

        return ramp;
    }
    private void removal(RemovalNotification<String, RMap<String, String>> notification) {
        var rmap = notification.getValue();
        rmap.expireAsync(14, TimeUnit.DAYS);
    }

    public String generateKey(int nowYW) {
        return KEY + nowYW;
    }

    private RMap<String, String> getCachedRMap(int nowYW) {;
        return cached.getUnchecked(generateKey(nowYW));
    }


    public RMPlayerDuelHistory add(int nowYW, long playerId, int criticalSeed, TPlayerDuel win, TPlayerDuel lose) {
        long uniqueId = redisson.getAtomicLong(UNIQUE_KEY).incrementAndGet();
        var model = RMPlayerDuelHistory.of(uniqueId, criticalSeed, win.getPlayerId(), win.getName(), win.getAvatarId(), win.getSeed(),
                lose.getPlayerId(), lose.getName(), lose.getAvatarId(), lose.getSeed());

        var values = getCachedRMap(nowYW);

        var histories = values.get(String.valueOf(playerId));
        if(Objects.isNull(histories)) {
            histories = JsonConverter.to(List.of(model));
        } else {
            var l = Arrays.stream(JsonConverter.of(histories, RMPlayerDuelHistory[].class))
                    .collect(Collectors.toList());
            l.add(model);
            histories = JsonConverter.to(l);
        }

        values.put(String.valueOf(playerId), histories);

        return model;
    }

    public List<RMPlayerDuelHistory> getAll(int nowYW, long playerId) {
        var values = getCachedRMap(nowYW);

        var histories = values.get(String.valueOf(playerId));

        return Arrays.stream(JsonConverter.of(histories, RMPlayerDuelHistory[].class)).
                collect(Collectors.toList());
    }
}
