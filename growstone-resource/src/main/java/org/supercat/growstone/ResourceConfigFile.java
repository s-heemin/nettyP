package org.supercat.growstone;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum ResourceConfigFile {
    GAME("GameData.xml"),
    LEVEL("LevelConfig.xml"),
    EXPLORATION("ExplorationConfig.xml"),
    FORMULA_DATA("FormulaData.xml"),
    FARM("FarmConfig.xml"),
    DIGGING("DiggingConfig.xml"),
    STONE_STATUE("StoneStatueConfig.xml"),
    CLAN("ClanConfig.xml"),
    COMMUNITY("CommunityConfig.xml"),
    ;

    public final String file;

    ResourceConfigFile(String file) {
        this.file = file;
    }

    // XML 파일 이름으로 ResourceFile 을 찾는다
    public static ResourceConfigFile ofIgnoreCaseXml(String xmlName) {
        final String xmlExtension = ".xml";
        final String fileName = StringUtils.removeEndIgnoreCase(xmlName, xmlExtension);
        return Arrays.stream(values())
                .filter(x -> StringUtils.removeEndIgnoreCase(x.file, xmlExtension).equalsIgnoreCase(fileName))
                .findAny()
                .orElse(null);
    }
}
