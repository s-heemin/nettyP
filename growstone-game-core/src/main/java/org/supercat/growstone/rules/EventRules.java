package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZEvent;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.events.ResourceAttendanceEvent;
import org.supercat.growstone.types.ResetType;

import java.util.*;
import java.util.stream.Collectors;

public class EventRules {

    public static Set<ZEvent.Type> ATTENDANCE_TYPES = Set.of(ZEvent.Type.ATTENDANCE, ZEvent.Type.FIRST_PURCHASE_ATTENDANCE);
    public static Set<ZEvent.Type> NEED_TRIGGER_START_TYPE = Set.of(ZEvent.Type.FIRST_PURCHASE_ATTENDANCE);

    public static List<ResourceReward> computeAttendanceRewards(int progress, ResourceAttendanceEvent resEvent) {
        var result = new ArrayList<ResourceReward>();

        var attendanceRewards =  resEvent.attendanceRewards.get(progress);
        if(Objects.nonNull(attendanceRewards)) {
            result.addAll(attendanceRewards);
        }

        var accumulatedRewards = resEvent.attendanceCountRewards.get(progress);
        if(Objects.nonNull(accumulatedRewards)) {
            result.addAll(accumulatedRewards);
        }

        return result;
    }

    public static List<Integer> getAttendanceReward(String rewards) {
        var l = JsonConverter.of(rewards, Integer[].class);
        if(Objects.isNull(l)) {
            return List.of();
        }

        return Arrays.stream(l).collect(Collectors.toList());
    }
    public static int getResetDay(ResetType resetType) {
        if(resetType == ResetType.DAILY) {
            return UtcZoneDateTime.getYmd();
        } else if(resetType == ResetType.WEEKLY) {
            return UtcZoneDateTime.getYW();
        }

        return 0;
    }
    public static boolean isEnableAttendance(long last_updated_date, ResetType resetType) {
        if(resetType == ResetType.NONE) {
            return false;
        }

        if(resetType == ResetType.DAILY) {
            int nowYmd = UtcZoneDateTime.getYmd();
            if(last_updated_date != nowYmd) {
                return true;
            }
        } else if(resetType == ResetType.WEEKLY) {
            int nowYw = UtcZoneDateTime.getYW();
            if(last_updated_date != nowYw) {
                return true;
            }
        }

        return false;
    }
}
