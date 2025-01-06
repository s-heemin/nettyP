package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerAvatar;

import java.time.Instant;
import java.util.List;

public interface PlayerAvatarMapper {
    @Select("SELECT * FROM `player_avatars` WHERE `id` = #{id}")
    DMPlayerAvatar get(@Param("id") long id);

    @Select("SELECT * FROM `player_avatars` WHERE `player_id` = #{player_id} AND `isEquip` = 1")
    DMPlayerAvatar getByEquipAvatar(@Param("player_id") long player_id);

    @Select("SELECT * FROM `player_avatars` WHERE `player_id` = #{player_id}")
    List<DMPlayerAvatar> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_avatars`(`player_id`, `avatar_id`, `isEquip`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{avatar_id}, #{isEquip}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerAvatar model);

    @Update("UPDATE `player_avatars` SET " +
            "`isEquip` = #{isEquip}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateEquip(@Param("id") long id,
                    @Param("isEquip") boolean isEquip,
                    @Param("updated_at") Instant updated_at);

    @Delete("DELETE FROM `player_avatars` WHERE `id` = #{id}")
    int deleteForCheat(@Param("id") long id);
}
