package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerEquipPresetMapper;
import org.supercat.growstone.models.DMPlayerEquipPreset;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerEquipPresetDBSet {
    public DBSqlExcutor executor;
    public PlayerEquipPresetDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerEquipPreset get(long id) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).get(id));
    }
    public List<DMPlayerEquipPreset> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).getByPlayerId(playerId));
    }
    public List<DMPlayerEquipPreset> getByPreset(long playerId, int presetIndex) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).getByPreset(playerId, presetIndex));
    }

    public int save(DMPlayerEquipPreset model) {
        if (model.id == 0) {
            return insert(model);
        } else {
            return update(model);
        }
    }

    private int update(DMPlayerEquipPreset model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).update(model));
    }
    private int insert(DMPlayerEquipPreset model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int deleteForCheat(long playerId) {
        return executor.query(db -> db.getMapper(PlayerEquipPresetMapper.class).deleteForCheat(playerId));
    }
}
