package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.supercat.growstone.models.DMPlayerAvatar;

public interface BannedNameMapper {
    @Select("SELECT count(*) FROM `banned_names` WHERE `text` = #{text}")
    int get(@Param("text") String text);
}
