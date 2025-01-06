package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.LongCodec;
import org.supercat.growstone.SRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CooperativeRaidScoreManager {
    private RedissonClient redisson;
    private final String KEY = "COOPERATIVE_RAID:";
    private final String PLAYER_KEY = "COOPERATIVE_RAID_PLAYER:";
    private final LoadingCache<String, RMap<Long, Long>> playerCached;


    public CooperativeRaidScoreManager(RedissonClient redisson) {
        this.redisson = redisson;
        this.playerCached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<>(){
                    @Override
                    public RMap<Long, Long> load(String key) {
                        return redisson.getMap(key, LongCodec.INSTANCE);
                    }
                });

    }
    private RMap<Long, Long> getScoreCached(long raidDataId, int nowYmd) {
        try {
            return playerCached.get(generatePlayerKey(raidDataId, nowYmd));
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to load cache", e);
        }
    }

    private String generateKey(long raidId, int nowYmd) {
        return KEY + raidId + ":" + nowYmd;
    }

    public void add(long raidId,  int nowYmd, long score) {
        var key = generateKey(raidId, nowYmd);
        RMap<String, Integer> values = redisson.getMap(key, IntegerCodec.INSTANCE);
        values.addAndGet(key, score);
    }

    public long get(long raidId, int nowYmd) {
        var key = generateKey(raidId, nowYmd);
        RMap<String, Integer> rMap = redisson.getMap(key, IntegerCodec.INSTANCE);
        if (rMap.isEmpty()) {
            return 0;
        }

        return rMap.getOrDefault(key, 0);
    }

    private String generatePlayerKey(long raidId, int nowYmd) {
        return PLAYER_KEY + raidId + ":" + nowYmd;
    }

    public void addPlayer(long raidId, int nowYmd, long playerId, long score) {
        RMap<Long, Long> values = getScoreCached(raidId, nowYmd);
        values.put(playerId, score);
    }

    public Map<Long, Long> getAll(long raidId, int nowYmd) {
        RMap<Long, Long> values = getScoreCached(raidId, nowYmd);

        return values.readAllMap();
    }

    public void clearForTest(long raidId, int nowYmd) {
        var values = getScoreCached(raidId, nowYmd);
        values.clear();

        values = redisson.getMap(generateKey(raidId, nowYmd), IntegerCodec.INSTANCE);
        values.clear();
    }
}
