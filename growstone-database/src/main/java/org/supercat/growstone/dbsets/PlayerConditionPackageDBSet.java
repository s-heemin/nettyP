package org.supercat.growstone.dbsets;


import org.supercat.growstone.mappers.PlayerConditionPackageMapper;
import org.supercat.growstone.models.DMPlayerConditionPackage;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class PlayerConditionPackageDBSet {
    private DBSqlExcutor executor;

    public PlayerConditionPackageDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerConditionPackage model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    public List<DMPlayerConditionPackage> getAll(long playerId) {
        return executor.query(db -> db.getMapper(PlayerConditionPackageMapper.class).getAll(playerId));
    }

    private int update(DMPlayerConditionPackage model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerConditionPackageMapper.class).update(model));
    }

    private int insert(DMPlayerConditionPackage model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerConditionPackageMapper.class).insert(model));
    }
}
