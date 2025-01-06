import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.config.SConfig;
import org.supercat.growstone.setups.SDB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class BaseServerTests {
    @BeforeAll
    public static void setup() throws UnknownHostException {
        // config init
        SConfig.init();
        // db excutor init
        var conf = SConfig.getConfig().getDbConf();
        SDB.init(conf.url, conf.user, conf.password);
        // Async init
        Async.init();

        GameZoneDateTime.init();
        UtcZoneDateTime.init();
        // resource init
        GameData.reloadWithExit();
        ResourceManager.initialize(SConfig.getConfig().getResourceDir());


        // 월드 init
        InetAddress inetAddress = InetAddress.getLocalHost();
        var model = SDB.dbContext.server.getByInstanceId(inetAddress.getHostAddress());
        if(Objects.isNull(model)) {
            System.exit(-1);
            return;
        }

        var redisConfigs = new ArrayList<RedisServerConfig>();
        SDB.dbContext.serverRedisConfig.getAll(model.id).forEach(x -> redisConfigs.add(RedisServerConfig.of(x)));

        // redis
        SRedis.INSTANCE = SRedis.of(redisConfigs);
        SRedis.INSTANCE.init();

        World.INSTANCE = World.of(model);
        World.INSTANCE.initialize();
    }

    @Test
    void itemGeneratorTest() {
        var itemIds = new HashSet<Long>();
        for (int i = 0; i < 5000000; i++) {
           itemIds.add(World.itemIDGenerator.nextId());
        }

        Assertions.assertEquals(5000000, itemIds.size());
    }

    @Test
    void random() {
        var today = GameZoneDateTime.now().withHour(Constants.DAILY_RESET_HOUR).withMinute(0).withSecond(0).withNano(0);
        int a = 3;
    }
}
