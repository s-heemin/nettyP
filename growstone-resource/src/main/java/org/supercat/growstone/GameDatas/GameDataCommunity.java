package org.supercat.growstone.GameDatas;

public class GameDataCommunity {
    public final long friendGiftItemId;
    public final int friendGiftItemCount;
    public final int friendMaxCount;
    public final int friendBlockMaxCount;
    public final int recommendFriendCount;
    public static GameDataCommunity ofPath(String absolutePath) throws Exception {
        var loader = GameDataLoader.of();
        if (!loader.load(absolutePath)) {
            throw new Exception();
        }

        return new GameDataCommunity(loader);
    }

    private GameDataCommunity(GameDataLoader loader) {
        friendGiftItemId = loader.getLong("FriendGiftItemId");
        friendGiftItemCount = loader.getInt("FriendGiftItemCount");
        friendMaxCount = loader.getInt("FriendMaxCount");
        friendBlockMaxCount = loader.getInt("FriendBlockMaxCount");
        recommendFriendCount = loader.getInt("FriendRecommendCount");
    }

    public boolean verify() {
        return true;
    }

}
