package org.supercat.growstone.types;

import java.util.Set;

public enum RedisUseType {
    NONE(0, "알 수 없음"),
    CONTENTS(1, "콘텐츠"),
    RANK(2, "랭킹"),
    CHAT(3, "채팅"),

    ;

    public int value;
    public String desc;

    RedisUseType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static RedisUseType of(int value) {
        for (RedisUseType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NONE;
    }

    public static Set<RedisUseType> validTypes() {
        return Set.of(CONTENTS, RANK, CHAT);
    }
}
