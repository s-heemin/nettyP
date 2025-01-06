package org.supercat.growstone.types;

public enum ScheduleTaskType {
    NONE(0, "알 수 없음"),
    DAILY_RESET(1, "일일 리셋"),
    WEEKLY_RESET(2, "주간 리셋"),
    ;

    public int value;
    public String desc;

    ScheduleTaskType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ScheduleTaskType of(int value) {
        for (ScheduleTaskType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NONE;
    }
}
