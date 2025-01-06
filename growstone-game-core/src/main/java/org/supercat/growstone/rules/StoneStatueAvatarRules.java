package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZStat;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.models.DMPlayerStoneStatueAvatar;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class StoneStatueAvatarRules {
    public static void computeStats(HashMap<ZStat.Type, Double> stats, List<DMPlayerStoneStatueAvatar> models) {
        for (var model : models) {
            var resAvatar = ResourceManager.INSTANCE.avatar.get(model.avatar_id);
            if (Objects.isNull(resAvatar)) {
                continue;
            }

            for (var entry : resAvatar.stats.entrySet()) {
                var stat = entry.getKey();
                var value = entry.getValue();

                stats.compute(stat, (k, v) -> Objects.isNull(v) ? value : v + value);
            }
        }
    }
}
