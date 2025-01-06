package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMServer;
import org.supercat.growstone.models.DMServerRedisConfig;

import java.time.Instant;
import java.util.List;

public interface ServerRedisConfigMapper {
    @Select("SELECT * FROM `server_redis_config` WHERE `channel_id` = #{channel_id}")
    List<DMServerRedisConfig> getAll(@Param("channel_id") long channel_id);

    @Insert("INSERT INTO server_redis_config(`channel_id`, `use_type`, `db_index`, `port`, `connection_string`, " +
            "`connection_minimum`, `connection_maximum`, `updated_at`, `created_at`)" +
            " VALUES(#{channel_id}, #{use_type}, #{db_index}, #{port}, #{connection_string}, " +
            "#{connection_minimum}, #{connection_maximum}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMServerRedisConfig model);
}
