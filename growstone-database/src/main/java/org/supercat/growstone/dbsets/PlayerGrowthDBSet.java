package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerGrowthMapper;
import org.supercat.growstone.models.DMPlayerGrowth;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerGrowthDBSet {
    public DBSqlExcutor executor;
    public PlayerGrowthDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerGrowth get(long id) {
        return executor.query(db -> db.getMapper(PlayerGrowthMapper.class).get(id));
    }
    public List<DMPlayerGrowth> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerGrowthMapper.class).getByPlayerId(playerId));
    }

    public DMPlayerGrowth getByGrowthId(long playerId, long growthId) {
        return executor.query(db -> db.getMapper(PlayerGrowthMapper.class).getByGrowthId(playerId, growthId));
    }

    public int save(DMPlayerGrowth model) {
        if (model.id == 0) {
            return insert(model);
        } else {
            return update(model);
        }
    }

    private int update(DMPlayerGrowth model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerGrowthMapper.class).update(model));
    }
    private int insert(DMPlayerGrowth model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerGrowthMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public void clearForCheat(long playerId)  {
        executor.query(db -> db.getMapper(PlayerGrowthMapper.class).clearForCheat(playerId));
    }
}
