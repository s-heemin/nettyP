package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerGachaMapper;
import org.supercat.growstone.models.DMPlayerGacha;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class PlayerGachaDBSet {
    public DBSqlExcutor executor;

    public PlayerGachaDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerGacha get(long id) {
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).get(id));
    }

    public DMPlayerGacha getByIndex(long playerId, long index) {
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).getByIndex(playerId, index));
    }

    public List<DMPlayerGacha> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).getByPlayerId(playerId));
    }

    public DMPlayerGacha getOrDefault(long playerId, long index) {
        var model = getByIndex(playerId, index);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerGacha.of(playerId, index);
        return model;
    }
    public int save(DMPlayerGacha model) {
        if (model.id == 0) {
            return insert(model);
        } else {
            return update(model);
        }
    }

    private int update(DMPlayerGacha model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).update(model));
    }

    public int updateGachaMaxCount(DMPlayerGacha model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).updateGachaMaxCount(model));
    }

    public int updateViewCommercialCount(DMPlayerGacha model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerGachaMapper.class).updateViewCommercialCount(model));
    }

    private int insert(DMPlayerGacha model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerGachaMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
