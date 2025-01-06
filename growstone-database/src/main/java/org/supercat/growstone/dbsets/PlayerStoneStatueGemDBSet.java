package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerStoneStatueGemMapper;
import org.supercat.growstone.models.DMPlayerStoneStatueGem;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerStoneStatueGemDBSet {
    public DBSqlExcutor executor;

    public PlayerStoneStatueGemDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMPlayerStoneStatueGem> getAll(long playerId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueGemMapper.class).getAll(playerId));
    }

    public DMPlayerStoneStatueGem get(long playerId, long gemId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueGemMapper.class).get(playerId, gemId));
    }

    public int save(DMPlayerStoneStatueGem model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int update(DMPlayerStoneStatueGem model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerStoneStatueGemMapper.class).update(model));
    }

    private int insert(DMPlayerStoneStatueGem model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerStoneStatueGemMapper.class).insert(model));
    }
}
