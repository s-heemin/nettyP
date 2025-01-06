package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerStoneStatue;

public interface PlayerStoneStatueMapper {
    @Select("SELECT * FROM `player_stone_statue` WHERE `player_id` = #{player_id}")
    DMPlayerStoneStatue get(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_stone_statue` (" +
            "`player_id`, `enchant_level`, `enchant_exp`, `enchant_safe_grade`, `avatar_id`, `updated_at`, `created_at`" +
            ") VALUES (" +
            "#{player_id}, #{enchant_level}, #{enchant_exp}, #{enchant_safe_grade}, #{avatar_id}, #{updated_at}, #{created_at}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerStoneStatue model);

    @Update("UPDATE `player_stone_statue` SET " +
            "`enchant_level` = #{enchant_level}, " +
            "`enchant_exp` = #{enchant_exp}, " +
            "`enchant_safe_grade` = #{enchant_safe_grade}, " +
            "`avatar_id` = #{avatar_id}, " +
            "`updated_at` = #{updated_at} WHERE `player_id` = #{player_id}")
    int update(DMPlayerStoneStatue model);
}
