package org.supercat.growstone.options;

import com.supercat.growstone.network.messages.TItem;
import org.supercat.growstone.items.ResourceItem;

public class RewardFunctions {
    @FunctionalInterface
    public interface Remove {
        int remove(long rewardId,  int count);
    }

    @FunctionalInterface
    public interface Add {
        int add(long rewardId,  int count);
    }
}
