package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerAvatarMapper;
import org.supercat.growstone.mappers.PlayerClanJoinRequestMapper;
import org.supercat.growstone.models.DMPlayerClanJoinRequest;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerClanJoinRequestDBSet {
    public DBSqlExcutor excutor;
    public PlayerClanJoinRequestDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public DMPlayerClanJoinRequest get(long id) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).get(id));
    }
    public List<DMPlayerClanJoinRequest> getByPlayerId(long playerId) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).getByPlayerId(playerId));
    }

    public DMPlayerClanJoinRequest getByPlayerIdAndClanId(long playerId, long clanId) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).getByPlayerIdAndClanId(playerId, clanId));
    }
    public List<DMPlayerClanJoinRequest> getByClanId(long clan_id) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).getByClanId(clan_id));
    }

    public int insert(DMPlayerClanJoinRequest model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }


    public int deleteByPlayerId(long playerId) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).deleteByPlayerId(playerId));
    }

    public int deleteByPlayerIdAndClanId(long playerId, long clanId) {
        return excutor.query(db -> db.getMapper(PlayerClanJoinRequestMapper.class).deleteByPlayerIdAndClanId(playerId, clanId));
    }
}
