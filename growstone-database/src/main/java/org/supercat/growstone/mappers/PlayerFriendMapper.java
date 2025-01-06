package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerFriend;

import java.time.Instant;
import java.util.List;

public interface PlayerFriendMapper {
    @Select("SELECT * FROM `player_friends` WHERE `id` = #{id}")
    DMPlayerFriend get(@Param("id") long id);

    @Select("SELECT * FROM `player_friends` WHERE `player_id` = #{player_id}")
    List<DMPlayerFriend> getByPlayerId(@Param("player_id") long playerId);

    @Select("SELECT count(*) FROM `player_friends` WHERE `player_id` = #{player_id} and `state` = 1")
    int getFriendSizeByPlayerId(@Param("player_id") long playerId);

    @Select("SELECT * FROM `player_friends` WHERE `player_id` = #{player_id} AND `target_player_id` = #{target_player_id}")
    DMPlayerFriend getByPlayerIdAndTargetPlayerId(@Param("player_id") long playerId, @Param("target_player_id") long targetPlayerId);

    @Insert("INSERT INTO `player_friends`(`player_id`, `target_player_id`, `state`, `receive_gift_expire_at`, `send_gift_expire_at`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{target_player_id}, #{state}, #{receive_gift_expire_at}, #{send_gift_expire_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerFriend model);

    @Update("UPDATE `player_friends` SET " +
            "`state` = #{state}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerFriend model);

    @Update("UPDATE `player_friends` SET " +
            "`send_gift_expire_at` = #{send_gift_expire_at}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateSendGiftExpiretime(DMPlayerFriend model);

    @Update("UPDATE `player_friends` SET " +
            "`receive_gift_expire_at` = #{receive_gift_expire_at}, " +
            "`updated_at` = #{updated_at} WHERE `player_id` = #{player_id} and `target_player_id` = #{target_player_id}")
    int updateReceiveGiftExpiretime(@Param("player_id") long playerId,
                                    @Param("target_player_id") long targetPlayerId,
                                    @Param("receive_gift_expire_at") Instant receiveGiftExpireAt,
                                    @Param("updated_at") Instant updatedAt);
    @Delete("DELETE FROM `player_friends` WHERE `id` = #{id}")
    int deleteById(@Param("id") long id);


}
