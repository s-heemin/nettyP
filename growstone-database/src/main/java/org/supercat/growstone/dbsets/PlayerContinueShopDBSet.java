package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerContinueShopMapper;
import org.supercat.growstone.models.DMPlayerContinueShop;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.Objects;

public class PlayerContinueShopDBSet {
    private DBSqlExcutor executor;

    public PlayerContinueShopDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerContinueShop model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    private DMPlayerContinueShop getByGroupId(long playerId, int groupId) {
        return executor.query(db -> db.getMapper(PlayerContinueShopMapper.class).getByGroupId(playerId, groupId));
    }

    public DMPlayerContinueShop getOrDefault(long playerId, int groupId) {
        var model = getByGroupId(playerId, groupId);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerContinueShop.of(playerId, groupId);
        return model;
    }

    private int update(DMPlayerContinueShop model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerContinueShopMapper.class).update(model));
    }

    private int insert(DMPlayerContinueShop model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerContinueShopMapper.class).insert(model));
    }
}
