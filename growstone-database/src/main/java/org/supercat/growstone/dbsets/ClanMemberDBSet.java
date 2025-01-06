package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.ClanMemberMapper;
import org.supercat.growstone.models.DMClanMember;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class ClanMemberDBSet {
    public DBSqlExcutor executor;

    public ClanMemberDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public List<DMClanMember> getAll(long clanId) {
        return executor.query(db -> db.getMapper(ClanMemberMapper.class).getAll(clanId));
    }

    public DMClanMember getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(ClanMemberMapper.class).getByPlayerId(playerId));
    }
    public int save(DMClanMember model) {
        if (model.id > 0) {
            return update(model);
        }

        return insert(model);
    }


    private int update(DMClanMember model) {
        model.updated_at = Instant.now();
        try {
            return executor.query(db -> db.getMapper(ClanMemberMapper.class).update(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int insert(DMClanMember model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(ClanMemberMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updateRole(DMClanMember model) {
        return executor.query(db -> db.getMapper(ClanMemberMapper.class).updateRole(model));
    }

    public int updateContribute(DMClanMember model) {
        return executor.query(db -> db.getMapper(ClanMemberMapper.class).updateContribute(model));
    }

    public int deleteByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(ClanMemberMapper.class).deleteByPlayerId(playerId));
    }
}
