package org.supercat.growstone.GameDatas;

public class GameDataPlayer {
    public final long[] starterAvatarIds;
    public final long[] starterPortraitIconIds;
    public final long defaultAvatarId;
    public final long defaultPortraitIconId;

    public final long acceleratorItemId;
    public final int acceleratorDayMaxCountByCommercial;
    public final long acceleratorSecond;
    public static GameDataPlayer of(GameDataLoader loader) {
        return new GameDataPlayer(loader);
    }

    private GameDataPlayer(GameDataLoader loader) {
        starterAvatarIds = loader.getLongArray("StarterAvatars");
        starterPortraitIconIds = loader.getLongArray("StarterPortraitIcons");
        defaultAvatarId = loader.getLong("DefaultAvatar");
        defaultPortraitIconId = loader.getLong("DefaultPortraitIcon");
        acceleratorItemId = loader.getLong("AcceleratorItemId");
        acceleratorDayMaxCountByCommercial = loader.getInt("AcceleratorDayMaxCountByCommercial");
        acceleratorSecond = loader.getLong("AcceleratorSecond");
    }

    public boolean verify() {
        if(!checkStarterAvatars()) {
            return false;
        }

        return true;
    }

    private boolean checkStarterAvatars() {

        return true;
    }
}
