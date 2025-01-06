package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerGacha;

import java.util.List;

public interface PlayerGachaMapper {
    @Select("SELECT * FROM `player_gachas` WHERE `id` = #{id}")
    DMPlayerGacha get(@Param("id") long id);

    @Select("SELECT * FROM `player_gachas` WHERE `player_id` = #{player_id} and `index` = #{index}")
    DMPlayerGacha getByIndex(@Param("player_id") long player_id, @Param("index") long index);

    @Select("SELECT * FROM `player_gachas` WHERE `player_id` = #{player_id}")
    List<DMPlayerGacha> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_gachas`(`player_id`, `index`, `level`, `exp`, `gacha_max_count`,`reset_ymd`,`ad_view_count`,`updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{index}, #{level}, #{exp}, #{gacha_max_count}, #{reset_ymd}, #{ad_view_count}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerGacha model);

    @Update("UPDATE `player_gachas` SET " +
            "`level` = #{level}, " +
            "`exp` = #{exp}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerGacha model);

    @Update("UPDATE `player_gachas` SET " +
            "`gacha_max_count` = #{gacha_max_count}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateGachaMaxCount(DMPlayerGacha model);

    @Update("UPDATE `player_gachas` SET " +
            "`ad_view_count` = #{ad_view_count}, " +
            "`reset_ymd` = #{reset_ymd}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateViewCommercialCount(DMPlayerGacha model);
}
