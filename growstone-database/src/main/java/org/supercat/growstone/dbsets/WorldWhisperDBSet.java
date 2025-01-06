package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.WorldWhisperMapper;
import org.supercat.growstone.models.DMWorldWhisper;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class WorldWhisperDBSet {
    public DBSqlExcutor excutor;
    public WorldWhisperDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public List<DMWorldWhisper> getAll(long channelId, long receivePlayerId, long senderPlayerId, Instant untilAt) {
        return excutor.query(db -> db.getMapper(WorldWhisperMapper.class).getAll(channelId, receivePlayerId, senderPlayerId, untilAt));
    }

    public int insert(DMWorldWhisper model) {
        model.created_at = Instant.now();
        try {
            return excutor.query(db -> db.getMapper(WorldWhisperMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }
}
