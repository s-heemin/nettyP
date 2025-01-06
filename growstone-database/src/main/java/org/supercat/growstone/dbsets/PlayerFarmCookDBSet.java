package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerFarmCookMapper;
import org.supercat.growstone.models.DMPlayerFarmCook;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.Objects;

public class PlayerFarmCookDBSet {
    public DBSqlExcutor executor;

    public PlayerFarmCookDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerFarmCook model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    public DMPlayerFarmCook getOrDefault(long player_id) {
        var model = get(player_id);
        if (Objects.nonNull(model)) {
            return model;
        }

        return DMPlayerFarmCook.of(player_id);
    }

    private DMPlayerFarmCook get(long player_id) {
        return executor.query(db -> db.getMapper(PlayerFarmCookMapper.class).get(player_id));
    }

    private int update(DMPlayerFarmCook model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerFarmCookMapper.class).update(model));
    }

    private int insert(DMPlayerFarmCook model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerFarmCookMapper.class).insert(model));
    }
}
