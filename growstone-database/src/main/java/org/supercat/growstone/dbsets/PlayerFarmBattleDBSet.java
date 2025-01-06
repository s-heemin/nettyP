package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerFarmBattleMapper;
import org.supercat.growstone.models.DMPlayerFarmBattle;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerFarmBattleDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmBattleDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMPlayerFarmBattle> getAll(long playerId, int limitCount, Instant at) {
        return executor.query(db -> db.getMapper(PlayerFarmBattleMapper.class).getAll(playerId, limitCount, at));
    }

    public void insertAsync(DMPlayerFarmBattle model) {
        model.created_at = Instant.now();
        executor.queryAsync(db -> {
            db.getMapper(PlayerFarmBattleMapper.class).insert(model);
        });
    }
}
