package org.supercat.growstone.GameDatas;

public class GameDataDigging {
    public final int diggingDefaultSpoonCount;
    public final long spoonId;
    public final long diggingDefaultSecond;
    public final int diggingDefaultZoneCount;
    public final int diggingZoneMaxCount;
    public final int diggingReduceTimeMaxLevel;
    public final int diggingZoneMaxLevel;
    public final int diggingSpoonMaxLevel;
    public final int diggingTierMaxLevel;
    public final int boostMinute;
    public final int maxCommercialViewCount;
    public static GameDataDigging ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataDigging(loader);
    }

    private GameDataDigging(GameDataLoader loader) {
        diggingDefaultSpoonCount = loader.getInt("DiggingDefaultSpoonCount");
        spoonId = loader.getLong("SpoonID");
        diggingDefaultZoneCount = loader.getInt("DiggingDefaultZoneCount");
        diggingDefaultSecond = loader.getLong("DiggingDefaultSecond");
        diggingZoneMaxCount = loader.getInt("DiggingZoneMaxCount");
        diggingReduceTimeMaxLevel = loader.getInt("DiggingReduceTimeMaxLevel");
        diggingZoneMaxLevel = loader.getInt("DiggingZoneMaxLevel");
        diggingSpoonMaxLevel = loader.getInt("DiggingSpoonMaxLevel");
        diggingTierMaxLevel = loader.getInt("DiggingTierMaxLevel");
        boostMinute = loader.getInt("BoostMinutes");
        maxCommercialViewCount = loader.getInt("MaxCommercialViewCount");
    }
}
