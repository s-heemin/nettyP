package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.ClanMapper;
import org.supercat.growstone.models.DMClan;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class ClanDBSet {
    public DBSqlExcutor excutor;

    public ClanDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public List<DMClan> getAll() {
        return excutor.query(db -> db.getMapper(ClanMapper.class).getAll());
    }
    public DMClan get(long id) {
        return excutor.query(db -> db.getMapper(ClanMapper.class).get(id));
    }

    public DMClan getByMasterPlayerId(long playerId) {
        return excutor.query(db -> db.getMapper(ClanMapper.class).getByMasterPlayerId(playerId));
    }

    public int insert(DMClan model) {
        model.last_change_name_at = Instant.now();
        model.updated_at = model.last_change_name_at;
        model.created_at = model.updated_at;
        try {
            return excutor.query(db -> db.getMapper(ClanMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updateClanExp(DMClan model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateClanExp(model));
    }
    public int updateText(DMClan model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateText(model));
    }

    public int updateName(DMClan model) {
        model.updated_at = Instant.now();
        model.last_change_name_at = model.updated_at;
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateName(model));
    }

    public int updateMaster(DMClan model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateMaster(model));
    }

    public int updateMasterLastLogoutAt(DMClan model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateMasterLastLogoutAt(model));
    }

    public int updateState(DMClan model) {
        model.updated_at = Instant.now();
        return excutor.query(db -> db.getMapper(ClanMapper.class).updateState(model));
    }
}
