package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Constants;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerEquipPreset;
import org.supercat.growstone.models.DMPlayerEquipPresetName;
import org.supercat.growstone.rules.GrowthRules;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.rules.PresetRules;
import org.supercat.growstone.setups.SDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerEquipPresetComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerEquipPresetComponent.class);

    private WorldPlayer player;
    private HashMap<Integer, DMPlayerEquipPresetName> presetNames = new HashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();
    private ConcurrentHashMap<Integer, HashMap<ZPreset.Type, List<DMPlayerEquipPreset>>> models = new ConcurrentHashMap<>();

    public PlayerEquipPresetComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var presets = SDB.dbContext.equipPreset.getByPlayerId(player.getId());
        for (var preset : presets) {
            models.computeIfAbsent(preset.preset_index, k -> new HashMap<>())
                    .computeIfAbsent(NetEnumRules.ofPreset(preset.type), k -> new ArrayList<>())
                    .add(preset);
        }

        var names = SDB.dbContext.equipPresetName.getByPlayerId(player.getId());
        for (var name : names) {
            presetNames.put(name.index, name);
        }
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheStats);
    }

    private void refresh() {
        cacheStats.clear();

        var presets = models.get(player.getPresetIndex());
        if (Objects.isNull(presets) || presets.isEmpty()) {
            return;
        }

        computeStats(presets);
    }

    public HashMap<ZPreset.Type, List<DMPlayerEquipPreset>> getCurrentPresetInfo() {
        int presetIndex = player.getPresetIndex();
        var equipInfosByType = models.get(presetIndex);
        if (Objects.isNull(equipInfosByType) || equipInfosByType.isEmpty()) {
            return new HashMap<>();
        }

        return equipInfosByType;
    }

    public List<TEquipPresets> getTPresets() {
        var result = new ArrayList<TEquipPresets>();
        for (var model : models.entrySet()) {
            int presetIndex = model.getKey();
            var equipInfosByType = model.getValue();
            String presetName = player.preset.getPresetName(presetIndex);
            var TPreset = new ArrayList<TEquipPresetsByType>();
            for (var equipInfos : equipInfosByType.entrySet()) {
                var equipType = equipInfos.getKey();
                var equips = equipInfos.getValue();
                var TPresetEquips = new ArrayList<TEquipPreset>();
                for (var equip : equips) {
                    TPresetEquips.add(TBuilderOf.buildOf(equip));
                }

                TPreset.add(TBuilderOf.buildOf(equipType, TPresetEquips));
            }

            result.add(TEquipPresets.newBuilder()
                    .setPresetIndex(presetIndex)
                    .addAllEquips(TPreset)
                    .setPresetName(presetName)
                    .build());
        }

        // 장착 장비에는 존재하지 않지만 프리셋 이름 변경에 존재한다면 따로 추가해준다.
        for(var kv : presetNames.entrySet()) {
            var index = kv.getKey();
            var model = kv.getValue();

            if(models.containsKey(index)) {
                continue;
            }

            result.add(TEquipPresets.newBuilder()
                    .setPresetIndex(index)
                    .setPresetName(model.name)
                    .build());
        }

        return result;
    }

    public int presetEquip(int presetIndex, ZPreset.Type type, List<TEquipPreset> presets, TEquipPresetsByType.Builder outPresets) {
        if (presets.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        for (var preset : presets) {
            int status = PresetRules.review(presetIndex, type, preset.getEquipIndex());
            if (!StatusCode.isSuccess(status)) {
                return status;
            }

            if (!isHavingPreset(type, preset.getDataId())) {
                return StatusCode.INVALID_PRESET_DATA;
            }
        }

        // 여기까지 통과되었다면 변경시켜줘야한다.
        var equipInfosByType = models.computeIfAbsent(presetIndex, k -> new HashMap<>());
        var equips = equipInfosByType.computeIfAbsent(type, k -> new ArrayList<>());

        for (var preset : presets) {
            var model = equips.stream()
                    .filter(x -> x.equip_index == preset.getEquipIndex())
                    .findAny()
                    .orElseGet(() -> {
                        var newModel = DMPlayerEquipPreset.of(player.getId(), type.getNumber(), presetIndex, preset.getEquipIndex(), preset.getDataId());
                        equips.add(newModel);
                        return newModel;
                    });

            if (model.data_id != preset.getDataId()) {
                model.data_id = preset.getDataId();
            }

            SDB.dbContext.equipPreset.save(model);
        }

        var l = new ArrayList<TEquipPreset>();
        equips.stream().forEach(x -> l.add(TBuilderOf.buildOf(x)));
        outPresets.setType(type).addAllEquips(l).build();

        refresh();

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public String getPresetName(int presetIndex) {
        var preset = presetNames.get(presetIndex);
        return Objects.isNull(preset) ? "" : preset.name;
    }

    public List<TEquipPresetName> getTEquipPresetNames() {
        return presetNames.values().stream()
                .map(x -> TBuilderOf.buildOf(x))
                .collect(Collectors.toList());
    }

    public int presetIndexChange(int presetIndex) {
        if (presetIndex < 0 || presetIndex > Constants.MAX_EQUIP_PRESET_SLOT_COUNT) {
            return StatusCode.INVALID_PRESET_INDEX;
        }

        if (presetIndex == player.getPresetIndex()) {
            return StatusCode.ALREADY_EQUIP_PRESET_SAME_INDEX;
        }

        // 슬롯 변경
        player.changePresetIndex(presetIndex);

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public int presetNameChange(int presetIndex, String name, TEquipPresetName.Builder out) {
        var preset = presetNames.get(presetIndex);
        if (Objects.isNull(preset)) {
            preset = DMPlayerEquipPresetName.of(player.getId(), presetIndex, name);
        }

        preset.name = name;
        presetNames.put(presetIndex, preset);
        SDB.dbContext.equipPresetName.save(preset);

        out.setName(preset.name).setIndex(preset.index).build();
        return StatusCode.SUCCESS;
    }

    public int presetUnEquip(int presetIndex, ZPreset.Type type, int equipIndex, TEquipPresetsByType.Builder outPresets) {
        int status = PresetRules.review(presetIndex, type, equipIndex);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var equipInfosByType = models.get(presetIndex);
        if (Objects.isNull(equipInfosByType) || equipInfosByType.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        var equips = equipInfosByType.get(type);
        if (Objects.isNull(equips) || equips.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        var equip = equips.stream()
                .filter(x -> x.equip_index == equipIndex)
                .findAny()
                .orElse(null);
        if (Objects.isNull(equip)) {
            return StatusCode.INVALID_REQUEST;
        }

        equip.data_id = 0;

        SDB.dbContext.equipPreset.save(equip);

        var l = new ArrayList<TEquipPreset>();
        equips.stream().forEach(x -> l.add(TBuilderOf.buildOf(x)));
        outPresets.setType(type).addAllEquips(l).build();

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public boolean isHavingPreset(ZPreset.Type type, long dataId) {
        switch (type) {
            case PARTS:
            case PET:
            case STONE:
                return Objects.isNull(player.growth.getGrowth(dataId)) ? false : true;
            default:
                return false;
        }
    }

    private void computeStats(HashMap<ZPreset.Type, List<DMPlayerEquipPreset>> models) {
        for (var model : models.entrySet()) {
            var type = model.getKey();
            // 일단 각인은 제외
            if(type == ZPreset.Type.ENCHANT) {
                continue;
            }

            for (var equip : model.getValue()) {
                if (equip.data_id <= 0) {
                    continue;
                }

                var resGrowth = ResourceManager.INSTANCE.growth.get(equip.data_id);
                if (Objects.isNull(resGrowth)) {
                    logger.error("growth not found. playerId({}),  dataId({})", player.getId(), equip.data_id);
                    continue;
                }

                var growth = player.growth.getGrowth(equip.data_id);
                if (Objects.isNull(growth)) {
                    logger.error("player has no growth. playerId({}),  dataId({})", player.getId(), equip.data_id);
                    continue;
                }

                float bonusPercent = 1.0f;
                var partsModel = player.partsSlot.getOrDefault(resGrowth.partsSlotType);
                if(Objects.nonNull(partsModel) && partsModel.level > 0) {
                    var resPartsSlot = ResourceManager.INSTANCE.partsSlot.get(resGrowth.partsSlotType);
                    if(Objects.nonNull(resPartsSlot)) {
                        bonusPercent += ((partsModel.level * resPartsSlot.addPercent) / 100);
                    }

                }

                GrowthRules.calculateStats(resGrowth.equipStats, ZGrowthStatTarget.Type.LEVEL, growth.model.level, bonusPercent, cacheStats);
                GrowthRules.calculateStats(resGrowth.equipStats, ZGrowthStatTarget.Type.PROMOTE, growth.model.promote_level, bonusPercent, cacheStats);
                GrowthRules.calculateStats(resGrowth.equipStats, ZGrowthStatTarget.Type.LIMIT_BREAK, growth.model.limit_break_level, bonusPercent, cacheStats);
            }
        }
    }

    public void clearForCheat() {
        models.clear();

        SDB.dbContext.equipPreset.deleteForCheat(player.getId());

        player.stat.statsNotify();
    }
}
