package org.supercat.growstone;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class GameZoneDateTime {
    private static final Logger logger = LoggerFactory.getLogger(GameZoneDateTime.class);

    // DB에 1970-01-01 00:00:01 이상만 들어감, DB의 timestamp 최소값임
    public final static Instant SAFE_INSTANT_MIN = Instant.EPOCH.plusSeconds(1);

    // DB에 2038-01-19 03:14:07 이하만 들어감, DB의 timestamp 최대값임
    public final static Instant SAFE_INSTANT_MAX = Instant.EPOCH.plusSeconds(
            TimeUnit.DAYS.toSeconds(365 * 68) // 68년
                    + TimeUnit.DAYS.toSeconds(16) // 16일 더 추가, 왜냐하면 윤달이 있기 때문
                    + TimeUnit.DAYS.toSeconds(19) // 19일
                    + TimeUnit.HOURS.toSeconds(3) // 3시
                    + TimeUnit.MINUTES.toSeconds(14) // 14분
                    + TimeUnit.SECONDS.toSeconds(7) // 7초
    );

    private static ZoneId GAME_TIME_ZONE = null;

    public static DateTimeFormatter yyyy_MM_dd_HHmm;
    public static DateTimeFormatter yyyy_MM_dd_HHmmss;
    public static DateTimeFormatter yyyyDotMMDotdd_HHmm;
    public static DateTimeFormatter yyyy_MM_ddTHHmmssUTC;
    public static DateTimeFormatter yyyy_MM_ddTHHmmss;
    public static DateTimeFormatter rfc3339_utc0;

    private static ZonedDateTime EPOCH_ZONED_DAYS;  // 0 일 기준값
    private static ZonedDateTime EPOCH_ZONED_WEEKS; // 0 주차 기준값, 1970-01-01 이 1주차가 되어야 한다

    public static int MINUTE_SEC = 60;              // 분을 초로 환산
    public static int HOUR_SEC = MINUTE_SEC * 60;   // 시간을 초로 환산
    public static int DAY_SEC = HOUR_SEC * 24;      // 하루를 초로 환산
    public static int WEEK_SEC = DAY_SEC * 7;       // 일주일을 초로 환산

    public static void init() {
        // 혹시 모르니 시스템 시간은 UTC 로 강제함
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        GAME_TIME_ZONE = ZoneId.of("Asia/Seoul");

        yyyy_MM_dd_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(GAME_TIME_ZONE);
        yyyy_MM_dd_HHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(GAME_TIME_ZONE);
        yyyyDotMMDotdd_HHmm = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm").withZone(GAME_TIME_ZONE);
        yyyy_MM_ddTHHmmssUTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneOffset.UTC);
        yyyy_MM_ddTHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(GAME_TIME_ZONE);
        rfc3339_utc0 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'").withZone(ZoneOffset.UTC);

        EPOCH_ZONED_DAYS = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, GAME_TIME_ZONE);
        EPOCH_ZONED_WEEKS = ZonedDateTime.of(1969, 12, 22, 0, 0, 0, 0, GAME_TIME_ZONE);
    }

    public static DateTimeFormatter formatterOfPattern(String format) {
        return DateTimeFormatter.ofPattern(format)
                .withZone(GAME_TIME_ZONE);
    }


    public static long betweenDays(ZonedDateTime a, ZonedDateTime b) {
        return Math.abs(ChronoUnit.DAYS.between(a, b));
    }

    public static int randBetweenHMSs(int a, int b) {
        var ymd = nowYmd();
        var dateA = ofYmdhms(ymd, a);
        var dateB = ofYmdhms(ymd, b);

        //
        var max = ChronoUnit.SECONDS.between(dateA, dateB);
        var randDate = dateA.plus((long) (max * SRandomUtils.nextFloat(0f, 1f)), ChronoUnit.SECONDS);

        //
        return getHms(randDate);
    }

    // 현재 시간 기준 2019-01-01 00:00:01 중 Ymd 리턴
    public static int nowYmd() {
        return getYmd(now());
    }

    // 현재 시간 기준, 2019-01-01 00:00:01 중 Hms 리턴
    public static int nowHms() {
        var dateTime = now();
        return dateTime.getHour() * 1_00_00
                + dateTime.getMinute() * 1_00
                + dateTime.getSecond();
    }

    public static int nowHm() {
        return getHm(now());
    }

    public static int getHm(ZonedDateTime dateTime) {
        return dateTime.getHour() * 1_00
                + dateTime.getMinute();
    }

    public static int getHms(ZonedDateTime dateTime) {
        return dateTime.getHour() * 1_00_00
                + dateTime.getMinute() * 1_00
                + dateTime.getSecond();
    }

    public static int getHms(int hour, int minute, int second) {
        return hour * 1_00_00
                + minute * 1_00
                + second;
    }

    // 현재 시간 기준, 2019-01-01 00:00:01
    public static long nowYmdHms() {
        var dateTime = now();
        return ymdHmsOfZonedDateTime(dateTime);
    }

    public static long ymdHmsOfZonedDateTime(ZonedDateTime dateTime) {
        long ymd = getYmd(dateTime);

        return ymd * 1_00_00_00L
                + dateTime.getHour() * 1_00_00L
                + dateTime.getMinute() * 1_00L
                + dateTime.getSecond();
    }

    public static long ymdHmsOfInstant(Instant instant) {
        var dateTime = ofInstant(instant);

        return ymdHmsOfZonedDateTime(dateTime);
    }

    public static int ymdOfInstant(Instant instant) {
        var dateTime = ofInstant(instant);
        return getYmd(dateTime);
    }

    public static int ywOfInstant(Instant instant) {
        var dateTime = ofInstant(instant);
        return getYW(dateTime);
    }


    // 2019-01-01 00:00 까지
    public static long nowYmdHm() {
        return nowYmdHms() / 100;
    }

    public static long getYmdH(ZonedDateTime dateTime) {
        return ymdHmsOfZonedDateTime(dateTime) / 1_00_00L;
    }

    // Ymd 기준으로 어제 Ymd 반환
    public static int yesterdayByYmd(int ymd) {
        return getYmd(ofYmd(ymd).minusDays(1));
    }

    // 현재 기준 이번주 연도 주차
    public static int nowYW() {
        return getYW(now());
    }

    public static int nextYW() {
        return getYW(now().plusWeeks(1));
    }

    // 현재 기준 저번주 연도 주차
    public static int lastYW() {
        return getYW(now().minusWeeks(1));
    }

    // Ymd 기준 연도 주차
    public static int ywOfYmd(int Ymd) {
        return getYW(ofYmd(Ymd));
    }

    public static int lastWeekOfYW(int yw) {
        return getYW(ofYW(yw).minusWeeks(1));
    }

    public static int ymOfYm(int Ymd) {
        if (0 < Ymd) {
            return Ymd / 100;
        }
        return 0;
    }

    // Ymd 기준 저번주 연도 주차
    public static int lastYwOfYmd(int Ymd) {
        return getYW(ofYmd(Ymd).minusWeeks(1));
    }

    // 현재 기준 이번주 연도 월
    public static int nowYm() {
        return getYm(now());
    }

    // Ym 기준으로 지난달 Ym 반환
    public static int lastMonthByYm(int ym) {
        return getYm(ofYm(ym).minusMonths(1));
    }

    // 주의 : (일요일은 1, 토요일은 7)
    public static int dayOfWeek() {
        // JAVA 8 에서는 월요일이 1, 일요일이 7 이므로, 7 로 모듈러 연산 뒤 + 1 처리 한다
        // 기존 데이터와 호환성 때문에 건들면 안된다
        return (now().getDayOfWeek().getValue() % 7) + 1;
    }

    public static int weekOfMonth() {
        return now().get(WeekFields.ISO.weekOfMonth());
    }

    public static ZonedDateTime dayOfWeekInNextMonth(Instant at, int ordinal, DayOfWeek dayOfWeek) {
        return ofInstant(at)
                .plusMonths(1)
                .with(TemporalAdjusters.dayOfWeekInMonth(ordinal, dayOfWeek))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 다음달 1일 시작 Date
    public static ZonedDateTime firstDayOfNextMonth() {
        return now()
                .with(TemporalAdjusters.firstDayOfNextMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 이번달 1일 시작
    public static ZonedDateTime firstDayOfMonth() {
        return now()
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 이번달 마지막 일자
    public static ZonedDateTime lastDayOfMonth() {
        return now()
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 다음주 월요일 시작 Date
    public static ZonedDateTime firstDayOfNextWeek() {
        return firstDayOfWeek().plusWeeks(1);
    }

    // 이번주 월요일 시작 millis
    public static ZonedDateTime firstDayOfWeek() {
        return now()
                .with(DayOfWeek.MONDAY)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime firstDayOfLastWeek() {
        return firstDayOfWeek().minusWeeks(1);
    }

    // 오늘 시작일
    public static ZonedDateTime firstTimeOfDay() {
        return now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 특정 날짜의 시작일
    public static ZonedDateTime firstTimeOfDay(Instant date) {
        return GameZoneDateTime.ofInstant(date)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime lastTimeOfDay(Instant date) {
        return GameZoneDateTime.ofInstant(date)
                .plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .minusNanos(1);
    }

    public static ZonedDateTime dayOfWeekBy(DayOfWeek dayOfWeek) {
        return dayOfWeekByAt(dayOfWeek, Instant.now());
    }

    public static ZonedDateTime dayOfWeekByAt(DayOfWeek dayOfWeek, Instant at) {
        return ofInstant(at)
                .with(dayOfWeek)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static int yesterdayYmd() {
        return getYmd(now().minusDays(1));
    }

    public static int tomorrowYmd() {
        return getYmd(now().plusDays(1));
    }

    //
    public static ZonedDateTime firstTimeOfNextDay() {
        return firstTimeOfNextDays(1);
    }

    //
    public static ZonedDateTime firstTimeOfNextDays(int day) {
        return firstTimeOfDay()
                .plusDays(day);
    }

    public static ZonedDateTime firstTimeOfNextWeek() {
        return now()
                .plusWeeks(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime firstTimeOfNextMonth() {
        return now()
                .plusMonths(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime firstDayOfPreviousMonth() {
        return now()
                .minusMonths(1)
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime lastDayOfPreviousMonth() {
        return now()
                .minusMonths(1)
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime firstTimeOfPreviousMonth() {
        return now()
                .minusMonths(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // Ymd 포맷으로 리턴
    public static int getYmd(int year, int month, int day) {
        return (year * 1_00_00) // 20190000
                + (month * 1_00)    // 20190700
                + day;              // 20190723
    }

    public static int getYm(int year, int month) {
        return (year * 1_00)    // 201900
                + month;            // 201907
    }

    public static int getYmd(ZonedDateTime zdt) {
        return getYmd(zdt.getYear(), zdt.getMonthValue(), zdt.getDayOfMonth());
    }

    public static int getYW(ZonedDateTime dateTime) {
        int year = dateTime.get(IsoFields.WEEK_BASED_YEAR);
        int weekOfYear = dateTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        return year * 100 + weekOfYear;
    }

    public static int getYm(ZonedDateTime zdt) {
        return getYm(zdt.getYear(), zdt.getMonthValue());
    }

    public static ZonedDateTime ofNextAM(int hour) {
        return ofNextAM(hour, 0);
    }

    public static ZonedDateTime ofNextAM(int hour, int minute) {
        // 현재 날짜 기준으로 시간을 만들고
        var dateTime = now();

        // 시간대가 이미 지났을 경우, 다음날짜로 만듬
        if (dateTime.getHour() >= hour) {
            dateTime = dateTime.plusDays(1);
        }

        return dateTime
                .withHour(hour)
                .withMinute(minute)
                .withSecond(0)
                .withNano(0);
    }


    // 월별 구분 파티션키
    public static int getPartKey(Instant at) {
        return at.atZone(ZoneId.of("UTC")).getMonth().getValue();
    }

    public static ZonedDateTime ofInstant(Instant instant) {
        return ZonedDateTime.ofInstant(instant, GAME_TIME_ZONE);
    }

    public static ZonedDateTime ofYm(int ym) {
        final int year = ym / 1_00;
        final int month = ym % 100;

        return ZonedDateTime.of(year, month, 1, 0, 0, 0, 0, GAME_TIME_ZONE);
    }

    public static ZonedDateTime ofYW(int yw) {
        // 년/주차 분리
        int year = yw / 100;
        int week = yw % 100;
        // 해당 연도의 1월 1일 만듬
        var ymd = getYmd(year, 1, 1);
        // 해당 연도의 zonedDateTime 얻어옴
        var zdt = ofYmd(ymd);

        // 주차를 더해줌
        var newZdt = zdt.plusWeeks(week)
                .with(DayOfWeek.MONDAY)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        // 재구성 yw 가 입력값과 같은지 비교
        var tempYW = getYW(newZdt);
        // 주차 보정
        int offsetWeek = 0;
        if (yw < tempYW) {
            offsetWeek = 1;
        } else if (yw > tempYW) {
            offsetWeek = -1;
        }
        // yw 의 왈요일 zoned date time 계산
        return newZdt.plusWeeks(offsetWeek);
    }

    public static ZonedDateTime ofYmd(int ymd) {
        final int year = ymd / 1_00_00;
        final int month = (ymd / 100) % 100;
        final int day = ymd % 100;

        return ZonedDateTime.of(year, month, day, 0, 0, 0, 0, GAME_TIME_ZONE);
    }

    public static ZonedDateTime ofYmdhms(long ymdhms) {
        int ymd = (int) (ymdhms / 1_00_00_00);
        int hms = (int) (ymdhms % 1_00_00_00);

        return ofYmdhms(ymd, hms);
    }

    public static ZonedDateTime ofYmdhms(int ymd, int hms) {
        final int year = ymd / 1_00_00;
        final int month = (ymd / 100) % 100;
        final int day = ymd % 100;
        final int hour = hms / 1_00_00;
        final int minute = (hms / 100) % 100;
        final int second = hms % 100;

        return ZonedDateTime.of(year, month, day, hour, minute, second, 0, GAME_TIME_ZONE);
    }

    // 런타임에 쓰지 말것, 단순한 데이터 퍼싱을 위한 함수
    public static ZonedDateTime ofyyyyMMddHHmmss(String strDateTime) {
        try {
            return ZonedDateTime.parse(strDateTime, yyyy_MM_dd_HHmmss);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }


    public static ZonedDateTime now() {
        return ZonedDateTime.now(GAME_TIME_ZONE);
    }

    public static Instant dailyResetAt(int resetHour) {
        return now()
                .withHour(resetHour).withMinute(0).withSecond(0) .withNano(0).toInstant();

    }
    public static int epochDays() {
        return (int) ChronoUnit.DAYS.between(EPOCH_ZONED_DAYS, now());
    }

    public static int epochDays(Instant at) {
        var dateTimeAt = GameZoneDateTime.ofInstant(at);
        return (int) ChronoUnit.DAYS.between(EPOCH_ZONED_DAYS, dateTimeAt);
    }

    public static int epochWeeks() {
        return (int) ChronoUnit.WEEKS.between(EPOCH_ZONED_WEEKS, now());
    }

    public static ZonedDateTime nowOfHm(int hm) {
        return now()
                .withHour(hm / 100)
                .withMinute(hm % 100)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime nowOfHms(int hms) {
        return now()
                .withHour(hms / 1_00_00)
                .withMinute((hms / 100) % 100)
                .withSecond(hms % 100)
                .withNano(0);
    }

    public static ZonedDateTime ofInstantWithHms(Instant instant, int hms) {
        return ofInstant(instant)
                .withHour(hms / 1_00_00)
                .withMinute((hms / 100) % 100)
                .withSecond(hms % 100)
                .withNano(0);
    }

    // 클랜 랭킹 초기화 타임 중일 경우,
    public static boolean isClanRankingResetTimeAround(ZonedDateTime dateTime) {
        var dayOfWeek = dateTime.getDayOfWeek();

        var hour = dateTime.getHour();
        var minute = dateTime.getMinute();

        // 일요일 23:55 분 이상일 경우
        if (DayOfWeek.SUNDAY == dayOfWeek && 23 == hour && 55 <= minute) {
            return true;
        }

        // 월요일 00:05 분 이하일 경우
        if (DayOfWeek.MONDAY == dayOfWeek && 0 == hour && 5 >= minute) {
            return true;
        }

        return false;
    }

    // 날짜 변경선 근처일 경우,
    public static boolean isDailyResetTimeAround(ZonedDateTime dateTime) {
        var hour = dateTime.getHour();
        var minute = dateTime.getMinute();

        // 23:55 분 이상일 경우
        if (23 == hour && 55 <= minute) {
            return true;
        }

        // 00:05 분 이하일 경우
        if (0 == hour && 5 >= minute) {
            return true;
        }

        return false;
    }

    public static ZonedDateTime getClosestDayOfWeekAfterToday(DayOfWeek dayOfWeek) {
        return getClosestDayOfWeekAfterToday(dayOfWeek, Instant.now());
    }

    public static ZonedDateTime getClosestDayOfWeekAfterToday(DayOfWeek dayOfWeek, Instant instant) {
        ZonedDateTime now = ofInstant(instant);
        DayOfWeek todaysDayOfWeek = now.getDayOfWeek();
        if (todaysDayOfWeek.getValue() < dayOfWeek.getValue()) {
            return now.with(dayOfWeek)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        } else {
            return now.plusWeeks(1)
                    .with(dayOfWeek)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        }
    }

    public static ZonedDateTime getClosestDayOfWeekBeforeToday(DayOfWeek dayOfWeek, Instant instant) {
        ZonedDateTime now = ofInstant(instant);
        DayOfWeek todaysDayOfWeek = now.getDayOfWeek();
        if (todaysDayOfWeek.getValue() < dayOfWeek.getValue()) {
            return now.minusWeeks(1)
                    .with(dayOfWeek)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        } else {
            return now.with(dayOfWeek)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        }
    }

    public static ZonedDateTime nowHour() {
        return now()
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime prevHour() {
        return now()
                .minusHours(1)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static ZonedDateTime dayOfWeekAfterWeek(DayOfWeek dayOfWeek, int week, Instant at) {
        return ofInstant(at)
                .with(dayOfWeek)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .plusWeeks(week);
    }

    public static long getRemainingTimeUntilTomorrow() {
        var at = now();
        var tomorrow = at.plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return at.until(tomorrow, ChronoUnit.SECONDS);
    }

    public static long getRemainingTimeUntilNextWeek() {
        var at = now();
        var nextWeek = at.plusWeeks(1)
                .with(DayOfWeek.MONDAY)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return at.until(nextWeek, ChronoUnit.SECONDS);
    }

    public static long getRemainingTimeUntilNextMonth() {
        var at = now();
        var nextWeek = at.plusMonths(1)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        return at.until(nextWeek, ChronoUnit.SECONDS);
    }

    // 게임 서버 타임
    public static double getGameTime() {
        return Instant.now().toEpochMilli() / 1000.0d;
    }

    public static long getGameTimeToLong() {
        return (long) getGameTime();
    }
}
