package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerStatGrowth;

import java.util.List;

public interface PlayerStatGrowthMapper {
    @Select("SELECT * FROM `player_stat_growths` WHERE `id` = #{id}")
    DMPlayerStatGrowth get(@Param("id") long id);

    @Select("SELECT * FROM `player_stat_growths` WHERE `player_id` = #{player_id}")
    List<DMPlayerStatGrowth> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_stat_growths`(`player_id`, `page`, `level`, `stat`,  `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{page}, #{level}, #{stat}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerStatGrowth model);

    @Update("UPDATE `player_stat_growths` SET " +
            "`page` = #{page}, " +
            "`level` = #{level}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerStatGrowth model);
}
