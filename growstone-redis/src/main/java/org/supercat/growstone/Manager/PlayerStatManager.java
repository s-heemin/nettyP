package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.ZStat;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.DoubleCodec;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.StringCodec;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PlayerStatManager {
    private RedissonClient redisson;
    private final String KEY = "STATS:";
    private final LoadingCache<String, RMap<String, String>> cached;

    public PlayerStatManager(RedissonClient redisson) {
        this.redisson = redisson;
        this.cached = CacheBuilder.newBuilder()
                .expireAfterAccess(8, TimeUnit.HOURS)
                .build(new CacheLoader<>(){
                    @Override
                    public RMap<String, String> load(String key) {
                        return redisson.getMap(key, StringCodec.INSTANCE);
                    }
                });
    }

    public String generateKey(long playerId) {
        return KEY + playerId;
    }
    private RMap<String, String> getCached(long playerId) {
        try {
            return cached.get(generateKey(playerId));
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to load cache", e);
        }
    }
    public void addStat(long playerId, ZStat.Type type, Double value) {
        var sessionMap = getCached(playerId);
        sessionMap.put(String.valueOf(type.getNumber()), String.valueOf(value));
    }

    public Map<String, String> getStatByPlayerId(long playerId) {
        var sessionMap = getCached(playerId);
        return sessionMap.readAllMap();
    }
}
