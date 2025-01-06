package org.supercat.growstone.components.playerComponents;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.supercat.growstone.network.messages.TAdvertiseInfo;
import com.supercat.growstone.network.messages.ZContentAdvertise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.SLog;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.events.EventSubscribeBuilder;
import org.supercat.growstone.events.EventType;
import org.supercat.growstone.models.DMPlayerAdvertise;
import org.supercat.growstone.setups.SDB;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerAdvertiseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerAdvertiseComponent.class);

    private WorldPlayer player;
    private LoadingCache<ZContentAdvertise.Type, DMPlayerAdvertise> cache;


    public PlayerAdvertiseComponent(WorldPlayer player) {
        this.player = player;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(CacheLoader.from(this::load));
        this.player.topic.subscribes(EventSubscribeBuilder.newBuilder().on(EventType.PLAYER_DAILY_RESET,
                this::handle_EventPlayerDailyReset));
    }

    private void handle_EventPlayerDailyReset(EventPlayerDailyResetSchedule event) {
        for(var type : ZContentAdvertise.Type.values()) {
            if(type == ZContentAdvertise.Type.NONE ||
                    type == ZContentAdvertise.Type.UNRECOGNIZED) {
                continue;
            }

            var model = getOrCreate(type);
            if(Objects.isNull(model)) {
                continue;
            }

            model.view_count = 0;
            SDB.dbContext.advertise.save(model);
        }
    }

    private DMPlayerAdvertise load(ZContentAdvertise.Type type) {
        return SDB.dbContext.advertise.getOrDefault(player.getId(), type.getNumber());
    }

    public DMPlayerAdvertise getOrCreate(ZContentAdvertise.Type type) {
        try {
            return cache.getUnchecked(type);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }

    public int getViewCommercial(ZContentAdvertise.Type type) {
        return getOrCreate(type).view_count;
    }
    public void addViewCommercial(ZContentAdvertise.Type type) {
        var model = getOrCreate(type);
        ++model.view_count;
        SDB.dbContext.advertise.save(model);
    }

    public boolean isEnableUseAcceleratorByViewCommercial() {
        var model = getOrCreate(ZContentAdvertise.Type.ACCELERATOR);
        return model.view_count < GameData.PLAYER.acceleratorDayMaxCountByCommercial;
    }

    public TAdvertiseInfo getAdvertiseInfo(ZContentAdvertise.Type type) {
        return TAdvertiseInfo.newBuilder()
                .setType(type)
                .setViewCount(getViewCommercial(type))
                .build();
    }

    public List<TAdvertiseInfo> getAdvertiseInfos(List<ZContentAdvertise.Type> types) {
        return types.stream()
                .filter(x -> x != ZContentAdvertise.Type.NONE)
                .map(x -> getAdvertiseInfo(x))
                .collect(Collectors.toList());
    }
}
