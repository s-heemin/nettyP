package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerCollectionMapper;
import org.supercat.growstone.models.DMPlayerCollection;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerCollectionDBSet {
    public DBSqlExcutor executor;
    public PlayerCollectionDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerCollection get(long id) {
        return executor.query(db -> db.getMapper(PlayerCollectionMapper.class).get(id));
    }
    public List<DMPlayerCollection> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerCollectionMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerCollection model) {
        if(model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerCollection model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerCollectionMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerCollection model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerCollectionMapper.class).update(model));
    }
}
