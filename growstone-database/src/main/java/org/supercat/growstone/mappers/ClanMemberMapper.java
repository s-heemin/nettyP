package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMClan;
import org.supercat.growstone.models.DMClanMember;

import java.util.List;

public interface ClanMemberMapper {
    @Select("SELECT * FROM clan_members WHERE id = #{id}")
    DMClanMember get(@Param("id") long id);

    @Select("SELECT * FROM clan_members WHERE clan_id = #{clan_id}")
    List<DMClanMember> getAll(@Param("clan_id") long clan_id);

    @Select("SELECT * FROM clan_members WHERE player_id = #{player_id}")
    DMClanMember getByPlayerId(@Param("player_id") long player_id);
    @Insert("INSERT INTO clan_members(player_id, clan_id, role, accumulate_contribution, donate_count, donate_reset_ymd, penalty_end_at, updated_at, created_at)" +
            " VALUES(#{player_id}, #{clan_id}, #{role}, #{accumulate_contribution},  #{donate_count}, #{donate_reset_ymd}, #{penalty_end_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMClanMember model);

    @Update("UPDATE clan_members SET " +
            "role = #{role}, " +
            "clan_id = #{clan_id}, " +
            "donate_count = #{donate_count}, " +
            "donate_reset_ymd = #{donate_reset_ymd}, " +
            "accumulate_contribution = #{accumulate_contribution}, " +
            "penalty_end_at = #{penalty_end_at}, " +
            "updated_at = #{updated_at} WHERE player_id = #{player_id}")
    int update(DMClanMember model);

    @Update("UPDATE clan_members SET " +
            "role = #{role}, " +
            "updated_at = #{updated_at} WHERE player_id = #{player_id} and clan_id = 0")
    int acceptJoinClan(DMClanMember model);
    @Update("UPDATE clan_members SET " +
            "role = #{role}, " +
            "updated_at = #{updated_at} WHERE id = #{id}")
    int updateRole(DMClanMember model);

    @Update("UPDATE clan_members SET " +
            "accumulate_contribution = #{accumulate_contribution}, " +
            "donate_count = #{donate_count}, " +
            "donate_reset_ymd = #{donate_reset_ymd}, " +
            "updated_at = #{updated_at} WHERE player_id = #{player_id} and clan_id = #{clan_id}")
    int updateContribute(DMClanMember model);

    @Delete("DELETE FROM clan_members WHERE player_id = #{player_id}")
    int deleteByPlayerId(@Param("player_id") long player_id);
}
