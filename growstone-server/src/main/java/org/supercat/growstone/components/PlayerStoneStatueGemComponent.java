package org.supercat.growstone.components;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TStoneStatueGem;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.Out;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerStoneStatueGem;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.rules.StoneStatueGemRules;
import org.supercat.growstone.setups.SDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerStoneStatueGemComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerStoneStatueGemComponent.class);
    WorldPlayer player;
    private Map<Long, DMPlayerStoneStatueGem> models = new HashMap<>();
    private HashMap<ZStat.Type, Double> cacheStats = new HashMap<>();

    public PlayerStoneStatueGemComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGems();
        for (var gem : resStoneStatueGem) {
            getOrCreate(gem.id);
        }
    }

    private DMPlayerStoneStatueGem getOrCreate(long gemId) {
        return models.computeIfAbsent(gemId, k -> getGemModelOrCreate(gemId));
    }

    private DMPlayerStoneStatueGem getGemModelOrCreate(long gemId) {
        var model = SDB.dbContext.playerStoneStatueGem.get(player.getId(), gemId);
        if (Objects.isNull(model)) {
            model = DMPlayerStoneStatueGem.of(player.getId(), gemId, 0);
        }

        return model;
    }

    public List<TStoneStatueGem> getAllTStoneStatueGem() {
        return TBuilderOf.ofTStoneStatueGemAll(models);
    }

    public int tryGemLimitLevelUp(Out<Integer> outGemLevel) {
        var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGem();
        if (Objects.isNull(resStoneStatueGem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var model = getGemModelOrCreate(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID());
        var resStoneStatueStatGroup = resStoneStatueGem.statGroup.get((long) model.gem_level);
        if (Objects.isNull(resStoneStatueStatGroup)) {
            return StatusCode.INVALID_RESOURCE;
        }

        // 제한레벨이 최대치인지 확인
        if (model.gem_level == ResourceManager.INSTANCE.stoneStatueGem.getMainStoneMaxLevel()) {
            return StatusCode.INVALID_REQUEST;
        }

        // 모든 보석들이 현재단계의 최대레벨에 도달했는지 확인
        if (!isAllMaxLevel(model.gem_level)) {
            return StatusCode.INVALID_REQUEST;
        }

        // 재화 체크
        var cost = player.itemBag.getItemCount(GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID);
        if (cost < resStoneStatueStatGroup.price) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        // 재화 소모
        var status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID, resStoneStatueStatGroup.price);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 제한레벨 증가
        model.gem_level++;
        SDB.dbContext.playerStoneStatueGem.save(model);

        outGemLevel.set(model.gem_level);

        player.stat.statsNotify();

        return StatusCode.SUCCESS;
    }

    public int tryGemLevelUp(long gemId, Out<TStoneStatueGem> outGem) {
        var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGem(gemId);
        if (Objects.isNull(resStoneStatueGem)) {
            return StatusCode.FAIL;
        }

        var mainGemModel = getGemModelOrCreate(ResourceManager.INSTANCE.stoneStatueGem.getMainStoneGemID());

        var resStoneStatueGemGroup = resStoneStatueGem.statGroup.get((long) mainGemModel.gem_level);
        if (Objects.isNull(resStoneStatueGemGroup)) {
            return StatusCode.FAIL;
        }

        // 해당 젬이 이미 최대레벨에 달성했을 경우
        var model = getOrCreate(gemId);
        var maxLevel = resStoneStatueGemGroup.maxLevel + resStoneStatueGemGroup.beforeMaxLevel;
        if (model.gem_level == maxLevel) {
            return StatusCode.FAIL;
        }

        // 재화 보유량 체크
        var cost = player.itemBag.getItemCount(GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID);
        if (cost < resStoneStatueGemGroup.price) {
            return StatusCode.NOT_ENOUGH_ITEM;
        }

        // 재화 소모
        var status = player.categoryFilter.use(ZCategory.Type.ITEM, GameData.STONE_STATUE.GEM_UPGRADE_CURRENCY_ID, resStoneStatueGemGroup.price);
        if (!StatusCode.isSuccess(status)) {
            return status;
        }

        // 성공확률 계산
        var checkNextLevel = model.gem_level - resStoneStatueGemGroup.beforeMaxLevel;
        var isSuccess = StoneStatueGemRules.runUpgrade(resStoneStatueGemGroup.statRatioGroupId, checkNextLevel);

        // 성공이면 레벨 1증가
        if (isSuccess) {
            model.gem_level++;
            SDB.dbContext.playerStoneStatueGem.save(model);

            player.stat.statsNotify();
        }

        outGem.set(TBuilderOf.ofTStoneStatueGem(model));

        return StatusCode.SUCCESS;
    }

    public boolean isAllMaxLevel(int limitLevel) {
        for (var model : models.values()) {
            var resStoneStatueGem = ResourceManager.INSTANCE.stoneStatueGem.getStoneStatueGem(model.gem_id);
            if (Objects.isNull(resStoneStatueGem)) {
                return false;
            }

            var resStoneStatueGemGroup = resStoneStatueGem.statGroup.get((long) limitLevel);
            if (Objects.isNull(resStoneStatueGemGroup)) {
                return false;
            }

            var maxLevel = resStoneStatueGemGroup.maxLevel + resStoneStatueGemGroup.beforeMaxLevel;
            if (model.gem_level < maxLevel) {
                return false;
            }
        }

        return true;
    }

    public ImmutableMap<ZStat.Type, Double> getCacheStats() {
        refresh();

        return ImmutableMap.copyOf(cacheStats);
    }

    private void refresh() {
        cacheStats.clear();

        StoneStatueGemRules.computeStats(cacheStats, models.values().stream().toList());
    }

    public DMPlayerStoneStatueGem getStoneStatueGemForTest(long gemId) {
        return getOrCreate(gemId);
    }

    public void setStoneStatueGemLevelForTest(long gemId, int level) {
        var model = getOrCreate(gemId);
        model.gem_level = level;
    }
}
