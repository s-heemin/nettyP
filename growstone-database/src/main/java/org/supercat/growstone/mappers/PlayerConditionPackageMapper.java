package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerConditionPackage;

import java.util.List;

public interface PlayerConditionPackageMapper {

    @Select("SELECT * FROM `player_condition_packages` WHERE `player_id` = #{player_id} and `expire_at` > now()")
    List<DMPlayerConditionPackage> getAll(@Param("player_id") long player_id);
    @Select("SELECT * FROM `player_condition_packages` WHERE `player_id` = #{player_id} and `package_id` = #{package_id}")
    DMPlayerConditionPackage getByPackageId(@Param("player_id") long player_id, @Param("package_id") long package_id);

    @Insert("INSERT INTO `player_condition_packages`(player_id, `package_id`, `is_complete`,`expire_at`, `updated_at`, `created_at`) " +
            "VALUES(#{player_id}, #{package_id}, #{is_complete}, #{expire_at}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerConditionPackage model);

    @Update("UPDATE `player_condition_packages` SET " +
            "`is_complete` = #{is_complete}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerConditionPackage model);
}
