package org.supercat.growstone.components;

public class Category {

    @FunctionalInterface
    public interface Use {
        int use(long dataId, long count);
    }

    @FunctionalInterface
    public interface Add {
        int add(long dataId, long count);
    }
}
