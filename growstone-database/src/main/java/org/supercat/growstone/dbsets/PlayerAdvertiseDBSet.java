package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerAdvertiseMapper;
import org.supercat.growstone.models.DMPlayerAdvertise;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.Objects;

public class PlayerAdvertiseDBSet {
    public DBSqlExcutor executor;

    public PlayerAdvertiseDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerAdvertise model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    public DMPlayerAdvertise getOrDefault(long playerId, int type) {
        var model = getByType(playerId, type);
        if (Objects.nonNull(model)) {
            return model;
        }

        return DMPlayerAdvertise.of(playerId, type);
    }


    private DMPlayerAdvertise getByType(long playerId, int type) {
        return executor.query(db -> db.getMapper(PlayerAdvertiseMapper.class).getByType(playerId, type));
    }

    private int update(DMPlayerAdvertise model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerAdvertiseMapper.class).update(model));
    }

    private int insert(DMPlayerAdvertise model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerAdvertiseMapper.class).insert(model));
    }
}
