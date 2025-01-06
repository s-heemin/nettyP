package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.models.DMPlayerPickUpGachaPoint;

import java.util.List;

public interface PlayerPickUpGachaMapper {
    @Select("SELECT * FROM `player_pick_up_gacha_points` WHERE `id` = #{id}")
    DMPlayerPickUpGachaPoint get(@Param("id") long id);

    @Select("SELECT * FROM `player_pick_up_gacha_points` WHERE `player_id` = #{player_id} and `shop_data_id` = #{shop_data_id}")
    DMPlayerPickUpGachaPoint getByDataId(@Param("player_id") long player_id, @Param("shop_data_id") long shop_data_id);
    @Select("SELECT * FROM `player_pick_up_gacha_points` WHERE `player_id` = #{player_id}")
    List<DMPlayerPickUpGachaPoint> getByPlayerId(@Param("player_id") long playerId);
    @Insert("INSERT INTO `player_pick_up_gacha_points`(`player_id`, `shop_data_id`, `point`, `rewards`,`updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{shop_data_id}, #{point}, #{rewards}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerPickUpGachaPoint model);

    @Update("UPDATE `player_pick_up_gacha_points` SET " +
            "`point` = #{point}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updatePoint(DMPlayerPickUpGachaPoint model);

    @Update("UPDATE `player_pick_up_gacha_points` SET " +
            "`rewards` = #{rewards}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateReward(DMPlayerPickUpGachaPoint model);
}
