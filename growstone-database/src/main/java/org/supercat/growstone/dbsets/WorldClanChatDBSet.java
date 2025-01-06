package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.WorldClanChatMapper;
import org.supercat.growstone.models.DMWorldClanChat;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class WorldClanChatDBSet {
    public DBSqlExcutor executor;

    public WorldClanChatDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMWorldClanChat> getAll(long channelId, long clanId, Instant untilAt) {
        return executor.query(db -> db.getMapper(WorldClanChatMapper.class).getAll(channelId, clanId, untilAt));
    }

    public int insert(DMWorldClanChat model) {
        model.created_at = Instant.now();
        try {
            return executor.query(db -> db.getMapper(WorldClanChatMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
