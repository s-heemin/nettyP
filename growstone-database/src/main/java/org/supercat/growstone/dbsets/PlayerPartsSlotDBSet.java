package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerPartsSlotMapper;
import org.supercat.growstone.models.DMPlayerPartsSlot;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerPartsSlotDBSet {
    public DBSqlExcutor executor;
    public PlayerPartsSlotDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerPartsSlot get(long id) {
        return executor.query(db -> db.getMapper(PlayerPartsSlotMapper.class).get(id));
    }
    public List<DMPlayerPartsSlot> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerPartsSlotMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerPartsSlot model) {
        if(model.id <= 0) {
            return insert(model);
        }

        return update(model);
    }

    private int update(DMPlayerPartsSlot model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerPartsSlotMapper.class).update(model));
    }

    private int insert(DMPlayerPartsSlot model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerPartsSlotMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
