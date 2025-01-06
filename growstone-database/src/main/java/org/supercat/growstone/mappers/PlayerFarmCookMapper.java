package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerFarmCook;

public interface PlayerFarmCookMapper {
    @Select("SELECT * FROM `player_farm_cooks` WHERE `player_id` = #{player_id}")
    DMPlayerFarmCook get(@Param("player_id") long player_id);

    @Insert("INSERT INTO `player_farm_cooks`(player_id, `level`, `slots`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{level}, #{slots}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarmCook model);

    @Update("UPDATE `player_farm_cooks` SET " +
            "`level` = #{level}, " +
            "`slots` = #{slots}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerFarmCook model);

}
