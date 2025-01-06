package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerStoneStatueMapper;
import org.supercat.growstone.models.DMPlayerStoneStatue;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerStoneStatueDBSet {
    public DBSqlExcutor executor;

    public PlayerStoneStatueDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerStoneStatue get(long playerId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueMapper.class).get(playerId));
    }

    public int save(DMPlayerStoneStatue model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int update(DMPlayerStoneStatue model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerStoneStatueMapper.class).update(model));
    }

    private int insert(DMPlayerStoneStatue model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerStoneStatueMapper.class).insert(model));
    }

}
