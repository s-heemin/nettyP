package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerDiggingMapper;
import org.supercat.growstone.models.DMPlayerDigging;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerDiggingDBSet {
    public DBSqlExcutor executor;
    public PlayerDiggingDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerDigging get(long id) {
        return executor.query(db -> db.getMapper(PlayerDiggingMapper.class).get(id));
    }
    public DMPlayerDigging getByIndex(long playerId, int index) {
        return executor.query(db -> db.getMapper(PlayerDiggingMapper.class).getByIndex(playerId, index));
    }
    public List<DMPlayerDigging> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerDiggingMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerDigging model) {
        if (model.id == 0) {
            return insert(model);
        } else {
            return update(model);
        }
    }

    private int update(DMPlayerDigging model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerDiggingMapper.class).update(model));
    }
    private int insert(DMPlayerDigging model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerDiggingMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
