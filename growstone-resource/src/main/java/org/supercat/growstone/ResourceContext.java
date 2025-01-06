package org.supercat.growstone;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.events.ResourceEvent;
import org.supercat.growstone.managers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ResourceContext {
    private static final Logger logger = LoggerFactory.getLogger(ResourceContext.class);

    public final String rootPath;
    private final ImmutableMap<Supplier<IResourceManageable>, ImmutableSet<ResourceFile>> loaders;

    public ResourceWordManager name = ResourceWordManager.of(this);
    public ResourceItemManager item;
    public ResourceGrowthManager growth;
    public ResourceCollectionManager collection;
    public ResourcePartsSlotManager partsSlot;
    public ResourceMapManager map;
    public ResourceStageGroupManager stageGroup;
    public ResourceDropTableManager dropTable;
    public ResourceAvatarManager avatar;
    public ResourceStatGrowthManager statGrowth;
    public ResourceDungeonManager dungeon;
    public ResourceExplorationManager exploration;
    public ResourceFarmManager farm;
    public ResourceDiggingManager digging;
    public ResourceGachaManager gacha;
    public ResourceShopManager shop;
    public ResourceConditionPackageManager conditionPackage;
    public ResourceShopPassManager shopPass;
    public ResourceEventManager event;
    public ResourceDailyContentManager dailyContent;
    public ResourceAchievementManager achievement;
    public ResourceQuestManager quest;
    public ResourceStoneStatueEnchantManager stoneStatueEnchant;
    public ResourceStoneStatueGemManager stoneStatueGem;
    public ResourceContext(String rootPath) {
        this.rootPath = Utility.combine(rootPath, ResourcePath.RELATIVE_PATH);
        this.loaders = createLoaders();
    }

    private ImmutableMap<Supplier<IResourceManageable>, ImmutableSet<ResourceFile>> createLoaders() {
        return new ImmutableMap.Builder<Supplier<IResourceManageable>, ImmutableSet<ResourceFile>>()
                .put(this::resetItem, ImmutableSet.of(
                        ResourceFile.ITEMS))
                .put(this::resetGrowth, ImmutableSet.of(
                        ResourceFile.GROWTHS))
                .put(this::resetGrowth, ImmutableSet.of(
                        ResourceFile.GROWTH_UPGRADE))
                .put(this::resetCollection, ImmutableSet.of(
                        ResourceFile.COLLECTION))
                .put(this::resetPartsSlot, ImmutableSet.of(
                        ResourceFile.PARTS_SLOT))
                .put(this::resetMap, ImmutableSet.of(
                        ResourceFile.MAPS))
                .put(this::resetStageGroup, ImmutableSet.of(
                        ResourceFile.STAGE_GROUPS))
                .put(this::resetDropTable, ImmutableSet.of(
                        ResourceFile.DROPS))
                .put(this::resetAvatar, ImmutableSet.of(
                        ResourceFile.AVATARS))
                .put(this::resetStatGrowth, ImmutableSet.of(
                        ResourceFile.STAT_GROWTH))
                .put(this::resetDungeon, ImmutableSet.of(
                        ResourceFile.DUNGEONS))
                .put(this::resetExploration, ImmutableSet.of(
                        ResourceFile.EXPLORATION_TIERS,
                        ResourceFile.EXPLORATION_LEVELS))
                .put(this::resetFarm, ImmutableSet.of(
                        ResourceFile.FARMS,
                        ResourceFile.FARM_PLANTS))
                .put(this::resetDigging, ImmutableSet.of(
                        ResourceFile.DIGGING_REWARDS,
                        ResourceFile.DIGGING_REDUCE_TIMES,
                        ResourceFile.DIGGING_SPOON_COUNT,
                        ResourceFile.DIGGING_TIERS,
                        ResourceFile.DIGGING_ZONE_COUNT))
                .put(this::resetGacha, ImmutableSet.of(
                        ResourceFile.GACHA,
                        ResourceFile.GACHA_GROUPS,
                        ResourceFile.GACHA_LEVEL_GROUPS,
                        ResourceFile.GACHA_PICK_UPS))
                .put(this::resetShop, ImmutableSet.of(
                        ResourceFile.SHOP_ITEMS))
                .put(this::resetConditionPackage, ImmutableSet.of(
                        ResourceFile.CONDITION_PACKAGES))
                .put(this::resetShopPass, ImmutableSet.of(
                        ResourceFile.SHOP_PASSES))
                .put(this::resetEvent, ImmutableSet.of(
                        ResourceFile.EVENTS
                ))
                .put(this::resetDailyContent, ImmutableSet.of(
                        ResourceFile.DAILY_CONTENTS
                ))
                .put(this::resetAchievement, ImmutableSet.of(
                        ResourceFile.ACHIEVEMENTS
                ))
                .put(this::resetQuest, ImmutableSet.of(
                        ResourceFile.QUESTS
                ))
                .put(this::resetStoneStatueEnchant, ImmutableSet.of(
                        ResourceFile.STONE_STATUE_ENCHANTS,
                        ResourceFile.STONE_STATUE_ENCHANT_RATIO_GROUP))
                .put(this::resetStoneStatueGem, ImmutableSet.of(
                        ResourceFile.STONE_STATUE_GEMS,
                        ResourceFile.STONE_STATUE_GEM_UPGRADE_RATIO))
                .build();
    }

    private boolean checkResourceFiles() {
        // 로드 가능한 리소스 파일을 모두 모은다
        var resourceFiles = loaders.values()
                .stream()
                .flatMap(x -> x.stream())
                .collect(ImmutableSet.toImmutableSet());

        // 총 리소스 파일이 로드 가능한 리소스 파일에 있는지 체크 한다
        var notFoundFiles = Arrays.stream(ResourceFile.values())
                .filter(x -> !resourceFiles.contains(x))
                .collect(Collectors.toList());

        notFoundFiles.forEach(x -> logger.error("not found loader - files({})", x.files));

        return notFoundFiles.isEmpty();
    }

    public boolean initialize() {
        if (!checkResourceFiles()) {
            return false;
        }

        var mgrs = new ArrayList<IResourceManageable>();
        for (var loader : loaders.keySet()) {
            var mgr = loader.get();
            logger.info("loaded - {}", mgr);

            mgrs.add(mgr);
        }

        int errCount = 0;
        for (var mgr : mgrs) {
            if (!mgr.verify()) {
                logger.error("resource verify failure - {}", mgr);
                errCount += 1;
            }
        }

        return 0 == errCount;
    }
    public Set<String> absolutePathBy(ResourceFile resFile) {
        return ResourcePath.absoluteBy(rootPath, resFile);
    }

    // 실제 파일 이름을 ResourceFile 타입으로 변경하고, ResourceFile 로 로더를 찾아서 로드 한다
    public boolean reload(Set<String> fileNames) {
        var resFiles = fileNames.stream()
                .map(String::trim)
                .map(ResourceFile::ofIgnoreCaseXml)
                .collect(ImmutableSet.toImmutableSet());

        var realFileNames = resFiles.stream()
                .flatMap(x -> x.files.stream())
                .collect(Collectors.toList());

        // 중복을 제거한 모든 로드를 읽는다
        var tempLoaders = loaders.entrySet()
                .stream()
                .filter(x -> x.getValue().stream().anyMatch(resFiles::contains))
                .collect(Collectors.toSet());

        // 비어 있다면 로그 출력
        if (tempLoaders.isEmpty()) {
            logger.error("reload is empty - file({})", realFileNames);
            return false;
        }

        for (var loader : tempLoaders) {
            try {
                var action = loader.getKey();
                var mgr = action.get();
                logger.info("reload - mgr({})", Objects.nonNull(mgr) ? mgr : "");
            } catch (Exception e) {
            }
        }

        return true;
    }

    public ResourceItemManager resetItem() {
        var tempItem = ResourceItemManager.of(this);
        if (Objects.isNull(tempItem)) {
            return null;
        }

        item = tempItem;
        return tempItem;
    }

    public ResourceGrowthManager resetGrowth() {
        var tempGrowth = ResourceGrowthManager.of(this);
        if (Objects.isNull(tempGrowth)) {
            return null;
        }

        growth = tempGrowth;
        return tempGrowth;
    }

    public ResourceCollectionManager resetCollection() {
        var tempCollection = ResourceCollectionManager.of(this);
        if (Objects.isNull(tempCollection)) {
            return null;
        }

        collection = tempCollection;
        return tempCollection;
    }

    public ResourcePartsSlotManager resetPartsSlot() {
        var tempPartsSlot = ResourcePartsSlotManager.of(this);
        if (Objects.isNull(tempPartsSlot)) {
            return null;
        }

        partsSlot = tempPartsSlot;
        return tempPartsSlot;
    }

    public ResourceMapManager resetMap() {
        var tempMap = ResourceMapManager.of(this);
        if (Objects.isNull(tempMap)) {
            return null;
        }

        map = tempMap;
        return tempMap;
    }

    public ResourceStageGroupManager resetStageGroup() {
        var tempStageGroup = ResourceStageGroupManager.of(this);
        if (Objects.isNull(tempStageGroup)) {
            return null;
        }

        stageGroup = tempStageGroup;
        return tempStageGroup;
    }

    public ResourceDropTableManager resetDropTable() {
        var tempDropTable = ResourceDropTableManager.of(this);
        if (Objects.isNull(tempDropTable)) {
            return null;
        }

        dropTable = tempDropTable;
        return tempDropTable;
    }

    public ResourceAvatarManager resetAvatar() {
        var tempAvatar = ResourceAvatarManager.of(this);
        if (Objects.isNull(tempAvatar)) {
            return null;
        }

        avatar = tempAvatar;
        return tempAvatar;
    }

    public ResourceStatGrowthManager resetStatGrowth() {
        var tempStatGrowth = ResourceStatGrowthManager.of(this);
        if (Objects.isNull(tempStatGrowth)) {
            return null;
        }

        statGrowth = tempStatGrowth;
        return tempStatGrowth;
    }

    public ResourceDungeonManager resetDungeon() {
        var tempDungeon = ResourceDungeonManager.of(this);
        if (Objects.isNull(tempDungeon)) {
            return null;
        }

        dungeon = tempDungeon;
        return tempDungeon;
    }
    public ResourceExplorationManager resetExploration() {
        var tempExploration = ResourceExplorationManager.of(this);
        if (Objects.isNull(tempExploration)) {
            return null;
        }

        exploration = tempExploration;
        return tempExploration;
    }

    public ResourceFarmManager resetFarm() {
        var temp = ResourceFarmManager.of(this);
        farm = temp;
        return temp;
    }

    public ResourceDiggingManager resetDigging() {
        var temp = ResourceDiggingManager.of(this);
        digging = temp;
        return temp;
    }

    public ResourceStoneStatueEnchantManager resetStoneStatueEnchant() {
        var tempStoneStatueEnchant = ResourceStoneStatueEnchantManager.of(this);
        if (Objects.isNull(tempStoneStatueEnchant)) {
            return null;
        }

        stoneStatueEnchant = tempStoneStatueEnchant;
        return tempStoneStatueEnchant;
    }

    public ResourceStoneStatueGemManager resetStoneStatueGem() {
        var tempStoneStatueGem = ResourceStoneStatueGemManager.of(this);
        if (Objects.isNull(tempStoneStatueGem)) {
            return null;
        }

        stoneStatueGem = tempStoneStatueGem;
        return tempStoneStatueGem;
    }

    public ResourceEventManager resetEvent() {
        var temp = ResourceEventManager.of(this);
        event = temp;
        return temp;
    }

    public ResourceGachaManager resetGacha() {
        var temp = ResourceGachaManager.of(this);
        gacha = temp;
        return temp;
    }

    public ResourceShopManager resetShop() {
        var temp = ResourceShopManager.of(this);
        shop = temp;
        return temp;
    }

    public ResourceConditionPackageManager resetConditionPackage() {
        var temp = ResourceConditionPackageManager.of(this);
        conditionPackage = temp;
        return temp;
    }

    public ResourceShopPassManager resetShopPass() {
        var temp = ResourceShopPassManager.of(this);
        shopPass = temp;
        return temp;
    }
    public ResourceDailyContentManager resetDailyContent() {
        var temp = ResourceDailyContentManager.of(this);
        dailyContent = temp;
        return temp;
    }

    public ResourceAchievementManager resetAchievement() {
        var temp = ResourceAchievementManager.of(this);
        achievement = temp;
        return temp;
    }

    public ResourceQuestManager resetQuest() {
        var temp = ResourceQuestManager.of(this);
        quest = temp;
        return temp;
    }
}
