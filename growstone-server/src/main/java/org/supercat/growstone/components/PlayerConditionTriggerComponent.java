package org.supercat.growstone.components;

import com.supercat.growstone.network.messages.ZCondition;
import com.supercat.growstone.network.messages.ZPlayerConditionPackageNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayerConditionPackage;
import org.supercat.growstone.player.WorldPlayer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerConditionTriggerComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerConditionTriggerComponent.class);

    private WorldPlayer player;

    public PlayerConditionTriggerComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.CLEAR_STAGE,this::handle_EventPlayerClearStage)
                .on(EventType.CLEAR_DUNGEON_STEP,this::handle_EventClearDungeonStep)
                .on(EventType.LEVEL_UP,this::handle_EventPlayerLevelUp)
                .on(EventType.GET_GROWTH,this::handle_EventPlayerGetGrowth)
                .on(EventType.PROMOTE_GROWTH,this::handle_EventPlayerPromoteGrowth)
                .on(EventType.LIMIT_BREAK_GROWTH,this::handle_EventPlayerLimitBreakGrowth));
    }
    private void handle_EventPlayerClearStage(EventPlayerClearStage event) {
        var resPackages = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.CLEAR_STAGE);
        if(!resPackages.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resPackage : resPackages) {
                if(resPackage.completeCondition.param1 != event.groupId ||
                        resPackage.completeCondition.param2 != event.stageId) {
                    continue;
                }

                var model = player.conditionPackage.add(resPackage.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }

            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }

        var resShopPasses = ResourceManager.INSTANCE.shopPass.getAllByType(ZCondition.Type.CLEAR_STAGE);
        if(!resShopPasses.isEmpty()) {
            for (var resShopPass : resShopPasses) {
                var model = player.shopPass.getOrCreate(resShopPass.id);
                var step = resShopPass.steps.get(model.open_step + 1);
                if(Objects.isNull(step)) {
                    continue;
                }

                if(step.param1 != event.groupId || step.param2 != event.stageId) {
                    continue;
                }

                player.shopPass.setOpenStep(resShopPass.id, step.step);
            }
        }
    }

    private void handle_EventPlayerLevelUp(EventPlayerLevelUp event) {
        var resConditions = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.LEVEL_UP);
        if(!resConditions.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resCondition : resConditions) {
                if(resCondition.completeCondition.param1 != event.level) {
                    continue;
                }

                var model = player.conditionPackage.add(resCondition.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }

            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }

        var resShopPasses = ResourceManager.INSTANCE.shopPass.getAllByType(ZCondition.Type.CLEAR_STAGE);
        if(!resShopPasses.isEmpty()) {
            for (var resShopPass : resShopPasses) {
                var model = player.shopPass.getOrCreate(resShopPass.id);
                var step = resShopPass.steps.get(model.open_step + 1);
                if(Objects.isNull(step)) {
                    continue;
                }

                if(step.param1 != event.level) {
                    continue;
                }

                player.shopPass.setOpenStep(resShopPass.id, step.step);
            }
        }
    }

    private void handle_EventClearDungeonStep(EventPlayerClearDungeonStep event) {
        var resPackages = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.CLEAR_DUNGEON);
        if(!resPackages.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resPackage : resPackages) {
                if(resPackage.completeCondition.param1 != event.dungeonId) {
                    continue;
                }

                var model = player.conditionPackage.add(resPackage.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }

            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }
    }

    private void handle_EventPlayerGetGrowth(EventPlayerGetGrowth event) {
        var resConditions = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.GET_GROWTH);
        if(!resConditions.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resCondition : resConditions) {
                if(resCondition.completeCondition.param1 != event.growthId) {
                    continue;
                }

                var model = player.conditionPackage.add(resCondition.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }

            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }
    }

    private void handle_EventPlayerPromoteGrowth(EventPlayerPromoteGrowth event) {
        var resConditions = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.GROWTH_PROMOTE);
        if(!resConditions.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resCondition : resConditions) {
                if(resCondition.completeCondition.param1 != event.growthId) {
                    continue;
                }

                var model = player.conditionPackage.add(resCondition.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }

            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }
    }

    private void handle_EventPlayerLimitBreakGrowth(EventPlayerLimitBreakGrowth event) {
        var resConditions = ResourceManager.INSTANCE.conditionPackage.getAllByType(ZCondition.Type.GROWTH_LIMIT_BREAK);
        if(!resConditions.isEmpty()) {
            var models = new ArrayList<DMPlayerConditionPackage>();
            for(var resCondition : resConditions) {
                if(resCondition.completeCondition.param1 != event.growthId) {
                    continue;
                }

                var model = player.conditionPackage.add(resCondition.id);
                if(Objects.nonNull(model)) {
                    models.add(model);
                }
            }
            player.sendPacket(0L, ZPlayerConditionPackageNotify.newBuilder()
                    .addAllConditionPackage(models.stream()
                            .map(x -> TBuilderOf.buildOf(x))
                            .collect(Collectors.toList())));
        }
    }
}
