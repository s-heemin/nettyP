package org.supercat.growstone.Manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.redisson.api.RBucket;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.model.RMPlayerCompetitiveRaid;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompetitiveRaidRankingManager {
    private RedissonClient redissonClient;
    public final LoadingCache<String, RBucket<String>> cached;

    private final String KEY = "COMPETITIVE_RAID:";
    private final String PLAYER_INFO = "PLAYER_INFO:";
    private static final int EXPIRE_DAYS = 1;

    public CompetitiveRaidRankingManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        this.cached = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
    }

    private RBucket<String> load(String key) {
        RBucket<String> b = redissonClient.getBucket(key, StringCodec.INSTANCE);
        b.expireAsync(EXPIRE_DAYS, TimeUnit.DAYS);
        return b;
    }

    private String generateKey(long raidId, int nowYmd) {
        return KEY + raidId + ":" + nowYmd;
    }

    private String generateKey(long raidId, int nowYmd, long playerId) {
        return PLAYER_INFO + raidId + ":" + nowYmd + ":" + playerId;
    }

    public void addOrUpdateScore(long raidId, int nowYmd, long playerId, double score) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));
        rankings.addAsync(score, String.valueOf(playerId));
    }
    public long getRank(long raidId, int nowYmd, long playerId) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));

        var rank = rankings.revRank(String.valueOf(playerId));

        return Objects.nonNull(rank) ? rank + 1 : -1;
    }

    public List<Map.Entry<String, Long>> entries(long raidId, int nowYmd, int beginIdx, int endIdx) {
        RScoredSortedSet<String> rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));
        var getItems = rankings.entryRangeReversed(beginIdx, endIdx);

        if (Objects.isNull(getItems) || getItems.isEmpty()) {
            return null;
        }

        return getItems.stream()
                .map(entry -> Map.entry(entry.getValue(), Math.round(entry.getScore())))
                .collect(Collectors.toList());
    }

    public String getTopRankPlayerId(long raidId, int nowYmd) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));
        return rankings.first().toString();
    }
    public long getTopRankPlayerScore(long raidId, int nowYmd) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));

        var topPlayerId = rankings.first().toString();

        return rankings.getScore(topPlayerId).longValue();
    }

    public String getCached(long raidId, int nowYmd, long playerId) {
        return cached.getUnchecked(generateKey(raidId, nowYmd, playerId)).get();
    }

    public void addAsync(long raidId, int nowYmd, long playerId, RMPlayerCompetitiveRaid model) {
        var value = JsonConverter.to(model);
        cached.getUnchecked(generateKey(raidId, nowYmd, playerId)).set(value, EXPIRE_DAYS, TimeUnit.DAYS);
    }


    public void clearForTest(long raidId, int nowYmd) {
        var key = PLAYER_INFO + raidId + ":" + nowYmd + ":*";
        cached.invalidate(key);
        var rankings = redissonClient.getScoredSortedSet(generateKey(raidId, nowYmd));
        rankings.clear();
    }
}
