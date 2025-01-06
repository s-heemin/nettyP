package org.supercat.growstone.components;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TStoneStatueEnchant;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.jsons.JMStoneStatueEnchantSlot;
import org.supercat.growstone.models.DMPlayerStoneStatueEnchant;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.StoneStatueEnchantRules;
import org.supercat.growstone.setups.SDB;

import java.util.*;

public class PlayerStoneStatueEnchantComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerStoneStatueEnchantComponent.class);

    private final WorldPlayer player;
    private Map<Integer, DMPlayerStoneStatueEnchant> models = new HashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();


    public PlayerStoneStatueEnchantComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        for (int i = 0; i < Constants.MAX_EQUIP_PRESET_SLOT_COUNT; ++i) {
            // 장비프리셋이 1부터 시작
            getOrCreate(i + 1);
        }
    }

    private DMPlayerStoneStatueEnchant getOrCreate(int orderId) {
        return models.computeIfAbsent(orderId, k -> getEnchantModelOrCreate(orderId));
    }

    private DMPlayerStoneStatueEnchant getEnchantModelOrCreate(int orderId) {
        var model = SDB.dbContext.playerStoneStatueEnchant.get(player.getId(), orderId);
        if (Objects.isNull(model)) {
            model = DMPlayerStoneStatueEnchant.of(player.getId(), orderId,
                    StoneStatueEnchantRules.addEnchantSlot(player.stoneStatue.getEnchantLevel(), "[]"));
        }

        return model;
    }

    public List<TStoneStatueEnchant> getAllTStoneStatueEnchant() {
        return TBuilderOf.ofTStoneStatueEnchantAll(models);
    }

    public int lockEnchantSlot(int slotId, boolean lock, Out<TStoneStatueEnchant> outEnchant) {
        if (slotId <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        var selectPreset = models.get(player.getPresetIndex());
        if (Objects.isNull(selectPreset)) {
            return StatusCode.FAIL;
        }

        var enchantSlots = JsonConverter.of(selectPreset.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(enchantSlots)) {
            return StatusCode.FAIL;
        }

        var slot = Arrays.stream(enchantSlots)
                .filter(x -> x.slotId == slotId)
                .findFirst()
                .orElse(null);
        if (Objects.isNull(slot)) {
            return StatusCode.FAIL;
        }

        if (slot.isLocked == lock) {
            return StatusCode.FAIL;
        }

        slot.isLocked = lock;

        selectPreset.data = JsonConverter.to(enchantSlots);
        SDB.dbContext.playerStoneStatueEnchant.save(selectPreset);

        outEnchant.set(TBuilderOf.ofTStoneStatueEnchant(selectPreset));

        return StatusCode.SUCCESS;
    }

    public int enchant(Out<TStoneStatueEnchant> outEnchant, Out<Integer> outLevel, Out<Integer> outExp) {
        var presetIndex = player.getPresetIndex();
        var model = models.get(presetIndex);
        if (Objects.isNull(model)) {
            return StatusCode.INVALID_REQUEST;
        }

        var enchantSlots = JsonConverter.of(model.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(enchantSlots) || enchantSlots.length == 0) {
            return StatusCode.INVALID_REQUEST;
        }

        // 모든 슬롯이 잠겨있으면 에러 반환
        var unLockCount = Arrays.stream(enchantSlots).filter(x -> !x.isLocked).count();
        if (unLockCount == 0) {
            return StatusCode.INVALID_REQUEST;
        }

        var lockCount = Arrays.stream(enchantSlots).filter(x -> x.isLocked).count();

        // 소모할 재화 체크 ( 잠겨있는 슬롯 숫자에 따라 소모량 증가 )
        var amount = GameData.STONE_STATUE.ENCHANT_COST + (lockCount * GameData.STONE_STATUE.ENCHANT_LOCK_INCREASE_AMOUNT);
        // 부족할 경우 에러
        var cost = player.itemBag.getItemCount(GameData.STONE_STATUE.ENCHANT_CURRENCY_ID);
        if (cost < amount) {
            return StatusCode.NOT_ENOUGH_MATERIAL;
        }

        // 재화 소모
        var status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.STONE_STATUE.ENCHANT_CURRENCY_ID, amount);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var beforeLevel = player.stoneStatue.getEnchantLevel();
        // 각인 진행
        var newEnchant = StoneStatueEnchantRules.tryEnchant(beforeLevel, model);
        model.data = JsonConverter.to(newEnchant);
        SDB.dbContext.playerStoneStatueEnchant.save(model);

        // 경험치 증가 및 레벨업 체크
        player.stoneStatue.addExp(amount);

        var afterLevel = player.stoneStatue.getEnchantLevel();
        var afterExp = player.stoneStatue.getEnchantExp();
        boolean isLevelUp = false;
        if (beforeLevel != afterLevel) {
            // 레벨업 시 처리
            isLevelUp = true;
        }

        if (isLevelUp) {
            for (var enchant : models.values()) {
                enchant.data = StoneStatueEnchantRules.addEnchantSlot(afterLevel, enchant.data);
                SDB.dbContext.playerStoneStatueEnchant.save(enchant);
            }
        }

        // 현재 프리셋의 석상 각인 정보만 보내줌
        outEnchant.set(TBuilderOf.ofTStoneStatueEnchant(model));
        outLevel.set(afterLevel);
        outExp.set(afterExp);


        return StatusCode.SUCCESS;
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheStats);
    }

    private void refresh() {
        cacheStats.clear();

        var nowPreset = models.get(player.getPresetIndex());
        StoneStatueEnchantRules.computeStats(cacheStats, nowPreset);
    }

    public JMStoneStatueEnchantSlot[] getNowPresetEnchantSlotListForTest() {
        var nowPreset = models.get(player.getPresetIndex());
        return JsonConverter.of(nowPreset.data, JMStoneStatueEnchantSlot[].class);
    }

    public void setNowPresetEnchantSlotLockForTest(int slotId, boolean lock) {
        var nowPreset = models.get(player.getPresetIndex());
        var enchantSlots = JsonConverter.of(nowPreset.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.isNull(enchantSlots)) {
            return;
        }

        var slot = Arrays.stream(enchantSlots)
                .filter(x -> x.slotId == slotId)
                .findFirst()
                .orElse(null);

        if (Objects.isNull(slot)) {
            return;
        }

        slot.isLocked = lock;
        nowPreset.data = JsonConverter.to(enchantSlots);
    }

    public void setNowPresetEnchantSlotForTest(String data) {
        var nowPreset = models.get(player.getPresetIndex());
        nowPreset.data = data;
    }
}
