package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.avatars.ResourceAvatar;
import org.supercat.growstone.models.DMPlayerAvatar;
import org.supercat.growstone.rules.AvatarRules;
import org.supercat.growstone.setups.SDB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerAvatarComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerAvatarComponent.class);

    private WorldPlayer player;

    private ConcurrentHashMap<Long, DMPlayerAvatar> models = new ConcurrentHashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();

    public PlayerAvatarComponent(WorldPlayer player) {
        this.player = player;
    }


    public void initialize() {
        models = SDB.dbContext.avatar.getByPlayerId(player.getId()).stream()
                .collect(ConcurrentHashMap::new, (x, y) -> x.put(y.avatar_id, y), ConcurrentHashMap::putAll);

        if (models.isEmpty()) {
            // 기본 아바타 및 아바타 아이콘을 생성한다.
            for (var avatarId : GameData.PLAYER.starterAvatarIds) {
                var avatar = DMPlayerAvatar.of(player.getId(), avatarId);
                if (avatarId == GameData.PLAYER.defaultAvatarId) {
                    avatar.isEquip = true;
                }

                SDB.dbContext.avatar.insert(avatar);
                models.put(avatarId, avatar);
            }

        }
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();
        return ImmutableMap.copyOf(cacheStats);
    }

    public void refresh() {
        cacheStats.clear();
        AvatarRules.computeStats(cacheStats, models.values().stream().toList());
    }

    public long getEquipAvatarId() {
        var model = models.values().stream()
                .filter(x -> x.isEquip)
                .findAny()
                .orElse(null);
        return Objects.isNull(model) ? 0 : model.avatar_id;
    }

    public List<TAvatar> getTAvatars() {
        return models.values().stream().
                map(TBuilderOf::buildOf).
                collect(Collectors.toList());
    }

    public int add(long dataId, long count) {
        if(count <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        var resAvatar = ResourceManager.INSTANCE.avatar.get(dataId);
        if (Objects.isNull(resAvatar)) {
            logger.error("invalid avatar duplicate reward item - playerId({}), itemId({})", player.getId(), dataId);
            return StatusCode.INVALID_RESOURCE;
        }

        // 아바타가 존재한다면 패스
        var avatar = models.get(dataId);
        if (Objects.isNull(avatar)) {
            // 객체 생성 및 DB 저장
            avatar = DMPlayerAvatar.of(player.getId(), dataId);
            SDB.dbContext.avatar.insert(avatar);

            // 아바타 메모리 저장
            models.put(dataId, avatar);

            player.stat.statsNotify();

            --count;
        }

        if(count > 0) {
            duplicateReward(resAvatar, count);
        }

        player.sendPacket(0, ZAvatarNotify.newBuilder()
                .addAllAvatars(getTAvatars()));

        return StatusCode.SUCCESS;
    }

    private void duplicateReward(ResourceAvatar resAvatar, long count) {
        if (Objects.isNull(resAvatar.duplicateReward) || resAvatar.duplicateReward.type == ZCategory.Type.AVATAR) {
            return;
        }

        player.categoryFilter.add(resAvatar.duplicateReward, count);
    }


    public int changeAvatar(long avatarId) {
        var resAvatar = ResourceManager.INSTANCE.avatar.get(avatarId);
        if (Objects.isNull(resAvatar) || resAvatar.category != ZCategory.Type.AVATAR) {
            return StatusCode.INVALID_RESOURCE;
        }

        // 갖고 있는 아바타인지 체크
        var targetAvatar = models.get(avatarId);
        if (Objects.isNull(targetAvatar)) {
            return StatusCode.INVALID_AVATAR;
        }

        // 장착 중인 아바타를 갖고 있는지 체크
        var equipedAvatar = models.values().stream()
                .filter(x -> x.isEquip)
                .findAny()
                .orElse(null);
        if (Objects.isNull(equipedAvatar)) {
            return StatusCode.NOT_EQUIP_AVATAR;
        }

        // 아바타 해제
        equipedAvatar.isEquip = false;
        SDB.dbContext.avatar.updateEquip(equipedAvatar.id, false);

        // 아바타 장착
        targetAvatar.isEquip = true;
        SDB.dbContext.avatar.updateEquip(targetAvatar.id, true);

        return StatusCode.SUCCESS;
    }

    public int clearAllForCheat() {
        for(var model : models.values()) {
            if(Arrays.stream(GameData.PLAYER.starterAvatarIds).anyMatch(x -> x == model.avatar_id)) {
                continue;
            }

            SDB.dbContext.avatar.deleteForCheat(model.id);

            models.remove(model.avatar_id);
        }

        player.sendPacket(0L, ZAvatarNotify.newBuilder()
                .addAllAvatars(getTAvatars()));

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }
}
