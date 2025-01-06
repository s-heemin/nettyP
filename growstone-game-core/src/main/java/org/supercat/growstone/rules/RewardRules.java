package org.supercat.growstone.rules;

import org.supercat.growstone.JsonConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RewardRules {
    public static List<Integer> getReward(String rewards) {
        var l = JsonConverter.of(rewards, Integer[].class);
        if(Objects.isNull(l)) {
            return List.of();
        }

        return Arrays.stream(l).collect(Collectors.toList());
    }
}
