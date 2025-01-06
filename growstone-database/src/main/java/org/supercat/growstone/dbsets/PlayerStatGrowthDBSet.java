package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerStatGrowthMapper;
import org.supercat.growstone.models.DMPlayerStatGrowth;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerStatGrowthDBSet {
    public DBSqlExcutor executor;

    public PlayerStatGrowthDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerStatGrowth get(long id) {
        return executor.query(db -> db.getMapper(PlayerStatGrowthMapper.class).get(id));
    }

    public List<DMPlayerStatGrowth> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerStatGrowthMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerStatGrowth model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int insert(DMPlayerStatGrowth model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerStatGrowthMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerStatGrowth model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerStatGrowthMapper.class).update(model));

    }
}
