package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerItemMapper;
import org.supercat.growstone.models.DMPlayerItem;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerItemDBSet {
    public DBSqlExcutor executor;
    public PlayerItemDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerItem get(long id) {
        return executor.query(db -> db.getMapper(PlayerItemMapper.class).get(id));
    }
    public List<DMPlayerItem> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerItemMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerItem model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerItem model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerItemMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerItem model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerItemMapper.class).update(model));
    }
}
