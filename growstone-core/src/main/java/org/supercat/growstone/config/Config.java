package org.supercat.growstone.config;


public class Config {
    private final int serverPort;
    private final boolean debugMode;
    public  String resourceDir = "";
    private dbConfig dbConf;

    Config(Builder builder) {
        this.serverPort = builder.serverPort;
        this.debugMode = builder.debugMode;
        this.dbConf = builder.dbConf;
        this.resourceDir = builder.resourceDir;
    }

    public String getResourceDir() {
        return resourceDir;
    }
    public int getServerPort() {
        return serverPort;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public dbConfig getDbConf() {
        return dbConf;
    }

    public static class Builder {
        private int serverPort;
        private boolean debugMode;
        private dbConfig dbConf;
        private String resourceDir;

        public Builder serverPort(int serverPort) {
            this.serverPort = serverPort;
            return this;
        }

        public Builder debugMode(boolean debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        public Builder dbConfiguration(String url, String user, String password) {
            this.dbConf = dbConfig.of(url, user, password);
            return this;
        }

        public Builder resourceDir(String resourceDir) {
            this.resourceDir = resourceDir;
            return this;
        }
        public Config build() {
            return new Config(this);
        }
    }
}
