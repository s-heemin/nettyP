package org.supercat.growstone.config;

import org.supercat.growstone.SLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SConfig {
    private static final String CONFIG_FILE = "/properties/config.properties";
    private static final String ROOT_PATH = System.getProperty("user.dir");
    private static Config config;

    public static void init() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(ROOT_PATH + CONFIG_FILE)) {
            properties.load(fis);

            // 빌더 패턴을 사용하여 Config 객체를 생성
            Config.Builder builder = new Config.Builder()
                    .serverPort(Integer.parseInt(properties.getProperty("server.port")))
                    .debugMode(Boolean.parseBoolean(properties.getProperty("debug.mode")))
                    .dbConfiguration(properties.getProperty("sql.url"),
                            properties.getProperty("sql.user"),
                            properties.getProperty("sql.password"))
                    .resourceDir(properties.getProperty("resource.path"));

            config = builder.build();

        } catch (IOException e) {
            SLog.logException(e);
            // 예외 처리: 설정 파일 로드 실패
        }
    }
    public static Config getConfig() {
        return config;
    }
}
