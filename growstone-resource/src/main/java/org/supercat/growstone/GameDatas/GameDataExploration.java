package org.supercat.growstone.GameDatas;

public class GameDataExploration {
    public final int QuestTicketItemID;
    public final int MaxQuestTicketCount;
    public final int QuestChangerItemID;
    public final int MaxQuestChangerCount;
    public final int AcceleratorItemID;
    public final int AcceleratorTimeUnitMS;
    public final int MaxCommercialViewCount;
    public final int CommercialTimeUnitMS;
    public final int ElapsedTimeUnit;
    public final int IncreaseRewardPercent;
    public final int MaxQuestCount;
    public final int MaxNeedPetTierCount;

    public static GameDataExploration ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataExploration(loader);
    }

    private GameDataExploration(GameDataLoader loader) {
        this.QuestTicketItemID = loader.getInt("QuestTicketItemID");
        this.MaxQuestTicketCount = loader.getInt("MaxQuestTicketCount");
        this.QuestChangerItemID = loader.getInt("QuestChangerItemID");
        this.MaxQuestChangerCount = loader.getInt("MaxQuestChangerCount");
        this.AcceleratorItemID = loader.getInt("AcceleratorItemID");
        this.AcceleratorTimeUnitMS = loader.getInt("AcceleratorTimeUnit") * 60 * 1000;
        this.MaxCommercialViewCount = loader.getInt("MaxCommercialViewCount");
        this.CommercialTimeUnitMS = loader.getInt("CommercialTimeUnit") * 60 * 1000;
        this.ElapsedTimeUnit = loader.getInt("ElapsedTimeUnit");
        this.IncreaseRewardPercent = loader.getInt("IncreaseRewardPercent");
        this.MaxQuestCount = loader.getInt("MaxQuestCount");
        this.MaxNeedPetTierCount = loader.getInt("MaxNeedPetTierCount");
    }
}
