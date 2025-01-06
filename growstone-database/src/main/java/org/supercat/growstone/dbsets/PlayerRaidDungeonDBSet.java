package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerRaidDungeonMapper;
import org.supercat.growstone.models.DMPlayerRaidDungeon;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerRaidDungeonDBSet {
    public DBSqlExcutor executor;
    public PlayerRaidDungeonDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerRaidDungeon get(long id) {
        return executor.query(db -> db.getMapper(PlayerRaidDungeonMapper.class).get(id));
    }
    public List<DMPlayerRaidDungeon> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerRaidDungeonMapper.class).getByPlayerId(playerId));
    }

    public int save(DMPlayerRaidDungeon model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMPlayerRaidDungeon model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerRaidDungeonMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerRaidDungeon model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerRaidDungeonMapper.class).update(model));
    }
}
