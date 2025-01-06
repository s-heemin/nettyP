package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerScheduleTaskMapper;
import org.supercat.growstone.models.DMPlayerScheduleTask;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerScheduleTaskDBSet {
    public DBSqlExcutor executor;
    public PlayerScheduleTaskDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerScheduleTask get(long id) {
        return executor.query(db -> db.getMapper(PlayerScheduleTaskMapper.class).get(id));
    }

    public DMPlayerScheduleTask get(long playerId, int type) {
        return executor.query(db -> db.getMapper(PlayerScheduleTaskMapper.class).getByPlayerId(playerId, type));
    }
    public int insert(DMPlayerScheduleTask model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerScheduleTaskMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updateResetAt(long id, int resetAt) {
        return executor.query(db -> db.getMapper(PlayerScheduleTaskMapper.class).updateResetAt(id, resetAt, Instant.now()));
    }
}
