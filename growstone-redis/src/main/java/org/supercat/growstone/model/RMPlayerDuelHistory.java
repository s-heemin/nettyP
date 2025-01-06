package org.supercat.growstone.model;

public class RMPlayerDuelHistory {
    public long id;
    public int critical_seed;
    public long win_player_id;
    public String win_player_name;
    public long win_player_avatar_id;
    public int win_player_seed;
    public long lose_player_id;
    public String lose_player_name;
    public long lose_player_avatar_id;
    public int lose_player_seed;
    public static RMPlayerDuelHistory of(long id, int criticalSeed, long win_player_id, String win_player_name, long win_player_avatar_id, int win_player_seed,
                                         long lose_player_id, String lose_player_name, long lose_player_avatar_id, int lose_player_seed) {
        var model = new RMPlayerDuelHistory();
        model.id = id;
        model.critical_seed = criticalSeed;
        model.win_player_id = win_player_id;
        model.win_player_name = win_player_name;
        model.win_player_avatar_id = win_player_avatar_id;
        model.win_player_seed = win_player_seed;
        model.lose_player_id = lose_player_id;
        model.lose_player_name = lose_player_name;
        model.lose_player_avatar_id = lose_player_avatar_id;
        model.lose_player_seed = lose_player_seed;
        return model;
    }
}
