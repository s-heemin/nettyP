package org.supercat.growstone;

import org.apache.commons.lang3.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.types.RedisUseType;
import org.supercat.growstone.config.SConfig;
import org.supercat.growstone.models.DMServer;
import org.supercat.growstone.netty.NettyServer;
import org.supercat.growstone.setups.SDB;

import java.net.InetAddress;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static long channelId = 0;
    public static void main(String[] args) {
        try {
            init();
            start();
            var doneSignal = new CountDownLatch(1);
            try {
                doneSignal.await();
            } catch (InterruptedException e) {
                SLog.logException(e);
            }
        } catch (Exception e) {
            SLog.logException(e);
        } finally {
            cleanUp();
        }

        logger.info("server closed");
        System.exit(-1);
    }

    private static void init() {
        // config init
        SConfig.init();
        // db excutor init
        var conf = SConfig.getConfig().getDbConf();
        SDB.init(conf.url, conf.user, conf.password);

        var model = getServerModel();
        if(Objects.isNull(model)) {
            logger.error("server model is null");
            System.exit(-1);
        }
        channelId = model.id;

        // Async init
        Async.init();

        // GameZonDate init
        GameZoneDateTime.init();
        UtcZoneDateTime.init();

        // resource init
        GameData.reloadWithExit();
        ResourceManager.initialize(SConfig.getConfig().getResourceDir());

        var redisConfigs = new ArrayList<RedisServerConfig>();
        if(SConfig.getConfig().isDebugMode()) {
            var devs = SDB.dbContext.serverRedisConfig.getAll(0).stream()
                    .collect(Collectors.toMap(x -> x.use_type, x -> x));
            for (var useType : RedisUseType.validTypes()) {
                var dev = devs.get(useType.value);
                dev.id = 0;
                dev.db_index = 0;
                dev.channel_id = model.id;
                redisConfigs.add(RedisServerConfig.of(dev));
            }
        } else {
            // 처음에는 1채널이 존재할테니 1채널에 대한 redis 설정을 복사한다.
            var bases = SDB.dbContext.serverRedisConfig.getAll(1).stream()
                    .collect(Collectors.toMap(x -> x.use_type, x-> x));
            long startDbIdx = model.id * bases.size() - bases.size() + 1;
            for(var useType : RedisUseType.values()) {
                var base = bases.get(useType.value);
                base.id = 0;
                base.db_index = startDbIdx++;
                base.channel_id = model.id;
                SDB.dbContext.serverRedisConfig.insert(base);
            }
        }
        // redis
        SRedis.INSTANCE = SRedis.of(redisConfigs);
        SRedis.INSTANCE.init();

        // 월드 init
        World.INSTANCE = World.of(model);
        World.INSTANCE.initialize();
    }

    private static void start() {
        // 맨 마지막에
        NettyServer.INSTANCE.serverStart();
    }

    private static void cleanUp() {
        try {
            // 3초 후 클린업
            ThreadUtils.sleep(Duration.ofSeconds(3));
            Async.shutdown();
            SDB.cleanUp();
        } catch (Exception e) {
            SLog.logException(e);
        }
    }

    private static DMServer getServerModel() {
        var config = SConfig.getConfig();
        if (Objects.isNull(config)) {
            logger.error("server config is null");
            System.exit(-1);
            return null;
        }

        if (config.isDebugMode()) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                var model = SDB.dbContext.server.getByInstanceId(inetAddress.getHostAddress());
                if (Objects.isNull(model)) {
                    model = DMServer.of(inetAddress.getHostAddress(), config.getServerPort());
                    SDB.dbContext.server.insert(model);
                }
                return model;
            } catch (Exception e) {
                SLog.logException(e);
            }
        } else {
            try {
                var instanceId = EC2InstanceUtil.getInstanceId();
                var model = SDB.dbContext.server.getByInstanceId(instanceId);
                if (Objects.isNull(model)) {
                    model = DMServer.of(instanceId, config.getServerPort());
                    SDB.dbContext.server.insert(model);
                }

                return model;
            } catch (Exception e) {
                SLog.logException(e);
            }
        }
        return null;
    }
}
