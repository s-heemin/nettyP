package org.supercat.growstone.events;

public enum ClanEventType {
    CREATE_CLAN(1, "클랜 생성"),
    REQUEST_JOIN_CLAN(2,"클랜 가입 요청"),
    CHANGE_NOTICE(3, "클랜 공지 사항 변경"),
    CHANGE_INTRODUCTION(4, "클랜 가입 소개란 변경"),
    EXPULSION_CLAN_MEMBER(5, "클랜원 추방"),
    CHANGE_CLAN_NAME(6, "클랜 이름 변경"),
    REPLY_JOIN_CLAN(7, "클랜 가입 요청 응답"),
    CONTRIBUTION(8, "클랜 기여도"),
    CHANGE_CLAN_LEADER(9, "클랜 마스터 변경"),
    DISSOLUTION(10, "클랜 해체"),
            ;

    public final int value;
    public final String desc;

    ClanEventType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
