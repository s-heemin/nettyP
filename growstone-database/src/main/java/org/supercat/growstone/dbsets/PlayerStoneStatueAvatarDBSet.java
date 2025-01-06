package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerStoneStatueAvatarMapper;
import org.supercat.growstone.models.DMPlayerStoneStatueAvatar;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerStoneStatueAvatarDBSet {
    public DBSqlExcutor executor;

    public PlayerStoneStatueAvatarDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMPlayerStoneStatueAvatar> getAll(long playerId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueAvatarMapper.class).getAll(playerId));
    }

    public DMPlayerStoneStatueAvatar get(long playerId, long avatarId) {
        return executor.query(db -> db.getMapper(PlayerStoneStatueAvatarMapper.class).get(playerId, avatarId));
    }

    public int insert(DMPlayerStoneStatueAvatar model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        return executor.query(db -> db.getMapper(PlayerStoneStatueAvatarMapper.class).insert(model));
    }
}
