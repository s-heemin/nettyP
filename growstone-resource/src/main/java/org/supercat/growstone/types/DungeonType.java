package org.supercat.growstone.types;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public enum DungeonType {

    NONE(0, "알 수 없음"),
    DAILY(1, "일일 던전"),
    RAID(2, "레이드"),
    TOWER(3, "무한의 석탑"),
    ;
    public int value;
    public String desc;

    DungeonType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static DungeonType of(int value) {
        for (var type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NONE;
    }

    public static ImmutableSet<DungeonType> SoloDungeonTypes = ImmutableSet.of(DAILY, TOWER);
    public static ImmutableSet<DungeonType> UseTicketDungeonTypes = ImmutableSet.of(DAILY, RAID);
}
