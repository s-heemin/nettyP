package org.supercat.growstone.types;

public enum ResetType {
    NONE(0, "알 수 없음"),
    NORMAL(1, "초기화 없음"),
    DAILY(2, "일일 초기화"),
    WEEKLY(3, "주간 초기화")
    ;


    public final int value;
    public final String desc;

    ResetType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static ResetType of(int value) {
        for (ResetType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NONE;
    }
}
