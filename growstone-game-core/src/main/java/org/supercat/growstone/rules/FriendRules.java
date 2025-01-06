package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZCategory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.containers.ResourceReward;

import java.util.List;

public class FriendRules {
    public static List<ResourceReward> sendDailyGiftReward() {
        return List.of( new ResourceReward(ZCategory.Type.ITEM, GameData.COMMUNITY.friendGiftItemId, GameData.COMMUNITY.friendGiftItemCount));
    }

}

