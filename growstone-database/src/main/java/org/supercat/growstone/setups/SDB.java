package org.supercat.growstone.setups;

import org.supercat.growstone.dbContexts.DBContext;

public class SDB {
    public static DBContext dbContext;
    public static void init(String host, String userName, String password) {
        dbContext = DBContext.of(host, userName, password);
    }

    public static void cleanUp() {
        dbContext.executor.cleanUp();
    }

}
