package org.supercat.growstone.dbContexts;

import org.supercat.growstone.dbsets.*;
import org.supercat.growstone.setups.DBSqlExcutor;
import org.supercat.growstone.setups.DBSqlExecutorBuilder;

public class DBContext {
    public DBSqlExcutor executor;
    public BannedNameDBSet bannedName;

    public GlobalMasterPlayerDBSet globalMasterPlayer;
    public PlayerDBSet player;
    public PlayerCurrencyDBSet currency;
    public PlayerItemDBSet item;
    public PlayerGrowthDBSet growth;
    public PlayerAvatarDBSet avatar;
    public PlayerMailDBSet mail;
    public PlayerCollectionDBSet collection;
    public PlayerPartsSlotDBSet partsSlot;
    public PlayerPortraitIconDBSet portraitIcon;
    public PlayerStatGrowthDBSet statGrowth;
    public PlayerScheduleTaskDBSet scheduleTask;
    public PlayerEquipPresetDBSet equipPreset;
    public PlayerEquipPresetNameDBSet equipPresetName;
    public PlayerDailyDungeonDBSet dailyDungeon;
    public PlayerTowerDBSet tower;
    public PlayerRaidDungeonDBSet raidDungeon;
    public PlayerFriendDBSet friend;
    public PlayerExplorationDBSet exploration;
    public PlayerFarmDBSet farm;
    public PlayerFarmPlantDBSet farmPlant;
    public PlayerFarmTheftLimitDBSet farmTheftLimit;
    public PlayerFarmHistoryDBSet farmHistory;
    public PlayerFarmBattleDBSet farmBattle;
    public PlayerFarmCookDBSet farmCook;
    public PlayerAdvertiseDBSet advertise;
    public PlayerDiggingDBSet digging;
    public PlayerDiggingUpgradeDBSet diggingUpgrade;
    public PlayerGachaDBSet gacha;
    public PlayerPickUpGachaDBSet pickUpGacha;
    public PlayerContinueShopDBSet continueShop;
    public PlayerConditionPackageDBSet conditionPackage;
    public PlayerBuyShopItemDBSet buyShopItem;
    public PlayerShopPassDBSet shopPass;
    public PlayerEventDBSet event;
    public PlayerDailyContentDBSet dailyContent;
    public PlayerAchievementDBSet achievement;
    public PlayerQuestDBSet quest;
    public ClanDBSet clan;
    public ClanMemberDBSet clanMember;
    public PlayerClanJoinRequestDBSet clanJoinRequest;

    public WorldScheduleTaskDBSet worldScheduleTask;
    public WorldEventDBSet worldEvent;

    public PlayerStoneStatueDBSet playerStoneStatue;
    public PlayerStoneStatueEnchantDBSet playerStoneStatueEnchant;
    public PlayerStoneStatueGemDBSet playerStoneStatueGem;
    public PlayerStoneStatueAvatarDBSet playerStoneStatueAvatar;

    public WorldChatDBSet worldChat;
    public WorldClanChatDBSet worldClanChat;
    public WorldWhisperDBSet worldWhisper;

    public PlayerBlockPlayerDBSet block;

    public ServerDBSet server;
    public ServerRedisConfigDBSet serverRedisConfig;

    public DBContext(DBSqlExcutor executor) {
        this.executor = executor;

        init();
    }

    public static DBContext of(String host, String userName, String password) {
        var sqlExecutor = DBSqlExecutorBuilder.newBuilder()
                .withHost(host)
                .withUserName(userName)
                .withPassword(password)
                .withAsyncExecutor(org.supercat.growstone.Async::logDBAsync)
                .executeBuild();
        return new DBContext(sqlExecutor);
    }

    public void init() {
        bannedName = new BannedNameDBSet(executor);
        globalMasterPlayer = new GlobalMasterPlayerDBSet(executor);
        player = new PlayerDBSet(executor);
        currency = new PlayerCurrencyDBSet(executor);
        item = new PlayerItemDBSet(executor);
        growth = new PlayerGrowthDBSet(executor);
        avatar = new PlayerAvatarDBSet(executor);
        mail = new PlayerMailDBSet(executor);
        collection = new PlayerCollectionDBSet(executor);
        partsSlot = new PlayerPartsSlotDBSet(executor);
        portraitIcon = new PlayerPortraitIconDBSet(executor);
        statGrowth = new PlayerStatGrowthDBSet(executor);
        scheduleTask = new PlayerScheduleTaskDBSet(executor);
        equipPreset = new PlayerEquipPresetDBSet(executor);
        equipPresetName = new PlayerEquipPresetNameDBSet(executor);
        dailyDungeon = new PlayerDailyDungeonDBSet(executor);
        tower = new PlayerTowerDBSet(executor);
        raidDungeon = new PlayerRaidDungeonDBSet(executor);
        friend = new PlayerFriendDBSet(executor);
        exploration = new PlayerExplorationDBSet(executor);
        farm = new PlayerFarmDBSet(executor);
        farmPlant = new PlayerFarmPlantDBSet(executor);
        farmTheftLimit = new PlayerFarmTheftLimitDBSet(executor);
        farmHistory = new PlayerFarmHistoryDBSet(executor);
        farmBattle = new PlayerFarmBattleDBSet(executor);
        farmCook = new PlayerFarmCookDBSet(executor);
        advertise = new PlayerAdvertiseDBSet(executor);
        digging = new PlayerDiggingDBSet(executor);
        diggingUpgrade = new PlayerDiggingUpgradeDBSet(executor);
        event = new PlayerEventDBSet(executor);
        gacha = new PlayerGachaDBSet(executor);
        pickUpGacha = new PlayerPickUpGachaDBSet(executor);
        continueShop = new PlayerContinueShopDBSet(executor);
        conditionPackage = new PlayerConditionPackageDBSet(executor);
        buyShopItem = new PlayerBuyShopItemDBSet(executor);
        shopPass = new PlayerShopPassDBSet(executor);
        dailyContent = new PlayerDailyContentDBSet(executor);
        achievement = new PlayerAchievementDBSet(executor);
        quest = new PlayerQuestDBSet(executor);
        worldEvent = new WorldEventDBSet(executor);
        worldScheduleTask = new WorldScheduleTaskDBSet(executor);
        clanMember = new ClanMemberDBSet(executor);
        clan = new ClanDBSet(executor);
        clanJoinRequest = new PlayerClanJoinRequestDBSet(executor);

        playerStoneStatue = new PlayerStoneStatueDBSet(executor);
        playerStoneStatueEnchant = new PlayerStoneStatueEnchantDBSet(executor);
        playerStoneStatueGem = new PlayerStoneStatueGemDBSet(executor);
        playerStoneStatueAvatar = new PlayerStoneStatueAvatarDBSet(executor);
        worldChat = new WorldChatDBSet(executor);
        worldClanChat = new WorldClanChatDBSet(executor);
        worldWhisper = new WorldWhisperDBSet(executor);
        block = new PlayerBlockPlayerDBSet(executor);

        server = new ServerDBSet(executor);
        serverRedisConfig = new ServerRedisConfigDBSet(executor);
    }
}
