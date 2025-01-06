package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerGachaMapper;
import org.supercat.growstone.mappers.PlayerPickUpGachaMapper;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.models.DMPlayerPickUpGachaPoint;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class PlayerPickUpGachaDBSet {
    public DBSqlExcutor executor;

    public PlayerPickUpGachaDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerPickUpGachaPoint get(long id) {
        return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).get(id));
    }

    public DMPlayerPickUpGachaPoint getByDataId(long playerId, long shopDataId) {
        return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).getByDataId(playerId, shopDataId));
    }

    public List<DMPlayerPickUpGachaPoint> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).getByPlayerId(playerId));
    }


    public DMPlayerPickUpGachaPoint getOrDefault(long playerId, long shopDataId) {
        var model = getByDataId(playerId, shopDataId);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerPickUpGachaPoint.of(playerId, shopDataId);

        insert(model);

        return model;
    }
    public int updatePoint(DMPlayerPickUpGachaPoint model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).updatePoint(model));
    }

    public int updateReward(DMPlayerPickUpGachaPoint model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).updateReward(model));
    }

    public int insert(DMPlayerPickUpGachaPoint model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerPickUpGachaMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
