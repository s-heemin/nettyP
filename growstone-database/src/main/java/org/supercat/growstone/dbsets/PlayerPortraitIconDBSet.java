package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerPortaitIconMapper;
import org.supercat.growstone.models.DMPlayerPortraitIcon;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerPortraitIconDBSet {
    public DBSqlExcutor executor;
    public PlayerPortraitIconDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerPortraitIcon get(long id) {
        return executor.query(db -> db.getMapper(PlayerPortaitIconMapper.class).get(id));
    }
    public List<DMPlayerPortraitIcon> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerPortaitIconMapper.class).getByPlayerId(playerId));
    }

    public int insert(DMPlayerPortraitIcon model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerPortaitIconMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int deleteForCheat(long id) {
        return executor.query(db -> db.getMapper(PlayerPortaitIconMapper.class).deleteForCheat(id));
    }
}
