package org.supercat.growstone.managers;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZEvent;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.*;
import org.supercat.growstone.types.ResetType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ResourceEventManager implements IResourceManageable {
    private static final Logger logger = LoggerFactory.getLogger(ResourceEventManager.class);

    private final ResourceContext ctx;
    private final ImmutableMap<Long, ResourceEvent> events;
    public static ResourceEventManager of(ResourceContext ctx) {
        return new ResourceEventManager(ctx);
    }

    private ResourceEventManager(ResourceContext ctx) {
        this.ctx = ctx;
        this.events = load(ctx.absolutePathBy(ResourceFile.EVENTS));
    }

    public static ImmutableMap<Long, ResourceEvent> load(Set<String> absolutePaths) {
        var tempEventMap = new ConcurrentHashMap<Long, ResourceEvent>();
        var elements = XMLHelper.loadAll(absolutePaths, "Event");
        for (Element e : elements) {
            var type = XMLHelper.getChildEnum(e, "Type", ZEvent.Type.NONE);
            ResourceEvent res = ResourceEventFactory.build(type, e);
            ResourceCollectors.tryPut(tempEventMap, res);
        }

        return ImmutableMap.copyOf(tempEventMap);
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceEvent> T get(long eventId) {
        T resEvent = (T) events.get(eventId);

        return resEvent;
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceEvent> List<T> getAllByType(ZEvent.Type type) {
        return (List<T>) events.values().stream()
                .filter(x -> x.type == type)
                .collect(Collectors.toList());
    }


    public boolean verify() {
        if(!checkAttendanceEvent()) {
            return false;
        }

        if(!checkCumulativeConsumeEvent()) {
            return false;
        }

        return true;
    }

    public boolean checkCumulativeConsumeEvent() {
        var errors = new ArrayList<String>();
        events.values().stream()
                .filter(x -> x.type == ZEvent.Type.CUMULATIVE_CONSUMPTION_EVENT)
                .forEach(x -> {
                    if (!(x instanceof ResourceCumulativeConsumeEvent)) {
                        errors.add(String.format("event is not an attendance event - eventId ({%d})", x.id));
                        return;
                    }

                    var resEvent = (ResourceCumulativeConsumeEvent) x;
                    for(var kv : resEvent.rewards.entrySet()) {
                        var amount = kv.getKey();
                        var rewards = kv.getValue();

                        if(amount <= 0) {
                            errors.add(String.format("event cumulative consume amount is invalid - eventId ({%d})", x.id));
                        }

                        for(var reward : rewards) {
                            if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                                errors.add(String.format("event cumulative consume reward is invalid - " +
                                                "eventId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                        , x.id, reward.type.getNumber(), reward.rewardId, reward.count));
                            }
                        }
                    }

                    for(var shopId : resEvent.exceptShopIds) {
                        var shop = ctx.shop.get(shopId);
                        if(Objects.isNull(shop)) {
                            errors.add(String.format("event cumulative consume except shop id is invalid - eventId ({%d})", x.id));
                        }
                    }
                });

        errors.forEach(logger::error);

        return errors.isEmpty();
    }

    public boolean checkAttendanceEvent() {
        var errors = new ArrayList<String>();
        events.values().stream()
                .filter(x -> x.type == ZEvent.Type.ATTENDANCE || x.type == ZEvent.Type.FIRST_PURCHASE_ATTENDANCE)
                .forEach(x -> {
                    if (!(x instanceof ResourceAttendanceEvent)) {
                       errors.add(String.format("event is not an attendance event - eventId ({%d})", x.id));
                        return;
                    }

                    var attendanceEvent = (ResourceAttendanceEvent) x;
                    if(attendanceEvent.resetType == ResetType.NONE) {
                        errors.add(String.format("event reset type is invalid - eventId ({%d})", x.id));
                    }

                    if(x.type == ZEvent.Type.ATTENDANCE) {
                        if(attendanceEvent.attendanceCountRewards.size() <= 0) {
                            errors.add(String.format("event attendance count rewards is empty - eventId ({%d})", x.id));
                        }

                        if(attendanceEvent.attendanceCountRewards.size() > 3) {
                            errors.add(String.format("event attendance count rewards is invalid - eventId ({%d})", x.id));
                        }

                        for(var rewards : attendanceEvent.attendanceCountRewards.values()) {
                            if(rewards.size() > 2) {
                                errors.add(String.format("event attendance reward count is invalid - eventId ({%d})", x.id));
                            }
                            for(var reward : rewards) {
                                if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                                    errors.add(String.format("event attendance count reward is invalid - eventId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                            , x.id, reward.type.getNumber(), reward.rewardId, reward.count));
                                }
                            }
                        }
                    }

                    if(attendanceEvent.attendanceRewards.size() <= 0) {
                        errors.add(String.format("attendance rewards is empty - eventId ({%d})", x.id));
                    }

                    if(attendanceEvent.lastDay <= 0) {
                        errors.add(String.format("event last day is invalid - eventId ({%d})", x.id));
                    }

                    for(var rewards : attendanceEvent.attendanceRewards.values()) {
                        for(var reward : rewards) {
                            if(!checkReward(ctx, reward.type, reward.rewardId, reward.count)) {
                                errors.add(String.format("attendance reward is invalid - " +
                                                "eventId ({%d}), category({%d}), dataId({%d}), count({%d})"
                                        , x.id, reward.type.getNumber(), reward.rewardId, reward.count));
                            }
                        }
                    }

                    for(var shopId : attendanceEvent.exceptShopIds) {
                        var shop = ctx.shop.get(shopId);
                        if(Objects.isNull(shop)) {
                            errors.add(String.format("event cumulative consume except shop id is invalid - eventId ({%d})", x.id));
                        }
                    }
                });

        errors.forEach(logger::error);

        return errors.isEmpty();
    }
}
