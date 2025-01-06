package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.ServerMapper;
import org.supercat.growstone.models.DMServer;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class ServerDBSet {
    public DBSqlExcutor excutor;
    public ServerDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public DMServer getByInstanceId(String host) {
        return excutor.query(db -> db.getMapper(ServerMapper.class).getByInstanceId(host));
    }
    public int insert(DMServer model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return excutor.query(db -> db.getMapper(ServerMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updatePlayers(long id, int players) {
        return excutor.query(db -> db.getMapper(ServerMapper.class).updatePlayers(id, players, Instant.now()));
    }
}
