package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerTower;

import java.util.List;

public interface PlayerTowerMapper {
    @Select("SELECT * FROM `player_towers` WHERE `id` = #{id}")
    DMPlayerTower get(@Param("id") long id);

    @Select("SELECT * FROM `player_towers` WHERE `player_id` = #{player_id}")
    List<DMPlayerTower> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_towers`(`player_id`, `tower_data_id`, `stage`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{tower_data_id}, #{stage}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerTower model);

    @Update("UPDATE `player_towers` SET " +
            "`stage` = #{stage}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerTower model);
}
