package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerShopPass;
import org.supercat.growstone.models.DMPlayerStatGrowth;

import java.util.List;

public interface PlayerShopPassMapper {
    @Select("SELECT * FROM `player_shop_passes` WHERE `id` = #{id}")
    DMPlayerShopPass get(@Param("id") long id);

    @Select("SELECT * FROM `player_shop_passes` WHERE `player_id` = #{player_id} and `shop_pass_id` = #{shop_pass_id}")
    DMPlayerShopPass getByShopPassId(@Param("player_id") long playerId, @Param("shop_pass_id") long shopPassId);

    @Insert("INSERT INTO `player_shop_passes`(`player_id`, `shop_pass_id`, `open_step`, `is_paid`,`last_free_reward_step`, `last_paid_reward_step`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{shop_pass_id}, #{open_step}, #{is_paid}, #{last_free_reward_step}, #{last_paid_reward_step}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerShopPass model);

    @Update("UPDATE `player_shop_passes` SET " +
            "`open_step` = #{open_step}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerShopPass model);

    @Update("UPDATE `player_shop_passes` SET " +
            "`last_free_reward_step` = #{last_free_reward_step}, " +
            "`last_paid_reward_step` = #{last_paid_reward_step}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateRewards(DMPlayerShopPass model);

    @Update("UPDATE `player_shop_passes` SET " +
            "`is_paid` = #{is_paid}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updatePaid(DMPlayerShopPass model);
}
