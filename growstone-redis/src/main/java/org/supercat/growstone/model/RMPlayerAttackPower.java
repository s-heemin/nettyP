package org.supercat.growstone.model;

public class RMPlayerAttackPower {
    public long playerId;
    public long attackPower;
    public long avatarId;
    public long portraitId;
    public int level;
    public String name;

    public static RMPlayerAttackPower of(long playerId, int level, long attackPower, long avatarId, long portraitId, String name) {
        var model = new RMPlayerAttackPower();
        model.level = level;
        model.playerId = playerId;
        model.attackPower = attackPower;
        model.avatarId = avatarId;
        model.portraitId = portraitId;
        model.name = name;

        return model;
    }
}
