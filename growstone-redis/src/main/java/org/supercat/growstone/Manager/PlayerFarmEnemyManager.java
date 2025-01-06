package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.LongCodec;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerFarmEnemyManager {
    private RedissonClient redisson;
    private final String key = "FARM_ENEMY_PLAYER_IDS";
    private LoadingCache<String, RScoredSortedSet<Long>> cached;
    public static final int EXPIRE_DAYS = 3;

    public PlayerFarmEnemyManager(RedissonClient redisson) {
        this.redisson = redisson;
        cached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
    }

    public String generateKey(long playerId) {
        return key + ":" + playerId;
    }

    private RScoredSortedSet<Long> load(String key) {
        RScoredSortedSet<Long> values = redisson.getScoredSortedSet(key, LongCodec.INSTANCE);
        values.expireAsync(Instant.now().plus(EXPIRE_DAYS, ChronoUnit.DAYS));
        return values;
    }

    public void add(long playerId, long enemyId) {
        var key = generateKey(playerId);
        RScoredSortedSet<Long> values = cached.getUnchecked(key);
        values.add(Instant.now().getEpochSecond(), enemyId);
    }

    public List<Long> get(long playerId, int searchCount, int searchHours) {
        var endAt = Instant.now();
        var startAt = endAt.minus(searchHours, ChronoUnit.HOURS);
        var values = cached.getUnchecked(generateKey(playerId))
                .valueRange(startAt.getEpochSecond(), true, endAt.getEpochSecond(), true,
                        0, searchCount
                );

        // 과거 데이터 정리
        cached.getUnchecked(generateKey(playerId)).removeRangeByScoreAsync(0, true
                , startAt.getEpochSecond(), false);

        return new ArrayList<>(values);
    }
}
