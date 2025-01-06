import com.supercat.growstone.network.messages.TMaterial;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZPartsSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.SRandomUtils;
import org.supercat.growstone.SRedis;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.containers.ResultContainer;
import org.supercat.growstone.rules.GrowthRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerPartsSlotComponentTests extends BaseServerTests {
    @Test
    void levelUp() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var levelUpCount = 10;
        int status = player.partsSlot.partsSlotLevelUp(ZPartsSlot.Type.NONE, levelUpCount, new ResultContainer<>());
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        var type = ZPartsSlot.Type.HAT;
        var model = player.partsSlot.getOrDefault(type);
        Assertions.assertEquals(0, model.level);

        var resPartsSlot = ResourceManager.INSTANCE.partsSlot.get(type);
        Assertions.assertNotNull(resPartsSlot);

        int count = GrowthRules.computePartsSlotLevelMaterialCounts(model.level + levelUpCount, resPartsSlot.material);
        status = player.partsSlot.partsSlotLevelUp(type, count, new ResultContainer<>());
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_ITEM, status);

        status = player.categoryFilter.add(ZCategory.Type.ITEM, resPartsSlot.material.itemId, count);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        final int prevLevel = model.level;
        status = player.partsSlot.partsSlotLevelUp(type, levelUpCount, new ResultContainer<>());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        model = player.partsSlot.getOrDefault(type);
        Assertions.assertEquals(prevLevel + levelUpCount, model.level);
    }
}
