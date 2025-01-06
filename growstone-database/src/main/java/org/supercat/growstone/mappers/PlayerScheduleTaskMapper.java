package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerPortraitIcon;
import org.supercat.growstone.models.DMPlayerScheduleTask;

import java.time.Instant;
import java.util.List;

public interface PlayerScheduleTaskMapper {
    @Select("SELECT * FROM `player_schedule_task` WHERE `id` = #{id}")
    DMPlayerScheduleTask get(@Param("id") long id);

    @Select("SELECT * FROM `player_schedule_task` WHERE `player_id` = #{player_id} and `type` = #{type}")
    DMPlayerScheduleTask getByPlayerId(@Param("player_id") long playerId,
                                       @Param("type") int type);

    @Insert("INSERT INTO `player_schedule_task`(`player_id`, `type`, `reset_at`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{type}, #{reset_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerScheduleTask model);

    @Update("UPDATE `player_schedule_task` SET " +
            "`reset_at` = #{reset_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateResetAt(@Param("id") long id,
                    @Param("reset_at") int reset_at,
                    @Param("updated_at") Instant updated_at);
}
