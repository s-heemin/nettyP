package org.supercat.growstone;

import com.supercat.growstone.network.messages.ZBuyReset;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.concurrent.TimeUnit;

public class UtcZoneDateTime {
    private static ZoneId GAME_TIME_ZONE = null;
    public static DateTimeFormatter yyyy_MM_dd_HHmmss;
    public final static Instant SAFE_INSTANT_MIN = Instant.EPOCH.plusSeconds(1);
    public final static Instant SAFE_INSTANT_MAX = Instant.EPOCH.plusSeconds(
            TimeUnit.DAYS.toSeconds(365 * 68) // 68년
                    + TimeUnit.DAYS.toSeconds(16) // 16일 더 추가, 왜냐하면 윤달이 있기 때문
                    + TimeUnit.DAYS.toSeconds(19) // 19일
                    + TimeUnit.HOURS.toSeconds(3) // 3시
                    + TimeUnit.MINUTES.toSeconds(14) // 14분
                    + TimeUnit.SECONDS.toSeconds(7) // 7초
    );

    public static void init() {
        GAME_TIME_ZONE = ZoneId.of("UTC");
        yyyy_MM_dd_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(GAME_TIME_ZONE);

    }

    public static Instant getPlusDay(int plusDays) {
        var utcDateTime = Instant.now().atZone(GAME_TIME_ZONE);
        return utcDateTime.plusDays(plusDays).toInstant();
    }

    public static int getPreviousDayYmd() {
        var utcDateTime = Instant.now().atZone(GAME_TIME_ZONE);
        var previousDay = utcDateTime.minusDays(1);
        return getYmd(previousDay.getYear(), previousDay.getMonthValue(), previousDay.getDayOfMonth());
    }
    public static int getYmd() {
        var utcDateTime = Instant.now().atZone(GAME_TIME_ZONE);
        return getYmd(utcDateTime.getYear(), utcDateTime.getMonthValue(), utcDateTime.getDayOfMonth());
    }

    public static int getYmd(Instant at) {
        var utcDateTime = at.atZone(GAME_TIME_ZONE);
        return getYmd(utcDateTime.getYear(), utcDateTime.getMonthValue(), utcDateTime.getDayOfMonth());
    }
    private static int getYmd(int year, int month, int day) {
        return (year * 1_00_00) // 20190000
                + (month * 1_00)    // 20190700
                + day;              // 20190723
    }

    public static ZonedDateTime ofNextResetTime(int hour) {
        ZonedDateTime dateTime = Instant.now().atZone(GAME_TIME_ZONE);
       // 시간대가 이미 지났을 경우, 다음날짜로 만듬
        if (dateTime.getHour() >= hour) {
            dateTime = dateTime.plusDays(1);
        }

        return dateTime
                .withHour(hour)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime getNextMondayMidnight() {
        // 현재 시간 가져오기
        ZonedDateTime now = ZonedDateTime.now(GAME_TIME_ZONE);

        // 다음 월요일의 00:00:00 가져오기
        ZonedDateTime nextMonday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return nextMonday;
    }

    public static int getYW() {
        LocalDate date = Instant.now().atZone(GAME_TIME_ZONE).toLocalDate();
        WeekFields weekFields = WeekFields.ISO;
        int weekOfYear = date.get(weekFields.weekOfWeekBasedYear());
        int year = date.get(weekFields.weekBasedYear());
        var makeStr =  String.format("" + year + weekOfYear);

        return Integer.parseInt(makeStr);
    }

    public static int getYM() {
        LocalDate date = Instant.now().atZone(GAME_TIME_ZONE).toLocalDate();
        return date.getYear() * 100 + date.getMonthValue();
    }

    public static ZonedDateTime ofyyyyMMddHHmmss(String strDateTime) {
        try {
            return ZonedDateTime.parse(strDateTime, yyyy_MM_dd_HHmmss);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    public static int getResetDay(ZBuyReset.Type type) {
        switch (type) {
            case DAILY:
                return getYmd();
            case WEEKLY:
                return getYW();
            case MONTHLY:
                return getYM();
            case NONE:
                return 0;
        }

        return 0;
    }
}
