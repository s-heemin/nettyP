package org.supercat.growstone;

import org.supercat.growstone.models.DMServerRedisConfig;
import org.supercat.growstone.types.RedisUseType;


import java.util.List;

public class RedisServerConfig {
    public long channel_id;
    public int use_type;
    public long db_index;
    public String connection_string;
    public int port;
    public int connection_minimum;
    public int connection_maximum;

    public static RedisServerConfig of(DMServerRedisConfig model) {
        var config = new RedisServerConfig();
        config.channel_id = model.channel_id;
        config.use_type = model.use_type;
        config.db_index = model.db_index;
        config.port = model.port;
        config.connection_string = model.connection_string;
        config.connection_minimum = model.connection_minimum;
        config.connection_maximum = model.connection_maximum;
        return config;
    }

    public static RedisServerConfig fetch(RedisUseType useType, List<RedisServerConfig> list) {
        return list.stream()
                .filter(m -> RedisUseType.of(m.use_type) == useType)
                .findFirst()
                .orElse(null);
    }

}
