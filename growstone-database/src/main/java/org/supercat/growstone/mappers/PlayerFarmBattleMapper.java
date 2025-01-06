package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMPlayerFarmBattle;

import java.time.Instant;
import java.util.List;

public interface PlayerFarmBattleMapper {
    @Select("SELECT * FROM `player_farm_battles` " +
            "WHERE `player_id` = #{player_id} AND `created_at` >= #{at} " +
            "ORDER BY id DESC LIMIT #{count}")
    List<DMPlayerFarmBattle> getAll(@Param("player_id") long playerId, @Param("count") int count, @Param("at") Instant at);

    @Insert("INSERT INTO `player_farm_battles` (`player_id`, `target_player_id`, `data`, `created_at`) " +
            " VALUES(#{player_id}, #{target_player_id}, #{data}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarmBattle model);
}
