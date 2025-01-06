package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMServer;

import java.time.Instant;

public interface ServerMapper {
    @Select("SELECT * FROM `servers` WHERE `instance_id` = #{instance_id}")
    DMServer getByInstanceId(@Param("instance_id") String instance_id);

    @Insert("INSERT INTO `servers`(`instance_id`, `port`, `players`, `updated_at`, `created_at`)" +
            " VALUES(#{instance_id}, #{port}, #{players}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMServer model);

    @Update("UPDATE `servers` SET `players` = #{players}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updatePlayers(@Param("id") long id,
                      @Param("players") long players,
                      @Param("updated_at") Instant updated_at);
}
