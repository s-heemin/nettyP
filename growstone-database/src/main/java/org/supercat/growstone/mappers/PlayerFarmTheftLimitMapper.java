package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerFarmTheftLimit;

public interface PlayerFarmTheftLimitMapper {
    @Select("SELECT * FROM `player_farm_theft_limits` WHERE `player_id` = #{player_id}")
    DMPlayerFarmTheftLimit get(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_farm_theft_limits` (`player_id`, `ymd`, `count`, `updated_at`, `created_at`) " +
            " VALUES(#{player_id}, #{ymd}, #{count}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarmTheftLimit model);

    @Update("UPDATE `player_farm_theft_limits` SET " +
            "`ymd` = #{ymd}, " +
            "`count` = #{count}, " +
            "`updated_at` = #{updated_at} WHERE id = #{id}")
    int update(DMPlayerFarmTheftLimit model);
}
