package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerPartsSlotMapper;
import org.supercat.growstone.mappers.PlayerQuestMapper;
import org.supercat.growstone.models.DMPlayerPartsSlot;
import org.supercat.growstone.models.DMPlayerQuest;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerQuestDBSet {
    public DBSqlExcutor executor;

    public PlayerQuestDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerQuest get(long id) {
        return executor.query(db -> db.getMapper(PlayerQuestMapper.class).get(id));
    }

    public DMPlayerQuest getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerQuestMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerQuest model) {
        if (model.id <= 0) {
            return insert(model);
        }

        return update(model);
    }

    private int update(DMPlayerQuest model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerQuestMapper.class).update(model));
    }

    private int insert(DMPlayerQuest model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        return executor.query(db -> db.getMapper(PlayerQuestMapper.class).insert(model));
    }
}
