import com.supercat.growstone.network.messages.TStoneStatueEnchant;
import com.supercat.growstone.network.messages.TStoneStatueGem;
import com.supercat.growstone.network.messages.ZCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.Out;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;

import java.util.Arrays;
import java.util.Objects;

public class PlayerStoneStatueComponentTests extends BaseServerTests {

    @Test
    void testStoneStatueLockEnchantSlot() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        Out<TStoneStatueEnchant> outEnchant = Out.of(null);
        var slot = -1;
        var status = player.stoneStatueEnchant.lockEnchantSlot(slot, true, outEnchant);
        Assertions.assertEquals(status, StatusCode.INVALID_REQUEST);

        // 아직 오픈되어 있지 않는 슬롯으로 요청했을 경우
        slot = 100;
        status = player.stoneStatueEnchant.lockEnchantSlot(slot, true, outEnchant);
        Assertions.assertEquals(status, StatusCode.FAIL);

        var slotInfoList = player.stoneStatueEnchant.getNowPresetEnchantSlotListForTest();
        var slotInfo = Arrays.stream(slotInfoList)
                .filter(x -> !x.isLocked)
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(slotInfo);

        player.stoneStatueEnchant.setNowPresetEnchantSlotLockForTest(slotInfo.slotId, true);

        // 요청 슬롯이 이미 잠겨있는 경우
        status = player.stoneStatueEnchant.lockEnchantSlot(slotInfo.slotId, true, outEnchant);
        Assertions.assertEquals(status, StatusCode.FAIL);

        player.stoneStatueEnchant.setNowPresetEnchantSlotLockForTest(slotInfo.slotId, false);

