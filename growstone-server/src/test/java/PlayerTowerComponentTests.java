import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TDailyDungeon;
import com.supercat.growstone.network.messages.TTower;
import com.supercat.growstone.network.messages.ZCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.types.DungeonType;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerTowerComponentTests extends BaseServerTests{
    @Test
    void towerInfo() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        var model = player.tower.getOrCreate(resTower.id);
        Assertions.assertNotNull(model);

        var l = player.tower.getTTower();
        Assertions.assertNotNull(l);
        Assertions.assertEquals(1, l.size());

        for(var info : l) {
            Assertions.assertEquals(resTower.id, info.getId());
            Assertions.assertEquals(1, info.getStage());
        }
    }
    @Test
    void towerStartTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);

        var resRaidDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.RAID)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resRaidDungeon);

        int status = player.tower.isEnableStartTower(0, 0);
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE,status);

        // 잘못된 타워ID
        status = player.tower.isEnableStartTower(resRaidDungeon.id, 0);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST,status);

        // 잘못된 타워스테이지ID
        int stageSize = resTower.clearRewardsByStage.size();
        status = player.tower.isEnableStartTower(resTower.id, stageSize + 1);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST,status);

        var model = player.tower.getOrCreate(resTower.id);
        // 소탕권이 없는 타워는 스테이지가 무조건 같아야한다.
        status = player.tower.isEnableStartTower(resTower.id, model.stage - 1);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.tower.isEnableStartTower(resTower.id, model.stage);
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void clearTowerTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resTower = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.TOWER)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resTower);
        var resDailyDungeon = ResourceManager.INSTANCE.dungeon.getAll().stream()
                .filter(x -> x.type == DungeonType.DAILY)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resDailyDungeon);

        var model = player.tower.getOrCreate(resTower.id);
        Assertions.assertEquals(resTower.id, model.tower_data_id);
        Assertions.assertEquals(1, model.stage);


        var out = TTower.newBuilder();
        var rewards = new ArrayList<TContentReward>();
        int status = player.tower.clearTower(resDailyDungeon.id, 1, out, rewards);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        // 잘못된 스테이지
        status = player.tower.clearTower(resTower.id, -1, out, rewards);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        final int currentStage = model.stage;
        status = player.tower.clearTower(resTower.id, model.stage, out, rewards);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var resNextStage = resTower.clearRewardsByStage.get(model.stage + 1);
        if(Objects.isNull(resNextStage)) {
            Assertions.assertEquals(currentStage , model.stage);
        } else {
            Assertions.assertEquals(currentStage + 1 , model.stage);
        }

        var resReward = resTower.clearRewardsByStage.get(currentStage);
        for(var reward : resReward) {
            var count = player.categoryFilter.getMaterial(reward.type, reward.rewardId);
            Assertions.assertEquals(reward.count + reward.count * resDailyDungeon.rewardBonusPercent, count);
        }
    }
}
