package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.jsons.JMPlayerFarmCookSlots;
import org.supercat.growstone.models.DMPlayerFarmCook;
import org.supercat.growstone.models.DMPlayerFarmHistory;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.FarmRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerFarmComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerFarmComponent.class);

    private WorldPlayer player;
    private DMPlayerFarmCook cookModel;
    private Map<Integer, TFarmCookSlot> cookSlots;

    public static void addHistory(long playerId, ZFarmHistory.Type type, Object... arguments) {
        var data = FarmRules.convertToHistortyData(arguments);
        var model = DMPlayerFarmHistory.of(playerId, type.getNumber(), data);
        SDB.dbContext.farmHistory.insertAsync(model);
    }

    public PlayerFarmComponent(WorldPlayer player) {
        this.player = player;
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder().on(EventType.PLAYER_DAILY_RESET,
                this::handle_EventPlayerDailyReset));
    }

    private void handle_EventPlayerDailyReset(EventPlayerDailyResetSchedule event) {
        long count = player.itemBag.getItemCount(GameData.FARM.SEED_ITEM_ID);

        //무료 씨앗 개수보다 많다면 할게 없다
        if (count >= (long) GameData.FARM.MAX_FREE_SEEDS) {
            return;
        }

        long addCount = (long) GameData.FARM.MAX_FREE_SEEDS - count;
        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.FARM.SEED_ITEM_ID, addCount);
    }

    public void initialize() {
        this.cookModel = SDB.dbContext.farmCook.getOrDefault(player.getId());
        if (Objects.isNull(cookModel.slots)) {
            this.cookSlots = initSlots();
        } else {
            this.cookSlots = JMPlayerFarmCookSlots.ofTFarmCookSlots(cookModel.slots);
        }

        //todo 광고 구매권을 구매한 상태라면 슬롯과 상관없이 버프 지급 필요
    }

    private Map<Integer, TFarmCookSlot> initSlots() {
        var slots = new HashMap<Integer, TFarmCookSlot>();
        int count = GameData.FARM.FARM_COOK_BUFF_IDS.size();
        for (int slotIndex = 0; slotIndex < count; ++slotIndex) {
            final int slotId = slotIndex + 1;
            slots.put(slotId, resetSlot(slotId));
        }

        return slots;
    }

    private TFarmCookSlot resetSlot(int slotId) {
        return TFarmCookSlot.newBuilder()
                .setSlotId(slotId)
                .setUntilAt(0L)
                .build();
    }

    public List<TFarmHistory> getAllByTFarmHistory(Instant at) {
        int limitCount = GameData.FARM.FARM_HISTORY_LIMIT_COUNT;
        var timeAt = at.minus(GameData.FARM.FARM_HISTORTY_HOUR, ChronoUnit.HOURS);
        return SDB.dbContext.farmHistory.getAll(player.getId(), limitCount, timeAt)
                .stream()
                .map(x -> TBuilderOf.ofFarmHistory(x, player.getPortraitIcon()))
                .collect(Collectors.toList());
    }

    public int getCookInfo(ZFarmCookInfoResponse.Builder response) {
        response.setLevel(cookModel.level)
                .addAllSlots(cookSlots.values());
        return StatusCode.SUCCESS;
    }

    public void save() {
        cookModel.slots = JMPlayerFarmCookSlots.of(cookSlots).toJson().toString();
        SDB.dbContext.farmCook.save(cookModel);
    }

    public int getCommercialInfo(ZFarmCommercialInfoResponse.Builder response) {
        response.setViewCount(player.advertise.getViewCommercial(ZContentAdvertise.Type.FARM_SEED));
        return StatusCode.SUCCESS;
    }

    public int advertiseSeed(ZFarmCommercialSeedViewResponse.Builder response) {
        var viewCount = player.advertise.getViewCommercial(ZContentAdvertise.Type.FARM_SEED);
        if (viewCount >= GameData.FARM.MAX_COMMERCIAL_SEED_ITEM_VIEW_COUNT) {
            return StatusCode.INVALID_REQUEST;
        }

        long count = player.itemBag.getItemCount(GameData.FARM.SEED_ITEM_ID);
        //무료 씨앗 개수보다 많다면 할게 없다
        if (count >= (long) GameData.FARM.MAX_FREE_SEEDS) {
            return StatusCode.INVALID_REQUEST;
        }

        long addCount = (long) GameData.FARM.MAX_FREE_SEEDS - count;
        player.categoryFilter.add(ZCategory.Type.ITEM, GameData.FARM.SEED_ITEM_ID, addCount);

        player.advertise.addViewCommercial(ZContentAdvertise.Type.FARM_SEED);

        response.setViewCount(++viewCount);

        return StatusCode.SUCCESS;
    }

    public int cook(ZFarmCookResponse.Builder response) {
        long exp = GameData.FARM.getCookExp(cookModel.level);
        if (exp <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        long itemCount = player.itemBag.getItemCount(GameData.FARM.PLANT_ITEM_ID);
        if (itemCount < exp) {
            return StatusCode.NOT_ENOUGH_MATERIAL;
        }

        //필요경험치 만큼 아이템 감소
        int status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.FARM.PLANT_ITEM_ID, exp);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        //현재 레벨에 맞는 경험치 삽
        cookModel.level += 1;
        response.setLevel(cookModel.level)
                .build();

        //TODO 버프 관련 작업이 필요

        return StatusCode.SUCCESS;
    }

    public int cookCommercial(int slotId, ZFarmCookCommercialViewResponse.Builder response, Instant at) {
        //TODO 광고 제거권 체크 필요
        var slot = cookSlots.get(slotId);
        if (Objects.isNull(slot)) {
            return StatusCode.INVALID_REQUEST;
        }

        //아직 시간이 남아 있다면
        if (slot.getUntilAt() > at.getEpochSecond()) {
            return StatusCode.INVALID_REQUEST;
        }

        var untilAt = at.plus(GameData.FARM.FARM_COOK_COMMERCIAL_BUFF_DURATION, ChronoUnit.MINUTES);
        var temp = slot.toBuilder()
                .setUntilAt(untilAt.getEpochSecond())
                .build();

        cookSlots.put(slotId, temp);
        response.setSlotId(slotId)
                .setUntilAt(untilAt.getEpochSecond());

        //TODO 버프 추가 필요

        return StatusCode.SUCCESS;
    }

}

