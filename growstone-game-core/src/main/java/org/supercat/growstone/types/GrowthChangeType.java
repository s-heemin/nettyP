package org.supercat.growstone.types;

public enum GrowthChangeType {
    NONE(0, "알 수 없음"),
    EQUIP(1, "장착"),
    POSSESSION(2, "소지"),
    INIT(3, "초기화"),
    ;

    public int id;
    public String desc;

    GrowthChangeType(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
