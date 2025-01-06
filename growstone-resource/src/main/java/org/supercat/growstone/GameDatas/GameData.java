package org.supercat.growstone.GameDatas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.ResourceConfigFile;
import org.supercat.growstone.ResourcePath;
import org.supercat.growstone.SLog;
import org.supercat.growstone.Utility;
import org.supercat.growstone.config.SConfig;

import java.util.ArrayList;
import java.util.function.Supplier;

public class GameData {
    private static final Logger logger = LoggerFactory.getLogger(GameData.class);

    public static GameDataPlayer PLAYER;
    public static GameDataLevel LEVEL;
    public static GameDataExploration EXPLORATION;
    public static GameDataFormulaData FORMULA_DATA;
    public static GameDataFarm FARM;
    public static GameDataDigging DIGGING;
    public static GameDataCommunity COMMUNITY;
    public static GameDataStoneStatue STONE_STATUE;
    public static GameDataClan CLAN;

    public static void reloadWithExit() {
        if (!reload()) {
            System.exit(-1);
        }
    }

    public static boolean reload() {
        try {
            var loader = GameDataLoader.of();
            if (!loader.load(makeAbsolutPath(ResourceConfigFile.GAME))) {
                return false;
            }

            PLAYER = GameDataPlayer.of(loader);
            LEVEL = GameDataLevel.ofPath(makeAbsolutPath(ResourceConfigFile.LEVEL));
            EXPLORATION = GameDataExploration.ofPath(makeAbsolutPath(ResourceConfigFile.EXPLORATION));
            FORMULA_DATA = GameDataFormulaData.ofPath(makeAbsolutPath(ResourceConfigFile.FORMULA_DATA));
            FARM = GameDataFarm.ofPath(makeAbsolutPath(ResourceConfigFile.FARM));
            DIGGING = GameDataDigging.ofPath(makeAbsolutPath(ResourceConfigFile.DIGGING));
            STONE_STATUE = GameDataStoneStatue.ofPath(makeAbsolutPath(ResourceConfigFile.STONE_STATUE));
            COMMUNITY = GameDataCommunity.ofPath(makeAbsolutPath(ResourceConfigFile.COMMUNITY));

            CLAN = GameDataClan.ofPath(makeAbsolutPath(ResourceConfigFile.CLAN));
        } catch (Exception e) {
            logger.error("failed to load GameData.xml files");
            SLog.logException(e);
            return false;
        }

        var items = new ArrayList<Supplier<Boolean>>();
        items.add(PLAYER::verify);
        items.add(STONE_STATUE::verify);


        return items.stream().allMatch(Supplier::get);
    }

    private static String makeAbsolutPath(ResourceConfigFile resFile) {
        var rootPath = Utility.combine(SConfig.getConfig().resourceDir, ResourcePath.RELATIVE_PATH);
        return ResourcePath.absolute(rootPath, resFile);
    }

}
