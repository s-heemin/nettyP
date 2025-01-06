package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.WorldChatMapper;
import org.supercat.growstone.models.DMWorldChat;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class WorldChatDBSet {
    public DBSqlExcutor excutor;
    public WorldChatDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public List<DMWorldChat> getAll(long channelId, Instant untilAt) {
        return excutor.query(db -> db.getMapper(WorldChatMapper.class).getAll(channelId, untilAt));
    }

    public int insert(DMWorldChat model) {
        model.created_at = Instant.now();
        try {
            return excutor.query(db -> db.getMapper(WorldChatMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
