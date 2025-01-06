package org.supercat.growstone.rules;

import org.supercat.growstone.Constants;
import org.supercat.growstone.GameZoneDateTime;
import org.supercat.growstone.UtcZoneDateTime;

import java.time.Instant;

public class ScheduleRules {
    // 모든걸 UTC +0 기준으로 한다.
    public static boolean needToDailyReset(int nowYmd) {
        return UtcZoneDateTime.getYmd() != nowYmd;
    }
}
