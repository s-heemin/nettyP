package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayerQuest;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.StatGrowthRules;
import org.supercat.growstone.setups.SDB;

import java.util.List;
import java.util.Objects;

public class PlayerQuestComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerQuestComponent.class);
    private WorldPlayer player;
    private DMPlayerQuest model;

    public PlayerQuestComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.CLEAR_STAGE, this::handle_EventPlayerClearStage)
                .on(EventType.PLAYER_PLAY_GACHA, this::handle_EventPlayerPlayGacha)
                .on(EventType.PLAYER_STAT_GROWTH_LEVEL_UP, this::handle_EventPlayerStatGrowthLevelUp)
                .on(EventType.PLAYER_UPGRADE_PARTS_SLOT, this::handle_EventPlayerUpgradePartsSlot));
    }

    public void initialize() {
        model = SDB.dbContext.quest.getByPlayerId(player.getId());
        if (Objects.isNull(model)) {
            model = DMPlayerQuest.of(player.getId(), ZClear.State.DOING.getNumber());
        }
    }
    public TQuest getTQuest() {
        return TBuilderOf.buildOf(model);
    }

    private void saveNextStep() {
        int lastStep = ResourceManager.INSTANCE.quest.questSize();
        model.step = model.step == lastStep ? 1 : model.step + 1;
        model.state = ZClear.State.DOING.getNumber();
        model.progress = 0;

        SDB.dbContext.quest.save(model);

        questNotify();
    }

    public int reward(List<TContentReward> outRewards) {
        var resQuest = ResourceManager.INSTANCE.quest.getByStep(model.step);
        if (Objects.isNull(resQuest)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (model.state != ZClear.State.COMPLETE.getNumber()) {
            return StatusCode.INVALID_REQUEST;
        }

        for (var reward : resQuest.rewards) {
            int status = player.categoryFilter.add(reward, 1);
            if (!StatusCode.isSuccess(status)) {
                logger.error("player get quest complete reward fail - playerId({}), questStep({}), dataId({}), count({})"
                        , player.getId(), model.step, reward.rewardId, reward.count);
                continue;
            }

            outRewards.add(TContentReward.newBuilder()
                    .setCategory(reward.type)
                    .setDataId(reward.rewardId)
                    .setCount(reward.count)
                    .build());
        }

        saveNextStep();

        return StatusCode.SUCCESS;
    }

    private void handle_EventPlayerClearStage(EventPlayerClearStage event) {
        if(model.state != ZClear.State.DOING.getNumber()) {
            return;
        }

        var resQuest = ResourceManager.INSTANCE.quest.getByStep(model.step);
        if (Objects.isNull(resQuest)) {
            return;
        }

        if (resQuest.type != ZCondition.Type.CLEAR_STAGE) {
            return;
        }

        if (model.next_stage_group_id_condition != event.groupId || model.next_stage_id_condition != event.stageId) {
            return;
        }

        ++model.progress;
        model.state = ZClear.State.COMPLETE.getNumber();

        // 다음 스테이지 설정
        var resStageGroup = ResourceManager.INSTANCE.stageGroup.get(event.groupId);
        if (Objects.isNull(resStageGroup)) {
            return;
        }

        var resStage = resStageGroup.stages.get(event.stageId + (int)resQuest.condition.param1);
        if (Objects.isNull(resStage)) {
            // 다음 스테이지 그룹으로 넘어가야한다.
            var resNextStageGroup = ResourceManager.INSTANCE.stageGroup.get(event.groupId + 1);
            if (Objects.isNull(resNextStageGroup)) {
                // 다음 스테이지 그룹도 없다면 임의로 다음 스테이지 그룹으로 넘기고, 5번째 스테이지로 넘겨놓는다.
                model.next_stage_group_id_condition += 1;
                model.next_stage_id_condition = 5;
                return;
            }

            // param1 에서 현재스테이지와 마지막스테이지의 차를 빼줘서, 그만큼 다음 스테이지에 넣어준다.
            model.next_stage_id_condition = (int) resQuest.condition.param1 - (resStageGroup.stages.size() - model.next_stage_id_condition);
            model.next_stage_group_id_condition = model.next_stage_group_id_condition + 1;
        } else {
            model.next_stage_id_condition += resQuest.condition.param1;
        }

        SDB.dbContext.quest.save(model);

        questNotify();
    }

    private void handle_EventPlayerPlayGacha(EventPlayerPlayGacha event) {
        if(model.state != ZClear.State.DOING.getNumber()) {
            return;
        }

        var resQuest = ResourceManager.INSTANCE.quest.getByStep(model.step);
        if (Objects.isNull(resQuest)) {
            return;
        }

        if (resQuest.type != ZCondition.Type.PLAY_GACHA || resQuest.condition.param1 != event.levelGroupId) {
            return;
        }

        model.progress += event.count;
        if(model.progress >= resQuest.condition.param2) {
            model.state = ZClear.State.COMPLETE.getNumber();
        }

        SDB.dbContext.quest.save(model);

        questNotify();
    }

    private void handle_EventPlayerStatGrowthLevelUp(EventPlayerStatGrowthLevelUp event) {
        if(model.state != ZClear.State.DOING.getNumber()) {
            return;
        }

        var resQuest = ResourceManager.INSTANCE.quest.getByStep(model.step);
        if (Objects.isNull(resQuest)) {
            return;
        }

        var clearType = StatGrowthRules.getClearType(event.stat);
        if (clearType == ZCondition.Type.NONE) {
            return;
        }

        if (resQuest.type != clearType) {
            return;
        }

        model.progress += event.levelUpCount;
        if(model.progress >= resQuest.condition.param1) {
            model.state = ZClear.State.COMPLETE.getNumber();
        }

        SDB.dbContext.quest.save(model);

        questNotify();
    }

    private void handle_EventPlayerUpgradePartsSlot(EventPlayerUpgradePartsSlot event) {
        if(model.state != ZClear.State.DOING.getNumber()) {
            return;
        }

        var resQuest = ResourceManager.INSTANCE.quest.getByStep(model.step);
        if (Objects.isNull(resQuest)) {
            return;
        }

        if (resQuest.type != ZCondition.Type.PARTS_SLOT) {
            return;
        }

        model.progress += event.levelUpCount;
        if(model.progress >= resQuest.condition.param1) {
            model.state = ZClear.State.COMPLETE.getNumber();
        }

        SDB.dbContext.quest.save(model);

        questNotify();
    }

    private void questNotify() {
        player.sendPacket(0L, ZPlayerQuestNotify.newBuilder()
                .setQuest(getTQuest()));
    }
}
