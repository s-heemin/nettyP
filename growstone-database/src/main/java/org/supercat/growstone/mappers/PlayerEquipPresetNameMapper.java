package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerEquipPresetName;

import java.util.List;

public interface PlayerEquipPresetNameMapper {
    @Select("SELECT * FROM `player_equip_preset_name` WHERE `id` = #{id}")
    DMPlayerEquipPresetName get(@Param("id") long id);

    @Select("SELECT * FROM `player_equip_preset_name` WHERE `player_id` = #{player_id}")
    List<DMPlayerEquipPresetName> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_equip_preset_name`(`player_id`, `index`, `name`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{index}, #{name}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerEquipPresetName model);

    @Update("UPDATE `player_equip_preset_name` SET " +
            "`name` = #{name}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerEquipPresetName model);
}
