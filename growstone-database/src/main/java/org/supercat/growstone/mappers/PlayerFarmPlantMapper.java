package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerFarmPlant;

import java.time.Instant;
import java.util.List;

public interface PlayerFarmPlantMapper {
    @Select("SELECT * FROM player_farm_plants WHERE `player_id` = #{player_id}")
    List<DMPlayerFarmPlant> getAllByPlayerId(@Param("player_id") long playerId);

    @Select("SELECT * `FROM player_farms` WHERE `player_id` = #{player_id} and slot_index = #{slot_index}")
    DMPlayerFarmPlant getByPlayerId(@Param("player_id") long playerId,
                                    @Param("slot_index") int slot_index);

    @Insert("INSERT INTO `player_farm_plants` (player_id, slot_index, status, " +
            "`plant_id`, `theft_ymd`, `theft_player_id`, `theft_limit_count`,`seed_start_at`, `seed_end_at`, " +
            "`theft_end_at`, `updated_at`, `created_at`) " +
            " VALUES(#{player_id}, #{slot_index}, #{status}, " +
            "#{plant_id}, #{theft_ymd}, #{theft_player_id}, #{theft_limit_count}, #{seed_start_at}, #{seed_end_at}, " +
            "#{theft_end_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarmPlant model);

    @Update("UPDATE `player_farm_plants` SET " +
            "`status` = #{status}, " +
            "`plant_id` = #{plant_id}, " +
            "`theft_ymd` = #{theft_ymd}, " +
            "`theft_player_id` = #{theft_player_id}, " +
            "`theft_limit_count` = #{theft_limit_count}, " +
            "`seed_start_at` = #{seed_start_at}, " +
            "`seed_end_at` = #{seed_end_at}, " +
            "`theft_end_at` = #{theft_end_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerFarmPlant model);

    @Select("SELECT * FROM `player_farm_plants` " +
            "WHERE `theft_end_at` <= #{at} " +
            "AND `status` = #{status} " +
            "ORDER BY `theft_end_at` ASC LIMIT #{count}")
    List<DMPlayerFarmPlant> getAllByTheft(@Param("status") int status, @Param("at") Instant at, @Param("count") int count);

    @Select("SELECT COUNT(*) FROM `player_farm_plants` " +
            "WHERE `theft_player_id` = #{theft_player_id}")
    int getStealCount(@Param("theft_player_id") long theft_player_id);
}
