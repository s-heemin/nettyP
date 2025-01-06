import com.supercat.growstone.network.messages.TStatGrowth;
import com.supercat.growstone.network.messages.TStatGrowthPage;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZStat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.Constants;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.StatusCode;

import java.util.ArrayList;

public class PlayerStatGrowthComponentTests extends BaseServerTests {
    @Test
    void testStatGrowth() {
        var outStatGrowthPage = TStatGrowthPage.newBuilder();

        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resStatGrowths = ResourceManager.INSTANCE.statGrowth.get(1);
        Assertions.assertNotNull(resStatGrowths);

        var model = player.statGrowth.getOrCreate(ZStat.Type.ATTACK);
        var resStatGrowth = ResourceManager.INSTANCE.statGrowth.get(model.page);
        Assertions.assertNotNull(resStatGrowth);

        int levelUpRand = SRandomUtils.nextInt(1, resStatGrowths.maxLevel);
        int status = player.statGrowth.levelUp(levelUpRand, ZStat.Type.ATTACK,  outStatGrowthPage);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        long needCost= 0;
        for(int i = model.level; i <levelUpRand; i++) {
            needCost += resStatGrowth.prices.get(i);
        }

        Assertions.assertNotNull(needCost);
        player.categoryFilter.add(ZCategory.Type.ITEM, Constants.GOLD_DATA_ID, needCost);
        status = player.statGrowth.levelUp(levelUpRand, ZStat.Type.ATTACK, outStatGrowthPage);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void testStatGrowthUpgradeMaxPage() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);
        var outStatGrowthPage = TStatGrowthPage.newBuilder();
        var resStatGrowths = ResourceManager.INSTANCE.statGrowth.getAll();
        for(var resStatGrowth : resStatGrowths) {
            for(var stats : resStatGrowth.stats.entrySet()) {
                for(int i=0 ; i<resStatGrowth.maxLevel; i++) {
                    var model = player.statGrowth.getOrCreate(stats.getKey());
                    var needCost = resStatGrowth.prices.get(model.level);
                    Assertions.assertNotNull(needCost);

                    player.categoryFilter.add(ZCategory.Type.ITEM, Constants.GOLD_DATA_ID, needCost);
                    int status = player.statGrowth.levelUp(1, stats.getKey(), outStatGrowthPage);
                    Assertions.assertEquals(StatusCode.SUCCESS, status);
                }
            }
        }

        int maxPage = ResourceManager.INSTANCE.statGrowth.getAll().size();
        var resLastStatGrowth = ResourceManager.INSTANCE.statGrowth.get(maxPage);
        Assertions.assertNotNull(resLastStatGrowth);
        Assertions.assertEquals(maxPage,  player.statGrowth.getTStatGrowthPage().getPage());
        for(var tGrowStat : player.statGrowth.getTStatGrowthPage().getStatGrowthsList()) {
            Assertions.assertEquals(resLastStatGrowth.maxLevel, tGrowStat.getLevel());
        }
    }
}
