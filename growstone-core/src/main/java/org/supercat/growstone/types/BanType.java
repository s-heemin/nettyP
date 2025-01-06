package org.supercat.growstone.types;

public enum BanType {
    NONE(0, "없음"),
    TEMPORARY(1, "일시적"),
    PERMANENT(2,   "영구적");

    public final int value;
    public final String desc;

    BanType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static BanType fromInt(int value) {
        for (BanType banType : BanType.values()) {
            if (banType.value == value) {
                return banType;
            }
        }
        return NONE;
    }
}
