package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerFinderManager {
    private RedissonClient redisson;
    private final String PLAYER_IDS = "PLAYER_IDS";
    private LoadingCache<String, RSet<Long>> cached;


    public PlayerFinderManager(RedissonClient redisson) {
        this.redisson = redisson;
        cached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<>(){
                    @Override
                    public RSet load(String key) {
                        return redisson.getSet(key, LongCodec.INSTANCE);
                    }
                });
    }

    private RSet<Long> getCached() {
        try {
            return cached.get(PLAYER_IDS);
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to load cache", e);
        }
    }

    public Set<Long> getPlayerRandomPlayer(int randomCount) {
        RSet<Long> playerIds = getCached();
        List<Long> allElements = new ArrayList<>(playerIds.readAll());
        Collections.shuffle(allElements);

        return allElements.stream().limit(randomCount).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void addPlayerId(long playerId) {
        RSet<Long> playerIds = getCached();
        playerIds.add(playerId);
    }
}
