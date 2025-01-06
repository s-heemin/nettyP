package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.models.DMPlayerExploration;

import java.util.List;

public interface PlayerEventMapper {
    @Select("SELECT * FROM `player_events` WHERE `id` = #{id}")
    DMPlayerEvent get(@Param("id") long id);

    @Select("SELECT * FROM `player_events` WHERE `player_id` = #{player_id}")
    List<DMPlayerEvent> getByPlayerId(@Param("player_id") long player_id);

    @Insert("INSERT INTO `player_events`(`player_id`,`event_id`, `event_data_id`, `state`, `progress`, `rewards`, `last_updated_date`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{event_id}, #{event_data_id}, #{state}, #{progress}, #{rewards}, #{last_updated_date}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerEvent model);

    @Update("UPDATE `player_events` SET " +
            "`progress` = #{progress}, " +
            "`state` = #{state}, " +
            "`last_updated_date` = #{last_updated_date}, " +
            "`rewards` = #{rewards}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerEvent model);
}
