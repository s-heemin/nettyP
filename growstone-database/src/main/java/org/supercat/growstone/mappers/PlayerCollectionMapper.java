package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerCollection;

import java.util.List;

public interface PlayerCollectionMapper {
    @Select("SELECT * FROM `player_collections` WHERE `id` = #{id}")
    DMPlayerCollection get(@Param("id") long id);

    @Select("SELECT * FROM `player_collections` WHERE `player_id` = #{player_id}")
    List<DMPlayerCollection> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_collections`(`player_id`, `type`, `collection_id`, `level`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{type}, #{collection_id}, #{level}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerCollection model);

    @Update("UPDATE `player_collections` SET " +
            "`level` = #{level}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerCollection model);
}