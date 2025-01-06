package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerEquipPreset;

import java.time.Instant;
import java.util.List;

public interface PlayerEquipPresetMapper {
    @Select("SELECT * FROM `player_equip_preset` WHERE `id` = #{id}")
    DMPlayerEquipPreset get(@Param("id") long id);

    @Select("SELECT * FROM `player_equip_preset` WHERE `player_id` = #{player_id}")
    List<DMPlayerEquipPreset> getByPlayerId(@Param("player_id") long playerId);

    @Select("SELECT * FROM `player_equip_preset` WHERE `player_id` = #{player_id} and `preset_index` = #{preset_index}")
    List<DMPlayerEquipPreset> getByPreset(@Param("player_id") long playerId,
                                          @Param("preset_index") int preset_index);
    @Insert("INSERT INTO `player_equip_preset`(`player_id`, `type`, `preset_index`, `equip_index`, `data_id`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{type}, #{preset_index}, #{equip_index}, #{data_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerEquipPreset model);

    @Update("UPDATE `player_equip_preset` SET " +
            "`preset_index` = #{preset_index}, " +
            "`equip_index` = #{equip_index}, " +
            "`data_id` = #{data_id}, " +
            "`updated_at` = #{updated_at} WHERE id = #{id}")
    int update(DMPlayerEquipPreset model);

    @Delete("DELETE FROM `player_equip_preset` WHERE `player_id` = #{player_id}")
    int deleteForCheat(@Param("player_id") long player_id);
}
