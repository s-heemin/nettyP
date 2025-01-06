package org.supercat.growstone.Manager;

import org.redisson.api.RedissonClient;
import org.supercat.growstone.UtcZoneDateTime;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.List;
import java.util.Objects;

public class ClanAttackRankManager {
    private RedissonClient redissonClient;
    private String KEY = "CLAN_ATTACK_RANK:";

    public ClanAttackRankManager(RedissonClient redissonClient) {
        this.redissonClient= redissonClient;
    }


    private String generateKey(int ymd) {
        return KEY + ymd;
    }
    public void addOrUpdateScore(int nowYmd, long clanId, long score) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));
        rankings.add(score, String.valueOf(clanId));
    }

    public long getRank(int nowYmd, long clanId) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));

        var rank = rankings.revRank(String.valueOf(clanId));

        return Objects.nonNull(rank) ? rank + 1 : -1;
    }
}
