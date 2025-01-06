package org.supercat.growstone.components.playerEventComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.events.EventPlayerBuyShopItemUseDiamond;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.events.ResourceAttendanceEvent;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.EventRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerEventAttendance extends PlayerEvent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerEventAttendance.class);

    public ResourceAttendanceEvent resEvent;

    public PlayerEventAttendance(long eventId) {
        super(eventId);
    }

    public void setOwner(WorldPlayer player, DMPlayerEvent model) {
        this.player = player;
        setModel(model);
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_BUY_SHOP_ITEM_USE_DIAMOND, this::handle_EventPlayerBuyShopItemUseDiamond));

        this.resEvent = ResourceManager.INSTANCE.event.get(model.event_data_id);
    }

    private void handle_EventPlayerBuyShopItemUseDiamond(EventPlayerBuyShopItemUseDiamond event) {
        var model = getModel();
        if (Objects.isNull(model)) {
            return;
        }

        if (model.state != ZEventProgress.State.NOT_STARTED.getNumber() && resEvent.type == ZEvent.Type.FIRST_PURCHASE_ATTENDANCE) {
            return;
        }

        if (resEvent.exceptShopIds.contains(event.shopDataId)) {
            return;
        }

        int status = World.INSTANCE.event.isActiveEvent(model.event_id);
        if (!StatusCode.isSuccess(status)) {
            return;
        }

        ++model.progress;

        model.last_updated_date = UtcZoneDateTime.getYmd();

        model.state = ZEventProgress.State.COMPLETE.getNumber();

        SDB.dbContext.event.save(model);
    }

    public void attendance() {
        var model = getModel();
        if (Objects.isNull(model)) {
            return;
        }

        if (Objects.isNull(resEvent)) {
            logger.error("invalid event type playerId({}) eventId({})", player.getId(), model.event_id);
            return;
        }

        if (model.state == ZEventProgress.State.NOT_STARTED.getNumber()) {
            return;
        }

        int status = World.INSTANCE.event.isActiveEvent(model.event_id);
        if (!StatusCode.isSuccess(status)) {
            return;
        }

        if (!EventRules.isEnableAttendance(model.last_updated_date, resEvent.resetType)) {
            return;
        }

        if (model.progress + 1 > resEvent.lastDay) {
            return;
        }

        ++model.progress;
        model.last_updated_date = EventRules.getResetDay(resEvent.resetType);
        model.state = ZEventProgress.State.COMPLETE.getNumber();

        save();

        player.sendPacket(0L, ZPlayerEventNotify.newBuilder()
                .setPlayerEvents(TBuilderOf.buildOf(model)));
    }

    @Override
    public int getReward(int value, TPlayerEvent.Builder tEvent, List<TContentReward> outRewards) {
        var model = getModel();
        if (Objects.isNull(model)) {
            return StatusCode.NOT_EXIST_EVENT;
        }

        if (model.state != ZEventProgress.State.COMPLETE.getNumber()) {
            return StatusCode.INVALID_REQUEST;
        }

        if (Objects.isNull(resEvent)) {
            logger.error("invalid event type playerId({}) eventId({})", player.getId(), model.event_id);
            return StatusCode.INVALID_RESOURCE;
        }

        int status = World.INSTANCE.event.isActiveEvent(model.event_id);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        var playerRewards = EventRules.getAttendanceReward(model.rewards);
        int lastRewardProgress = playerRewards.stream().max(Integer::compareTo).orElse(0);
        if (model.progress <= lastRewardProgress) {
            return StatusCode.ALREADY_GET_REWARD;
        }

        for (int i = lastRewardProgress + 1; i <= model.progress; i++) {
            var rewards = EventRules.computeAttendanceRewards(i, resEvent);
            for (var reward : rewards) {
                status = player.categoryFilter.add(reward, 1);
                if (!StatusCode.isSuccess(status)) {
                    logger.error("failed to add reward playerId({}) eventId({}) progress({})", player.getId(), model.event_id, i);
                    continue;
                }

                outRewards.add(TContentReward.newBuilder()
                        .setCategory(reward.type)
                        .setDataId(reward.rewardId)
                        .setCount(reward.count)
                        .build());
            }

            playerRewards.add(i);
        }

        model.rewards = JsonConverter.to(playerRewards);
        model.state = ZEventProgress.State.REWARDED.getNumber();

        save();

        tEvent.mergeFrom(TBuilderOf.buildOf(model));

        return StatusCode.SUCCESS;
    }
}
