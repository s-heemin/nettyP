package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLog {
    private static final Logger logger = LoggerFactory.getLogger(SLog.class);

    public static void logException(Exception e) {
        String border = "------------------------------------------exception log------------------------------------------";

        logger.error(border);
        logger.error("{}", e.toString());
        for (StackTraceElement elem : e.getStackTrace()) {
            logger.error("\t at " + elem.toString());
        }
        logger.error(border);
    }

}
