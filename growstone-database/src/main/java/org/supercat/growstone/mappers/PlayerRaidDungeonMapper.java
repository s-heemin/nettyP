package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerRaidDungeon;

import java.util.List;

public interface PlayerRaidDungeonMapper {
    @Select("SELECT * FROM `player_raid_dungeons` WHERE `id` = #{id}")
    DMPlayerRaidDungeon get(@Param("id") long id);

    @Select("SELECT * FROM `player_raid_dungeons` WHERE `player_id` = #{player_id}")
    List<DMPlayerRaidDungeon> getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_raid_dungeons`(`player_id`, `raid_data_id`, `score`, `remain_ticket_count`, `remain_view_ad_count`, `reset_ymd` ,`updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{raid_data_id}, #{score}, #{remain_ticket_count}, #{remain_view_ad_count}, #{reset_ymd}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerRaidDungeon model);

    @Update("UPDATE `player_raid_dungeons` SET " +
            "`score` = #{score}, " +
            "`remain_ticket_count` = #{remain_ticket_count}, " +
            "`remain_view_ad_count` = #{remain_view_ad_count}, " +
            "`reset_ymd` = #{reset_ymd}, " +
            "`updated_at` = #{updated_at} WHERE id = #{id}")
    int update(DMPlayerRaidDungeon model);
}
