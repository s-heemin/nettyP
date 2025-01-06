package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerCurrencyMapper;
import org.supercat.growstone.models.DMPlayerCurrency;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerCurrencyDBSet {
    public DBSqlExcutor executor;
    public PlayerCurrencyDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerCurrency get(long id) {
        return executor.query(db -> db.getMapper(PlayerCurrencyMapper.class).get(id));
    }
    public DMPlayerCurrency getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerCurrencyMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerCurrency model) {
        if(model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerCurrency model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerCurrencyMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerCurrency model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerCurrencyMapper.class).update(model));
    }
}
