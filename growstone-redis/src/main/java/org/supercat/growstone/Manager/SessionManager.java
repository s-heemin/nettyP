package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;
import org.supercat.growstone.SRedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SessionManager {
    private RedissonClient redissonClient;
    private final String SESSION = "SESSION";
    private final LoadingCache<String, RMap<Long, Long>> cached;
    public SessionManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        cached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<>(){
                    @Override
                    public RMap<Long, Long> load(String key) {
                        return redissonClient.getMap(key, LongCodec.INSTANCE);
                    }
                });
        // 서버가 올라올때 레디스에 있는 세션들은 정리해준다.
        clear();

    }

    public String generateKey() {
        return SESSION;
    }

    private RMap<Long, Long> getCached() {
        try {
            return cached.get(SESSION);
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to load cache", e);
        }
    }
    public void addSession(long globalMasterPlayerId, long sessionId) {
        var sessionMap = getCached();
        sessionMap.put(globalMasterPlayerId, sessionId);
    }

    public long getUserId(long globalMasterPlayerId) {
        var sessionMap = getCached();
        return sessionMap.getOrDefault(globalMasterPlayerId, 0L);
    }

    public void deleteSession(long globalMasterPlayerId) {
        var sessionMap = getCached();
        sessionMap.remove(globalMasterPlayerId);
    }

    private void clear() {
        var sessions = redissonClient.getMap(generateKey());
        sessions.clear();
    }
}
