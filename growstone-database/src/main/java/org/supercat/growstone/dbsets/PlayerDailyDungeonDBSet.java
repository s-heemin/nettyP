package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerDailyDungeonMapper;
import org.supercat.growstone.models.DMPlayerDailyDungeon;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerDailyDungeonDBSet {
    public DBSqlExcutor executor;
    public PlayerDailyDungeonDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerDailyDungeon get(long id) {
        return executor.query(db -> db.getMapper(PlayerDailyDungeonDBSet.class).get(id));
    }
    public List<DMPlayerDailyDungeon> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerDailyDungeonMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerDailyDungeon model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerDailyDungeon model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerDailyDungeonMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerDailyDungeon model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerDailyDungeonMapper.class).update(model));
    }
}
