package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMWorldClanChat;

import java.time.Instant;
import java.util.List;

public interface WorldClanChatMapper {
    @Select("SELECT * FROM `world_clan_chats` WHERE `channel_id` = #{channel_id} and `clan_id` = #{clan_id} and `created_at` > #{until_at} ORDER BY `created_at` DESC")
    List<DMWorldClanChat> getAll(@Param("channel_id") long channel_id,
                                 @Param("clan_id") long clan_id,
                                 @Param("until_at")Instant until_at);

    @Insert("INSERT INTO `world_clan_chats`(`channel_id`, `player_id`, `clan_id`, `name`, `portrait_icon`, `text`, `created_at`)" +
            " VALUES(#{channel_id}, #{player_id}, #{clan_id}, #{name}, #{portrait_icon}, #{text}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMWorldClanChat model);
}
