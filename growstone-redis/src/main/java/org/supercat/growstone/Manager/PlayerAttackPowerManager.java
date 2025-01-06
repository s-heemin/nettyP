package org.supercat.growstone.Manager;

import com.google.api.client.json.Json;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.model.RMPlayerAttackPower;

import java.util.*;

public class PlayerAttackPowerManager {
    private RedissonClient redisson;
    private final String KEY = "ATTACK_POWER";

    public PlayerAttackPowerManager(RedissonClient redisson) {
        this.redisson = redisson;
    }

    public String generateKey() {
        return KEY;
    }

    public void addTotalPower(long playerId, int level, long attackPower, long avatarId, long portraitId, String name) {
        RMap<Long, String> values = redisson.getMap(generateKey(),StringCodec.INSTANCE);
        var before = values.get(playerId);
        if(Objects.isNull(before)) {
            values.put(playerId, JsonConverter.to(RMPlayerAttackPower.of(playerId, level, attackPower, avatarId, portraitId, name)));
            return;
        }
        var model = JsonConverter.of(before, RMPlayerAttackPower.class);
        if(model.attackPower >= attackPower) {
           return;
        }

        model.level = level;
        model.attackPower = attackPower;
        model.avatarId = avatarId;
        model.portraitId = portraitId;
        model.name = name;

        values.put(playerId, JsonConverter.to(model));
    }

    public Set<Map.Entry<Long, String>> getAllPlayersAttackPower() {
        RMap<Long, String> attackPowerMap = redisson.getMap(KEY, StringCodec.INSTANCE);
        return attackPowerMap.entrySet();
    }
    public void clearForTest() {
        RMap<Long, Long> attackPowerMap = redisson.getMap(KEY, StringCodec.INSTANCE);
        attackPowerMap.clear();
    }
}
