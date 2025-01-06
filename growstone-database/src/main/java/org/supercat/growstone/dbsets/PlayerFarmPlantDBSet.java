package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerFarmPlantMapper;
import org.supercat.growstone.models.DMPlayerFarmPlant;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerFarmPlantDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmPlantDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMPlayerFarmPlant> getAllByTheft(int status, Instant at, int count) {
        return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).getAllByTheft(status, at, count));
    }

    public List<DMPlayerFarmPlant> getAllByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).getAllByPlayerId(playerId));
    }

    public DMPlayerFarmPlant getByPlayerId(int playerId, int slot_index) {
        return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).getByPlayerId(playerId, slot_index));
    }

    public int getStealCount(long theftPlayerId) {
        return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).getStealCount(theftPlayerId));
    }

    public int save(DMPlayerFarmPlant model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }


    private int update(DMPlayerFarmPlant model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).update(model));
    }

    private int insert(DMPlayerFarmPlant model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerFarmPlantMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
