package org.supercat.growstone.events;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.types.ResetType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceAttendanceEvent extends ResourceEvent {
    public final ImmutableMap<Integer, ImmutableList<ResourceReward>> attendanceRewards;
    public final ImmutableMap<Integer, ImmutableList<ResourceReward>> attendanceCountRewards;
    public final int lastDay;
    public final ImmutableList<Long> exceptShopIds;

    public ResourceAttendanceEvent(Element e) {
        super(e);
        var tempWeeklyRewards = new HashMap<Integer, ImmutableList<ResourceReward>>();
        for(var ele : e.getChildren("Attendance")) {
            int day = XMLHelper.getAttributeInt(ele, "Day", 0);
            var tempRewards = new ArrayList<ResourceReward>();
            for(var reward : ele.getChildren("Reward")) {
                tempRewards.add(new ResourceReward(reward));
            }

            tempWeeklyRewards.put(day, ImmutableList.copyOf(tempRewards));
        }

        this.attendanceRewards = ImmutableMap.copyOf(tempWeeklyRewards);
        this.lastDay = attendanceRewards.keySet().stream().max(Integer::compareTo).orElse(0);

        var tempAttendanceCountRewards = new HashMap<Integer, ImmutableList<ResourceReward>>();
        for(var ele : e.getChildren("AttendanceCountReward")) {
            int count = XMLHelper.getAttributeInt(ele, "Count", 0);
            var tempRewards = new ArrayList<ResourceReward>();
            for(var reward : ele.getChildren("Reward")) {
                tempRewards.add(new ResourceReward(reward));
            }

            tempAttendanceCountRewards.put(count, ImmutableList.copyOf(tempRewards));
        }

        this.attendanceCountRewards = ImmutableMap.copyOf(tempAttendanceCountRewards);

        var tempExcepts = new ArrayList<Long>();
        for(var ele : e.getChildren("ExceptShopItems")) {
            tempExcepts.add(XMLHelper.getChildLong(ele, "ShopItemID", 0));
        }
        exceptShopIds = ImmutableList.copyOf(tempExcepts);
    }

}
