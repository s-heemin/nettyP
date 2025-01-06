package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.GlobalMasterPlayerMapper;
import org.supercat.growstone.models.DMGlobalMasterPlayer;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class GlobalMasterPlayerDBSet {
    public DBSqlExcutor executor;
    public GlobalMasterPlayerDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMGlobalMasterPlayer get(long id) {
        return executor.query(db -> db.getMapper(GlobalMasterPlayerMapper.class).get(id));
    }
    public DMGlobalMasterPlayer getByLoginInfo(int login_type, String login_id) {
        return executor.query(db -> db.getMapper(GlobalMasterPlayerMapper.class).getByLoginInfo(login_type, login_id));
    }

    public DMGlobalMasterPlayer getByGuest(String guestTokenId) {
        return executor.query(db -> db.getMapper(GlobalMasterPlayerMapper.class).getByGuest(guestTokenId));
    }

    public int insert(DMGlobalMasterPlayer model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(GlobalMasterPlayerMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updateTokenIdByGuest(DMGlobalMasterPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(GlobalMasterPlayerMapper.class).updateTokenIdByGuest(model));
    }
}
