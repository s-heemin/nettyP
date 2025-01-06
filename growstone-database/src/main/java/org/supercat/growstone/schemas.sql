CREATE TABLE `global_master_players` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '글로벌 마스터 플레이어 아이디',
  `device_os` varchar(128) NOT NULL COMMENT '디바이스 os',
  `device_model` varchar(128) NOT NULL COMMENT '디바이스 모델',
  `login_type` tinyint(1) NOT NULL COMMENT '로그인 타입',
  `login_id` varchar(128) NOT NULL DEFAULT "" COMMENT '구글ID',
  `guest_token_id` varchar(32) NOT NULL DEFAULT "" COMMENT '구글 email',
  `ban_status` tinyint NOT NULL COMMENT '밴 상태',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_guest_token_id` (`guest_token_id`),
  KEY `idx_login_type_login_id` (`login_type`,`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='글로벌 마스터 플레이어 id';

CREATE TABLE `players` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '플레이어 아이디',
  `global_master_player_id` bigint NOT NULL COMMENT '글로벌 마스터 플레이어 아이디',
  `channel_id` bigint NOT NULL COMMENT '채널 아이디',
  `name` varchar(30) UNIQUE NULL COMMENT '이름',
  `portrait_id` bigint NOT NULL COMMENT '초상화 아이디',
  `level` int NOT NULL COMMENT '레벨',
  `exp` bigint NOT NULL COMMENT '경험치',
  `attack_power` bigint NOT NULL COMMENT '전투력',
  `stage_group` bigint NOT NULL COMMENT '스테이지 그룹',
  `stage` int NOT NULL COMMENT '스테이지',
  `on_boss_gauge` tinyint(1) NOT NULL COMMENT '보스 게이지',
  `preset_index` tinyint NOT NULL COMMENT '프리셋 인덱스',
  `ban_status` tinyint NOT NULL COMMENT '밴 상태',
  `friend_code` varchar(32) NULL COMMENT '친구 코드',
  `remove_ad` tinyint NOT NULL COMMENT '광고 제거 여부',
  `last_rest_reward_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '방치 보상 시간',
  `last_change_name_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '마지막 이름 변경 시간',
  `last_connected_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '접속시간',
  `last_disconnected_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '로그아웃시간',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_global_master_player_id` (`global_master_player_id`),
  KEY `idx_friend_code` (`friend_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='유저정보';

CREATE TABLE `player_daily_dungeons` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `dungeon_data_id` bigint NOT NULL COMMENT '던전 아이디',
  `stage` int NOT NULL COMMENT '단계',
  `remain_ticket_count` int NOT NULL COMMENT '티켓 카운트',
  `remain_view_ad_count` int NOT NULL COMMENT '광고 시청 횟수',
  `reset_ymd` int NOT NULL COMMENT '리셋',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_dungeon_data_id` (`player_id`,`dungeon_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='던전';

CREATE TABLE `player_currencies` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `gold` bigint NOT NULL COMMENT '골드',
  `free_ruby` bigint NOT NULL COMMENT '무료 루비',
  `paid_ruby` bigint NOT NULL COMMENT '유료 루비',
  `free_diamond` bigint NOT NULL COMMENT '유료 다이아몬드',
  `paid_diamond` bigint NOT NULL COMMENT '무료 다이아몬드',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='재화';

CREATE TABLE `player_towers` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `tower_data_id` bigint NOT NULL COMMENT '던전 아이디',
  `stage` tinyint NOT NULL COMMENT '단계',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_tower_data_id` (`player_id`,`tower_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='무한의 석탑';

CREATE TABLE `player_raid_dungeons` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `raid_data_id` bigint NOT NULL COMMENT '던전 아이디',
  `score` bigint NOT NULL COMMENT '레이드 점수',
  `remain_ticket_count` int NOT NULL COMMENT '티켓 카운트',
  `remain_view_ad_count` int NOT NULL COMMENT '광고 시청 횟수',
  `reset_ymd` int NOT NULL COMMENT '리셋',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_raid_data_id` (`player_id`,`raid_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='레이드';

