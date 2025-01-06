package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZDailyContent;
import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.avatars.ResourceAvatar;
import org.supercat.growstone.events.ResourceDailyContent;
import org.supercat.growstone.events.ResourceEvent;
import org.supercat.growstone.events.ResourceEventFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ResourceDailyContentManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceDailyContentManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceDailyContent> dailyContents;

    public static ResourceDailyContentManager of(ResourceContext ctx) {
        return new ResourceDailyContentManager(ctx);
    }

    private ResourceDailyContentManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.dailyContents = load(ResourceDailyContent::new, ctx.absolutePathBy(ResourceFile.DAILY_CONTENTS), "DailyContent");
    }

    public ResourceDailyContent getByType(ZDailyContent.Type type) {
        return dailyContents.values().stream()
                .filter(dailyContent -> dailyContent.type == type && dailyContent.visible)
                .findFirst()
                .orElse(null);
    }

    public boolean verify() {
        if (!checkStonePressType()) {
            return false;
        }

        if(!checkAttendanceType()) {
            return false;
        }

        return true;
    }

    private boolean checkStonePressType() {
        var errors = new ArrayList<String>();
        int visibleCount = 0;
        for (var dailyContent : dailyContents.values()) {
            if (dailyContent.type != ZDailyContent.Type.STONE_PRESS) {
                continue;
            }

            visibleCount++;

            if (dailyContent.dailyCount <= 1) {
                errors.add(String.format("stone press daily count is invalid - %d", dailyContent.id));
            }

            if (dailyContent.coolTimeSecond <= 0) {
                errors.add(String.format("stone press cool time is invalid - %d", dailyContent.id));
            }

            if (dailyContent.gachaRewards.isEmpty() || !dailyContent.dailyRewards.isEmpty()) {
                errors.add(String.format("stone press reward is invalid - %d", dailyContent.id));
                continue;
            }

            for (var reward : dailyContent.gachaRewards) {
                if (reward.ratio <= 0) {
                    errors.add(String.format("stone press reward ratio is invalid - %d", dailyContent.id));
                }

                if (!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                    errors.add(String.format("stone press reward is invalid - " +
                                    "id ({%d}), category({%d}), dataId({%d}), count({%d})"
                            , dailyContent.id, reward.type.getNumber(), reward.rewardId, reward.count));
                }
            }
        }

        if (visibleCount > 1) {
            errors.add("stone press visible must one");
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }

    private boolean checkAttendanceType() {
        var errors = new ArrayList<String>();
        int visibleCount = 0;
        for (var dailyContent : dailyContents.values()) {
            if (dailyContent.type != ZDailyContent.Type.ATTENDANCE) {
                continue;
            }

            if (dailyContent.dailyCount != 1) {
                errors.add(String.format("daily count is invalid - %d", dailyContent.id));
            }

            if (dailyContent.coolTimeSecond != 0) {
                errors.add(String.format("cool time is invalid - %d", dailyContent.id));
            }

            if (!dailyContent.gachaRewards.isEmpty() || dailyContent.dailyRewards.isEmpty()) {
                errors.add(String.format("attendance reward is invalid - %d", dailyContent.id));
                continue;
            }

            var dayCheck = new HashSet<Integer>();
            for (var kv : dailyContent.dailyRewards.entrySet()) {
                var day = kv.getKey();
                var reward = kv.getValue();

                if (day < 1 || day > 7) {
                    errors.add(String.format("attendance day is invalid - %d", dailyContent.id));
                }

                dayCheck.add(day);

                if (reward.ratio != 0) {
                    errors.add(String.format("attendance ratio is invalid - %d", dailyContent.id));
                }

                if (!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                    errors.add(String.format("stone press reward is invalid - " +
                                    "id ({%d}), category({%d}), dataId({%d}), count({%d})"
                            , dailyContent.id, reward.type.getNumber(), reward.rewardId, reward.count));
                }
            }

            if(dayCheck.size() != 7) {
                errors.add(String.format("attendance day must have 7 day - %d", dailyContent.id));
            }
        }


        if (visibleCount > 1) {
            errors.add("attendance visible must one");
        }

        errors.forEach(logger::error);
        return errors.isEmpty();
    }
}
