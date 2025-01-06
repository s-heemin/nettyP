package org.supercat.growstone.components.growths;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.events.EventPlayerStatGrowthLevelUp;
import org.supercat.growstone.events.EventPlayerUpgradePartsSlot;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.containers.ResultContainer;
import org.supercat.growstone.models.DMPlayerPartsSlot;
import org.supercat.growstone.rules.GrowthRules;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.setups.SDB;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerPartsSlotComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerPartsSlotComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<ZPartsSlot.Type, DMPlayerPartsSlot> models = new ConcurrentHashMap<>();
    public PlayerPartsSlotComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var slots = SDB.dbContext.partsSlot.getByPlayerId(player.getId());
        if(slots.isEmpty()) {
            return;
        }

        for(var slot : slots) {
            models.put(NetEnumRules.ofPartsSlot(slot.type), slot);
        }
    }

    public List<TPartsSlot> getTPartsSlots() {
        return models.entrySet().stream()
                .map(x -> TBuilderOf.buildOf(x.getValue()))
                .collect(Collectors.toList());
    }

    public DMPlayerPartsSlot getOrDefault(ZPartsSlot.Type type) {
        return models.getOrDefault(type, DMPlayerPartsSlot.of(player.getId(), type.getNumber()));
    }

    public int partsSlotLevelUp(ZPartsSlot.Type type, int levelUpCount, ResultContainer<TPartsSlot> out) {
        var resPartsSlot = ResourceManager.INSTANCE.partsSlot.get(type);
        if(Objects.isNull(resPartsSlot) || Objects.isNull(resPartsSlot.material)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var needMaterial = resPartsSlot.material;
        var model = getOrDefault(type);
        long needCount = GrowthRules.computePartsSlotLevelMaterialCounts( model.level + levelUpCount, resPartsSlot.material);
        long haveCount = player.itemBag.getItemCount(needMaterial.itemId);
        if(needCount > haveCount) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        int status = player.categoryFilter.use(ZCategory.Type.ITEM, resPartsSlot.material.itemId, needCount);
        if(!StatusCode.isSuccess(status)) {
            return status;
        }

        // 슬롯 레벨업
        model.level += levelUpCount;

        models.put(type, model);

        SDB.dbContext.partsSlot.save(model);

        out.setResultData(TBuilderOf.buildOf(model));

        player.stat.statsNotify();

        player.topic.publish(new EventPlayerUpgradePartsSlot(levelUpCount));

        return StatusCode.SUCCESS;
    }

    public void save() {
        models.values().stream()
                        .forEach(x ->SDB.dbContext.partsSlot.save(x));
    }
}
