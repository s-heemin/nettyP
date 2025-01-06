package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class ResourceManager {
    private static final Logger logger = LoggerFactory.getLogger(ResourceManager.class);
    public static ResourceContext INSTANCE;

    private static boolean isInitialized() {
        return Objects.nonNull(INSTANCE);
    }

    public static void initialize(String rootPath) {
        if (isInitialized()) {
            throw new RuntimeException("resource context initialize failure - already exists");
        }

        var ctx = new ResourceContext(rootPath);
        if (!ctx.initialize()) {
            throw new RuntimeException("resource context initialize failure");
        }

        INSTANCE = ctx;
    }
}
