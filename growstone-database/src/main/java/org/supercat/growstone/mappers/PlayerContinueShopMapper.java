package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerContinueShop;

public interface PlayerContinueShopMapper {
    @Select("SELECT * FROM `player_continue_shop` WHERE `player_id` = #{player_id} and `group_id` = #{group_id}")
    DMPlayerContinueShop getByGroupId(@Param("player_id") long player_id, @Param("group_id") int group_id);

    @Insert("INSERT INTO `player_continue_shop`(player_id, `group_id`, `step_id`,`updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{group_id}, #{step_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerContinueShop model);

    @Update("UPDATE `player_continue_shop` SET " +
            "`group_id` = #{group_id}, " +
            "`step_id` = #{step_id}, " +
            "`updated_at` = #{updated_at} WHERE `player_id` = #{player_id} and `type` = #{type}")
    int update(DMPlayerContinueShop model);
}
