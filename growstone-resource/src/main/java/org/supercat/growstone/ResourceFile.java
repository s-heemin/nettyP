package org.supercat.growstone;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

public enum ResourceFile {
    ITEMS("Items.xml", "Items-PortraitIcon.xml"),
    GROWTHS("Growth-Pets.xml", "Growth-Stones.xml", "Growth-Parts.xml", "Growth-Artifacts.xml"),
    PARTS_SLOT("PartsSlots.xml"),
    GROWTH_UPGRADE("GrowthUpgrades.xml"),
    COLLECTION("Collections.xml"),
    MAPS("Maps.xml"),
    STAGE_GROUPS("StageGroups.xml"),
    DROPS("DropTables.xml"),
    AVATARS("Avatars.xml"),
    STAT_GROWTH("StatGrowths.xml"),
    DUNGEONS("Dungeons.xml"),
    EXPLORATION_TIERS("ExplorationTiers.xml"),
    EXPLORATION_LEVELS("ExplorationLevels.xml"),
    FARMS("Farms.xml"),
    FARM_PLANTS("FarmPlants.xml"),
    DIGGING_REWARDS("DiggingRewards.xml"),
    DIGGING_REDUCE_TIMES("DiggingReduceTimes.xml"),
    DIGGING_SPOON_COUNT("DiggingSpoonCount.xml"),
    DIGGING_TIERS("DiggingTiers.xml"),
    DIGGING_ZONE_COUNT("DiggingZoneCount.xml"),
    GACHA("Gacha.xml"),
    GACHA_GROUPS("GachaGroups.xml"),
    GACHA_LEVEL_GROUPS("GachaLevelGroups.xml"),
    GACHA_PICK_UPS("GachaPickUps.xml"),
    SHOP_ITEMS("ShopItems.xml"),
    CONDITION_PACKAGES("ConditionPackages.xml"),
    SHOP_PASSES("ShopPasses.xml"),
    EVENTS("Events.xml"),
    DAILY_CONTENTS("DailyContents.xml"),
    ACHIEVEMENTS("Achievements.xml"),
    QUESTS("Quests.xml"),

    STONE_STATUE_ENCHANTS("StoneStatueEnchants.xml"),
    STONE_STATUE_ENCHANT_RATIO_GROUP("StoneStatueEnchantRatioGroups.xml"),
    STONE_STATUE_GEMS("StoneStatueGems.xml"),
    STONE_STATUE_GEM_UPGRADE_RATIO("StoneStatueGemUpgradeRatioGroups.xml"),
    ;
    public final ImmutableSet<String> files;

    ResourceFile(String... files) {
        this.files = ImmutableSet.copyOf(files);
    }

    public static ResourceFile ofIgnoreCaseXml(String xmlName) {
        final String xmlExtension = ".xml";
        final String fileName = StringUtils.removeEndIgnoreCase(xmlName, xmlExtension);
        return Arrays.stream(values())
                .filter(x -> x.files.stream() // 파일 스트림을 열고
                        .map(y -> StringUtils.removeEndIgnoreCase(y, xmlExtension)) // .xml 확장자를 제거 하고
                        .filter(y -> y.equalsIgnoreCase(fileName)) // 파일 대소문자를 무시하면서
                        .anyMatch(Objects::nonNull) // 하나라도 매칭 되었다면 true
                )
                .findAny()
                .orElse(null);
    }

    public static ResourceFile of(String xmlName) {
        return Arrays.stream(values())
                .filter(x -> x.files.stream() // 파일 스트림을 열고
                        .filter(y -> y.equalsIgnoreCase(xmlName)) // 파일 대소문자를 무시하면서
                        .anyMatch(Objects::nonNull) // 하나라도 매칭 되었다면 true
                )
                .findAny()
                .orElse(null);
    }
}
