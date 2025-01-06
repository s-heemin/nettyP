package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerClanJoinRequest;

import java.time.Instant;
import java.util.List;

public interface PlayerClanJoinRequestMapper {
    @Select("SELECT * FROM `player_clan_join_requests` WHERE `id` = #{id}")
    DMPlayerClanJoinRequest get(@Param("id") long id);

    @Select("SELECT * FROM `player_clan_join_requests` WHERE `player_id` = #{player_id}")
    List<DMPlayerClanJoinRequest> getByPlayerId(@Param("player_id") long playerId);

    @Select("SELECT * FROM `player_clan_join_requests` WHERE `clan_id` = #{clan_id}")
    List<DMPlayerClanJoinRequest> getByClanId(@Param("clan_id") long clan_id);

    @Select("SELECT * FROM `player_clan_join_requests` WHERE `player_id` = #{player_id} and `clan_id` = #{clan_id}")
    DMPlayerClanJoinRequest getByPlayerIdAndClanId(@Param("player_id") long playerId, @Param("clan_id") long clan_id);

    @Insert("INSERT INTO `player_clan_join_requests`(`player_id`, `clan_id`,  `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{clan_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerClanJoinRequest model);

    @Update("DELETE FROM `player_clan_join_requests` " +
            "WHERE `player_id` = #{player_id} and `clan_id` = #{clan_id}")
    int deleteByPlayerIdAndClanId(@Param("player_id") long player_id, @Param("clan_id") long clan_id);

    @Update("DELETE FROM `player_clan_join_requests` " +
            "WHERE `player_id` = #{player_id}")
    int deleteByPlayerId(@Param("player_id") long player_id);
}
