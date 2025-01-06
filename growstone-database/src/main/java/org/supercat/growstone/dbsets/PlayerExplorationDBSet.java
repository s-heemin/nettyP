package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerExplorationMapper;
import org.supercat.growstone.models.DMPlayerExploration;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.Objects;

public class PlayerExplorationDBSet {
    public DBSqlExcutor executor;

    public PlayerExplorationDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerExploration model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    public DMPlayerExploration getOrDefault(long player_id) {
        var model = get(player_id);
        if (Objects.nonNull(model)) {
            return model;
        }

        return DMPlayerExploration.of(player_id);
    }

    private DMPlayerExploration get(long player_id) {
        return executor.query(db -> db.getMapper(PlayerExplorationMapper.class).get(player_id));
    }

    private int update(DMPlayerExploration model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerExplorationMapper.class).update(model));
    }

    private int insert(DMPlayerExploration model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerExplorationMapper.class).insert(model));
    }
}
