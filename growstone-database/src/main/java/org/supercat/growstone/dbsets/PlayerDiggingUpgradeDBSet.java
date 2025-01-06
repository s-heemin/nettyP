package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerDiggingUpgradeMapper;
import org.supercat.growstone.models.DMPlayerDiggingUpgrade;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerDiggingUpgradeDBSet {
    public DBSqlExcutor executor;
    public PlayerDiggingUpgradeDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerDiggingUpgrade get(long id) {
        return executor.query(db -> db.getMapper(PlayerDiggingUpgradeMapper.class).get(id));
    }

    public DMPlayerDiggingUpgrade getByType(long playerId, int type) {
        return executor.query(db -> db.getMapper(PlayerDiggingUpgradeMapper.class).getByType(playerId, type));
    }

    public List<DMPlayerDiggingUpgrade> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerDiggingUpgradeMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerDiggingUpgrade model) {
        if (model.id == 0) {
            return insert(model);
        } else {
            return update(model);
        }
    }

    private int update(DMPlayerDiggingUpgrade model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerDiggingUpgradeMapper.class).update(model));
    }
    private int insert(DMPlayerDiggingUpgrade model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerDiggingUpgradeMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
