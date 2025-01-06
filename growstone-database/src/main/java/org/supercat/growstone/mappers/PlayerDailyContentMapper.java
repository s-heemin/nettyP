package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerAdvertise;
import org.supercat.growstone.models.DMPlayerDailyContent;

import java.util.List;

public interface PlayerDailyContentMapper {
    @Select("SELECT * FROM `player_daily_content` WHERE `player_id` = #{player_id} and `type` = #{type}")
    DMPlayerDailyContent getByType(@Param("player_id") long player_id, @Param("type") int type);

    @Insert("INSERT INTO `player_daily_content`(`player_id`, `type`, `progress`, `state`, `rewards`, `last_updated_date`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{type}, #{progress}, #{state}, #{rewards}, #{last_updated_date}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerDailyContent model);

    @Update("UPDATE `player_daily_content` SET " +
            "`progress` = #{progress}, " +
            "`state` = #{state}, " +
            "`rewards` = #{rewards}, " +
            "`last_updated_date` = #{last_updated_date}, " +
            "`updated_at` = #{updated_at} WHERE `player_id` = #{player_id} and `type` = #{type}")
    int update(DMPlayerDailyContent model);
}
