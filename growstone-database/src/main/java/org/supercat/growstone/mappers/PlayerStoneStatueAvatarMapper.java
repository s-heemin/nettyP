package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMPlayerStoneStatueAvatar;

import java.util.List;

public interface PlayerStoneStatueAvatarMapper {
    @Select("SELECT * FROM `player_stone_statue_avatar` WHERE `player_id` = #{player_id}")
    List<DMPlayerStoneStatueAvatar> getAll(@Param("player_id") long playerId);

    @Select("SELECT * FROM `player_stone_statue_avatar` WHERE `player_id` = #{player_id} AND `avatar_id` = #{avatar_id}")
    DMPlayerStoneStatueAvatar get(@Param("player_id") long playerId, @Param("avatar_id") long avatarId);

    @Insert("INSERT INTO `player_stone_statue_avatar` (" +
            "`player_id`, `avatar_id`, `updated_at`, `created_at`" +
            ") VALUES (" +
            "#{player_id}, #{avatar_id}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerStoneStatueAvatar model);

}
