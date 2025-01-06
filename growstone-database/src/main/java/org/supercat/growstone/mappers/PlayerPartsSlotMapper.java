package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerPartsSlot;

import java.util.List;

public interface PlayerPartsSlotMapper {
    @Select("SELECT * FROM `player_parts_slots` WHERE `id` = #{id}")
    DMPlayerPartsSlot get(@Param("id") long id);

    @Select("SELECT * FROM `player_parts_slots` WHERE `player_id` = #{player_id}")
    List<DMPlayerPartsSlot> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_parts_slots`(`player_id`, `type`, `level`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{type}, #{level}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerPartsSlot model);

    @Update("UPDATE `player_parts_slots` SET " +
            "`level` = #{level}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerPartsSlot model);
}
