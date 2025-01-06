package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerTowerMapper;
import org.supercat.growstone.mappers.WorldScheduleTaskMapper;
import org.supercat.growstone.models.DMPlayerTower;
import org.supercat.growstone.models.DMWorldScheduleTask;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class WorldScheduleTaskDBSet {
    public DBSqlExcutor excutor;
    public WorldScheduleTaskDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public DMWorldScheduleTask get(long id) {
        return excutor.query(db -> db.getMapper(WorldScheduleTaskMapper.class).get(id));
    }
    public List<DMWorldScheduleTask> getByChannelId(long channelId) {
        return excutor.query(db -> db.getMapper(WorldScheduleTaskMapper.class).getByChannelId(channelId));
    }

    public int save(DMWorldScheduleTask model) {
        if(model.id > 0) {
            model.updated_at = Instant.now();
            return update(model);
        }

        return insert(model);
    }

    private int insert(DMWorldScheduleTask model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return excutor.query(db -> db.getMapper(WorldScheduleTaskMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMWorldScheduleTask model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(WorldScheduleTaskMapper.class).update(model));
    }
}
