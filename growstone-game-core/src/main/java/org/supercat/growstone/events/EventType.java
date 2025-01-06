package org.supercat.growstone.events;

public enum EventType {
    NONE(0, "알 수 없음"),
    PLAYER_DAILY_RESET(1, "일일 리셋"),
    PLAYER_WEEKLY_RESET(2, "주간 리셋"),
    PLAYER_TIME_BASED_SCHEDULED_TASK(3, "플레이어 시간 기반 스케쥴링"),
    PLAYER_FARM_SEED(4, "농장 씨앗 심기"),
    PLAYER_FARM_BOOST(5, "농장 부스팅"),
    PLAYER_FARM_HARVEST(6, "농장 수확"),
    PLAYER_FARM_STEAL(7, "농장물 훔치기"),
    PLAYER_FARM_RETURN_STOLEN_PLANTS_TO_THIEF(8, "도둑에게 훔친 작물 주기"),
    PLAYER_FARM_REMOVE_THIEF_BY_BATTLE(9, "농자 도둑과 전투 후 제거"),
    CLEAR_STAGE(10, "스테이지 클리어"),
    GET_GROWTH(11, "성장 획득"),
    PROMOTE_GROWTH(12, "성장 승급"),
    LIMIT_BREAK_GROWTH(13, "성장 한계 돌파"),
    CLEAR_DUNGEON_STEP(14, "던전 스텝 클리어"),
    LEVEL_UP(15, "레벨 업"),
    PLAYER_EVENT_UPDATE(16, "이벤트 업데이트"),
    PLAYER_EVENT_LOGIN(17, "플레이어 로그인"),
    PLAYER_BUY_SHOP_ITEM_USE_DIAMOND(18, "다이아로 상점 아이템 구매"),
    PLAYER_CLEAR_DUNGEON(19, "던전 클리어"),
    PLAYER_PLAY_ARENA(20, "돌로세움 플레이"),
    PLAYER_ATTENDANCE(21,"접속"),
    PLAYER_PLAY_GACHA(22,"뽑기 진행"),
    PLAYER_GET_ITEM(23,"아이템 획득"),
    PLAYER_USE_ITEM(24,"아이템 사용"),
    PLAYER_COMPLETE_ACHIEVEMENT(25,"업적 완료"),
    PLAYER_UPGRADE_PARTS_SLOT(26,"파츠 슬롯 업그레이드"),
    PLAYER_STAT_GROWTH_LEVEL_UP(27,"스탯 성장 레벨 업"),

    // 친구 관련
    PLAYER_ADD_FRIEND(1001, "친구 추가"),
    PLAYER_DELETE_FRIEND(1002, "친구 삭제"),
    PLAYER_ADD_FRIEND_CANCEL(1003, "친구 신청 취소"),
    PLAYER_ADD_FRIEND_RESPONSE(1004, "친구 신청 응답"),
    PLAYER_BLOCK(1005, "차단 추가"),
    PLAYER_UNBLOCK(1006, "차단 삭제"),

    // 클랜 관련
    PLAYER_JOIN_CLAN_REQUEST(10000, "유저 클랜 가입 요청"),
    PLAYER_JOIN_CLAN(10001, "유저 클랜 가입"),
    PLAYER_CREATE_CLAN(10002, "클랜 생성"),
    PLAYER_LEAVE_CLAN(10003, "클랜 탈퇴"),
    PLAYER_EXPULSION_CLAN(10004, "클랜 추방"),
    ;

    public final int value;
    public final String desc;

    EventType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
