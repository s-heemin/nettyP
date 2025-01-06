package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMPlayerFarmHistory;

import java.time.Instant;
import java.util.List;

public interface PlayerFarmHistoryMapper {
    @Select("SELECT * FROM `player_farm_histories` " +
            "WHERE `player_id` = #{player_id} AND `created_at` >= #{at} " +
            "ORDER BY id DESC LIMIT #{count}")
    List<DMPlayerFarmHistory> getAll(@Param("player_id") long playerId, @Param("count") int count, @Param("at") Instant at);

    @Insert("INSERT INTO `player_farm_histories` (`player_id`, `type`, `data`, `created_at`) " +
            " VALUES(#{player_id}, #{type}, #{data}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFarmHistory model);
}
