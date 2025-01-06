package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerExploration;

public interface PlayerExplorationMapper {
    @Select("SELECT * FROM `player_explorations` WHERE `player_id` = #{player_id}")
    DMPlayerExploration get(@Param("player_id") long player_id);

    @Insert("INSERT INTO `player_explorations`(player_id, `level`, `exp`, `auto`, `quests`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{level}, #{exp}, #{auto}, #{quests}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerExploration model);

    @Update("UPDATE `player_explorations` SET " +
            "`level` = #{level}, " +
            "`exp` = #{exp}, " +
            "`auto` = #{auto}, " +
            "`quests` = #{quests}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerExploration model);

}
