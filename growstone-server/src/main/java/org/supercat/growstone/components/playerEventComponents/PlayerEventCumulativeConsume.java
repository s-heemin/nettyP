package org.supercat.growstone.components.playerEventComponents;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TPlayerEvent;
import com.supercat.growstone.network.messages.ZEventProgress;
import com.supercat.growstone.network.messages.ZPlayerEventNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.rules.EventRules;
import org.supercat.growstone.rules.RewardRules;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.player.WorldPlayer;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerEventCumulativeConsume extends PlayerEvent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerEventCumulativeConsume.class);

    public ResourceCumulativeConsumeEvent resEvent;
    public PlayerEventCumulativeConsume(long eventId) {
        super(eventId);
    }

    private void handle_EventPlayerBuyShopItemUseDiamond(EventPlayerBuyShopItemUseDiamond event) {
        var model = getModel();
        if(Objects.isNull(model)) {
            return;
        }

        if(event.value <= 0) {
            return;
        }

        if(resEvent.exceptShopIds.contains(event.shopDataId)) {
            return;
        }

        int status = World.INSTANCE.event.isActiveEvent(model.event_id);
        if(!StatusCode.isSuccess(status)) {
            return;
        }

        model.progress += event.value;

        model.last_updated_date = Instant.now().getEpochSecond();

        SDB.dbContext.event.save(model);

        player.sendPacket(0L, ZPlayerEventNotify.newBuilder()
                .setPlayerEvents(TBuilderOf.buildOf(model)));
    }

    public void setOwner(WorldPlayer player, DMPlayerEvent model) {
        this.player = player;

        setModel(model);

        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_BUY_SHOP_ITEM_USE_DIAMOND, this::handle_EventPlayerBuyShopItemUseDiamond));

        this.resEvent = ResourceManager.INSTANCE.event.get(model.event_data_id);
    }
    @Override
    public int getReward(int value, TPlayerEvent.Builder tEvent, List<TContentReward> outRewards) {
        var model = getModel();
        if(Objects.isNull(model)) {
            return StatusCode.NOT_EXIST_EVENT;
        }

        if(Objects.isNull(resEvent)) {
            logger.error("invalid event type playerId({}) eventId({})", player.getId(), model.event_id);
            return StatusCode.INVALID_RESOURCE;
        }

        int status = World.INSTANCE.event.isActiveEvent(model.event_id);
        if(!StatusCode.isSuccess(status)) {
            return status;
        }

        if(model.progress < value) {
            return StatusCode.INVALID_REQUEST;
        }

        var rewards = resEvent.rewards.get(value);
        if(Objects.isNull(rewards)) {
            return StatusCode.INVALID_REQUEST;
        }

        var playerRewards = RewardRules.getReward(model.rewards);
        if(playerRewards.contains(value)) {
            return StatusCode.ALREADY_GET_REWARD;
        }

        //보상을 받을 수 있다.
        for(var reward : rewards) {
            status = player.categoryFilter.add(reward, 1);
            if(!StatusCode.isSuccess(status)) {
                logger.error("failed to add reward playerId({}) eventId({})", player.getId(), model.event_id);
                continue;
            }

            outRewards.add(TContentReward.newBuilder()
                    .setCategory(reward.type)
                    .setDataId(reward.rewardId)
                    .setCount(reward.count)
                    .build());
        }

        playerRewards.add(value);

        model.rewards = JsonConverter.to(playerRewards);

        save();

        tEvent.mergeFrom(TBuilderOf.buildOf(model));

        return StatusCode.SUCCESS;
    }
}
