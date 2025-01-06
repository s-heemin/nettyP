package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.TMailReward;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZMail;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.containers.ContentReward;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.jsons.JMPlayerMailReward;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MailRules {
    public static List<JMPlayerMailReward> getJMPlayerMailReward(List<ResourceReward> rewards) {
        return rewards.stream().map(reward -> JMPlayerMailReward.of(reward.type, reward.rewardId, reward.count))
                .toList();
    }

    public static List<JMPlayerMailReward> getJMPlayerMailRewardByContentReward(List<ContentReward> rewards) {
        return rewards.stream().map(reward -> JMPlayerMailReward.of(reward.type, reward.dataId, reward.count))
                .toList();
    }

    public static JMPlayerMailReward getJMPlayerMailReward(ZCategory.Type type, int rewardId, int count) {
        return JMPlayerMailReward.of(type, rewardId, count);
    }
    public static List<TMailReward> getTMailRewards(List<JMPlayerMailReward> rewards) {
        return rewards.stream().map(reward -> TMailReward.newBuilder()
                        .setCategory(reward.type)
                        .setDataId(reward.data_id)
                        .setCount(reward.count)
                        .build())
                .toList();
    }
    public static List<TMailReward> getTMailRewards(String rewards) {
        var array = JsonConverter.of(rewards, JMPlayerMailReward[].class);
        return Arrays.stream(array)
                .filter(x -> Objects.nonNull(ResourceManager.INSTANCE.item.getItem(x.data_id)))
                .map(reward -> TMailReward.newBuilder()
                        .setCategory(reward.type)
                        .setDataId(reward.data_id)
                        .setCount(reward.count)
                        .build())
                .toList();
    }
}
