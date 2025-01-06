package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.WorldEventMapper;
import org.supercat.growstone.mappers.WorldScheduleTaskMapper;
import org.supercat.growstone.models.DMEvent;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class WorldEventDBSet {
    public DBSqlExcutor excutor;
    public WorldEventDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public DMEvent get(long id) {
        return excutor.query(db -> db.getMapper(WorldEventMapper.class).get(id));
    }
    public List<DMEvent> getAllByActive() {
        return excutor.query(db -> db.getMapper(WorldEventMapper.class).getAllByActive());
    }
    public int insert(DMEvent model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        return excutor.query(db -> db.getMapper(WorldEventMapper.class).insert(model));
    }
}
