package org.supercat.growstone.components.worldComponents;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.supercat.growstone.network.messages.*;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.components.playerComponents.PlayerFarmComponent;
import org.supercat.growstone.events.*;
import org.supercat.growstone.models.DMPlayerFarm;
import org.supercat.growstone.models.DMPlayerFarmPlant;
import org.supercat.growstone.models.DMPlayerFarmTheftLimit;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.FarmRules;
import org.supercat.growstone.rules.MailRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WorldFarmComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldFarmComponent.class);
    private long channelId;
    public EventTopic topic;
    private Cache<Long, DMPlayerFarm> cacheFarms;
    private Cache<Long, ConcurrentHashMap<Integer, DMPlayerFarmPlant>> cacheFarmPlants;
    private Cache<Long, DMPlayerFarmTheftLimit> cacheFarmTheftLimits;

    public WorldFarmComponent(long channelId) {
        this.channelId = channelId;
        this.cacheFarms = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();
        this.cacheFarmPlants = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();

        this.cacheFarmTheftLimits = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();

        this.topic = new EventTopic(new EventDispatcher());
        topic.subscribes(EventSubscribeBuilder.newBuilder()
                .on(EventType.PLAYER_FARM_SEED, this::handle_PLAYER_FARM_SEED)
                .on(EventType.PLAYER_FARM_BOOST, this::handle_PLAYER_FARM_BOOST)
                .on(EventType.PLAYER_FARM_HARVEST, this::handle_PLAYER_FARM_HARVEST)
                .on(EventType.PLAYER_FARM_STEAL, this::handle_PLAYER_FARM_STEAL)
                .on(EventType.PLAYER_FARM_RETURN_STOLEN_PLANTS_TO_THIEF, this::handle_PLAYER_FARM_RETURN_STOLEN_PLANTS_TO_THIEF)
                .on(EventType.PLAYER_FARM_REMOVE_THIEF_BY_BATTLE, this::handle_PLAYER_FARM_REMOVE_THIEF_BY_BATTLE)
        );
    }

    public void initalize() {
        start();
    }

    public void start() {
        Async.repeat(() -> update(), 0, 1, TimeUnit.SECONDS);
    }

    private void update() {
        //자동 정산 처리
        settleTheft(Instant.now());

        //토픽
        topic.getEventDispatcher().update();
    }

    private DMPlayerFarm getCacheFarm(long playerId) {
        var farm = cacheFarms.getIfPresent(playerId);
        if (Objects.nonNull(farm)) {
            return farm;
        }

        farm = SDB.dbContext.farm.getByPlayerId(playerId);
        if (Objects.isNull(farm)) {
            farm = DMPlayerFarm.of(playerId);
            SDB.dbContext.farm.save(farm);
        }
        cacheFarms.put(playerId, farm);
        return farm;
    }

    private Map<Integer, DMPlayerFarmPlant> getCachePlants(long playerId) {
        var plants = cacheFarmPlants.getIfPresent(playerId);
        if (Objects.nonNull(plants)) {
            return plants;
        }

        var models = SDB.dbContext.farmPlant.getAllByPlayerId(playerId);
        plants = models.stream()
                .collect(Collectors.toMap(x -> x.slot_index, x -> x,
                        (x, y) -> x, ConcurrentHashMap::new));
        cacheFarmPlants.put(playerId, plants);
        return plants;
    }

    private DMPlayerFarmTheftLimit getCacheFarmTheftLimit(long playerId) {
        var model = cacheFarmTheftLimits.getIfPresent(playerId);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = SDB.dbContext.farmTheftLimit.get(playerId);
        if (Objects.isNull(model)) {
            model = DMPlayerFarmTheftLimit.of(playerId, UtcZoneDateTime.getYmd());
            SDB.dbContext.farmTheftLimit.save(model);
        }
        cacheFarmTheftLimits.put(playerId, model);
        return model;
    }

    public DMPlayerFarmPlant getPlantOrDefault(long playerId, int index) {
        var plants = getCachePlants(playerId);
        var model = plants.get(index);
        if (Objects.nonNull(model)) {
            return model;
        }

        model = DMPlayerFarmPlant.of(playerId, index, ZFarmPlantStatus.Type.EMPTY.getNumber());
        SDB.dbContext.farmPlant.save(model);
        plants.put(index, model);

        return model;
    }

    public TFarm getTFarmByPlayerId(long playerId) {
        var player = World.INSTANCE.worldPlayers.getPlayer(playerId);
        return Objects.nonNull(player) ? getTFarm(player) : getTFarm(playerId);
    }

    public int getMyRestrictionsInfo(WorldPlayer player, ZFarmMyRestrictionsInfoResponse.Builder response) {
        if (Objects.isNull(player)) {
            return StatusCode.INVALID_REQUEST;
        }

        //내가 훔칠 수 있는 농장 횟수 체크
        int activeStealCount = SDB.dbContext.farmPlant.getStealCount(player.getId());
        var theftLimitModel = getCacheFarmTheftLimit(player.getId());
        response.setActiveStealCount(activeStealCount)
                .setStealLimitCount(theftLimitModel.count)
                .build();

        return StatusCode.SUCCESS;
    }

    public int getFarmFriendList(WorldPlayer player, List<TFarm> farmList) {
        if (Objects.isNull(player)) {
            return StatusCode.INVALID_REQUEST;
        }

        var playerIds = player.friend.getAcceptedFriendIds();
        for (var playerId : playerIds) {
            var tFarm = getTFarmByPlayerId(playerId);
            farmList.add(tFarm);
        }

        return StatusCode.SUCCESS;
    }

    public int getFarmOtherList(long playerId, ZFarmOtherListResponse.Builder response) {
        int count = GameData.FARM.MAX_OTHER_PLAYER_SEARCH_COUNT * 2 + Constants.RECOMMEND_FRIEND_BUFFER;
        var recommends = SRedis.INSTANCE.content.players.getPlayerRandomPlayer(count);
        if (Objects.isNull(recommends) || recommends.isEmpty()) {
            return StatusCode.FAIL;
        }

        var others = new ArrayList<TFarm>();
        for (var recommendPlayerId : recommends) {
            if (others.size() == GameData.FARM.MAX_OTHER_PLAYER_SEARCH_COUNT) {
                break;
            }

            if (recommendPlayerId == playerId) {
                continue;
            }

            var tFarm = getTFarmByPlayerId(recommendPlayerId);
            others.add(tFarm);
        }

        var tFarmEnemies = new ArrayList<TFarm>();
        var enemyPlayerIds = SRedis.INSTANCE.content.playerFarmEnemy.get(playerId,
                GameData.FARM.MAX_ENEMY_PLAYER_SEARCH_COUNT, GameData.FARM.ENEMY_PLAYER_SEARCH_HOUR);
        for (var enemyPlayerId : enemyPlayerIds) {
            var tFarm = getTFarmByPlayerId(enemyPlayerId);
            tFarmEnemies.add(tFarm);
        }

        response.addAllOthers(others)
                .addAllEnemies(tFarmEnemies)
                .build();

        return StatusCode.SUCCESS;
    }

    private TFarm getTFarm(WorldPlayer player) {
        var farmModel = getCacheFarm(player.getId());
        var farmPlants = getCachePlants(player.getId());
        int level = FarmRules.computeFarmLevel(farmModel.exp);
        return TBuilderOf.ofTFarm(farmModel, player.getName(), player.getAttackPower(), level, player.getPortraitIcon(),
                farmPlants.values().stream().toList());
    }

    private TFarm getTFarm(long playerId) {
        var targetModel = SDB.dbContext.player.get(playerId);
        if (Objects.isNull(targetModel)) {
            return TFarm.newBuilder().build();
        }

        var farmModel = getCacheFarm(playerId);
        var farmPlants = getCachePlants(playerId);
        int level = FarmRules.computeFarmLevel(farmModel.exp);
        return TBuilderOf.ofTFarm(farmModel, targetModel.name, targetModel.attack_power, level, targetModel.portrait_id,
                farmPlants.values().stream().toList());
    }

    private void settleTheft(Instant at) {
        var list = SDB.dbContext.farmPlant.getAllByTheft(ZFarmPlantStatus.Type.UNDER_THEFT.getNumber(),
                at, GameData.FARM.MAX_SETTLE_THEFT_COUNT);

        int rewardId = GameData.FARM.PLANT_ITEM_ID;
        for (var model : list) {
            var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(model.plant_id);
            if (Objects.isNull(resFarmPlant)) {
                continue;
            }

            String theftName = StringUtil.EMPTY_STRING;
            var theftPlayer = World.INSTANCE.worldPlayers.getPlayer(model.theft_player_id);
            if (Objects.isNull(theftPlayer)) {
                sendMail(model.theft_player_id, ZMail.Type.FARM_THEFT_REWARD_VALUE,
                        rewardId, resFarmPlant.stealCount);
                var theftModel = SDB.dbContext.player.get(model.theft_player_id);
                if (Objects.nonNull(theftModel)) {
                    theftName = theftModel.name;
                }
            } else {
                //아이템 지급
                theftPlayer.categoryFilter.add(ZCategory.Type.ITEM, rewardId, resFarmPlant.stealCount);
                theftName = theftPlayer.getName();
            }

            String playerName = StringUtil.EMPTY_STRING;
            var player = World.INSTANCE.worldPlayers.getPlayer(model.player_id);
            if (Objects.isNull(player)) {
                var playerModel = SDB.dbContext.player.get(model.player_id);
                if (Objects.nonNull(playerModel)) {
                    playerName = playerModel.name;
                }
            } else {
                playerName = player.getName();
            }

            //내가 훔친 기록
            PlayerFarmComponent.addHistory(model.theft_player_id, ZFarmHistory.Type.SUCCESS_THEFT,
                    playerName, model.plant_id, resFarmPlant.stealCount);

            //훔친 당한 기록
            PlayerFarmComponent.addHistory(model.player_id, ZFarmHistory.Type.PLANT_THEFT,
                    theftName, model.plant_id, resFarmPlant.stealCount);

            //내 작물을 훔친 유저 레디스에 저장
            SRedis.INSTANCE.content.playerFarmEnemy.add(model.player_id, model.theft_player_id);

            model.theft_player_id = 0L;
            model.status = ZFarmPlantStatus.Type.HARVEST.getNumber();
            SDB.dbContext.farmPlant.save(model);
        }
    }

    private void handle_PLAYER_FARM_SEED(EventPlayerFarmSeed event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        int status = reviewPlantSeed(player, event.slotIndexes);
        if (!StatusCode.isSuccess(status)) {
            player.sendPacket(event.packetId, ZFarmPlantSeedResponse.newBuilder()
                    .setStatus(status)
            );
            return;
        }

        for (var slotIndex : event.slotIndexes) {
            var model = getPlantOrDefault(player.getId(), slotIndex);

            long plantId = FarmRules.computePlant();
            if (plantId <= 0) {
                logger.error("plantId is invalid slotIndex({})", slotIndex);
                continue;
            }

            status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.FARM.SEED_ITEM_ID, 1);
            if (!StatusCode.isSuccess(status)) {
                player.sendPacket(event.packetId, ZFarmPlantSeedResponse.newBuilder()
                        .setStatus(status)
                );
                return;
            }

            //리소스 체크를하기 때문에 null 체크를 하지 않는다
            var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(plantId);
            model.status = ZFarmPlantStatus.Type.HARVEST.getNumber();
            model.theft_limit_count = 0;
            model.plant_id = plantId;
            model.seed_start_at = event.at;
            model.seed_end_at = event.at.plus(resFarmPlant.growTime, ChronoUnit.SECONDS);
            SDB.dbContext.farmPlant.save(model);

            PlayerFarmComponent.addHistory(player.getId(), ZFarmHistory.Type.SEED, model.plant_id);
        }

        player.sendPacket(event.packetId, ZFarmPlantSeedResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(getTFarm(player))
        );
    }

    private void handle_PLAYER_FARM_BOOST(EventPlayerFarmBoost event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        if (Objects.isNull(event.boosts) || event.boosts.isEmpty()) {
            player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        long totalCount = event.boosts.stream()
                .filter(x -> !x.getIsCommercial())
                .mapToInt(TFarmBoost::getCount)
                .sum();

        long itemCount = player.itemBag.getItemCount(GameData.FARM.BOOST_ITEM_ID);
        if (itemCount < totalCount) {
            player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                    .setStatus(StatusCode.NOT_ENOUGH_ITEM)
            );
            return;
        }

        long totalAdvertiseCount = event.boosts.stream()
                .filter(x -> x.getIsCommercial())
                .mapToInt(TFarmBoost::getCount)
                .sum();

        //광고는 1번씩만 시청 가능
        if (totalAdvertiseCount > 1) {
            player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //인덱스 검사
        var indexes = event.boosts.stream()
                .map(TFarmBoost::getSlotIndex)
                .collect(Collectors.toList());
        int status = reviewBoost(player, indexes, event.at);
        if (!StatusCode.isSuccess(status)) {
            player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                    .setStatus(status)
            );
            return;
        }

        for (var boost : event.boosts) {
            if (boost.getIsCommercial()) {
                var viewCount = player.advertise.getViewCommercial(ZContentAdvertise.Type.FARM_BOOST);
                if (viewCount >= GameData.FARM.MAX_COMMERCIAL_BOOST_VIEW_COUNT) {
                    player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                            .setStatus(StatusCode.ADVERTISE_VIEW_COUNT_EXCEEDED)
                    );
                    return;
                }
                player.advertise.addViewCommercial(ZContentAdvertise.Type.FARM_BOOST);
            } else {
                status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.FARM.BOOST_ITEM_ID, boost.getCount());
                if (!StatusCode.isSuccess(status)) {
                    player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                            .setStatus(status)
                    );
                    return;
                }
            }

            var model = getPlantOrDefault(player.getId(), boost.getSlotIndex());
            int minutes = boost.getCount() * GameData.FARM.BOOST_MINUTES;
            model.seed_end_at = model.seed_end_at.minus(minutes, ChronoUnit.MINUTES);
            SDB.dbContext.farmPlant.save(model);
        }

        player.sendPacket(event.packetId, ZFarmPlantBoostResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(getTFarm(player))
        );
    }

    private void handle_PLAYER_FARM_HARVEST(EventPlayerFarmHarvest event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        int status = reviewHarvest(player, event.slotIndexes, event.at);
        if (!StatusCode.isSuccess(status)) {
            player.sendPacket(event.packetId, ZFarmPlantHarvestResponse.newBuilder()
                    .setStatus(status)
            );
            return;
        }

        int rewardId = GameData.FARM.PLANT_ITEM_ID;
        var farmModel = getCacheFarm(player.getId());
        int farmLevel = FarmRules.computeFarmLevel(farmModel.exp);

        for (var slotIndex : event.slotIndexes) {
            var model = getPlantOrDefault(player.getId(), slotIndex);
            var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(model.plant_id);

            //도둑질 당한 만큼 제외
            int resultCount = resFarmPlant.resultCount - resFarmPlant.stealCount * model.theft_limit_count;

            //보상 지급
            player.categoryFilter.add(ZCategory.Type.ITEM, rewardId, resultCount);
            //경험치 추가
            farmModel.exp += resFarmPlant.resultExp;

            PlayerFarmComponent.addHistory(player.getId(), ZFarmHistory.Type.HARVEST, model.plant_id, resultCount);

            model.status = ZFarmPlantStatus.Type.EMPTY.getNumber();
            model.plant_id = 0;
            SDB.dbContext.farmPlant.save(model);
        }

        int resultFarmLevel = FarmRules.computeFarmLevel(farmModel.exp);
        if (farmLevel != resultFarmLevel) {
            //농장 레벨업
            PlayerFarmComponent.addHistory(player.getId(), ZFarmHistory.Type.FARM_LEVEL_UP, resultFarmLevel);
        }

        SDB.dbContext.farm.save(farmModel);
        player.sendPacket(event.packetId, ZFarmPlantHarvestResponse.newBuilder()
                .setStatus(status)
                .setFarm(getTFarm(player))
        );
    }

    private void handle_PLAYER_FARM_STEAL(EventPlayerFarmSteal event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        //한 유저는 하루에 한번
        int ymd = UtcZoneDateTime.getYmd();
        var theftLimitModel = getCacheFarmTheftLimit(player.getId());
        //날짜 체크
        if (theftLimitModel.ymd != ymd) {
            theftLimitModel.ymd = ymd;
            theftLimitModel.count = 0;
        } else {
            if (theftLimitModel.count >= GameData.FARM.MAX_STEAL_DAILY_COUNT) {
                player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                        .setStatus(StatusCode.HAS_ALREADY_STOLEN_FROM_FARM)
                );
                return;
            }
        }

        //내가 훔칠 수 있는 농장 횟수 체크
        int count = SDB.dbContext.farmPlant.getStealCount(player.getId());
        if (count >= GameData.FARM.MAX_STEAL_PLAYER_COUNT) {
            player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                    .setStatus(StatusCode.FARM_THEFT_LIMITED_EXCEEDED)
            );
            return;
        }

        //타겟의 농장 정보를 가져온다
        var plants = getCachePlants(event.targetPlayerId);
        var targetModel = plants.get(event.slotIndex);
        if (Objects.isNull(targetModel)) {
            player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //스틸 가능한지 확인
        if (targetModel.status != ZFarmPlantStatus.Type.HARVEST.getNumber()
                || targetModel.seed_end_at.isAfter(event.at)) {
            player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        if (targetModel.theft_ymd != ymd) {
            targetModel.theft_ymd = ymd;
            targetModel.theft_limit_count = 0;
        }

        //제한 횟수 확인
        if (targetModel.theft_limit_count >= GameData.FARM.MAX_THEFT_ATTEMPTS_PER_PLANT) {
            player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                    .setStatus(StatusCode.FARM_THEFT_LIMITED_EXCEEDED)
            );
            return;
        }

        var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(targetModel.plant_id);
        if (Objects.isNull(resFarmPlant)) {
            player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        targetModel.theft_player_id = event.playerId;
        targetModel.theft_limit_count += 1;
        targetModel.theft_end_at = event.at.plus(resFarmPlant.stealTime, ChronoUnit.SECONDS);
        targetModel.status = ZFarmPlantStatus.Type.UNDER_THEFT.getNumber();
        SDB.dbContext.farmPlant.save(targetModel);

        //횟수 제한 저장
        theftLimitModel.count += 1;
        SDB.dbContext.farmTheftLimit.save(theftLimitModel);

        var tFarm = getTFarm(event.targetPlayerId);
        player.sendPacket(event.packetId, ZFarmStealResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(tFarm)
        );

        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(event.targetPlayerId);
        if (Objects.nonNull(targetPlayer)) {
            targetPlayer.sendPacket(0, ZFarmChangeNotify.newBuilder()
                    .setFarm(tFarm)
            );
        }
    }

    //작물 주고 도둑 보내기
    private void handle_PLAYER_FARM_RETURN_STOLEN_PLANTS_TO_THIEF(EventPlayerReturnStolenPlantsToThief event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        //나의 농장 정보를 가져온다
        var plants = getCachePlants(event.playerId);
        var model = plants.get(event.slotIndex);
        if (Objects.isNull(model)) {
            player.sendPacket(event.packetId, ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //도둑질 당하는 상태가 아니라면
        if (model.status != ZFarmPlantStatus.Type.UNDER_THEFT.getNumber()) {
            player.sendPacket(event.packetId, ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //타겟이 잘못되었다면
        if (model.theft_player_id != event.targetPlayerId) {
            player.sendPacket(event.packetId, ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(model.plant_id);
        if (Objects.isNull(resFarmPlant)) {
            player.sendPacket(event.packetId, ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        String theftName = StringUtil.EMPTY_STRING;
        var targetPlayer = World.INSTANCE.worldPlayers.getPlayer(event.targetPlayerId);
        if (Objects.isNull(targetPlayer)) {
            sendMail(event.targetPlayerId, ZMail.Type.FARM_THEFT_REWARD_VALUE,
                    GameData.FARM.PLANT_ITEM_ID, resFarmPlant.stealCount);
            var theftModel = SDB.dbContext.player.get(model.theft_player_id);
            if (Objects.nonNull(theftModel)) {
                theftName = theftModel.name;
            }
        } else {
            //아이템 지급
            targetPlayer.categoryFilter.add(ZCategory.Type.ITEM, GameData.FARM.PLANT_ITEM_ID, resFarmPlant.stealCount);
            theftName = targetPlayer.getName();
        }


        //내가 훔친 기록
        PlayerFarmComponent.addHistory(model.theft_player_id, ZFarmHistory.Type.SUCCESS_THEFT,
                player.getName(), model.plant_id, resFarmPlant.stealCount);

        //훔친 당한 기록
        PlayerFarmComponent.addHistory(model.player_id, ZFarmHistory.Type.PLANT_THEFT,
                theftName, model.plant_id, resFarmPlant.stealCount);

        //내 작물을 훔친 유저 레디스에 저장
        SRedis.INSTANCE.content.playerFarmEnemy.add(model.player_id, model.theft_player_id);

        model.theft_player_id = 0;
        model.status = ZFarmPlantStatus.Type.HARVEST.getNumber();
        SDB.dbContext.farmPlant.save(model);

        player.sendPacket(event.packetId, ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(getTFarm(player))
        );

        //내 정보를 도둑에게 알려준다.
        var tFarm = getTFarm(player);
        if (Objects.nonNull(targetPlayer)) {
            targetPlayer.sendPacket(0, ZFarmChangeNotify.newBuilder()
                    .setFarm(tFarm)
            );
        }
    }

    //전투하기
    private void handle_PLAYER_FARM_REMOVE_THIEF_BY_BATTLE(EventPlayerFarmRemoveThiefByBattle event) {
        var player = World.INSTANCE.worldPlayers.getPlayer(event.playerId);
        if (Objects.isNull(player)) {
            return;
        }

        //타겟의 농장 정보를 가져온다
        var plants = getCachePlants(player.getId());
        var plantModel = plants.get(event.slotIndex);
        if (Objects.isNull(plantModel)) {
            player.sendPacket(event.packetId, ZFarmRemoveThiefByBattleResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //도둑질 당하는 상태가 아니라면
        if (plantModel.status != ZFarmPlantStatus.Type.UNDER_THEFT.getNumber()) {
            player.sendPacket(event.packetId, ZFarmRemoveThiefByBattleResponse.newBuilder()
                    .setStatus(StatusCode.INVALID_REQUEST)
            );
            return;
        }

        //TODO 전투 로직 추가 필요
        //TODO 훔치기 실패 기록 추가 필요

        long theftPlayerId = plantModel.theft_player_id;

        //방어 성공시 코드
        plantModel.status = ZFarmPlantStatus.Type.HARVEST.getNumber();
        plantModel.theft_player_id = 0;
        plantModel.theft_end_at = Instant.now();
        SDB.dbContext.farmPlant.save(plantModel);

        player.sendPacket(event.packetId, ZFarmRemoveThiefByBattleResponse.newBuilder()
                .setStatus(StatusCode.SUCCESS)
                .setFarm(getTFarm(player))
        );

        //내 정보를 도둑에게 알려준다.
        //TODO 방어 성공시 도둑에게 농장 갱신 알림을 해줘야 한다.
        var tFarm = getTFarm(player);
        var theftPlayer = World.INSTANCE.worldPlayers.getPlayer(theftPlayerId);
        if (Objects.nonNull(theftPlayer)) {
            theftPlayer.sendPacket(0, ZFarmChangeNotify.newBuilder()
                    .setFarm(tFarm)
            );
        }
    }

    private int reviewPlantSeed(WorldPlayer player, List<Integer> slotIndexes) {
        if (slotIndexes.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        int status = FarmRules.reviewSlotIndexes(slotIndexes);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        long itemCount = player.itemBag.getItemCount(GameData.FARM.SEED_ITEM_ID);
        if (slotIndexes.size() > itemCount) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        //일괄 조건 체크
        //TODO 광고 구매권 확인 필요
        var farm = getCacheFarm(player.getId());
        int level = FarmRules.computeFarmLevel(farm.exp);
        if (slotIndexes.size() > 1) {
            if (level < GameData.FARM.UNLOCK_LEVEL_BATCH_SEED) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        for (var slotIndex : slotIndexes) {
            var model = getPlantOrDefault(player.getId(), slotIndex);
            //이미 심어진 식물이 있으면 실패
            if (model.status != ZFarmPlantStatus.Type.EMPTY.getNumber()) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        return StatusCode.SUCCESS;
    }

    private int reviewBoost(WorldPlayer player, List<Integer> slotIndexes, Instant at) {
        if (slotIndexes.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        int status = FarmRules.reviewSlotIndexes(slotIndexes);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        //일괄 조건 체크
        //TODO 광고 구매권 확인 필요
        var farm = getCacheFarm(player.getId());
        int level = FarmRules.computeFarmLevel(farm.exp);
        if (slotIndexes.size() > 1) {
            if (level < GameData.FARM.UNLOCK_LEVEL_BATCH_BOOST) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        for (var slotIndex : slotIndexes) {
            var model = getPlantOrDefault(player.getId(), slotIndex);
            //심어진 식물이 없다면
            if (model.status != ZFarmPlantStatus.Type.HARVEST.getNumber()) {
                return StatusCode.INVALID_REQUEST;
            }

            //시간이 지났다면
            if (model.seed_end_at.isBefore(at)) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        return StatusCode.SUCCESS;
    }

    private int reviewHarvest(WorldPlayer player, List<Integer> slotIndexes, Instant at) {
        if (slotIndexes.isEmpty()) {
            return StatusCode.INVALID_REQUEST;
        }

        int status = FarmRules.reviewSlotIndexes(slotIndexes);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        //일괄 조건 체크
        //TODO 광고 구매권 확인 필요
        var farm = getCacheFarm(player.getId());
        int level = FarmRules.computeFarmLevel(farm.exp);
        if (slotIndexes.size() > 1) {
            if (level < GameData.FARM.UNLOCK_LEVEL_BATCH_HARVEST) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        for (var slotIndex : slotIndexes) {
            var model = getPlantOrDefault(player.getId(), slotIndex);
            //
            if (model.status != ZFarmPlantStatus.Type.HARVEST.getNumber()) {
                return StatusCode.INVALID_REQUEST;
            }

            //시간이 지나지 않았다면
            if (model.seed_end_at.isAfter(at)) {
                return StatusCode.INVALID_REQUEST;
            }

            //리소스 체크
            var resFarmPlant = ResourceManager.INSTANCE.farm.getFarmPlant(model.plant_id);
            if (Objects.isNull(resFarmPlant)) {
                return StatusCode.INVALID_REQUEST;
            }
        }

        return StatusCode.SUCCESS;
    }

    private void sendMail(long playerId, int mailType, int rewardId, int count) {
        var jmReward = MailRules.getJMPlayerMailReward(ZCategory.Type.ITEM, rewardId, count);
        var json = JsonConverter.to(jmReward);
        var mail = DMPlayerMail.of(playerId, mailType,
                "system", json, UtcZoneDateTime.getPlusDay(7));
        SDB.dbContext.mail.insert(mail);
    }
}
