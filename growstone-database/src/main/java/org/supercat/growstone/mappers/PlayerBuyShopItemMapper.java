package org.supercat.growstone.mappers;

import org.apache.ibatis.annotations.*;
import org.supercat.growstone.models.DMPlayerBuyShopItem;
import org.supercat.growstone.models.DMPlayerCollection;

import java.util.List;

public interface PlayerBuyShopItemMapper {
    @Select("SELECT * FROM `player_buy_shop_items` WHERE `id` = #{id}")
    DMPlayerBuyShopItem get(@Param("id") long id);

    @Select("SELECT * FROM `player_buy_shop_items` WHERE `player_id` = #{player_id}")
    List<DMPlayerBuyShopItem> getAll(@Param("player_id") long player_id);

    @Insert("INSERT INTO `player_buy_shop_items`(`player_id`, `shop_data_id`, `count`, `buy_reset_day`, `ad_reset_day`, `ad_view_count`, `updated_at`, `created_at`)" +
            " VALUES(#{player_id}, #{shop_data_id}, #{count}, #{buy_reset_day}, #{ad_reset_day}, #{ad_view_count}, #{updated_at}, #{created_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(DMPlayerBuyShopItem model);

    @Update("UPDATE `player_buy_shop_items` SET " +
            "`count` = #{count}, " +
            "`buy_reset_day` = #{buy_reset_day}, " +
            "`ad_view_count` = #{ad_view_count}, " +
            "`ad_reset_day` = #{ad_reset_day}, " +
            "`updated_at` = #{updated_at} WHERE `id` = #{id}")
    int update(DMPlayerBuyShopItem model);
}
