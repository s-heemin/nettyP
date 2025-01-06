package org.supercat.growstone.config;

// TODO : 디비 정보는 일단 이렇게 하고, 나중에 정리하면서 변경 필요
public class dbConfig {
    public String url;
    public String user;
    public String password;

    public static dbConfig of(String url, String user, String password) {
        var conf = new dbConfig();
        conf.url = url;
        conf.user = user;
        conf.password = password;

        return conf;
    }
}
