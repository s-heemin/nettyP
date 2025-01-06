package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.ServerMapper;
import org.supercat.growstone.mappers.ServerRedisConfigMapper;
import org.supercat.growstone.models.DMServerRedisConfig;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class ServerRedisConfigDBSet {
    public DBSqlExcutor excutor;
    public ServerRedisConfigDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public List<DMServerRedisConfig> getAll(long channelId) {
        return excutor.query(db -> db.getMapper(ServerRedisConfigMapper.class).getAll(channelId));
    }
    public int insert(DMServerRedisConfig model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return excutor.query(db -> db.getMapper(ServerRedisConfigMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
