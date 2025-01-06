package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerFarmHistoryMapper;
import org.supercat.growstone.models.DMPlayerFarmHistory;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerFarmHistoryDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmHistoryDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMPlayerFarmHistory> getAll(long playerId, int limitCount, Instant at) {
        return executor.query(db -> db.getMapper(PlayerFarmHistoryMapper.class).getAll(playerId, limitCount, at));
    }

    public void insertAsync(DMPlayerFarmHistory model) {
        model.created_at = Instant.now();
        executor.queryAsync(db -> {
            db.getMapper(PlayerFarmHistoryMapper.class).insert(model);
        });
    }
}
