package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerDailyContentMapper;
import org.supercat.growstone.mappers.PlayerDailyDungeonMapper;
import org.supercat.growstone.models.DMPlayerDailyContent;
import org.supercat.growstone.models.DMPlayerDailyDungeon;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerDailyContentDBSet {
    public DBSqlExcutor executor;
    public PlayerDailyContentDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }


    public DMPlayerDailyContent getByType(long playerId, int type) {
        return executor.query(db -> db.getMapper(PlayerDailyContentMapper.class).getByType(playerId, type));
    }

    public int save(DMPlayerDailyContent model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerDailyContent model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerDailyContentMapper.class).insert(model));
    }

    private int update(DMPlayerDailyContent model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerDailyContentMapper.class).update(model));
    }
}
