package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerEquipPresetNameMapper;
import org.supercat.growstone.models.DMPlayerEquipPresetName;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerEquipPresetNameDBSet {
    public DBSqlExcutor executor;

    public PlayerEquipPresetNameDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerEquipPresetName get(long id) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetNameMapper.class).get(id));
    }

    public List<DMPlayerEquipPresetName> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetNameMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerEquipPresetName model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int insert(DMPlayerEquipPresetName model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerEquipPresetNameMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerEquipPresetName model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerEquipPresetNameMapper.class).update(model));
    }
}
