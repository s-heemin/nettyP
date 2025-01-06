package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.mappers.PlayerFriendMapper;
import org.supercat.growstone.models.DMPlayerFriend;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;
import java.util.List;

public class PlayerFriendDBSet {
    public DBSqlExcutor executor;

    public PlayerFriendDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayerFriend get(long id) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).get(id));
    }

    public List<DMPlayerFriend> getByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).getByPlayerId(playerId));
    }

    public int getFriendSizeByPlayerId(long playerId) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).getFriendSizeByPlayerId(playerId));
    }

    public DMPlayerFriend getByPlayerIdAndTargetPlayerId(long playerId, long targetPlayerId) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).getByPlayerIdAndTargetPlayerId(playerId, targetPlayerId));
    }
    public int save(DMPlayerFriend model) {
        if (model.id > 0) {
            return update(model);
        } else {
            return insert(model);
        }
    }

    private int insert(DMPlayerFriend model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;
        try {
            return executor.query(db -> db.getMapper(PlayerFriendMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    private int update(DMPlayerFriend model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).update(model));
    }
    public int delete(long id) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).deleteById(id));
    }

    public int updateSendGiftExpiretime(DMPlayerFriend model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).updateSendGiftExpiretime(model));
    }

    public int updateReceiveGiftExpiretime(long playerId, long targetPlayerId, Instant receiveGiftExpireAt) {
        return executor.query(db -> db.getMapper(PlayerFriendMapper.class).updateReceiveGiftExpiretime(playerId, targetPlayerId, receiveGiftExpireAt, Instant.now()));
    }



}
