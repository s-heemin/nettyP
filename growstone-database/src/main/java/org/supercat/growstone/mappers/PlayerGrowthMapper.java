package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerGrowth;

import java.time.Instant;
import java.util.List;

public interface PlayerGrowthMapper {
    @Select("SELECT * FROM `player_growths` WHERE `id` = #{id}")
    DMPlayerGrowth get(@Param("id") long id);

    @Select("SELECT * FROM `player_growths` WHERE `player_id` = #{player_id}")
    List<DMPlayerGrowth> getByPlayerId(@Param("player_id") long playerId);
    @Select("SELECT * FROM `player_growths` WHERE `player_id` = #{player_id} and `growth_id` = #{growth_id}")
    DMPlayerGrowth getByGrowthId(@Param("player_id") long playerId,
                                 @Param("growth_id") long growth_id);
    @Insert("INSERT INTO `player_growths`(`player_id`, `growth_id`, `count`, `type`, " +
            "`level`, `promote_level`, `limit_break_level`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{growth_id}, #{count}, #{type}, " +
            "#{level}, #{promote_level}, #{limit_break_level}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerGrowth model);

    @Update("UPDATE `player_growths` SET " +
            "`player_id` = #{player_id}, " +
            "`growth_id` = #{growth_id}, " +
            "`count` = #{count}, " +
            "`level` = #{level}, " +
            "`promote_level` = #{promote_level}, " +
            "`limit_break_level` = #{limit_break_level}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerGrowth model);

    @Delete("DELETE FROM `player_growths` WHERE `player_id` = #{player_id}")
    int clearForCheat(@Param("player_id") long player_id);
}
