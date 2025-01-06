package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerFarmTheftLimitMapper;
import org.supercat.growstone.models.DMPlayerFarmTheftLimit;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerFarmTheftLimitDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmTheftLimitDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerFarmTheftLimit get(long playerId) {
        return executor.query(db -> db.getMapper(PlayerFarmTheftLimitMapper.class).get(playerId));
    }

    public int save(DMPlayerFarmTheftLimit model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }


    private int update(DMPlayerFarmTheftLimit model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerFarmTheftLimitMapper.class).update(model));
    }

    private int insert(DMPlayerFarmTheftLimit model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerFarmTheftLimitMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
