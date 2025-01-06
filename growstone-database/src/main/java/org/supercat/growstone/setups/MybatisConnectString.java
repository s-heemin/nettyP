package org.supercat.growstone.setups;

public class MybatisConnectString {
    public static String fetchString(String host, String username, String password) {
        var target = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "    <!DOCTYPE configuration\n" +
                "    PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n" +
                "    \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
                "<configuration>\n" +
                "    <environments default=\"development\">\n" +
                "        <environment id=\"development\">\n" +
                "            <transactionManager type=\"JDBC\"/>\n" +
                "            <dataSource type=\"org.supercat.growstone.HikariDataSourceFactory\">\n" +
                "                <property name=\"jdbcUrl\" value=\"%s&amp;characterEncoding=utf8&amp;reconnect=true&amp;useLocalSessionState=true\"/>\n" +
                "                <property name=\"username\" value=\"%s\"/>\n" +
                "                <property name=\"password\" value=\"%s\"/>\n" +
                "                <property name=\"maximumPoolSize\" value=\"10\"/>\n" +
                "                <property name=\"minimumIdle\" value=\"5\"/>\n" +
                "                <property name=\"idleTimeout\" value=\"300000\"/>\n" +
                "                <property name=\"maxLifetime\" value=\"1800000\"/>\n" +
                "                <property name=\"connectionTimeout\" value=\"30000\"/>\n" +
                "                <property name=\"connectionTestQuery\" value=\"SELECT 1\"/>\n" +
                "            </dataSource>\n" +
                "        </environment>\n" +
                "    </environments>\n" +
                "</configuration>", host, username, password);
        return target;
    }
}
