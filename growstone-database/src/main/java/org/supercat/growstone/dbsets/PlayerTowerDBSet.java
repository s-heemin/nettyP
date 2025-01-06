package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerTowerMapper;
import org.supercat.growstone.models.DMPlayerTower;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerTowerDBSet {
    public DBSqlExcutor executor;
    public PlayerTowerDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerTower get(long id) {
        return executor.query(db -> db.getMapper(PlayerTowerMapper.class).get(id));
    }
    public List<DMPlayerTower> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerTowerMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerTower model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerTower model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerTowerMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerTower model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerTowerMapper.class).update(model));
    }
}
