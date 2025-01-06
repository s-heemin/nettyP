package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerBlock;

import java.util.List;

public interface PlayerBlockMapper {
    @Select("SELECT * FROM `player_blocks` WHERE `id` = #{id}")
    DMPlayerBlock get(@Param("id") long id);

    @Select("SELECT * FROM `player_blocks` WHERE `player_id` = #{player_id} AND `target_player_id` = #{target_player_id}")
    DMPlayerBlock getByPlayerIdAndTargetPlayerId(@Param("player_id") long playerId, @Param("target_player_id") long targetPlayerId);
    @Select("SELECT * FROM `player_blocks` WHERE `player_id` = #{player_id}")
    List<DMPlayerBlock> getByPlayerId(@Param("player_id") long playerId);
    @Insert("INSERT INTO `player_blocks`(`player_id`, `target_player_id`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{target_player_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerBlock model);
    @Delete("DELETE FROM `player_blocks` WHERE `id` = #{id}")
    int delete(@Param("id") long id);
}
