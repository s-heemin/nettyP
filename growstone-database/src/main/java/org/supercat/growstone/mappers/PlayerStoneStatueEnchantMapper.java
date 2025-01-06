package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerStoneStatueEnchant;

import java.util.List;

public interface PlayerStoneStatueEnchantMapper {
    @Select("SELECT * FROM `player_stone_statue_enchant` WHERE `player_id` = #{player_id}")
    List<DMPlayerStoneStatueEnchant> getAll(@Param("player_id") int playerId);

    @Select("SELECT * FROM `player_stone_statue_enchant` WHERE `player_id` = #{player_id} AND `order_id` = #{order_id}")
    DMPlayerStoneStatueEnchant get(@Param("player_id") long playerId, @Param("order_id") int orderId);

    @Insert("INSERT INTO `player_stone_statue_enchant` (" +
            "`player_id`, `order_id`, `data`, `updated_at`, `created_at`" +
            ") VALUES (" +
            "#{player_id}, #{order_id}, #{data}, #{updated_at}, #{created_at}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerStoneStatueEnchant model);

    @Update("UPDATE `player_stone_statue_enchant` SET " +
            "`data` = #{data}, `updated_at` = #{updated_at} " +
            "WHERE `player_id` = #{player_id} AND `order_id` = #{order_id}")
    int update(DMPlayerStoneStatueEnchant model);
}
