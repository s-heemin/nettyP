package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.*;

import java.util.Objects;

public class NetEnumRules {
    public static ZDiggingUpgrade.Type ofDiggingUpgrade(int type) {
        var upgradeType = ZDiggingUpgrade.Type.forNumber(type);
        return Objects.isNull(upgradeType) ? ZDiggingUpgrade.Type.NONE : upgradeType;
    }

    public static ZTier.Type ofTier(int type) {
        var tierType = ZTier.Type.forNumber(type);
        return Objects.isNull(tierType) ? ZTier.Type.NONE : tierType;
    }
    public static ZEvent.Type ofEvent(int type) {
        var eventType = ZEvent.Type.forNumber(type);
        return Objects.isNull(eventType) ? ZEvent.Type.NONE : eventType;
    }

    public static ZEventProgress.State ofEventProgress(int state) {
        var progressState = ZEventProgress.State.forNumber(state);
        return Objects.isNull(progressState) ? ZEventProgress.State.NONE : progressState;
    }

    public static ZFriend.State ofFriend(int state) {
        var friendState = ZFriend.State.forNumber(state);
        return Objects.isNull(friendState) ? ZFriend.State.NONE : friendState;
    }

    public static ZPartsSlot.Type ofPartsSlot(int type) {
        var  slotType = ZPartsSlot.Type.forNumber(type);
        return Objects.isNull(slotType) ? ZPartsSlot.Type.NONE : slotType;
    }

    public static ZGrowth.Type ofGrowth(int type) {
        var growthType = ZGrowth.Type.forNumber(type);
        return Objects.isNull(growthType) ? ZGrowth.Type.NONE : growthType;
    }

    public static ZMail.Type ofMail(int type) {
        var mailType = ZMail.Type.forNumber(type);
        return Objects.isNull(mailType) ? ZMail.Type.NONE : mailType;
    }

    public static ZPreset.Type ofPreset(int type) {
        var presetType = ZPreset.Type.forNumber(type);
        return Objects.isNull(presetType) ? ZPreset.Type.NONE : presetType;
    }

    public static ZStat.Type ofStat(int type) {
        var statType = ZStat.Type.forNumber(type);
        return Objects.isNull(statType) ? ZStat.Type.NONE : statType;
    }

    public static ZReward.State ofReward(int state) {
        var rewardState = ZReward.State.forNumber(state);
        return Objects.isNull(rewardState) ? ZReward.State.NONE : rewardState;
    }

    public static ZDailyContent.Type ofDailyContent(int type) {
        var dailyContentType = ZDailyContent.Type.forNumber(type);
        return Objects.isNull(dailyContentType) ? ZDailyContent.Type.NONE : dailyContentType;
    }

    public static ZClear.State ofClear(int state) {
        var clearState = ZClear.State.forNumber(state);
        return Objects.isNull(clearState) ? ZClear.State.NONE : clearState;
    }
}
