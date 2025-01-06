package org.supercat.growstone.dbsets;

import org.supercat.growstone.SLog;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.mappers.PlayerMapper;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.setups.DBSqlExcutor;

import java.time.Instant;

public class PlayerDBSet {
    public DBSqlExcutor executor;
    public PlayerDBSet(DBSqlExcutor executor) {
        this.executor = executor;
    }

    public DMPlayer get(long id) {
        return executor.query(db -> db.getMapper(PlayerMapper.class).get(id));
    }

    public DMPlayer getLastConnectedPlayer(long globalMasterPlayerId) {
        return executor.query(db -> db.getMapper(PlayerMapper.class).getLastConnectedPlayer(globalMasterPlayerId));
    }

    public DMPlayer getPlayerByFriendCode(String friendCode) {
        return executor.query(db -> db.getMapper(PlayerMapper.class).getPlayerByFriendCode(friendCode));
    }

    public DMPlayer getPlayerByFriendName(String name) {
        return executor.query(db -> db.getMapper(PlayerMapper.class).getPlayerByFriendName(name));
    }
    public int save(DMPlayer model) {
        if(model.id > 0) {
            return update(model);
        }

        return insert(model);
    }

    private int update(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).update(model));
    }



    private int insert(DMPlayer model) {
        model.updated_at = Instant.now();
        model.created_at = model.updated_at;

        try {
            return executor.query(db -> db.getMapper(PlayerMapper.class).insert(model));
        } catch (Exception e) {
            SLog.logException(e);
            return 0;
        }
    }

    // insert되고 id를 갖고 넣어줘야하는 데이터들
    public int updateAfterInsertData(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updateAfterInsertData(model));
    }

    public int updateStage(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updateStage(model));
    }

    public int updatePresetIndex(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updatePresetIndex(model));
    }

    public int updatePortrait(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updatePortrait(model));
    }

    public int onBossGauge(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).onBossGauge(model));
    }

    public int updateRemoveAd(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updateRemoveAd(model));
    }

    public int clearClanIdByPlayerId(long playerId, long clanId) {
        return executor.query(db -> db.getMapper(PlayerMapper.class).updateClanIdByPlayerId(playerId, clanId, Instant.now()));
    }

    public int updateLevel(DMPlayer model) {
        model.updated_at = Instant.now();
        return executor.query(db -> db.getMapper(PlayerMapper.class).updateLevel(model));
    }

    public int updateName(long id, String name) {
        try {
            executor.query(db -> db.getMapper(PlayerMapper.class).updateName(id, name, Instant.now()));
        } catch (Exception e) {
            return StatusCode.ALREADY_EXIST_NAME;
        }

        return StatusCode.SUCCESS;

    }
}
