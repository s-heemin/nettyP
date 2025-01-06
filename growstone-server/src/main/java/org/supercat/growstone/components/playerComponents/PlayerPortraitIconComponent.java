package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.TPortraitIcon;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZPortraitNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerPortraitIcon;
import org.supercat.growstone.setups.SDB;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerPortraitIconComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerPortraitIconComponent.class);

    private WorldPlayer player;

    private ConcurrentHashMap<Long, DMPlayerPortraitIcon> models = new ConcurrentHashMap<>();

    public PlayerPortraitIconComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        models = SDB.dbContext.portraitIcon.getByPlayerId(player.getId()).stream()
                .collect(ConcurrentHashMap::new, (x, y) -> x.put(y.icon_id, y), ConcurrentHashMap::putAll);

        if (models.isEmpty()) {
            for (var portraitIcon : GameData.PLAYER.starterPortraitIconIds) {
                var model = DMPlayerPortraitIcon.of(player.getId(), portraitIcon);
                if (model.icon_id == GameData.PLAYER.defaultPortraitIconId) {
                    player.changePortrait(model.icon_id);
                }

                SDB.dbContext.portraitIcon.insert(model);
                models.put(model.icon_id, model);
            }
        }
    }

    public DMPlayerPortraitIcon get(long portraitIconId) {
        return models.get(portraitIconId);
    }
    public List<TPortraitIcon> getTPortraitIcon() {
        return models.values().stream().
                map(TBuilderOf::buildOf).
                collect(Collectors.toList());
    }

    public int add(long dataId, long count) {
        var resPortrait = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resPortrait)) {
            logger.error("invalid portraitIcon playerId : ({}) dataID : ({}),",  player.getId(), dataId);
            return StatusCode.INVALID_RESOURCE;
        }

        var portrait = models.get(dataId);
        if (Objects.nonNull(portrait) && Objects.nonNull(resPortrait.duplicateReward)) {
            if(resPortrait.duplicateReward.type == ZCategory.Type.PORTRAIT_ICON) {
                //무한 루프에 빠질 수 있기 때문에 에러를 줘야한다.
                return StatusCode.INVALID_REQUEST;
            }

            return player.categoryFilter.add(resPortrait.duplicateReward, count);
        }
        // 객체 생성 및 DB 저장
        portrait = DMPlayerPortraitIcon.of(player.getId(), dataId);
        SDB.dbContext.portraitIcon.insert(portrait);

        // 아바타 메모리 저장
        models.put(portrait.icon_id, portrait);

        player.sendPacket(0L, ZPortraitNotify.newBuilder()
                .addPortraitIcons(TBuilderOf.buildOf(portrait)));

        return StatusCode.SUCCESS;
    }
    public int changePortraitIcon(long portraitIconId) {
        // 장착할 아이콘
        var targetIcon = models.get(portraitIconId);
        if (Objects.isNull(targetIcon)) {
            return StatusCode.INVALID_REQUEST;
        }

        player.changePortrait(portraitIconId);

        return StatusCode.SUCCESS;
    }

    public int clearPortraitIcon() {
        player.changePortrait(GameData.PLAYER.defaultPortraitIconId);
        for(var model : models.values()) {
            if(Arrays.stream(GameData.PLAYER.starterPortraitIconIds)
                    .anyMatch(x -> x == model.icon_id)) {
                continue;
            }

            SDB.dbContext.portraitIcon.deleteForCheat(model.id);

            models.remove(model.icon_id);
        }

        player.stat.statsNotify();

        player.sendPacket(0L, ZPortraitNotify.newBuilder()
                .addAllPortraitIcons(getTPortraitIcon()));

        return StatusCode.SUCCESS;
    }
}
