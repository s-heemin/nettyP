package org.supercat.growstone.components.worldComponents;

import com.supercat.growstone.network.messages.TCharacterGrowthDetailInfo;
import com.supercat.growstone.network.messages.TCharacterGrowthInfo;
import com.supercat.growstone.network.messages.TPlayerDetailInfo;
import com.supercat.growstone.network.messages.ZPreset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Async;
import org.supercat.growstone.SLog;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerEquipPreset;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.NetEnumRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorldPlayerComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldPlayerComponent.class);

    private ConcurrentHashMap<Long, WorldPlayer> players = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler;
    private long channelId;
    public WorldPlayerComponent(long channelId) {
        this.channelId = channelId;
        scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        Async.repeat(this::tickForPlayerReport, 0, 30, TimeUnit.SECONDS);

    }

    public void initialize() {
        updatePlayers();
    }
    public void updatePlayers() {
        scheduler.scheduleAtFixedRate(() -> {
            for (var player : players.values()) {
                    scheduler.submit(player::update);
            }
        }, 0, 1000 / 60, TimeUnit.MILLISECONDS);
    }
    public void insertWorldPlayer(WorldPlayer player) {
        try {
            players.put(player.getId(), player);
        }   catch (Exception e) {
            SLog.logException(e);
        }
    }
    private void tickForPlayerReport() {
        SDB.dbContext.server.updatePlayers(channelId, players.size());
    }

    public void removeWorldPlayer(long playerId) {
        players.remove(playerId);
    }

    public WorldPlayer getPlayer(long playerId) {
        return players.get(playerId);
    }
    public Collection<WorldPlayer> getPlayers() {
        return players.values();
    }

    public TPlayerDetailInfo getPlayerDetailInfo(long playerId) {
        var tPlayerDetailInfo = TPlayerDetailInfo.newBuilder();
        var now = Instant.now();
        var growths = new ArrayList<TCharacterGrowthInfo>();
        var targetPlayer = players.get(playerId);
        if (Objects.isNull(targetPlayer)) {
            var offline = SDB.dbContext.player.get(playerId);
            if (Objects.nonNull(offline)) {
                tPlayerDetailInfo
                        .setPlayerId(offline.id)
                        .setFriendCode(offline.friend_code)
                        .setLevel(offline.level)
                        .setName(offline.name)
                        .setAttackPower(offline.attack_power)
                        .setTimeSinceLastLogoutTime(now.getEpochSecond() - offline.last_disconnected_at.getEpochSecond())
                        .setPortraitId(offline.portrait_id);

                var avatars = SDB.dbContext.avatar.getByPlayerId(playerId);
                if(!avatars.isEmpty()) {
                    var equipAvatar = avatars.stream().filter(x -> x.isEquip).findFirst().orElse(null);
                    if(Objects.nonNull(equipAvatar)) {
                        tPlayerDetailInfo.setAvatarId(equipAvatar.avatar_id);
                    }
                }

                var maps = new HashMap<ZPreset.Type, List<TCharacterGrowthDetailInfo>>();
                var equipGrowths = new HashMap<ZPreset.Type, List<DMPlayerEquipPreset>>();
                var equipPresets = SDB.dbContext.equipPreset.getByPreset(playerId, offline.preset_index);
                for (var equipPreset : equipPresets) {
                    var type = NetEnumRules.ofPreset(equipPreset.type);
                    equipGrowths.computeIfAbsent(type, k -> new ArrayList<>()).add(equipPreset);
                }

                for (var equipGrowth : equipGrowths.entrySet()) {
                    var type = equipGrowth.getKey();
                    for(var equip : equipGrowth.getValue()) {
                        var growthModel = SDB.dbContext.growth.getByGrowthId(playerId, equip.data_id);
                        if (Objects.isNull(growthModel)) {
                            logger.error("getFriendDetailInfo failed. growthModel is null. playerId({}), dataId({})", playerId, equip.data_id);
                            continue;
                        }

                        var tGrowth = TBuilderOf.buildOf(growthModel);
                        var tDetailInfo = TCharacterGrowthDetailInfo.newBuilder()
                                .setEquipIndex(equip.equip_index)
                                .setGrowth(tGrowth)
                                .build();


                        maps.computeIfAbsent(type, k -> new ArrayList<>())
                                .add(tDetailInfo);
                    }
                }

                maps.entrySet().stream()
                        .forEach(x -> growths.add(TCharacterGrowthInfo.newBuilder()
                                .setType(x.getKey())
                                .addAllInfo(x.getValue())
                                .build()));
            }
        } else {
            tPlayerDetailInfo
                    .setPlayerId(targetPlayer.getId())
                    .setFriendCode(targetPlayer.getFriendCode())
                    .setLevel(targetPlayer.getLevel())
                    .setName(targetPlayer.getName())
                    .setAttackPower(targetPlayer.getAttackPower())
                    .setTimeSinceLastLogoutTime(0)
                    .setAvatarId(targetPlayer.avatar.getEquipAvatarId())
                    .setPortraitId(targetPlayer.getPortraitIcon());

            var presetsByType = targetPlayer.preset.getCurrentPresetInfo();
            var maps = new HashMap<ZPreset.Type, List<TCharacterGrowthDetailInfo>>();
            for (var preset : presetsByType.entrySet()) {
                var type = preset.getKey();
                var equips = preset.getValue();
                for (var equip : equips) {
                    var growth = targetPlayer.growth.getGrowth(equip.data_id);
                    if (Objects.isNull(growth)) {
                        logger.error("getFriendDetailInfo failed. growth is null. playerId({}), dataId({})", targetPlayer.getId(), equip.data_id);
                        continue;
                    }

                    var tGrowth = TBuilderOf.buildOf(growth.model);
                    maps.computeIfAbsent(type, k -> new ArrayList<>())
                            .add(TCharacterGrowthDetailInfo.newBuilder()
                                    .setEquipIndex(equip.equip_index)
                                    .setGrowth(tGrowth)
                                    .build());
                }
            }
            maps.entrySet().stream()
                    .forEach(x -> growths.add(TCharacterGrowthInfo.newBuilder()
                            .setType(x.getKey())
                            .addAllInfo(x.getValue())
                            .build()));

        }

        tPlayerDetailInfo.addAllGrowths(growths);

        return tPlayerDetailInfo.build();
    }
}
