package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerAchievement;
import org.supercat.growstone.models.DMPlayerAdvertise;

public interface PlayerAchievementMapper {
    @Select("SELECT * FROM `player_achievements` WHERE `player_id` = #{player_id}")
    DMPlayerAchievement getByPlayerId(@Param("player_id") long player_id);

    @Insert("INSERT INTO `player_achievements`(`player_id`, `data`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{data}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerAchievement model);

    @Update("UPDATE `player_achievements` SET " +
            "`data` = #{data}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerAchievement model);
}
