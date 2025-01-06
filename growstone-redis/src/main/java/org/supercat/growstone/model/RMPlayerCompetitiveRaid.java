package org.supercat.growstone.model;

public class RMPlayerCompetitiveRaid {
    public long player_id;
    public long avatar_id;
    public String name;

    public static RMPlayerCompetitiveRaid of(long playerId, long avatarId, String name) {
        var model = new RMPlayerCompetitiveRaid();
        model.player_id = playerId;
        model.avatar_id = avatarId;
        model.name = name;

        return model;
    }
}
