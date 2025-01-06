package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerCollection;
import org.supercat.growstone.models.DMPlayerCurrency;

import java.util.List;

public interface PlayerCurrencyMapper {
    @Select("SELECT * FROM `player_currencies` WHERE `id` = #{id}")
    DMPlayerCurrency get(@Param("id") long id);

    @Select("SELECT * FROM `player_currencies` WHERE `player_id` = #{player_id}")
    DMPlayerCurrency getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_currencies`(`player_id`, `gold`, `free_ruby`, `paid_ruby`, `free_diamond`, `paid_diamond`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{gold}, #{free_ruby}, #{paid_ruby}, #{free_diamond}, #{paid_diamond}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerCurrency model);

    @Update("UPDATE player_currencies SET " +
            "`gold` = #{gold}, " +
            "`free_ruby` = #{free_ruby}, " +
            "`paid_ruby` = #{paid_ruby}, " +
            "`free_diamond` = #{free_diamond}, " +
            "`paid_diamond` = #{paid_diamond}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerCurrency model);
}
