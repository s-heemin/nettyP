package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerPickUpGachaPoint;
import org.supercat.growstone.models.DMPlayerPortraitIcon;
import org.supercat.growstone.models.DMPlayerQuest;

import java.util.List;

public interface PlayerQuestMapper {
    @Select("SELECT * FROM `player_quests` WHERE `id` = #{id}")
    DMPlayerQuest get(@Param("id") long id);

    @Select("SELECT * FROM `player_quests` WHERE `player_id` = #{player_id}")
    DMPlayerQuest getByPlayerId(@Param("player_id") long playerId);

    @Insert("INSERT INTO `player_quests`(`player_id`, `step`, `progress`, `state`, `next_stage_group_id_condition`, `next_stage_id_condition` , `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{step}, #{progress}, #{state}, #{next_stage_group_id_condition}, #{next_stage_id_condition}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerQuest model);

    @Update("UPDATE `player_quests` SET " +
            "`step` = #{step}, " +
            "`progress` = #{progress}, " +
            "`state` = #{state}, " +
            "`next_stage_group_id_condition` = #{next_stage_group_id_condition}, " +
            "`next_stage_id_condition` = #{next_stage_id_condition}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerQuest model);
}