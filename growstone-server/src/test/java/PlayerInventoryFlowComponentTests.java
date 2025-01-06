import com.supercat.growstone.network.messages.ZCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerInventoryFlowComponentTests extends BaseServerTests {

    @Test
    void getItemsTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resItems = ResourceManager.INSTANCE.item.getAll().stream()
                .filter(x -> x.category == ZCategory.Type.ITEM)
                .collect(Collectors.toList());
        Assertions.assertTrue(resItems.size() > 0);

        var items = new HashMap<Long, Long>();
        for (var resItem : resItems) {
            long count = SRandomUtils.nextInt(0, 10000000);
            items.put(resItem.id, count);
        }

        items.entrySet().stream()
                .forEach(x -> player.categoryFilter.add(ZCategory.Type.ITEM, x.getKey(), x.getValue()));


        var models = SDB.dbContext.item.getByPlayerId(player.getId());
        Assertions.assertTrue(!models.isEmpty());

        for (var model : models) {
            Assertions.assertEquals(model.count, items.get(model.item_data_id), "item_id: " + model.item_data_id);
        }

    }

    @Test
    void getAvatarIconTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var avatars = new HashMap<Long, Long>();
        for (int i = 0; i < GameData.PLAYER.starterAvatarIds.length; i++) {
            var id = GameData.PLAYER.starterAvatarIds[i];
            long count = SRandomUtils.nextInt(0, 100);
            avatars.put(id, count);
            player.categoryFilter.add(ZCategory.Type.AVATAR, id, count);
        }

        var duplicateCompute = new HashMap<Long, Long>();
        for (var icon : avatars.entrySet()) {
            var resIcon = ResourceManager.INSTANCE.item.getItem(icon.getKey());
            Assertions.assertNotNull(resIcon);

            duplicateCompute.compute(resIcon.duplicateReward.rewardId, (k, v) -> Objects.isNull(v) ?
                    resIcon.duplicateReward.count * icon.getValue() :
                    v + resIcon.duplicateReward.count * icon.getValue());
        }

        for (var entry : duplicateCompute.entrySet()) {
            Assertions.assertEquals(player.itemBag.getItemCount(entry.getKey()), entry.getValue());
        }
    }

    @Test
    void getPortraitIconTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var icons = new HashMap<Long, Long>();
        for (int i = 0; i < GameData.PLAYER.starterPortraitIconIds.length; i++) {
            var id = GameData.PLAYER.starterPortraitIconIds[i];
            long count = SRandomUtils.nextInt(0, 100);
            icons.put(id, count);
            player.categoryFilter.add(ZCategory.Type.PORTRAIT_ICON, id, count);
        }

        var duplicateCompute = new HashMap<Long, Long>();
        for (var icon : icons.entrySet()) {
            var resIcon = ResourceManager.INSTANCE.item.getItem(icon.getKey());
            Assertions.assertNotNull(resIcon);

            duplicateCompute.compute(resIcon.duplicateReward.rewardId, (k, v) -> Objects.isNull(v) ?
                    resIcon.duplicateReward.count * icon.getValue() :
                    v + resIcon.duplicateReward.count * icon.getValue());
        }

        for (var entry : duplicateCompute.entrySet()) {
            Assertions.assertEquals(player.itemBag.getItemCount(entry.getKey()), entry.getValue());
        }
    }

    @Test
    void getGrowthTests() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resGrowths = ResourceManager.INSTANCE.growth.getAll();
        Assertions.assertTrue(resGrowths.size() > 0);

        var growths = new HashMap<Long, Long>();
        for(var resgrowth : resGrowths) {
            long count = SRandomUtils.nextInt(0, 10000);
            player.categoryFilter.add(ZCategory.Type.GROWTH, resgrowth.id, count);
            growths.put(resgrowth.id, count);
        }

        for(var growth : growths.entrySet()) {
            long growthId = growth.getKey();
            long growthCount = growth.getValue();

            Assertions.assertEquals(player.growth.getGrowth(growthId).model.count, growthCount);
        }

        var models = SDB.dbContext.growth.getByPlayerId(player.getId());
        Assertions.assertNotNull(models);

        for(var model : models) {
            Assertions.assertEquals(model.count, growths.get(model.growth_id));
        }
    }
}
