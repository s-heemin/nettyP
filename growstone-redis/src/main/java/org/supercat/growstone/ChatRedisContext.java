package org.supercat.growstone;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ChatRedisContext {
    private static JedisPool jedisPool;
    private RedisServerConfig config;

    private ChatRedisContext(RedisServerConfig config) {
        this.config = config;
    }
    public static ChatRedisContext of(RedisServerConfig config) {
        return new ChatRedisContext(config);
    }
    public void init() {
        initJedis();
    }

    private void initJedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(config.connection_maximum);
        poolConfig.setMinIdle(config.connection_minimum);
        jedisPool = new JedisPool(poolConfig, config.connection_string, config.port, 2000,
                null, (int)config.db_index);
    }

}
