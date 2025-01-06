package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerEventMapper;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerEventDBSet {
    public DBSqlExcutor executor;

    public PlayerEventDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerEvent get(long id) {
        return executor.query(db -> db.getMapper(PlayerEventMapper.class).get(id));
    }

    public List<DMPlayerEvent> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerEventMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerEvent model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int insert(DMPlayerEvent model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerEventMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerEvent model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerEventMapper.class).update(model));
    }
}
