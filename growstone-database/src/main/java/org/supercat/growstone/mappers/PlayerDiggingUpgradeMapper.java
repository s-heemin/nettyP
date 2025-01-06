package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerDigging;
import org.supercat.growstone.models.DMPlayerDiggingUpgrade;

import java.util.List;

public interface PlayerDiggingUpgradeMapper {
    @Select("SELECT * FROM `player_digging_upgrades` WHERE `id` = #{id}")
    DMPlayerDiggingUpgrade get(@Param("id") long id);

    @Select("SELECT * FROM `player_digging_upgrades` WHERE `player_id` = #{player_id} and `type` = #{type}")
    DMPlayerDiggingUpgrade getByType(@Param("player_id") long playerId, @Param("type") int type);

    @Select("SELECT * FROM `player_digging_upgrades` WHERE `player_id` = #{player_id}")
    List<DMPlayerDiggingUpgrade> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_digging_upgrades`(`player_id`, `type`, `level`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{type}, #{level},  #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerDiggingUpgrade model);

    @Update("UPDATE `player_digging_upgrades` SET " +
            "`level` = #{level}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerDiggingUpgrade model);
}
