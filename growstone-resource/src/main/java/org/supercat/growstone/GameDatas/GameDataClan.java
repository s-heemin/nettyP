package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.jdom2.Element;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GameDataClan {
    public final int DEPUTY_LEADER_COUNT;
    public final int NAME_CHANGE_RUBY;
    public final int NAME_CHANGE_EXPIRE_SECOND;
    public final int CLAN_RECOMMEND_LIST_COUNT;
    public final int CLAN_JOIN_REQUEST_MAX_COUNT;
    public final long CLAN_CREATE_NEED_ITEM_DATA_ID;
    public final int CLAN_CREATE_NEED_AMOUNT;
    public final int CLAN_PENALTY_SECOND;
    public final int CLAN_MASTER_DISCONNECT_DAY;
    public final int CLAN_DESTROY_DAY;
    public final int CLAN_DAILY_CONTRIBUTE_MAX_COUNT;
    public final int EXP_BY_CONTRIBUTE;
    public final long DONATE_PAID_ITEM_DATA_ID;

    public final ImmutableMap<Integer, Donation> DONATIONS;
    public final ImmutableMap<Integer, Integer> MEMBER_COUNT_BY_LEVEL;
    public final ImmutableMap<Integer, Integer> NEED_EXP_BY_LEVEL;

    public class Donation {
        public final int donationCount;
        public final long cost;
        public final List<ResourceReward> rewards;

        public Donation(Element e, int donationCount, long cost) {
            this.donationCount = donationCount;
            this.cost = cost;

            var temp = new ArrayList<ResourceReward>();
            for (var reward : e.getChildren("Reward")) {
                temp.add(new ResourceReward(reward));
            }

            this.rewards = ImmutableList.copyOf(temp);
        }

    }

    public static GameDataClan ofPath(String absolutePath) {
        var doc = XMLHelper.load(absolutePath);
        return new GameDataClan(doc.getRootElement());
    }


    public GameDataClan(Element e) {
        this.DEPUTY_LEADER_COUNT =  XMLHelper.getChildInt(e, "DeputyLeaderCount", 0);
        this.NAME_CHANGE_RUBY = XMLHelper.getChildInt(e, "NameChangeRuby", 0);
        this.NAME_CHANGE_EXPIRE_SECOND = XMLHelper.getChildInt(e, "NameChangeSeconds", 0);
        this.CLAN_RECOMMEND_LIST_COUNT = XMLHelper.getChildInt(e, "ClanRecommendListCount", 0);
        this.CLAN_JOIN_REQUEST_MAX_COUNT = XMLHelper.getChildInt(e, "ClanJoinRequestMaxCount", 0);
        this.CLAN_CREATE_NEED_ITEM_DATA_ID = XMLHelper.getChildLong(e, "ClanCreateNeedItemDataId", 0);
        this.CLAN_CREATE_NEED_AMOUNT = XMLHelper.getChildInt(e, "ClanCreateNeedRubyCount", 0);
        this.CLAN_PENALTY_SECOND = XMLHelper.getChildInt(e, "ClanPenaltySeconds", 0);
        this.CLAN_MASTER_DISCONNECT_DAY = XMLHelper.getChildInt(e, "ClanMasterDisconnectDays", 0);
        this.CLAN_DESTROY_DAY = XMLHelper.getChildInt(e, "ClanDestroyDays", 0);
        this.CLAN_DAILY_CONTRIBUTE_MAX_COUNT = XMLHelper.getChildInt(e, "ClanDailyContributeMaxCount", 0);
        this.EXP_BY_CONTRIBUTE = XMLHelper.getChildInt(e, "ExpByContribute", 0);
        this.DONATE_PAID_ITEM_DATA_ID = XMLHelper.getChildLong(e, "DonatePaidItemDataId", 0);
        var temps = new HashMap<Integer, Integer>();
        var tempMemberLimits = e.getChild("ClanMemberLimits");
        if (Objects.nonNull(tempMemberLimits)) {
            var tempLevels = tempMemberLimits.getChildren("Level");
            for (var tempLevel : tempLevels) {
                var level = XMLHelper.getAttributeInt(tempLevel, "Level", 0);
                var memberCount = XMLHelper.getAttributeInt(tempLevel, "MaxMembers", 0);
                temps.put(level, memberCount);
            }
        }
        this.MEMBER_COUNT_BY_LEVEL = ImmutableMap.copyOf(temps);

        temps.clear();
        var tempClanLevelExps = e.getChild("ClanLevelExps");
        if (Objects.nonNull(tempClanLevelExps)) {
            var tempLevels = tempClanLevelExps.getChildren("Level");
            for (var tempLevel : tempLevels) {
                var level = XMLHelper.getAttributeInt(tempLevel, "Level", 0);
                var memberCount = XMLHelper.getAttributeInt(tempLevel, "RequiredExp", 0);
                temps.put(level, memberCount);
            }
        }
        this.NEED_EXP_BY_LEVEL = ImmutableMap.copyOf(temps);

        var tempDonationMap = new HashMap<Integer, Donation>();
        var tempDailyDonations = e.getChild("DailyDonations");
        if (Objects.nonNull(tempDailyDonations)) {
            var tempDonations = tempDailyDonations.getChildren("Donation");
            for (var tempDonation : tempDonations) {
                var donationCount = XMLHelper.getAttributeInt(tempDonation, "Count", 0);
                var cost = XMLHelper.getAttributeLong(tempDonation, "Cost", 0);
                tempDonationMap.put(donationCount, new Donation(tempDonation, donationCount, cost));
            }
        }
        this.DONATIONS = ImmutableMap.copyOf(tempDonationMap);
    }
}
