package org.supercat.growstone.types;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;

public enum CheatCmd {
    ITEMS("아이템", "//아이템 {ID} {개수}"),
    CURRENCY("재화", "//재화 {ID} {개수}"),
    GROWTHS("성장", "//성장 {ID} {개수}"),
    GROWTH_ALL("성장전체획득", "//성장전체획득 {개수}"),
    GROWTHS_CLEAR("성장초기화", "//성장초기화"),
    GROWTHS_ALL_UPGRADE("성장전체업그레이드", "//성장전체업그레이드"),
    AVATAR("아바타", "//아바타 {ID} {개수}"),
    AVATAR_ALL("아바타전체획득", "//아바타전체획득"),
    AVATAR_CLEAR("아바타초기화", "//아바타초기화"),
    PORTRAIT("초상화", "//초상화 {ID}"),
    PORTRAIT_ALL("초상화전체획득", "//아바타전체획득"),
    PORTRAIT_CLEAR("초상화초기화", "//초상화초기화"),
    STAGE("스테이지", "//스테이지 {GROUPID} {STAGEID}"),
    DAILY_RESET("일간초기화", "//일간초기화 {sec}"),
    STAT_GROWTH_INIT("스탯성장초기화", "//스탯성장초기화"),
    COLLECTION_ALL("도감전체완료", "//도감전체완료"),
    COLLECTION_CLEAR("도감초기화", "//도감초기화"),
    ATTACK_RANK("전투력 랭킹 정산", "//전투력랭킹정산"),
    ;
    public final String ko;
    public final String usage;
    public final static ImmutableMap<String, CheatCmd> stringTo = stringTo();

    CheatCmd(String ko, String usage) {
        this.ko = ko;
        this.usage = usage;
    }

    private static ImmutableMap<String, CheatCmd> stringTo() {
        var temp = new HashMap<String, CheatCmd>();
        for (var x : values()) {
            temp.put(x.ko, x);
            temp.put(x.name().toLowerCase(), x);
        }
        return ImmutableMap.copyOf(temp);
    }
}
