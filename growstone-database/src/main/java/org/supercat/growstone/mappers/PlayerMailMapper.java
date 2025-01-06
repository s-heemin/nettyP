package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerGrowth;
import org.supercat.growstone.models.DMPlayerMail;

import java.time.Instant;
import java.util.List;

public interface PlayerMailMapper {
    @Select("SELECT * FROM `player_mails` WHERE `id` = #{id}")
    DMPlayerMail get(@Param("id") long id);

    @Select("SELECT * FROM `player_mails` WHERE `player_id` = #{player_id} and `expire_at` > now() and `is_read` = false")
    List<DMPlayerMail> getAllByNoReadMail(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_mails`(`player_id`, `mail_type`, " +
            "`rewards`, `is_read`, `sender`, `expire_at`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{mail_type}, " +
            "#{rewards}, #{is_read}, #{sender}, #{expire_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerMail model);

    @Update("UPDATE `player_mails` SET " +
            "`is_read` = #{is_read}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateRead(DMPlayerMail model);
}