        // 정상
        status = player.stoneStatueEnchant.lockEnchantSlot(slotInfo.slotId, true, outEnchant);
        Assertions.assertEquals(status, StatusCode.SUCCESS);
    }

    @Test
    void testStoneStatueTryEnchant() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        Out<TStoneStatueEnchant> outEnchant = Out.of(null);
        Out<Integer> outLevel = Out.of(0);
        Out<Integer> outExp = Out.of(0);

        // 각인 슬롯이 없는데 각인 요청한 경우
        var slotInfoList = player.stoneStatueEnchant.getNowPresetEnchantSlotListForTest();
        player.stoneStatueEnchant.setNowPresetEnchantSlotForTest("[]");

        var status = player.stoneStatueEnchant.enchant(outEnchant, outLevel, outExp);
        Assertions.assertEquals(status, StatusCode.INVALID_REQUEST);

        player.stoneStatueEnchant.setNowPresetEnchantSlotForTest(JsonConverter.to(slotInfoList));

        // 모든 슬롯이 잠겨있는 경우
        for (var slotInfo : slotInfoList) {
            player.stoneStatueEnchant.setNowPresetEnchantSlotLockForTest(slotInfo.slotId, true);
        }

        status = player.stoneStatueEnchant.enchant(outEnchant, outLevel, outExp);
        Assertions.assertEquals(status, StatusCode.INVALID_REQUEST);

        for (var slotInfo : slotInfoList) {
            player.stoneStatueEnchant.setNowPresetEnchantSlotLockForTest(slotInfo.slotId, false);
        }

        var lockCount = Arrays.stream(slotInfoList).filter(x -> x.isLocked).count();
        // 재화가 부족할 경우
        status = player.stoneStatueEnchant.enchant(outEnchant, outLevel, outExp);
        Assertions.assertEquals(status, StatusCode.NOT_ENOUGH_MATERIAL);

        var needAmount = GameData.STONE_STATUE.ENCHANT_COST + (lockCount * GameData.STONE_STATUE.ENCHANT_LOCK_INCREASE_AMOUNT);
        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.STONE_STATUE.ENCHANT_CURRENCY_ID, needAmount);

        // 정상

        status = player.stoneStatueEnchant.enchant(outEnchant, outLevel, outExp);
        Assertions.assertEquals(status, StatusCode.SUCCESS);
    }

    @Test
    void testGemLevelUp() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        long gemId = -1;
        Out<TStoneStatueGem> outGem = Out.of(null);
        // 잘못된 젬id를 요청한 경우
        var status = player.stoneStatueGem.tryGemLevelUp(gemId, outGem);
        Assertions.assertEquals(status, StatusCode.FAIL);

        var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGems()
                .stream()
                .findAny()
                .orElse(null);
        Assertions.assertNotNull(resStoneStatueGem);

        gemId = resStoneStatueGem.id;
        // 이미 젬 레벨이 최대인 경우
        var dmGem = player.stoneStatueGem.getStoneStatueGemForTest(gemId);
        var beforeLevel = dmGem.gem_level;
        var mainGemModel = player.stoneStatueGem.getStoneStatueGemForTest(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID());
        var nowLimitLevel = mainGemModel.gem_level;

        var resStoneStatueGemGroup = resStoneStatueGem.statGroup.get((long) nowLimitLevel);
        var maxLevel = resStoneStatueGemGroup.maxLevel + resStoneStatueGemGroup.beforeMaxLevel;
        player.stoneStatueGem.setStoneStatueGemLevelForTest(gemId, maxLevel);

        status = player.stoneStatueGem.tryGemLevelUp(gemId, outGem);
        Assertions.assertEquals(status, StatusCode.FAIL);

        player.stoneStatueGem.setStoneStatueGemLevelForTest(gemId, beforeLevel);

        // 재화가 부족한 경우
        status = player.stoneStatueGem.tryGemLevelUp(gemId, outGem);
        Assertions.assertEquals(status, StatusCode.NOT_ENOUGH_ITEM);

        var needAmount = resStoneStatueGemGroup.price;
        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID, needAmount);

        // 정상
        status = player.stoneStatueGem.tryGemLevelUp(gemId, outGem);
        Assertions.assertEquals(status, StatusCode.SUCCESS);
    }

    @Test
    void testGemLimitLevelUp() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var model = player.stoneStatueGem.getStoneStatueGemForTest(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID());
        var beforeLevel = model.gem_level;

        // 이미 제한 레벨이 최대인 경우
        player.stoneStatueGem.setStoneStatueGemLevelForTest(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID(), ResourceManager.INSTANCE.stoneStatueGem.getMainStoneMaxLevel());

        Out<Integer> outGemLevel = Out.of(0);
        var status = player.stoneStatueGem.tryGemLimitLevelUp(outGemLevel);
        Assertions.assertEquals(status, StatusCode.INVALID_REQUEST);

        player.stoneStatueGem.setStoneStatueGemLevelForTest(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID(), beforeLevel);

        // 모든 보석들이 현재단계의 최대레벨에 도달하지 않은 경우
        status = player.stoneStatueGem.tryGemLimitLevelUp(outGemLevel);
        Assertions.assertEquals(status, StatusCode.INVALID_REQUEST);

        var resStoneStatueGemList = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGems();

        for (var resStoneStatueGem : resStoneStatueGemList) {
            var resStoneStatueGemGroup = resStoneStatueGem.statGroup.get((long) model.gem_level);
            if (Objects.isNull(resStoneStatueGemGroup)) {
                continue;
            }

            player.stoneStatueGem.setStoneStatueGemLevelForTest(resStoneStatueGem.id, resStoneStatueGemGroup.maxLevel);
        }

        // 재화가 부족한 경우
        status = player.stoneStatueGem.tryGemLimitLevelUp(outGemLevel);
        Assertions.assertEquals(status, StatusCode.NOT_ENOUGH_ITEM);

        var resMainStoneGem = ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGem();
        Assertions.assertNotNull(resMainStoneGem);

        var resMainStoneGemStatGroup = resMainStoneGem.statGroup.get((long) model.gem_level);
        Assertions.assertNotNull(resMainStoneGemStatGroup);

        var needAmount = resMainStoneGemStatGroup.price;
        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID, needAmount);

        // 정상
        status = player.stoneStatueGem.tryGemLimitLevelUp(outGemLevel);
        Assertions.assertEquals(status, StatusCode.SUCCESS);
    }

    @Test
    void testEquipStoneStatueAvatar() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        // TODO: avatar 데이터 추가 후 확인 필요
        // 잘못된 아바타 아이디를 요청한 경우

        // 정상
//        var status = player.stoneStatue.equipAvatar(0);
//        Assertions.assertEquals(status, StatusCode.SUCCESS);
    }
}
