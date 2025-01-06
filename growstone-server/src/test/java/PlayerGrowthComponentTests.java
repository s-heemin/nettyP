import com.supercat.growstone.network.messages.TGrowth;
import com.supercat.growstone.network.messages.TMaterial;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZGrowthStatTarget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.containers.ResultContainer;
import org.supercat.growstone.rules.GrowthRules;

import java.util.ArrayList;
import java.util.List;

public class PlayerGrowthComponentTests extends BaseServerTests {

    @Test
    void levelUpTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var out = TGrowth.newBuilder();
        var zMaterial = TMaterial.newBuilder().build();
        int status = player.growth.levelUp(-1, zMaterial, out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        var resGrowth = ResourceManager.INSTANCE.growth.getAll().stream()
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resGrowth);

        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH, status);

        status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.growth.levelUp(resGrowth.id, null, out);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(resGrowth.type, resGrowth.tier);
        Assertions.assertNotNull(resGrowthUpgrade);

        var resGrowthUpgradeLevel = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.LEVEL);
        Assertions.assertNotNull(resGrowthUpgradeLevel);

        int defaultLevel = resGrowthUpgradeLevel.maxUpgradeLevel;
        var model = player.growth.getGrowth(resGrowth.id);
        Assertions.assertNotNull(model);
        model.model.level = defaultLevel;

        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);

        model.model.level = 3;
        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_MATERIAL_ID, status);

        // 레벨당 필요한 재료의 수가 다르기 떄문에 계산이 필요하다
        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.LEVEL);
        Assertions.assertNotNull(resMaterial);

        zMaterial = TMaterial.newBuilder()
                .setId(resMaterial.itemId)
                .build();

        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);

        int needCount = GrowthRules.computeMaterialCountByType(model.model.level, resMaterial);
        zMaterial = TMaterial.newBuilder()
                .setId(resMaterial.itemId)
                .setCount(needCount - 1)
                .build();
        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);

        zMaterial = TMaterial.newBuilder()
                .setId(resMaterial.itemId)
                .setCount(needCount)
                .build();

        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_ITEM, status);

        final int beforeLevel = model.model.level;
        status = player.categoryFilter.add(ZCategory.Type.ITEM, resMaterial.itemId, needCount);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(beforeLevel + 1, model.model.level);
    }

    @Test
    void promoteTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        int status = player.growth.promote(List.of(), List.of());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var resGrowth = ResourceManager.INSTANCE.growth.getAll().stream()
                .findAny()
                .orElseGet(null);
        Assertions.assertNotNull(resGrowth);
        var zMaterial = TMaterial.newBuilder()
                .setId(-3)
                .setCount(0)
                .build();

        var out = new ArrayList<TGrowth>();
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        zMaterial = TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(0)
                .build();
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH, status);

        status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(resGrowth.type, resGrowth.tier);
        Assertions.assertNotNull(resGrowthUpgrade);

        var resGrowthUpgradePromote = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.PROMOTE);
        Assertions.assertNotNull(resGrowthUpgradePromote);

        var model = player.growth.getGrowth(resGrowth.id);
        Assertions.assertNotNull(model);

        model.model.promote_level = resGrowthUpgradePromote.maxUpgradeLevel;
        zMaterial = TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(0)
                .build();
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);

        model.model.promote_level -= 1;
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);

        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.PROMOTE);
        Assertions.assertNotNull(resMaterial);

        int needCount = GrowthRules.computeMaterialCountByType(model.model.promote_level, resMaterial);
        zMaterial = TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(needCount - 1)
                .build();
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);
        zMaterial = TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(needCount)
                .build();

        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, needCount);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        final int beforeLevel = model.model.promote_level;
        status = player.growth.promote(List.of(zMaterial), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(model.model.count, 0);
        Assertions.assertEquals(beforeLevel + 1, model.model.promote_level);
    }

    @Test
    void limitBreakTests() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var outs = new ArrayList<TGrowth>();
        int status = player.growth.limitBreak(null, outs );
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        var zMaterials = new ArrayList<TMaterial>();
        zMaterials.add(TMaterial.newBuilder()
                .setId(-1)
                .setCount(0)
                .build());

        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);

        var resGrowth = ResourceManager.INSTANCE.growth.getAll().stream()
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resGrowth);
        zMaterials.clear();

        zMaterials.add(TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(0)
                .build());
        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.INVALID_GROWTH, status);

        status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(resGrowth.type, resGrowth.tier);
        Assertions.assertNotNull(resGrowthUpgrade);

        var resGrowthUpgradeLimitBreak = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.LIMIT_BREAK);
        Assertions.assertNotNull(resGrowthUpgradeLimitBreak);

        var model = player.growth.getGrowth(resGrowth.id);
        model.model.limit_break_level = resGrowthUpgradeLimitBreak.maxUpgradeLevel;
        status = player.growth.limitBreak(zMaterials, outs);
        Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
        --model.model.limit_break_level;

        var resGrowthUpgradePromote = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.PROMOTE);
        Assertions.assertNotNull(resGrowthUpgradePromote);

        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_PROMOTE_LEVEL, status);
        model.model.promote_level = resGrowthUpgradePromote.maxUpgradeLevel;

        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);

        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.LIMIT_BREAK);
        Assertions.assertNotNull(resGrowth);

        int needCount = GrowthRules.computeMaterialCountByType(model.model.limit_break_level, resMaterial);
        zMaterials.clear();
        zMaterials.add(TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(needCount - 1)
                .build());
        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.INVALID_GROWTH_COUNT, status);
        zMaterials.clear();
        zMaterials.add(TMaterial.newBuilder()
                .setId(resGrowth.id)
                .setCount(needCount)
                .build());

        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_MATERIAL, status);

        status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, needCount);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        final int beforeLevel = model.model.limit_break_level;
        status = player.growth.limitBreak(zMaterials, outs );
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        Assertions.assertEquals(beforeLevel + 1, model.model.limit_break_level);
        Assertions.assertEquals(model.model.count, 0);
    }

    @Test
    void increaseMaxLevel() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resGrowth = ResourceManager.INSTANCE.growth.getAll().stream()
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resGrowth);

        var status = player.categoryFilter.add(ZCategory.Type.GROWTH, resGrowth.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resGrowthUpgrade = ResourceManager.INSTANCE.growth.get(resGrowth.type, resGrowth.tier);
        Assertions.assertNotNull(resGrowthUpgrade);

        var resGrowthUpgradeLevel = resGrowthUpgrade.levelUpgrades.get(ZGrowthStatTarget.Type.LEVEL);
        Assertions.assertNotNull(resGrowthUpgradeLevel);

        var model = player.growth.getGrowth(resGrowth.id);
        Assertions.assertNotNull(model);

        model.model.level = resGrowthUpgradeLevel.maxUpgradeLevel;
        var resMaterial = resGrowthUpgrade.materials.get(ZGrowthStatTarget.Type.LEVEL);
        Assertions.assertNotNull(resMaterial);

        int needCount = GrowthRules.computeMaterialCountByType(model.model.level, resMaterial);

        var zMaterial = TMaterial.newBuilder()
                .setId(resMaterial.itemId)
                .setCount(needCount)
                .build();

        var out = TGrowth.newBuilder();
        player.categoryFilter.add(ZCategory.Type.ITEM, zMaterial.getId(), zMaterial.getCount());
        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.ALREADY_MAX_LEVEL, status);
        ++model.model.limit_break_level;

        final int beforeLevel = model.model.level;
        status = player.growth.levelUp(resGrowth.id, zMaterial, out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
        Assertions.assertEquals(beforeLevel + 1, model.model.level);
        Assertions.assertEquals(model.model.count, 0);
        Assertions.assertEquals(model.model.limit_break_level, 1);
    }
}
