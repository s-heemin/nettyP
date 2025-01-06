package org.supercat.growstone;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.supercat.growstone.Manager.*;

public class ContentRedisContext {
    private RedissonClient redisson;
    private RedisServerConfig config;

    public SessionManager session;
    public CooperativeRaidScoreManager cooperativeRaidScore;
    public PlayerFinderManager players;
    public PlayerAttackPowerManager playerAttackPower;
    public PlayerStatManager playerStat;
    public PlayerFarmEnemyManager playerFarmEnemy;
    public PlayerDuelHistoryManager playerDuelHistory;

    private ContentRedisContext(RedisServerConfig config) {
        this.config = config;
    }
    public static ContentRedisContext of(RedisServerConfig config) {
        return new ContentRedisContext(config);
    }
    public void init() {
        initRedis();
        session = new SessionManager(redisson);

        playerAttackPower = new PlayerAttackPowerManager(redisson);
        cooperativeRaidScore = new CooperativeRaidScoreManager(redisson);
        players = new PlayerFinderManager(redisson);
        playerStat = new PlayerStatManager(redisson);
        playerFarmEnemy = new PlayerFarmEnemyManager(redisson);
        playerDuelHistory = new PlayerDuelHistoryManager(redisson);
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
