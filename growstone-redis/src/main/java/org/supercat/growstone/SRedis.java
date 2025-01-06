package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.types.RedisUseType;

import java.util.List;
import java.util.Objects;

public class SRedis {
    private static final Logger logger = LoggerFactory.getLogger(SRedis.class);

    public static SRedis INSTANCE;

    public ContentRedisContext content;
    public RankRedisContext rank;
    public ChatRedisContext chat;

    private SRedis(List<RedisServerConfig> models) {
        var contentConfig = RedisServerConfig.fetch(RedisUseType.CONTENTS, models);
        if (Objects.isNull(contentConfig)) {
            logger.error("CONTENTS RedisServerConfig is null");
            System.exit(-1);
        }
        content = ContentRedisContext.of(contentConfig);

        var rankConfig = RedisServerConfig.fetch(RedisUseType.RANK, models);
        if (Objects.isNull(rankConfig)) {
            logger.error("RANK RedisServerConfig is null");
            System.exit(-1);
        }
        rank = RankRedisContext.of(rankConfig);

        var chatConfig = RedisServerConfig.fetch(RedisUseType.CHAT, models);
        if (Objects.isNull(chatConfig)) {
            logger.error("CHAT RedisServerConfig is null");
            System.exit(-1);
        }
        chat = ChatRedisContext.of(chatConfig);
    }

    public static SRedis of(List<RedisServerConfig> configs) {
        return new SRedis(configs);
    }

    public void init() {
        content.init();
        rank.init();
        chat.init();
    }
}
