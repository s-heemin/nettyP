package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerShopPassMapper;
import org.supercat.growstone.mappers.PlayerStatGrowthMapper;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.models.DMPlayerShopPass;
import org.supercat.growstone.models.DMPlayerStatGrowth;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class PlayerShopPassDBSet {
    public DBSqlExcutor executor;

    public PlayerShopPassDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerShopPass get(long id) {
        return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).get(id));
    }

    public DMPlayerShopPass getByShopPassId(long playerId, long shopPassId) {
        return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).getByShopPassId(playerId, shopPassId));
    }

    public DMPlayerShopPass getOrDefault(long playerId, long shopPassId) {
        var model = getByShopPassId(playerId, shopPassId);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerShopPass.of(playerId, shopPassId);
        return model;
    }

    public int save(DMPlayerShopPass model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int insert(DMPlayerShopPass model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerShopPass model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).update(model));
    }

    public int updateRewards(DMPlayerShopPass model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).updateRewards(model));
    }

    public int updatePaid(DMPlayerShopPass model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerShopPassMapper.class).updatePaid(model));
    }

}
