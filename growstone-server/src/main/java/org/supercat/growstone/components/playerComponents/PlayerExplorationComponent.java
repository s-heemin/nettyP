package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.jsons.JMPlayerExplorationQuests;
import org.supercat.growstone.models.DMPlayerExploration;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.ExplorationRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.*;

public class PlayerExplorationComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerExplorationComponent.class);

    private WorldPlayer player;
    private DMPlayerExploration model;
    private Map<Integer, TExplorationQuest> quests; // slotId, quest


    public void initialize() {
        this.model = SDB.dbContext.exploration.getOrDefault(player.getId());
        if (Objects.isNull(model.quests)) {
            this.quests = initQuests();
        } else {
            this.quests = JMPlayerExplorationQuests.ofTExplorationQuests(model.quests);
        }

        // 탐광 임무 전달
        viewNotify();
    }

    private void viewNotify() {
        player.sendPacket(0, ZViewExplorationNotify.newBuilder()
                .setLevel(getLevel())
                .setExp(getExp())
                .addAllQuest(getQuests())
                .setAuto(isAuto())
        );
    }

    public void save() {
        this.model.quests = JMPlayerExplorationQuests.of(quests).toJson().toString();
        SDB.dbContext.exploration.save(model);
    }

    public PlayerExplorationComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder().on(EventType.PLAYER_DAILY_RESET,
                this::handle_EventPlayerDailyResetSchedule));
    }

    public int getLevel() {
        return model.level;
    }

    public int getExp() {
        return model.exp;
    }

    public boolean isAuto() {
        return model.isAuto();
    }

    public Collection<TExplorationQuest> getQuests() {
        return quests.values();
    }

    private void handle_EventPlayerDailyResetSchedule(EventPlayerDailyResetSchedule event) {
        // 탐광 임무권, 변경권 지급
        recoveryItem(GameData.EXPLORATION.QuestTicketItemID, GameData.EXPLORATION.MaxQuestTicketCount);
        recoveryItem(GameData.EXPLORATION.QuestChangerItemID, GameData.EXPLORATION.MaxQuestChangerCount);
        // 탐광 임무 갱신
        resetQuests();
        // 탐광 임무 전달
        viewNotify();
    }

    private Map<Integer, TExplorationQuest> initQuests() {
        var quests = new HashMap<Integer, TExplorationQuest>();
        for (int slotIndex = 0; slotIndex < GameData.EXPLORATION.MaxQuestCount; ++slotIndex) {
            final int slotId = slotIndex + 1;
            quests.put(slotId, resetQuest(slotId));
        }

        return quests;
    }

    private void resetQuests() {
        var newQuests = new HashMap<Integer, TExplorationQuest>();
        for (int slotIndex = 0; slotIndex < GameData.EXPLORATION.MaxQuestCount; ++slotIndex) {
            var quest = quests.get(slotIndex + 1);
            // 임무 진행중인 펫이 있다면 진행중이므로 초기화 되지 않음
            if (Objects.nonNull(quest) && quest.getPetIdCount() > 0) {
                newQuests.put(quest.getSlotId(), quest);
                continue;
            }

            final int slotId = slotIndex + 1;
            newQuests.put(slotId, resetQuest(slotId));
        }

        quests = newQuests;
    }

    private TExplorationQuest resetQuest(int slotId) {
        var resLevel = ResourceManager.INSTANCE.exploration.getLevel(getLevel());
        var randomTier = ExplorationRules.randomTier(resLevel);
        var resTier = ResourceManager.INSTANCE.exploration.getTier(randomTier);

        return TExplorationQuest.newBuilder()
                .setSlotId(slotId)
                .setTier(randomTier)
                .addAllNeedPetTier(ExplorationRules.randomNeedPetTiers())
                .setReward(ExplorationRules.randomReward(resTier, getLevel()))
                .setElapsedTimeUnit(ExplorationRules.randomElapsedTimeUnit(resTier))
                .setEarnExp(resTier.earnExp)
                .build();
    }

    private void recoveryItem(int itemDataId, int maxCount) {
        long recoveryCount = Math.max(0, maxCount - player.itemBag.getItemCount(itemDataId));
        if (recoveryCount > 0) {
            player.categoryFilter.add(ZCategory.Type.ITEM, itemDataId, recoveryCount);
        }
    }

    public int start(int slotId, int createdYMD, List<Long> petIdList, ZExplorationStartResponse.Builder response) {
        if (petIdList.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }
        // 임무가 없거나
        var quest = quests.get(slotId);
        if (Objects.isNull(quest)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 진행중이면 실패
        if (quest.getPetIdCount() > 0) {
            return StatusCode.INVALID_REQUEST;
        }
        // 같은 펫이 있으면 에러
        if (ExplorationRules.hasSamePet(petIdList)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 보너스 펫 개수가 편성하는 개수와 동일 해야 한다.
        if (quest.getNeedPetTierCount() != petIdList.size()) {
            return StatusCode.INVALID_REQUEST;
        }
        // 임무 시작 시간 확인
        if (quest.getCreatedYmd() != createdYMD) {
            return StatusCode.EXPLORATION_QUEST_RESET;
        }
        // 보유한 펫 인지 확인
        for (var petId : petIdList) {
            var pet = player.growth.getGrowth(petId);
            if (Objects.isNull(pet)) {
                return StatusCode.INVALID_REQUEST;
            }
        }
        // 탐광 임무권 소모
        int status = player.itemFilter.useItem(GameData.EXPLORATION.QuestTicketItemID, 1);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 펫 넣어주면서 임무 완료 시간 넣어주기
        var tempQuest = quest.toBuilder()
                .addAllPetId(petIdList)
                .setUntilAt(ExplorationRules.computeStartUntilAt(quest.getElapsedTimeUnit()))
                .build();

        // 서버 메모리에 저장
        quests.put(slotId, tempQuest);
        // 클라이언트 전달
        response.setQuest(tempQuest);

        return StatusCode.SUCCESS;
    }

    public int end(int slotId, Instant at, ZExplorationEndResponse.Builder response) {
        // 임무가 없거나
        var quest = quests.get(slotId);
        if (Objects.isNull(quest)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 진행중이 아니면 실패
        if (quest.getPetIdCount() <= 0) {
            return StatusCode.INVALID_REQUEST;
        }
        // 아직 완료 시간이 안될 경우 실패
        if (quest.getUntilAt() > at.toEpochMilli()) {
            return StatusCode.INVALID_REQUEST;
        }

        final long rewardCount = ExplorationRules.computeAchiveRewardCount(
                quest.getNeedPetTierList(), quest.getPetIdList(), quest.getReward().getCount());

        // 보상 지급
        player.categoryFilter.add(quest.getReward().getCategory(), quest.getReward().getDataId(), rewardCount);
        // 탐광 임무 경험치 보상
        addExp(quest.getEarnExp());
        // 새로운 탐광 임무 발급
        var newQuest = resetQuest(slotId);
        // 서버 메모리 갱신
        quests.put(slotId, newQuest);
        // 클라이언트 전달
        response.setLevel(getLevel())
                .setExp(getExp())
                .setQuest(newQuest)
                .setReward(TContentReward.newBuilder()
                        .setCategory(quest.getReward().getCategory())
                        .setDataId(quest.getReward().getDataId())
                        .setCount(quest.getReward().getCount()));

        return StatusCode.SUCCESS;
    }

    public int change(int slotId, ZExplorationChangeResponse.Builder response) {
        // 임무가 없거나
        var quest = quests.get(slotId);
        if (Objects.isNull(quest)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 진행중이면 실패
        if (quest.getPetIdCount() > 0) {
            return StatusCode.INVALID_REQUEST;
        }
        // 탐광 임무 변경권 소모
        int status = player.itemFilter.useItem(GameData.EXPLORATION.QuestChangerItemID, 1);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }
        // 새로운 탐광 임무 발급
        var newQuest = resetQuest(slotId);
        // 서버 메모리에 저장
        quests.put(slotId, newQuest);
        // 클라이언트 전달
        response.setQuest(newQuest);

        return StatusCode.SUCCESS;
    }

    public int viewCommercial(ZViewExplorationCommercialResponse.Builder response) {
        // 클라이언트 전달
        response.setViewCount(player.advertise.getViewCommercial(ZContentAdvertise.Type.EXPLORATION));

        return StatusCode.SUCCESS;
    }

    public int advertise(int slotId, int viewCount, ZExplorationCommercialResponse.Builder response) {
        // 임무가 없거나
        var quest = quests.get(slotId);
        if (Objects.isNull(quest)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 진행중이 아니면 실패
        if (quest.getPetIdCount() <= 0) {
            return StatusCode.INVALID_REQUEST;
        }
        // 반복 호출 방지
        int currentViewCount = player.advertise.getViewCommercial(ZContentAdvertise.Type.EXPLORATION);
        if (currentViewCount != viewCount) {
            return StatusCode.INVALID_REQUEST;
        }
        // 남은 광고 시청 횟수 확인
        if (currentViewCount >= GameData.EXPLORATION.MaxCommercialViewCount) {
            return StatusCode.INVALID_REQUEST;
        }

        player.advertise.addViewCommercial(ZContentAdvertise.Type.EXPLORATION);

        // 광고 시청 효과로 시간 감속
        long tempUntilAt = ExplorationRules.computeCommercialUntilAt(quest.getUntilAt());
        // 임무 완료 시간 넣어주기
        var tempQuest = quest.toBuilder()
                .setUntilAt(tempUntilAt)
                .build();
        // 서버 메모리에 저장
        quests.put(slotId, tempQuest);
        // 클라이언트 전달
        response.setQuest(tempQuest)
                .setViewCount(++currentViewCount);

        return StatusCode.SUCCESS;
    }

    public int acceleration(int slotId, int count, ZExplorationAccelerationResponse.Builder response) {
        // 임무가 없거나
        var quest = quests.get(slotId);
        if (Objects.isNull(quest)) {
            return StatusCode.INVALID_REQUEST;
        }
        // 진행중이 아니면 실패
        if (quest.getPetIdCount() <= 0) {
            return StatusCode.INVALID_REQUEST;
        }
        // 가속권 소모
        int status = player.itemFilter.useItem(GameData.EXPLORATION.AcceleratorItemID, count);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }
        //가속 효과로 시간 감속
        long tempUntilAt = ExplorationRules.computeAcceleratorUntilAt(quest.getUntilAt(), count);
        // 임무 완료 시간 넣어주기
        var tempQuest = quest.toBuilder()
                .setUntilAt(tempUntilAt)
                .build();
        // 서버 메모리에 저장
        quests.put(slotId, tempQuest);
        // 클라이언트 전달
        response.setQuest(tempQuest);

        return StatusCode.SUCCESS;
    }

    public int auto(boolean auto, ZExplorationEditAutoResponse.Builder response) {
        if (model.isAuto() == auto) {
            return StatusCode.INVALID_REQUEST;
        }
        // 서버 메모리에 저장
        model.setAuto(auto);
        // 클라이언트 전달
        response.setAuto(auto);

        return StatusCode.SUCCESS;
    }

    private void addExp(int exp) {
        // 최대 레벨 확인
        if (getLevel() >= ResourceManager.INSTANCE.exploration.getMaxLevel()) {
            return;
        }
        // 경험치 더하고
        model.exp += exp;
        do { // 레벨업 확인
            var resLevel = ResourceManager.INSTANCE.exploration.getLevel(getLevel());
            if (resLevel.nextLevelExp <= model.exp) {
                model.exp -= resLevel.nextLevelExp;
                model.level++;
                // 최대 레벨 확인
                if (getLevel() == ResourceManager.INSTANCE.exploration.getMaxLevel()) {
                    model.exp = 0;
                    break;
                }
            }
        } while (model.exp >= ResourceManager.INSTANCE.exploration.getLevel(model.level).nextLevelExp);
    }
}

