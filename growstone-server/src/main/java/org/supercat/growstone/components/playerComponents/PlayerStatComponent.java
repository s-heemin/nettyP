package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.TStat;
import com.supercat.growstone.network.messages.ZStat;
import com.supercat.growstone.network.messages.ZStatsNotify;
import org.supercat.growstone.SRedis;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.StatRules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerStatComponent {
    private WorldPlayer player;
    private HashMap<ZStat.Type, Double> stats = new HashMap<>();

    public PlayerStatComponent(WorldPlayer player) {
        this.player = player;
    }

    public List<TStat> getTStats() {
        refresh();
        return stats.entrySet().stream()
                .map(entry -> TBuilderOf.buildOf(entry.getKey(), entry.getValue()))
                .toList();
    }

    private void refresh() {
        stats.clear();

        var growths = Map.copyOf(player.growth.getCacheStats());
        var avatars = Map.copyOf(player.avatar.getCacheStats());
        var collections = Map.copyOf(player.collection.getCacheStats());
        var statGrowths = Map.copyOf(player.statGrowth.getCacheStats());
        var presets = Map.copyOf(player.preset.getCacheStats());
        var stoneStatueEnchants = Map.copyOf(player.stoneStatueEnchant.getCacheStats());
        var stoneStatueGems = Map.copyOf(player.stoneStatueGem.getCacheStats());
        var stoneStatueAvatars = Map.copyOf(player.stoneStatueAvatar.getCacheStats());

        stats = StatRules.calculateStats(
                growths, avatars, collections, statGrowths, presets,
                stoneStatueEnchants, stoneStatueGems, stoneStatueAvatars
        );

        var attackPower = StatRules.calculateAttackPower(stats);
        player.setAttackPower(attackPower);
    }

    public void saveStats() {
        for (var stat : stats.entrySet()) {
            SRedis.INSTANCE.content.playerStat.addStat(player.getId(), stat.getKey(), stat.getValue());
        }
    }

    public void statsNotify() {
        player.sendPacket(0, ZStatsNotify.newBuilder()
                .addAllStats(getTStats())
                .setAttackPower(player.getAttackPower()));
    }


}
