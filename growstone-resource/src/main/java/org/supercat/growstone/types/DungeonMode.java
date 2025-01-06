package org.supercat.growstone.types;

public enum DungeonMode {
    NONE(0,"알 수 없음"),

    NORMAL(1,"일반"),
    BOSS(2, "보스"),
    BOSS_RUSH(3, "보스 러쉬"),
    DEFENSE(4, "방어"),
    MONSTER_COUNT(5, "몬스터 킬수"),

    COMPETITIVE_RAID(6, "경쟁 레이드"),
    COOPERATIVE_RAID(7, "협동 레이드"),

            ;

    public int value;
    public String desc;

    DungeonMode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static DungeonMode of(int value) {
        for (var type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NONE;
    }
}
