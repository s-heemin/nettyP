import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.*;
import org.supercat.growstone.player.WorldPlayer;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRankComponentTests extends BaseServerTests {

    @Test
    void playerRankTest() {
        final int playerCount = 300;
        final int nowYmd = UtcZoneDateTime.getYmd();

        var l = new ArrayList<WorldPlayer>();
        for(int i = 0; i < playerCount; i++) {
            l.add(TestPlayerUtils.of());
        }

        SRedis.INSTANCE.rank.playerAttackPowerRank.clearForTest(nowYmd);
        SRedis.INSTANCE.content.playerAttackPower.clearForTest();
        var sortedMap = new HashMap<Long, Long>();
        for(var player : l) {
            long attack = SRandomUtils.nextInt(0, 1000000000);
            player.getModel().attack_power = attack;

            player.intervalSaveForTest();

            sortedMap.put(attack, player.getModel().id);
        }

        Map<Long, Long> descendingMap = sortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Long>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));


        World.INSTANCE.worldSchedule.dailyResetTaskByTest().resetDailyAttackRankForTest();

        List<Map.Entry<Long, Long>> entryList = new ArrayList<>(descendingMap.entrySet());
        var entries = SRedis.INSTANCE.rank.playerAttackPowerRank.entries(nowYmd, 0, -1);
        int rank = 0;
        for(var entry : entries) {
            long playerId = Long.parseLong(entry.getKey());
            long attackPower = entry.getValue();


            var info = entryList.get(rank);
            Assertions.assertEquals(info.getKey(), attackPower);
            Assertions.assertEquals(info.getValue(), playerId);
            ++rank;
        }
    }

    @Test
    void playerMaxRankTest() {
        final int playerCount = 300;
        final int nowYmd = UtcZoneDateTime.getYmd();

        var l = new ArrayList<WorldPlayer>();
        for(int i = 0; i < playerCount; i++) {
            l.add(TestPlayerUtils.of());
        }

        SRedis.INSTANCE.rank.playerAttackPowerRank.clearForTest(nowYmd);
        SRedis.INSTANCE.content.playerAttackPower.clearForTest();
        var sortedMap = new HashMap<Long, Long>();
        for(var player : l) {
            long attack = SRandomUtils.nextInt(0, 1000000000);
            player.getModel().attack_power = attack;

            player.intervalSaveForTest();

            sortedMap.put(attack, player.getModel().id);
        }

        World.INSTANCE.worldSchedule.dailyResetTaskByTest().resetDailyAttackRankForTest();

        var ranks = World.INSTANCE.worldRank.getRanks();
        Assertions.assertEquals(Constants.MAX_ATTACK_RANK_COUNT, ranks.size());

        var rankSet = new HashSet<Integer>();
        for(var rank : ranks) {
            rankSet.add(rank.getRank());
        }

        for(int i=1; i<=100; i++) {
            Assertions.assertTrue(rankSet.contains(i));
        }

        var randomPlayer = l.get(SRandomUtils.nextInt(0, Constants.MAX_ATTACK_RANK_COUNT));
        Assertions.assertNotNull(randomPlayer);

        var playerRankInfo = World.INSTANCE.worldRank.getPlayerRankInfo(randomPlayer.getModel().id);
        Assertions.assertNotNull(playerRankInfo);
    }
}
