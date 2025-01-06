package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMEvent;
import org.supercat.growstone.models.DMWorldScheduleTask;

import java.time.Instant;
import java.util.List;

public interface WorldEventMapper {
    @Select("SELECT * FROM `world_events` WHERE `id` = #{id}")
    DMEvent get(@Param("id") long id);

    @Select("SELECT * FROM `world_events` WHERE `start_at` <= NOW() AND `display_at` > NOW()")
    List<DMEvent> getAllByActive();
    @Insert("INSERT INTO `world_events`(`event_data_id`, `start_at`, `display_at`, `end_at`, `updated_at`, `created_at`)" +
            " VALUES(#{event_data_id}, #{start_at}, #{display_at}, #{end_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMEvent model);
}
