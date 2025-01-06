package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerStoneStatueEnchantMapper;
import org.supercat.growstone.models.DMPlayerStoneStatueEnchant;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerStoneStatueEnchantDBSet {
    public DBSqlExcutor executor;

    public PlayerStoneStatueEnchantDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerStoneStatueEnchant get(long playerId, int orderId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueEnchantMapper.class).get(playerId, orderId));
    }

    public int save(DMPlayerStoneStatueEnchant model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int update(DMPlayerStoneStatueEnchant model) {
        model.updated_at = Instant.now();

        return executor.query(db -> db.getMapper(PlayerStoneStatueEnchantMapper.class).update(model));
    }

    private int insert(DMPlayerStoneStatueEnchant model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerStoneStatueEnchantMapper.class).insert(model));
    }
}
