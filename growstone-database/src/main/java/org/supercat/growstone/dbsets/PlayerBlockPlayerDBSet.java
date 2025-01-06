package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.PlayerBlockMapper;
import org.supercat.growstone.models.DMPlayerBlock;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerBlockPlayerDBSet {
    public DBSqlExcutor executor;
    public PlayerBlockPlayerDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerBlock get(long id) {
        return executor.query(db -> db.getMapper(PlayerBlockMapper.class).get(id));
    }
    public List<DMPlayerBlock> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerBlockMapper.class).getByPlayerId(playerId));
    }

    public DMPlayerBlock getByPlayerIdAndTargetPlayerId(long playerId, long targetPlayerId) {
        return executor.query(db -> db.getMapper(PlayerBlockMapper.class).getByPlayerIdAndTargetPlayerId(playerId, targetPlayerId));
    }
    public int delete(long id) {
        return executor.query(db -> db.getMapper(PlayerBlockMapper.class).delete(id));
    }
    public int insert(DMPlayerBlock model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        return executor.query(db -> db.getMapper(PlayerBlockMapper.class).insert(model));
    }
}
