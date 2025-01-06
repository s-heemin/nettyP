package org.supercat.growstone.dungeons;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import io.opencensus.resource.Resource;
import org.jdom2.Element;
import org.json.XML;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.ResourceContext;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.Range;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.types.DungeonMode;
import org.supercat.growstone.types.DungeonType;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ResourceDungeon extends ResourceBase {

    public final DungeonType type;
    public final DungeonMode mode;
    public final long mapId;
    public final float rewardBonusPercent;
    public final Ticket ticket;
    public final int adViewMaxCount;
    public final ImmutableMap<Integer, List<ResourceReward>> clearRewardsByStage;
    public final ImmutableMap<Range, List<ResourceReward>> soloRankRewards;
    public final ImmutableSortedMap<Long, List<ResourceReward>> serverTotalPointRewards;
    public class Ticket {
        public long ticketId;
        public int ticketCount;

        public Ticket(Element e) {
            this.ticketId = XMLHelper.getAttributeLong(e, "ItemID", 0);
            this.ticketCount = XMLHelper.getAttributeInt(e, "DaliyRewardCount", 0);
        }
    }
    public ResourceDungeon(Element e) {
        super(e);
        this.mapId = XMLHelper.getChildLong(e, "MapID", 0);
        var tempBonus = XMLHelper.getChildInt(e, "RewardBonusPercent", 0);
        this.rewardBonusPercent = tempBonus > 0 ? tempBonus / 100.0f : 0.0f;
        this.type = XMLHelper.getChildEnum(e, "Type", DungeonType.NONE);
        this.mode = XMLHelper.getChildEnum(e, "Mode", DungeonMode.NONE);
        this.adViewMaxCount = XMLHelper.getAttributeInt(e, "AdViewMaxCount", 0);
        var tempTicket = e.getChild("Ticket");
        this.ticket = Objects.isNull(tempTicket) ? null : new Ticket(tempTicket);

        // 스테이지 클리어 보상
        var tempStage = e.getChildren("Stage");
        var temp = new HashMap<Integer, List<ResourceReward>>();
        if(Objects.nonNull(tempStage) && tempStage.size() > 0) {
            for(var ele : tempStage) {
                int step = XMLHelper.getAttributeInt(ele, "ID", 0);
                var tempClearReward = ele.getChild("ClearReward");
                if(Objects.nonNull(tempClearReward)) {
                    var tempReward = tempClearReward.getChildren("Reward").stream().map(ResourceReward::new).toList();
                    temp.put(step, tempReward);
                }
            }
        }

        // 경쟁 레이드 보상
        var tempSoloRank = e.getChild("SoloRankReward");
        var tempSoloRankReward = new HashMap<Range, List<ResourceReward>>();
        if(Objects.nonNull(tempSoloRank)) {
            var tempRank = tempSoloRank.getChildren("Rank");
            if(Objects.nonNull(tempRank) && temp.size() > 0) {
                for(var ele : tempRank) {
                    var tempRange = new Range(XMLHelper.getAttributeInt(ele, "Min", 0), XMLHelper.getAttributeInt(ele, "Max", 0));
                    var tempClearReward = ele.getChildren("Reward");
                    if(Objects.nonNull(tempClearReward) && tempClearReward.size() > 0) {
                        var tempReward = tempClearReward.stream().map(ResourceReward::new).toList();
                        tempSoloRankReward.put(tempRange, tempReward);
                    }
                }
            }
        }

        // 협동 레이드 보상
        var tempTotalPoint = e.getChild("ServerTotalPointReward");
        var tempRewards = new HashMap<Long, List<ResourceReward>>();
        if(Objects.nonNull(tempTotalPoint)) {
            var tempTotalPointRewards = tempTotalPoint.getChildren("ServerTotalPoint");
            if(Objects.nonNull(tempTotalPointRewards) && tempTotalPointRewards.size() > 0) {
                for(var ele : tempTotalPointRewards) {
                    long point = XMLHelper.getAttributeLong(ele, "Value", 0);
                    var tempClearReward = ele.getChildren("Reward");
                    if(Objects.nonNull(tempClearReward) && tempClearReward.size() > 0) {
                        var tempReward = tempClearReward.stream().map(ResourceReward::new).toList();
                        tempRewards.put(point, tempReward);
                    }
                }
            }
        }
        clearRewardsByStage = ImmutableMap.copyOf(temp);
        soloRankRewards = ImmutableMap.copyOf(tempSoloRankReward);
        serverTotalPointRewards = ImmutableSortedMap.copyOf(tempRewards);
    }

    public int getLastRank() {
        return soloRankRewards.keySet().stream()
                .map(x -> x.max)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<ResourceReward> getRankRewards(int rank) {
        return soloRankRewards.entrySet().stream()
                .filter(x -> x.getKey().min >= rank && x.getKey().max <= rank)
                .map(x -> x.getValue())
                .findFirst()
                .orElse(ImmutableList.of());
    }
}
