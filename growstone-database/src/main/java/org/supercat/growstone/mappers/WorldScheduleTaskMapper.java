package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerTower;
import org.supercat.growstone.models.DMWorldScheduleTask;

import java.util.List;

public interface WorldScheduleTaskMapper {
    @Select("SELECT * FROM `world_schedule_tasks` WHERE `id` = #{id}")
    DMWorldScheduleTask get(@Param("id") long id);

    @Select("SELECT * FROM `world_schedule_tasks` WHERE `channel_id` = #{channel_id}")
    List<DMWorldScheduleTask> getByChannelId(@Param("channel_id") long channelId);

    @Insert("INSERT INTO `world_schedule_tasks`(`channel_id`, `type`, `reset_at`, `updated_at`, `created_at`)" +
            " VALUES(#{channel_id}, #{type}, #{reset_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMWorldScheduleTask model);

    @Update("UPDATE `world_schedule_tasks` SET " +
            "reset_at = #{reset_at}, " +
            "updated_at = #{updated_at} WHERE id = #{id}")
    int update(DMWorldScheduleTask model);
}
