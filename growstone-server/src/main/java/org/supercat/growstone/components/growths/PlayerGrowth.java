package org.supercat.growstone.components.growths;

import com.supercat.growstone.network.messages.ZGrowth;
import com.supercat.growstone.network.messages.ZGrowthStatTarget;
import com.supercat.growstone.network.messages.ZStat;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SavableObject;
import org.supercat.growstone.models.DMPlayerGrowth;
import org.supercat.growstone.rules.GrowthRules;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.Objects;

public class PlayerGrowth extends SavableObject {
    public final WorldPlayer player;
    public DMPlayerGrowth model;
    public PlayerGrowth(WorldPlayer player, DMPlayerGrowth model) {
        this.player = player;
        this.model = model;
    }

    public static PlayerGrowth of(WorldPlayer player, long growthId, ZGrowth.Type type) {
        return new PlayerGrowth(player, DMPlayerGrowth.of(player.getId(), growthId, type.getNumber()));
    }

    public boolean saveInternal() {
        return SDB.dbContext.growth.save(model) > 0;
    }
    public HashMap<ZStat.Type, Double> calculateStats() {
        var result = new HashMap<ZStat.Type, Double>();
        var resGrowth = ResourceManager.INSTANCE.growth.get(model.growth_id);
        if(Objects.isNull(resGrowth)) {
            return result;
        }

        // 소유 스탯
        GrowthRules.calculateStats(resGrowth.ownedStats, ZGrowthStatTarget.Type.LEVEL, model.level, 1.0f, result);
        GrowthRules.calculateStats(resGrowth.ownedStats, ZGrowthStatTarget.Type.PROMOTE, model.promote_level, 1.0f, result);
        GrowthRules.calculateStats(resGrowth.ownedStats, ZGrowthStatTarget.Type.LIMIT_BREAK, model.limit_break_level, 1.0f,result);

        return result;
    }
}
