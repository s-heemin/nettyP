package org.supercat.growstone.dbsets;

import org.supercat.growstone.mappers.BannedNameMapper;
import org.supercat.growstone.mappers.PlayerAvatarMapper;
import org.supercat.growstone.models.DMPlayerAvatar;
import org.supercat.growstone.setups.DBSqlExcutor;

public class BannedNameDBSet {
    public DBSqlExcutor excutor;
    public BannedNameDBSet(DBSqlExcutor excutor) {
        this.excutor = excutor;
    }

    public int get(String text) {
        return excutor.query(db -> db.getMapper(BannedNameMapper.class).get(text));
    }
}
