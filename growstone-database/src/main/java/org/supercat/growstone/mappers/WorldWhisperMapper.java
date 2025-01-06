package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMWorldWhisper;

import java.time.Instant;
import java.util.List;

public interface WorldWhisperMapper {
    @Select("SELECT * FROM world_whispers WHERE channel_id = #{channel_id} and " +
            " ((receive_player_id = #{receive_player_id} and sender_player_id = #{sender_player_id}) OR" +
            " (receive_player_id = #{sender_player_id} and sender_player_id = #{receive_player_id}))" +
            "and created_at > #{until_at} ORDER BY created_at")
    List<DMWorldWhisper> getAll(@Param("channel_id") long channel_id,
                                @Param("receive_player_id") long receive_player_id,
                                @Param("sender_player_id") long sender_player_id,
                                @Param("until_at") Instant until_at);

    @Insert("INSERT INTO world_whispers(channel_id, receive_player_id, sender_player_id, sender_name, sender_portrait_icon, text, created_at)" +
            " VALUES(#{channel_id}, #{receive_player_id}, #{sender_player_id}, #{sender_name}, #{sender_portrait_icon}, #{text}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMWorldWhisper model);
}
