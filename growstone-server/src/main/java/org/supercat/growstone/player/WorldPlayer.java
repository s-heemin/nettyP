package org.supercat.growstone.player;

import com.supercat.growstone.PacketUtils;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.components.*;
import org.supercat.growstone.components.growths.PlayerGrowthComponent;
import org.supercat.growstone.components.growths.PlayerPartsSlotComponent;
import org.supercat.growstone.components.instanceDungeons.PlayerDailyDungeonComponent;
import org.supercat.growstone.components.instanceDungeons.PlayerRaidDungeonComponent;
import org.supercat.growstone.components.instanceDungeons.PlayerTowerComponent;
import org.supercat.growstone.components.playerComponents.*;
import org.supercat.growstone.components.playerEventComponents.PlayerDailyContentComponent;
import org.supercat.growstone.components.playerEventComponents.PlayerEventComponent;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMGlobalMasterPlayer;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.rules.NameRules;
import org.supercat.growstone.setups.SDB;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class WorldPlayer {
    private static final Logger logger = LoggerFactory.getLogger(WorldPlayer.class);
    private final AtomicBoolean isLoggedOut = new AtomicBoolean(false);

    private WorldSession session;
    private DMPlayer model;
    private DMGlobalMasterPlayer globalModel;
    public EventTopic topic;
    private long lastSaveAt;
    public PlayerGMComponent cheat;

    public PlayerCategoryFilterComponent categoryFilter;
    public PlayerItemFilterComponent itemFilter;

    public PlayerAvatarComponent avatar;
    public PlayerCurrencyComponent currency;
    public PlayerItemBagComponent itemBag;
    public PlayerGrowthComponent growth;
    public PlayerMailComponent mail;
    public PlayerCollectionComponent collection;
    public PlayerPartsSlotComponent partsSlot;
    public PlayerStatComponent stat;
    public PlayerPortraitIconComponent portraitIcon;
    public PlayerStatGrowthComponent statGrowth;
    public PlayerEquipPresetComponent preset;
    public PlayerDailyDungeonComponent dailyDungeon;
    public PlayerRaidDungeonComponent raidDungeon;
    public PlayerTowerComponent tower;
    public PlayerFriendComponent friend;
    public PlayerScheduleTask scheduleTask;
    public PlayerExplorationComponent exploration;
    public PlayerFarmComponent farm;
    public PlayerAdvertiseComponent advertise;
    public PlayerDiggingComponent digging;
    public PlayerShopComponent shop;
    public PlayerConditionTriggerComponent conditionTrigger;
    public PlayerConditionPackageComponent conditionPackage;
    public PlayerShopPassComponent shopPass;
    public PlayerEventComponent event;
    public PlayerDailyContentComponent dailyContent;
    public PlayerAchievementComponent achievement;
    public PlayerQuestComponent quest;
    public PlayerBlockComponent block;
    public PlayerStoneStatueComponent stoneStatue;
    public PlayerStoneStatueEnchantComponent stoneStatueEnchant;
    public PlayerStoneStatueGemComponent stoneStatueGem;
    public PlayerStoneStatueAvatarComponent stoneStatueAvatar;
    public PlayerClanComponent clan;

    private long lastPingAt;
    public WorldPlayer(WorldSession gameSession, DMGlobalMasterPlayer globalModel, DMPlayer model) {
        this.session = gameSession;
        this.model = model;
        this.globalModel = globalModel;
        this.categoryFilter = new PlayerCategoryFilterComponent(this);
        this.currency = new PlayerCurrencyComponent(this);
        this.topic = new EventTopic(new EventDispatcher());
        this.cheat = new PlayerGMComponent(this);

        this.avatar = new PlayerAvatarComponent(this);
        this.itemFilter = new PlayerItemFilterComponent(this);
        this.itemBag = new PlayerItemBagComponent(this);
        this.growth = new PlayerGrowthComponent(this);
        this.mail = new PlayerMailComponent(this);
        this.collection = new PlayerCollectionComponent(this);
        this.partsSlot = new PlayerPartsSlotComponent(this);
        this.portraitIcon = new PlayerPortraitIconComponent(this);
        this.statGrowth = new PlayerStatGrowthComponent(this);
        this.preset = new PlayerEquipPresetComponent(this);
        this.dailyDungeon = new PlayerDailyDungeonComponent(this);
        this.raidDungeon = new PlayerRaidDungeonComponent(this);
        this.tower = new PlayerTowerComponent(this);
        this.friend = new PlayerFriendComponent(this);
        this.scheduleTask = new PlayerScheduleTask(this);
        this.exploration = new PlayerExplorationComponent(this);
        this.farm = new PlayerFarmComponent(this);
        this.advertise = new PlayerAdvertiseComponent(this);
        this.digging = new PlayerDiggingComponent(this);
        this.shop = new PlayerShopComponent(this);
        this.conditionPackage = new PlayerConditionPackageComponent(this);
        this.conditionTrigger = new PlayerConditionTriggerComponent(this);
        this.shopPass = new PlayerShopPassComponent(this);
        this.event = new PlayerEventComponent(this);
        this.dailyContent = new PlayerDailyContentComponent(this);
        this.achievement = new PlayerAchievementComponent(this);
        this.quest = new PlayerQuestComponent(this);
        this.block = new PlayerBlockComponent(this);
        this.stoneStatue = new PlayerStoneStatueComponent(this);
        this.stoneStatueEnchant = new PlayerStoneStatueEnchantComponent(this);
        this.stoneStatueGem = new PlayerStoneStatueGemComponent(this);
        this.stoneStatueAvatar = new PlayerStoneStatueAvatarComponent(this);
        this.clan = new PlayerClanComponent(this);

        // 스탯은 맨 마지막에 계산되어야 한다.
        this.stat = new PlayerStatComponent(this);
        World.INSTANCE.worldPlayers.insertWorldPlayer(this);

        initialize();

        topic.publish(new EventPlayerLogin());
        topic.publish(new EventPlayerAttendance());
        topic.publish(new EventPlayerTimeBasedScheduledTask(UtcZoneDateTime.getYmd()));
    }

    private void initialize() {
        currency.initialize();
        avatar.initialize();
        itemBag.initialize();
        growth.initialize();
        mail.initialize();
        collection.initialize();
        partsSlot.initialize();
        portraitIcon.initialize();
        statGrowth.initialize();
        preset.initialize();
        dailyDungeon.initialize();
        raidDungeon.initialize();
        tower.initialize();
        friend.initialize();
        exploration.initialize();
        farm.initialize();
        conditionPackage.initialize();
        event.initialize();
        achievement.initialize();
        quest.initialize();
        block.initialize();

        stoneStatue.initialize();
        stoneStatueEnchant.initialize();
        stoneStatueGem.initialize();
        stoneStatueAvatar.initialize();
    }
    public void update() {
        // 1/60초마다 업데이트 돌게 있으면 여기로.
        // 이벤트 토픽
        var nowEpoch = Instant.now().getEpochSecond();
        if(nowEpoch - lastSaveAt > Constants.LAST_SAVE_INTERVAL) {
            intervalSave();
            lastSaveAt = nowEpoch;
        }
        topic.getEventDispatcher().update();

        // 핑 체크
        if (lastPingAt > 0 && nowEpoch - lastPingAt > Constants.PING_TIME_OUT_MINUTE * 60) {
            logout(true);
        }
    }

    public void setLastPingAt() {
        this.lastPingAt = Instant.now().getEpochSecond();
    }

    public String getFriendCode() {
        return model.friend_code;
    }
    public long getId() {
        return model.id;
    }

    public long getPortraitIcon() { return model.portrait_id;}

    public long getChannelId() {
        return model.channel_id;
    }

    public boolean isJoinClan() {
        return model.clan_id > 0;
    }
    public long getClanId() { return model.clan_id;}
    public void setClanId(long clanId) { model.clan_id = clanId;}
    public DMPlayer getModel() { return model;}
    public int getLevel() { return model.level;}
    public String getName() { return model.name;}
    public long getAttackPower() { return model.attack_power;}

    public void setAttackPower(long attackPower) {
        model.attack_power = attackPower;
    }

    public void activeRemoveAdvertise() {
        model.remove_ad = true;
        SDB.dbContext.player.updateRemoveAd(model);
    }
    public WorldSession getSession() {
        return session;
    }

    public int getPresetIndex() {
        return model.preset_index;
    }
    public void changePresetIndex(int index) {
        model.preset_index = index;
        SDB.dbContext.player.updatePresetIndex(model);
    }

    public void changePortrait(long portraitId) {
        model.portrait_id = portraitId;
        SDB.dbContext.player.updatePortrait(model);
    }
    public void login(long packetId) {
        // 레디스 세션 저장
        SRedis.INSTANCE.content.session.addSession(model.global_master_player_id, session.getSessionId());
        SRedis.INSTANCE.content.players.addPlayerId(model.id);
        var result = ZLoginResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .addAllItems(itemBag.getTItems())
                .addAllAvatars(avatar.getTAvatars())
                .addAllGrowths(growth.getTGrowths())
                .addAllPortraitIcons(portraitIcon.getTPortraitIcon())
                .addAllStats(stat.getTStats())
                .setCurrencies(currency.getTCurrencies())
                .addAllPresets(preset.getTPresets())
                .setGuestTokenId(globalModel.guest_token_id)
                .setPlayer(TBuilderOf.buildOf(model));

        session.sendPacket(PacketUtils.packet(packetId, result));
    }

    public void logout(boolean isForce) {
        if (isLoggedOut.compareAndSet(false, true)) {
            if (isForce) {
                session.sendPacket(0, ZLogoutResponse.newBuilder()
                        .setStatus(StatusCode.SUCCESS)
                        .setType(ZLogout.Type.SESSION_TIMEOUT));
            }
            // 세션 종료
            if (Objects.nonNull(session)) {
                session.close();
                session = null;
            }

            // 월드 플레이어에서 삭제
            World.INSTANCE.worldPlayers.removeWorldPlayer(getId());

            // 레디스 세션 삭제
            SRedis.INSTANCE.content.session.deleteSession(model.global_master_player_id);

            // 마스터라면 클랜에 저장
            clan.saveLogoutAt();

            model.last_disconnected_at = Instant.now();

            SDB.dbContext.player.save(model);
            // 컴포넌트 저장
            save();
        }
    }

    public int changeName(String name, ZChangePlayerNameResponse.Builder builder) {
        if(name == model.name) {
            return StatusCode.CHANGE_FAIL_TO_SAME_NAME;
        }

        var now = Instant.now();
        int status = NameRules.reviewName(name);
        if(!StatusCode.isSuccess(status)) {
            logger.error("name update failed. playerId({}), name({})", model.id, name);
            return status;
        }

        if(now.isBefore(model.last_change_name_at.plus(Duration.ofHours(24)))) {
            builder.setChangeRemainTime(Math.max(0, model.last_change_name_at.plus(Duration.ofHours(24)).getEpochSecond() - now.getEpochSecond()));
            return StatusCode.NOT_ENOUGH_CHANGE_TIME;
        }

        status = SDB.dbContext.player.updateName(model.id, name);
        if(!StatusCode.isSuccess(status)) {
            logger.error("name db update failed. playerId({}), name({})", model.id, name);
            return status;
        }

        model.name = name;
        model.last_change_name_at = now;

        SDB.dbContext.player.save(model);

        return StatusCode.SUCCESS;
    }

    public int saveOnBossGauge() {
        model.on_boss_gauge = true;
        SDB.dbContext.player.onBossGauge(model);
        return StatusCode.SUCCESS;
    }
    public int saveStage(long stageGroupId, int stageId, TNextStageInfo.Builder nextStageInfo) {
        var resStageGroup = ResourceManager.INSTANCE.stageGroup.get(stageGroupId);
        if (Objects.isNull(resStageGroup)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var resStage = resStageGroup.stages.get(stageId);
        if (Objects.isNull(resStage)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if (resStage.isLastStage) {
            if (resStageGroup.nextStageGroupId != 0) {
                model.stage_group = resStageGroup.nextStageGroupId;
                model.stage = 1;
            }
        } else  {
            model.stage = ++stageId;
        }

        for(var reward : resStage.clearRewards) {
            categoryFilter.add(reward, 1);
        }

        model.on_boss_gauge = false;
        SDB.dbContext.player.updateStage(model);

        nextStageInfo
                .setStage(model.stage)
                .setStageGroup(model.stage_group)
                .build();

        topic.publish(new EventPlayerClearStage(stageGroupId, stageId));

        return StatusCode.SUCCESS;
    }

    public void levelUp(long count) {
        // 경험치 아이템 1개당 exp 1을 올려준다.
        // 바뀔 여지가 있을 것 같아 일단 1을 곱해주는걸로.
        long addExp = count * Constants.ADD_EXP_PER_EXP_ITEM;
        boolean isLevelUp = false;

        while(addExp > 0) {
            var needExp = GameData.LEVEL.getNeedExp(model.level);
            if(needExp <= 0) {
                model.exp += addExp;
                return;
            }

            long diff = Math.max(0, needExp - model.exp);
            if(addExp < diff) {
                model.exp += addExp;
                break;
            }

            // 레벨업에 필요한 경험치가 초과했을 경우
            addExp -= diff;
            ++model.level;
            model.exp = 0;
            isLevelUp = true;
        }

        SDB.dbContext.player.updateLevel(model);

        if(isLevelUp) {
            sendPacket(0, ZPlayerLevelUpNotify.newBuilder()
                    .setLevel(model.level)
                    .setExp(model.exp));
        }
    }

    private void save() {
        itemBag.save();
        growth.save();
        partsSlot.save();
        exploration.save();
        stat.saveStats();
        farm.save();
        achievement.save();
    }

    private void intervalSave() {
        SDB.dbContext.player.save(model);
        // 전투력 저장
        SRedis.INSTANCE.content.playerAttackPower.addTotalPower(model.id, model.level,
                model.attack_power,
                avatar.getEquipAvatarId(),
                model.portrait_id,
                model.name);
    }

    public void intervalSaveForTest() {
        intervalSave();
    }
    public <Type extends com.google.protobuf.GeneratedMessageV3>
    void sendPacket(long id, Type.Builder contents) {
        sendPacket(PacketUtils.packet(id, contents));
    }

    private void sendPacket(Packet packet) {
        if(Objects.isNull(session)) {
            return;
        }

        session.sendPacket(packet);
    }
}
