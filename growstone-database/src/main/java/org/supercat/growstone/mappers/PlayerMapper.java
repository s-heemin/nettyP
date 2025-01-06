package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayer;

import java.time.Instant;

public interface PlayerMapper {
    @Select("SELECT * FROM `players` WHERE `id` = #{id}")
    DMPlayer get(@Param("id") long id);

    @Select("SELECT * FROM `players` WHERE `global_master_player_id` = #{global_master_player_id} ORDER BY `last_connected_at` DESC LIMIT 1")
    DMPlayer getLastConnectedPlayer(@Param("global_master_player_id") long globalMasterPlayerId);

    @Select("SELECT * FROM `players` WHERE `friend_code` = #{friend_code}")
    DMPlayer getPlayerByFriendCode(@Param("friend_code") String friendCode);

    @Select("SELECT * FROM `players` WHERE `name` = #{name}")
    DMPlayer getPlayerByFriendName(@Param("name") String name);

    @Insert("INSERT INTO `players`(`global_master_player_id`, `channel_id`, `name`, `portrait_id`, `level`, `exp`, `attack_power`, `stage_group`, `stage`, `on_boss_gauge`, `preset_index`, `friend_code`, `remove_ad`, `clan_id`, " +
            "`ban_status`, `last_change_name_at`, `last_connected_at`, `last_disconnected_at`, `updated_at`, `created_at`)" +
            " VALUES(#{global_master_player_id}, #{channel_id}, #{name}, #{portrait_id}, #{level}, #{exp}, #{attack_power}, #{stage_group}, #{stage}, #{on_boss_gauge}, #{preset_index}, #{friend_code}, #{remove_ad}, #{clan_id}, " +
            "#{ban_status},  #{last_change_name_at}, #{last_connected_at}, #{last_disconnected_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`level` = #{level}, " +
            "`exp` = #{exp}, " +
            "`stage_group` = #{stage_group}, " +
            "`stage` = #{stage}, " +
            "`name` = #{name}, " +
            "`portrait_id` = #{portrait_id}, " +
            "`attack_power` = #{attack_power}, " +
            "`on_boss_gauge` = #{on_boss_gauge}, " +
            "`last_change_name_at` = #{last_change_name_at}, " +
            "`last_disconnected_at` = #{last_disconnected_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`name` = #{name}, " +
            "`friend_code` = #{friend_code}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateAfterInsertData(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`name` = #{name}, " +
            "`last_change_name_at` = #{now}, " +
            "`updated_at` = #{now} WHERE `id` = #{id}")
    int updateName(@Param("id") long id,
                   @Param("name") String name,
                   @Param("now") Instant now);

    @Update("UPDATE `players` SET " +
            "`level` = #{level}, " +
            "`exp` = #{exp}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateLevel(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`stage_group` = #{stage_group}, " +
            "`stage` = #{stage}, " +
            "`on_boss_gauge` = #{on_boss_gauge}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateStage(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`preset_index` = #{preset_index}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updatePresetIndex(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`portrait_id` = #{portrait_id}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updatePortrait(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`on_boss_gauge` = #{on_boss_gauge}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int onBossGauge(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`remove_ad` = #{remove_ad}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateRemoveAd(DMPlayer model);

    @Update("UPDATE `players` SET " +
            "`clan_id` = 0, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id} and `clan_id` = #{clan_id}")
    int updateClanIdByPlayerId(@Param("id") long player_id,
                               @Param("clan_id") long clan_id,
                               @Param("updated_at") Instant updated_at);
}
