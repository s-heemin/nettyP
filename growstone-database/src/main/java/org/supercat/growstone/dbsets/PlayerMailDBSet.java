package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerMailMapper;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerMailDBSet {
    public DBSqlExcutor executor;
    public PlayerMailDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerMail get(long id) {
        return executor.query(db -> db.getMapper(PlayerMailMapper.class).get(id));
    }
    public List<DMPlayerMail> getAllByNoReadMail(long playerId) {
        return executor.query(db -> db.getMapper(PlayerMailMapper.class).getAllByNoReadMail(playerId));
    }

    public int updateRead(DMPlayerMail model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMailMapper.class).updateRead(model));
    }
    public int insert(DMPlayerMail model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerMailMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
