package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerAvatarMapper;
import org.supercat.growstone.models.DMPlayerAvatar;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerAvatarDBSet {
    public DBSqlExcutor executor;
    public PlayerAvatarDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerAvatar get(long id) {
        return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).get(id));
    }
    public List<DMPlayerAvatar> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).getByPlayerId(playerId));
    }

    public DMPlayerAvatar getByEquipAvatar(long playerId) {
        return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).getByEquipAvatar(playerId));
    }
    public int insert(DMPlayerAvatar model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    public int updateEquip(long id, boolean isEquip) {
        return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).updateEquip(id, isEquip, Instant.now()));
    }

    public int deleteForCheat(long id) {
        return executor.query(db -> db.getMapper(PlayerAvatarMapper.class).deleteForCheat(id));
    }

}
