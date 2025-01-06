package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerFarm;

public interface PlayerFarmMapper {
    @Select("SELECT * FROM `player_farms` WHERE `player_id` = #{player_id}")
    DMPlayerFarm getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_farms` (`player_id`, `exp`, `updated_at`, `created_at`) " +
            " VALUES(#{player_id}, #{exp}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarm model);

    @Update("UPDATE `player_farms` SET " +
            "`exp` = #{exp}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerFarm model);
}
