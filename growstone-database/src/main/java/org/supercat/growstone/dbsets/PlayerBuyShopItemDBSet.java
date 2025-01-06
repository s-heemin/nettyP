package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerAvatarMapper;
import org.supercat.growstone.mappers.PlayerBuyShopItemMapper;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.models.DMPlayerAvatar;
import org.supercat.growstone.models.DMPlayerBuyShopItem;
import org.supercat.growstone.models.DMPlayerContinueShop;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class PlayerBuyShopItemDBSet {
    public DBSqlExcutor executor;

    public PlayerBuyShopItemDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerBuyShopItem get(long id) {
        return executor.query(db -> db.getMapper(PlayerBuyShopItemMapper.class).get(id));
    }

    public List<DMPlayerBuyShopItem> getAll(long playerId) {
        return executor.query(db -> db.getMapper(PlayerBuyShopItemMapper.class).getAll(playerId));
    }

    public int save(DMPlayerBuyShopItem model) {
        if (model.id <= 0) {
            return insert(model);
        }

        return update(model);

    }

    private int update(DMPlayerBuyShopItem model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerBuyShopItemMapper.class).update(model));

    }

    private int insert(DMPlayerBuyShopItem model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        return executor.query(db -> db.getMapper(PlayerBuyShopItemMapper.class).insert(model));
    }
}
