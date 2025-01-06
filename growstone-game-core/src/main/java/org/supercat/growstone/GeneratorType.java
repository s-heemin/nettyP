package org.supercat.growstone;

public enum GeneratorType {

    NONE(0, "알 수 없음"),
    ITEM(100, "아이템"),
    FRIEND(3000, "친구 코드"),
    ;

    public int value;
    public String desc;

    GeneratorType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
