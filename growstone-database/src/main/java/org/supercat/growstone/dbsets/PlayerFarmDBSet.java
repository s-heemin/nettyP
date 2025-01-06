package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerFarmMapper;
import org.supercat.growstone.models.DMPlayerFarm;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerFarmDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerFarm getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerFarmMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerFarm model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }


    private int update(DMPlayerFarm model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerFarmMapper.class).update(model));
    }

    private int insert(DMPlayerFarm model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerFarmMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