CREATE TABLE `player_items` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `item_id` bigint NOT NULL COMMENT '아이템 유니크 ID',
  `item_data_id` bigint NOT NULL COMMENT '아이템 데이터 ID',
  `count` bigint NOT NULL COMMENT '아이템 개수',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_item_data_id` (`player_id`, `item_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='아이템';

CREATE TABLE `player_growths` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `growth_id` bigint NOT NULL COMMENT '성장 아이디',
  `count` bigint NOT NULL COMMENT '아이템 개수',
  `type` tinyint NOT NULL COMMENT '성장 타입',
  `level` int NOT NULL COMMENT '성장 레벨',
  `promote_level` int NOT NULL COMMENT '승급 레벨',
  `limit_break_level` int NOT NULL COMMENT '돌파 레벨',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_growth_id` (`player_id`,`growth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='성장';

CREATE TABLE `player_avatars` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `avatar_id` bigint NOT NULL COMMENT '아바타 아이디',
  `isEquip` tinyint NOT NULL COMMENT '장착 유무',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_avatar_id` (`player_id`,`avatar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='아바타 아이템';


CREATE TABLE `player_portrait_icon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `icon_id` bigint NOT NULL COMMENT '아이콘 아이디',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_icon_id` (`player_id`,`icon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='초상화 아이콘';


CREATE TABLE `player_mails` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `mail_type` int NOT NULL COMMENT '메일 타입',
  `rewards` json DEFAULT NULL COMMENT '보상',
  `is_read` tinyint NOT NULL COMMENT '읽음 유무',
  `sender` varchar(32) NOT NULL COMMENT '보낸 사람',
  `expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '만료일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='우편함';

CREATE TABLE `player_collections` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` int NOT NULL COMMENT '도감 타입',
  `collection_id` bigint NOT NULL COMMENT '도감 ID',
  `level` int NOT NULL COMMENT '레벨',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_collection_id` (`player_id`,`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='도감';

CREATE TABLE `player_parts_slots` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` tinyint NOT NULL COMMENT '타입',
  `level` int NOT NULL COMMENT '레벨',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_type` (`player_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='파츠 슬롯 성장';

CREATE TABLE `player_stat_growths` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `page` bigint NOT NULL COMMENT '페이지',
  `level` int NOT NULL COMMENT '레벨',
  `stat` tinyint DEFAULT NULL COMMENT '스탯',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_page_stat` (`player_id`,`page`,`stat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='캐릭트 스탯 성장';

CREATE TABLE `player_schedule_task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` tinyint NOT NULL COMMENT '일간/주간 스케쥴 타입',
  `reset_at` int  NOT NULL COMMENT '리셋 시간',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_type` (`player_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='스케줄 테스크';

CREATE TABLE `servers` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `instance_id` varchar(100) NOT NULL COMMENT '인스턴스Id',
  `port` int NOT NULL COMMENT '포트',
  `players` int NOT NULL COMMENT '플레이어 수',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_instance_id` (`instance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='서버정보';

CREATE TABLE `player_equip_preset` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` tinyint NOT NULL COMMENT '프리셋 타입',
  `preset_index` tinyint NOT NULL COMMENT '프리셋 인덱스',
  `equip_index` tinyint NOT NULL COMMENT '장착 인덱스',
  `data_id` bigint NOT NULL COMMENT '장착 id',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_type_preset_index_equip_index` (`player_id`,`type`,`preset_index`,`equip_index`),
  KEY `idx_player_id_preset_index` (`player_id`,`preset_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='장착 프리셋';

CREATE TABLE `player_equip_preset_name` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `index` tinyint NOT NULL COMMENT '프리셋 인덱스',
  `name` varchar(128) NOT NULL COMMENT '프리셋 이름',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_index` (`player_id`,`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='장착 프리셋';

CREATE TABLE `player_friends` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `target_player_id` bigint NOT NULL COMMENT '친구 플레이어 아이디',
  `state` tinyint NOT NULL COMMENT '상태',
  `receive_gift_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '받은 선물 만료 시간',
  `send_gift_expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '준 선물 만료 시간',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_target_player_id` (`player_id`,`target_player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='친구';

CREATE TABLE `world_schedule_tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` bigint NOT NULL COMMENT '채널 아이디',
  `type` tinyint NOT NULL COMMENT '리셋 타입',
  `reset_key` int NOT NULL COMMENT '리셋 시점',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_channel_id_type` (`channel_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='월드 스케줄 테스크';

CREATE TABLE `server_redis_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` bigint NOT NULL COMMENT '채널ID',
  `use_type` tinyint NOT NULL COMMENT '사용 타입',
  `db_index` bigint NOT NULL COMMENT 'redis index',
  `port` int NOT NULL COMMENT '포트',
  `connection_string` varchar(128) NOT NULL DEFAULT "" COMMENT '호스트',
  `connection_minimum` int NOT NULL  COMMENT '최소 연결 수',
  `connection_maximum` int NOT NULL COMMENT '최대 연결 수',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_channel_id_use_type` (`channel_id`,`db_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='레디스 정보';


CREATE TABLE `server_exception_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` bigint NOT NULL COMMENT '채널 아이디',
  `text` text NOT NULL COMMENT '정보',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='서버 예외 로그';

CREATE TABLE `player_farms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
  `exp` bigint(20) NOT NULL COMMENT '경헝치',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='농장 정보';

CREATE TABLE `player_farm_plants` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
  `status` int(10) NOT NULL COMMENT '농장 상태',
  `slot_index` int(10) NOT NULL COMMENT '농장 인덱스',
  `plant_id` bigint(20) NOT NULL COMMENT '농작물 id',
  `theft_ymd` int(10) NOT NULL COMMENT '도둑 당한 날짜',
  `theft_player_id` bigint(20) NOT NULL COMMENT '도둑 id',
  `theft_limit_count` int(10) NOT NULL COMMENT '도둑 가능한 개수',
  `seed_start_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '씨앗 심기 시작 시간',
  `seed_end_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '씨앗 심기 완료 시간',
  `theft_end_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '훔치기 완료 시간',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_slot_index` (`player_id`,`slot_index`),
  KEY `idx_theft_player_id` (`theft_player_id`),
  KEY `idx_theft_at` (`theft_end_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='농장 작물 농사 정보';

CREATE TABLE `player_farm_theft_limits` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
  `ymd` int(10) NOT NULL COMMENT '날짜',
  `count` int(10) NOT NULL COMMENT '도둑 횟수',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='농장 도둑 횟수 정보';

CREATE TABLE `player_farm_histories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
  `type` int(10) NOT NULL COMMENT '일지 타입',
  `data` json DEFAULT NULL COMMENT '일지 데이터',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_created_at` (`player_id`, `created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='농장 일지 정보';

CREATE TABLE `player_farm_battles` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `target_player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `data` json DEFAULT NULL COMMENT '일지 데이터',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_created_at` (`player_id`,`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='농장 전투 정보';

CREATE TABLE `player_farm_cooks` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT 'players.id',
  `level` int(10) NOT NULL COMMENT '레벨',
  `slots` json DEFAULT NULL COMMENT '요리 슬롯 정보',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id` (`player_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='플레이어 농장 요리 정보';


CREATE TABLE `player_digging` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `index` int NOT NULL COMMENT '발굴 위치',
  `tier` tinyint NOT NULL COMMENT '등급',
  `is_digging` tinyint NOT NULL COMMENT '발굴 여부',
  `complete_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '완료 시점',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_index` (`player_id`, `index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='발굴 정보';

CREATE TABLE `player_digging_upgrades` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` int NOT NULL COMMENT '업그레이드 타입',
  `level` bigint NOT NULL COMMENT '등급',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_type` (`player_id`, `type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='발굴 업그레이드 정보';

CREATE TABLE `player_advertises` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` tinyint NOT NULL COMMENT '광고 타입',
  `view_count` int NOT NULL COMMENT '광고 시청 횟수',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_type` (`player_id`, `type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='광고';


CREATE TABLE `player_buy_shop_items` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `shop_data_id` bigint NOT NULL COMMENT '상품ID',
  `count` bigint NOT NULL COMMENT '개수',
  `buy_reset_day` int NOT NULL COMMENT '구매 초기화 날짜',
  `ad_view_count` int NOT NULL COMMENT '광고 시청 횟수',
  `ad_reset_day` int NOT NULL COMMENT '광고 초기화 날짜',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_shop_data_id` (`player_id`, `shop_data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 구매 정보';

CREATE TABLE `player_condition_packages` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `package_id` bigint NOT NULL COMMENT '패키지ID',
  `is_complete` tinyint NOT NULL COMMENT '완료 여부',
  `expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '만료일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_package_id` (`player_id`, `package_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='조건 패키지 정보';

CREATE TABLE `player_gachas` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `index` bigint NOT NULL COMMENT '그룹 인덱스',
  `level` int NOT NULL COMMENT '레벨',
  `exp` bigint NOT NULL COMMENT '경험치',
  `gacha_max_count` int NOT NULL COMMENT '뽑기 최대 수 ',
  `reset_ymd` int NOT NULL COMMENT '리셋 날짜',
  `ad_view_count` int NOT NULL COMMENT '광고 시청 횟수',
  `expire_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '만료일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_index` (`player_id`, `index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='뽑기 정보';

CREATE TABLE `player_pick_up_gacha_points` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `shop_data_id` bigint NOT NULL COMMENT '상점ID',
  `point` int NOT NULL COMMENT '포인트',
  `rewards` varchar(255) NOT NULL COMMENT '보상',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id_shop_data_id` (`player_id`, `shop_data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='픽업 뽑기 정보';
CREATE TABLE `player_events` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `event_id` bigint NOT NULL COMMENT '이벤트 아이디',
  `event_data_id` bigint NOT NULL COMMENT '이벤트 데이터 아이디',
  `progress` int NOT NULL COMMENT '진행도',
  `state` tinyint NOT NULL COMMENT '상태',
  `rewards` json NOT NULL COMMENT '보상정보',
  `last_updated_date` bigint NOT NULL COMMENT '진행 갱신일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_player_id_event_id` (`player_id`, `event_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='유저 이벤트';

CREATE TABLE `world_events` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스',
  `event_data_id` bigint NOT NULL COMMENT '이벤트 데이터 아이디',
  `start_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '시작일',
  `end_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '종료일',
  `display_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '표시일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='이벤트';

CREATE TABLE `player_daily_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `type` tinyint NOT NULL COMMENT '타입',
  `progress` int NOT NULL COMMENT '진행도',
  `state` tinyint NOT NULL COMMENT '진행도',
  `rewards` json NOT NULL COMMENT '보상 정보',
  `last_updated_date` int NOT NULL COMMENT '진행 갱신일',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_player_id_type` (`player_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='일일 컨텐츠';

CREATE TABLE `player_achievements` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `data` mediumblob NOT NULL COMMENT '데이터',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='업적';

CREATE TABLE `player_quests` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `player_id` bigint NOT NULL COMMENT '플레이어 아이디',
  `step` int NOT NULL COMMENT '단계',
  `progress` bigint NOT NULL COMMENT '진행도',
  `state` tinyint NOT NULL COMMENT '상태',
  `next_stage_group_id_condition` bigint NOT NULL COMMENT '스테이지 그룹',
  `next_stage_id_condition` int NOT NULL COMMENT '스테이지',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='퀘스트';



# 석상 테이블
CREATE TABLE `player_stone_statue` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
    `enchant_level` int(10) NOT NULL COMMENT '각인 레벨',
    `enchant_exp` int(10) NOT NULL COMMENT '각인 경험치',
    `enchant_safe_grade` int(10) NOT NULL COMMENT '각인 보호등급',
    `avatar_id` bigint(20) NOT NULL COMMENT '외형',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='석상 정보';

# 석상 각인 테이블
CREATE TABLE `player_stone_statue_enchant` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
    `order_id` int(10) NOT NULL COMMENT '프리셋 아이디',
    `data` json NOT NULL COMMENT '슬롯 정보',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_player_id_order_id` (`player_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='석상 각인 정보';

# 석상 보석 테이블
CREATE TABLE `player_stone_statue_gem` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
    `gem_id` bigint(20) NOT NULL COMMENT '보석 아이디',
    `gem_level` int(10) NOT NULL COMMENT '보석 레벨',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_player_id_gem_id` (`player_id`,`gem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='석상 보석 정보';

# 석상 외형 아바타 테이블
CREATE TABLE `player_stone_statue_avatar` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint(20) NOT NULL COMMENT '플레이어 아이디',
    `avatar_id` bigint(20) NOT NULL COMMENT '아바타 아이디',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_player_id_avatar_id` (`player_id`,`avatar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='석상 아바타 정보';

CREATE TABLE `clans` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `name` varchar(32) NOT NULL COMMENT '클랜명',
    `level` int NOT NULL COMMENT '레벨',
    `exp` bigint NOT NULL COMMENT '경험치',
    `master_player_id` bigint NOT NULL COMMENT '클랜장',
    `rank` int NOT NULL COMMENT '랭킹',
    `notice` varchar(1024) NOT NULL COMMENT '공지 사항',
    `introduction` varchar(1024) NOT NULL COMMENT '소개',
    `join_type` tinyint NOT NULL COMMENT '가입 타입',
    `state` tinyint NOT NULL COMMENT '가입 타입',
    `last_change_name_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '클랜명 변경일',
    `master_last_logout_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '클랜장 마지막 로그아웃일',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_master_player_id` (`master_player_id`),
    UNIQUE KEY `udx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='클랜';

CREATE TABLE `clan_members` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint NOT NULL COMMENT '플레이어ID',
    `clan_id` bigint NOT NULL COMMENT '클랜ID',
    `role` tinyint NOT NULL COMMENT '역할',
    `accumulate_contribution` int NOT NULL COMMENT '누적 기부',
    `donate_count` int NOT NULL COMMENT '일일 기부 수',
    `donate_reset_ymd` int NOT NULL DEFAULT '0' COMMENT '기부 초기화일',
    `penalty_end_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '페널티 만료시간',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_player_id_clan_id` (`player_id`, `clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='유저 클랜';

CREATE TABLE `player_clan_join_requests` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint NOT NULL COMMENT '플레이어ID',
    `clan_id` bigint NOT NULL COMMENT '클랜ID',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
	KEY `udx_player_id_clan_id` (`player_id`, `clan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='유저 클랜 가입 신청';

CREATE TABLE `player_blocks` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
    `player_id` bigint NOT NULL COMMENT '플레이어ID',
    `target_player_id` bigint NOT NULL COMMENT '상대ID',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '갱신일',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    PRIMARY KEY (`id`),
	KEY `udx_player_id_target_player_id` (`player_id`, `target_player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='유저 차단';



CREATE TABLE `world_chats` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` int(10) NOT NULL COMMENT '채널ID',
  `name` varchar(16) NOT NULL COMMENT '이름',
  `portrait_icon` int(10) NOT NULL COMMENT '초상화 아이콘',
  `text` varchar(255) NOT NULL COMMENT '내용',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_channel_id_created_at` (`channel_id`, `created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='월드 채팅';

CREATE TABLE `world_whispers` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` int(10) NOT NULL COMMENT '채널ID',
  `sender_name` varchar(16) NOT NULL COMMENT '보낸 유저 이름',
  `sender_portrait_icon` int(10) NOT NULL COMMENT '보낸 유저 초상화 아이콘',
  `sender_player_id` int(10) NOT NULL COMMNET '보낸 플레이어 아이디',
  `receive_player_id` int(10) NOT NULL COMMNET '받는 플레이어 아이디',
  `text` varchar(255) NOT NULL COMMENT '내용',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_channel_id_receive_player_id_sender_player_id_created_at` (`channel_id`, `receive_player_id`,`sender_player_id`,`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='귓속말 채팅';

CREATE TABLE `world_clan_chats` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 아이디',
  `channel_id` int(10) NOT NULL COMMENT '채널ID',
  `clan_id` int(10) NOT NULL COMMENT '클랜ID',
  `name` varchar(16) NOT NULL COMMENT '이름',
  `portrait_icon` int(10) NOT NULL COMMENT '초상화 아이콘',
  `text` varchar(255) NOT NULL COMMENT '내용',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  PRIMARY KEY (`id`),
  KEY `idx_channel_id_clan_id_created_at` (`channel_id`, `clan_id`, `created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='클랜 채팅';