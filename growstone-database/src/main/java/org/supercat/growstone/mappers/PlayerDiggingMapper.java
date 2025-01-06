package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerDigging;
import org.supercat.growstone.models.DMPlayerEquipPreset;

import java.util.List;

public interface PlayerDiggingMapper {
    @Select("SELECT * FROM `player_digging` WHERE `id` = #{id}")
    DMPlayerDigging get(@Param("id") long id);

    @Select("SELECT * FROM `player_digging` WHERE `player_id` = #{player_id} and `index` = #{index}")
    DMPlayerDigging getByIndex(@Param("player_id") long player_id, @Param("index") int index);
    @Select("SELECT * FROM `player_digging` WHERE `player_id` = #{player_id}")
    List<DMPlayerDigging> getByPlayerId(@Param("player_id") long playerId);
    @Insert("INSERT INTO `player_digging`(`player_id`, `index`, `tier`, `is_digging`, `complete_at`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{index}, #{tier}, #{is_digging}, #{complete_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerDigging model);

    @Update("UPDATE `player_digging` SET " +
            "`tier` = #{tier}, " +
            "`is_digging` = #{is_digging}, " +
            "`complete_at` = #{complete_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerDigging model);
}
