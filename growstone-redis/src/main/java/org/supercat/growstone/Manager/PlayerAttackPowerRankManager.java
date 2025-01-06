package org.supercat.growstone.Manager;

import com.google.common.collect.ImmutableMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.supercat.growstone.model.RMPlayerAttackPower;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerAttackPowerRankManager {
    private RedissonClient redissonClient;

    private final String KEY = "ATTACK_POWER_RANK:";
    private HashMap<Long, RMPlayerAttackPower> rankSnapshot = new HashMap<>();

    public PlayerAttackPowerRankManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private String generateKey(int nowYmd) {
        return KEY + nowYmd;
    }

    public void addOrUpdateScore(int nowYmd, long playerId, RMPlayerAttackPower model) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));
        rankings.add(model.attackPower, String.valueOf(playerId));
        rankSnapshot.put(playerId, model);
    }
    public long getRank(int nowYmd, long playerId) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));

        var rank = rankings.revRank(String.valueOf(playerId));

        return Objects.nonNull(rank) ? rank + 1 : -1;
    }

    public List<Map.Entry<String, Long>> entries(int nowYmd, int beginIdx, int endIdx) {
        RScoredSortedSet<String> rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));
        var getItems = rankings.entryRangeReversed(beginIdx, endIdx);

        if (Objects.isNull(getItems) || getItems.isEmpty()) {
            return List.of();
        }

        return getItems.stream()
                .map(entry -> Map.entry(entry.getValue(), Math.round(entry.getScore())))
                .collect(Collectors.toList());
    }

    public RMPlayerAttackPower getSnapshot(long playerId) {
        return rankSnapshot.get(playerId);
    }

    public void clearForTest(int nowYmd) {
        var rankings = redissonClient.getScoredSortedSet(generateKey(nowYmd));
        rankings.clear();
    }
}
