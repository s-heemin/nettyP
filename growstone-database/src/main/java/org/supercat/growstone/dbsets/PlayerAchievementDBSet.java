package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerAchievementMapper;
import org.supercat.growstone.mappers.PlayerAdvertiseMapper;
import org.supercat.growstone.models.DMPlayerAchievement;
import org.supercat.growstone.models.DMPlayerAdvertise;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.Objects;

public class PlayerAchievementDBSet {
    public DBSqlExcutor executor;

    public PlayerAchievementDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public int save(DMPlayerAchievement model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }
    public DMPlayerAchievement getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerAchievementMapper.class).getByPlayerId(playerId));
    }

    private int update(DMPlayerAchievement model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerAchievementMapper.class).update(model));
    }

    private int insert(DMPlayerAchievement model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerAchievementMapper.class).insert(model));
    }
}
