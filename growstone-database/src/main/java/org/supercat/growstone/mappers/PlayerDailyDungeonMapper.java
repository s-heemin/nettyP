package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerDailyDungeon;

import java.util.List;

public interface PlayerDailyDungeonMapper {
    @Select("SELECT * FROM `player_daily_dungeons` WHERE `id` = #{id}")
    DMPlayerDailyDungeon get(@Param("id") long id);

    @Select("SELECT * FROM `player_daily_dungeons` WHERE `player_id` = #{player_id}")
    List<DMPlayerDailyDungeon> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_daily_dungeons`(`player_id`, `dungeon_data_id`, `stage`, `remain_ticket_count`, `remain_view_ad_count`, `reset_ymd` ,`updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{dungeon_data_id}, #{stage}, #{remain_ticket_count}, #{remain_view_ad_count}, #{reset_ymd}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerDailyDungeon model);

    @Update("UPDATE `player_daily_dungeons` SET " +
            "`stage` = #{stage}, " +
            "`remain_ticket_count` = #{remain_ticket_count}, " +
            "`remain_view_ad_count` = #{remain_view_ad_count}, " +
            "`reset_ymd` = #{reset_ymd}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerDailyDungeon model);
}
