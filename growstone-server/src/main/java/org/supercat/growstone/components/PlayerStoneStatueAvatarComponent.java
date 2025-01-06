package org.supercat.growstone.components;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMPlayerStoneStatueAvatar;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.StoneStatueAvatarRules;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerStoneStatueAvatarComponent {
    static final Logger logger = LoggerFactory.getLogger(PlayerStoneStatueAvatarComponent.class);

    private WorldPlayer player;
    private Map<Long, DMPlayerStoneStatueAvatar> models = new HashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();

    public PlayerStoneStatueAvatarComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var models = SDB.dbContext.playerStoneStatueAvatar.getAll(player.getId());
        for (var model : models) {
            this.models.put(model.avatar_id, model);
        }

        // 없는 경우 기본 아바타 추가
        if (this.models.isEmpty()) {
            var model = DMPlayerStoneStatueAvatar.of(player.getId(), GameData.STONE_STATUE.DEFAULT_STONE_STATUE_AVATAR_ID);
            SDB.dbContext.playerStoneStatueAvatar.insert(model);

            this.models.put(model.avatar_id, model);
        }
    }

    public List<Long> getStoneStatueAvatarIds() {
        return models.keySet().stream().toList();
    }

    public boolean isExist(long dataId) {
        return models.containsKey(dataId);
    }

    public int add(long dataId, long count) {
        var resAvatar = ResourceManager.INSTANCE.avatar.get(dataId);
        if (Objects.isNull(resAvatar)) {
            logger.error("invalid avatar stone statue - playerId({}), itemId({})", player.getId(), dataId);
            return StatusCode.INVALID_RESOURCE;
        }

        // 아바타가 존재한다면 패스
        var avatar = models.get(dataId);
        if (Objects.nonNull(avatar) && Objects.nonNull(resAvatar.duplicateReward)) {
            if (resAvatar.duplicateReward.type == ZCategory.Type.AVATAR_STONE_STATUE) {
                //무한 루프에 빠질 수 있기 때문에 에러를 줘야한다.
                return StatusCode.INVALID_REQUEST;
            }

            return player.categoryFilter.add(resAvatar.duplicateReward, count);
        }

        // 객체 생성 및 DB 저장
        var model = DMPlayerStoneStatueAvatar.of(player.getId(), dataId);
        SDB.dbContext.playerStoneStatueAvatar.insert(model);

        // 아바타 메모리 저장
        models.put(dataId, model);

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();
        return ImmutableMap.copyOf(cacheStats);
    }

    public void refresh() {
        cacheStats.clear();
        StoneStatueAvatarRules.computeStats(cacheStats, models.values().stream().toList());
    }
}
