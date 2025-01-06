package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMGlobalMasterPlayer;
import org.supercat.growstone.models.DMPlayer;

import java.util.List;

public interface GlobalMasterPlayerMapper {
    @Select("SELECT * FROM `global_master_players` WHERE `id` = #{id}")
    DMGlobalMasterPlayer get(@Param("id") long id);

    @Select("SELECT * FROM `global_master_players` WHERE `login_type` = #{login_type} and `login_id` = #{login_id}")
    DMGlobalMasterPlayer getByLoginInfo(@Param("login_type") int login_type, @Param("login_id") String login_id);

    @Select("SELECT * FROM `global_master_players` WHERE `guest_token_id` = #{guest_token_id}")
    DMGlobalMasterPlayer getByGuest(@Param("guest_token_id") String guest_token_id);

    @Insert("INSERT INTO `global_master_players`(`device_os`, `device_model`, `login_id`, `guest_token_id`, `login_type`, " +
            "`ban_status`, `updated_at`, `created_at`)" +
            " VALUES(#{device_os}, #{device_model}, #{login_id}, #{guest_token_id}, #{login_type}, " +
            "#{ban_status}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMGlobalMasterPlayer model);

    @Update("UPDATE `global_master_players` " +
            "SET `guest_token_id` = #{guest_token_id}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int updateTokenIdByGuest(DMGlobalMasterPlayer model);
}
