package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerAdvertise;

public interface PlayerAdvertiseMapper {
    @Select("SELECT * FROM `player_advertises` WHERE `player_id` = #{player_id} and `type` = #{type}")
    DMPlayerAdvertise getByType(@Param("player_id") long player_id, @Param("type") int type);

    @Insert("INSERT INTO `player_advertises`(player_id, `type`, `view_count`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{type}, #{view_count}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerAdvertise model);

    @Update("UPDATE `player_advertises` SET " +
            "`view_count` = #{view_count}, " +
            "`updated_at` = #{updated_at} WHERE `player_id` = #{player_id} and `type` = #{type}")
    int update(DMPlayerAdvertise model);
}
