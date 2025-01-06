package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerStoneStatueGem;

import java.util.List;

public interface PlayerStoneStatueGemMapper {
    @Select("SELECT * FROM `player_stone_statue_gem` WHERE `player_id` = #{player_id}")
    List<DMPlayerStoneStatueGem> getAll(@Param("player_id") long playerId);

    @Select("SELECT * FROM `player_stone_statue_gem` WHERE `player_id` = #{player_id} AND `gem_id` = #{gem_id}")
    DMPlayerStoneStatueGem get(@Param("player_id") long playerId, @Param("gem_id") long gemId);

    @Insert("INSERT INTO `player_stone_statue_gem` (`player_id`, `gem_id`, `gem_level`, `updated_at`, `created_at`) " +
            "VALUES (#{player_id}, #{gem_id}, #{gem_level}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerStoneStatueGem model);

    @Update("UPDATE `player_stone_statue_gem` SET " +
            "`gem_level` = #{gem_level}, `updated_at` = #{updated_at} " +
            "WHERE `player_id` = #{player_id} AND `gem_id` = #{gem_id}")
    int update(DMPlayerStoneStatueGem model);
}
