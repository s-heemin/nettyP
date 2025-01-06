package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMClan;

import java.util.List;

public interface ClanMapper {
    @Select("SELECT * FROM `clans` WHERE `id` = #{id}")
    DMClan get(@Param("id") long id);

    @Select("SELECT * FROM `clans`")
    List<DMClan> getAll();

    @Select("SELECT * FROM `clans` WHERE `master_player_id` = #{player_id}")
    DMClan getByMasterPlayerId(@Param("player_id") long player_id);

    @Insert("INSERT INTO `clans`(`name`, `level`, `exp`, `master_player_id`, `rank`, `notice`, `introduction`, `join_type`, `state`, `last_change_name_at`, `master_last_logout_at`, `updated_at`, `created_at`)" +
            " VALUES(#{name}, #{level}, #{exp}, #{master_player_id}, #{rank}, #{notice}, #{introduction}, #{join_type}, #{state}, #{last_change_name_at}, #{master_last_logout_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMClan model);

    @Update("UPDATE `clans` SET " +
            "`level` = #{level}, " +
            "`exp` = #{exp}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateClanExp(DMClan model);
    @Update("UPDATE `clans` SET " +
            "`introduction` = #{introduction}, " +
            "`notice` = #{notice}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateText(DMClan model);

    @Update("UPDATE `clans` SET " +
            "`name` = #{name}, " +
            "`last_change_name_at` = #{last_change_name_at}, " +
            "`updated_at` = #{updated_at} WHERE id = #{id}")
    int updateName(DMClan model);

    @Update("UPDATE `clans` SET " +
            "`master_last_logout_at` = #{master_last_logout_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateMasterLastLogoutAt(DMClan model);

    @Update("UPDATE `clans` SET " +
            "`state` = #{state}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateState(DMClan model);

    @Update("UPDATE `clans` SET " +
            "`master_player_id` = #{master_player_id}, " +
            "`master_last_logout_at` = #{master_last_logout_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateMaster(DMClan model);

}
