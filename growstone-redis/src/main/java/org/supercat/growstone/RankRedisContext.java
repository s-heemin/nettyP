package org.supercat.growstone;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.supercat.growstone.Manager.ClanAttackRankManager;
import org.supercat.growstone.Manager.CompetitiveRaidRankingManager;
import org.supercat.growstone.Manager.PlayerAttackPowerRankManager;

public class RankRedisContext {
    private RedissonClient redisson;
    private RedisServerConfig config;
    public CompetitiveRaidRankingManager competitiveRank;
    public PlayerAttackPowerRankManager playerAttackPowerRank;
    public ClanAttackRankManager clanAttackRank;

    private RankRedisContext(RedisServerConfig config) {
        this.config = config;
    }
    public static RankRedisContext of(RedisServerConfig config) {
        return new RankRedisContext(config);
    }
    public void init() {
        initRedis();
        competitiveRank = new CompetitiveRaidRankingManager(redisson);
        playerAttackPowerRank = new PlayerAttackPowerRankManager(redisson);
        clanAttackRank = new ClanAttackRankManager(redisson);

    }

    private void initRedis() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(this.config.connection_string)
                .setDatabase((int)this.config.db_index)
                .setConnectTimeout(2000)
                .setConnectionPoolSize(this.config.connection_maximum) // 최대 연결 수
                .setConnectionMinimumIdleSize(this.config.connection_minimum); // 최소 유휴 연결 수

        // Redisson 클라이언트 생성
        redisson = Redisson.create(config);
    }

}
