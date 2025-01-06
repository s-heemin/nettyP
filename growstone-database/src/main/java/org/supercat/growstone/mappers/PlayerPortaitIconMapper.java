package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerAvatar;
import org.supercat.growstone.models.DMPlayerPortraitIcon;

import java.time.Instant;
import java.util.List;

public interface PlayerPortaitIconMapper {
    @Select("SELECT * FROM `player_portrait_icon` WHERE `id` = #{id}")
    DMPlayerPortraitIcon get(@Param("id") long id);

    @Select("SELECT * FROM `player_portrait_icon` WHERE `player_id` = #{player_id}")
    List<DMPlayerPortraitIcon> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_portrait_icon`(`player_id`, `icon_id`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{icon_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerPortraitIcon model);

    @Delete("DELETE FROM `player_portrait_icon` WHERE `id` = #{id}")
    int deleteForCheat(@Param("id") long id);
}
