package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerItem;

import java.util.List;

public interface PlayerItemMapper {
    @Select("SELECT * FROM `player_items` WHERE `id` = #{id}")
    DMPlayerItem get(@Param("id") long id);

    @Select("SELECT * FROM `player_items` WHERE `player_id` = #{player_id}")
    List<DMPlayerItem> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_items`(`player_id`, `item_id`, `item_data_id`, `count`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{item_id}, #{item_data_id}, #{count}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerItem model);

    @Update("UPDATE `player_items` SET " +
            "`count` = #{count}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerItem model);
}
