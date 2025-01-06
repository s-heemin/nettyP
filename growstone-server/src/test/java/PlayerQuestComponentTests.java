import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TQuest;
import com.supercat.growstone.network.messages.ZClear;
import com.supercat.growstone.network.messages.ZStat;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.events.EventPlayerClearStage;
import org.supercat.growstone.events.EventPlayerPlayGacha;
import org.supercat.growstone.events.EventPlayerStatGrowthLevelUp;
import org.supercat.growstone.events.EventPlayerUpgradePartsSlot;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.quests.ResourceQuest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayerQuestComponentTests extends BaseServerTests {
    @Test
    void questTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var tQuest = player.quest.getTQuest();
        Assertions.assertNotNull(tQuest);
        Assertions.assertEquals(1, tQuest.getStep());
        Assertions.assertEquals(0, tQuest.getProgress());
        Assertions.assertEquals(ZClear.State.DOING, tQuest.getState());



        for (int i = 0; i < ResourceManager.INSTANCE.quest.questSize() + 1; i++) {
            var outRewards = new ArrayList<TContentReward>();
            tQuest = player.quest.getTQuest();
            var resQuest = ResourceManager.INSTANCE.quest.getByStep(tQuest.getStep());
            Assertions.assertNotNull(resQuest);

            questProcess(player, tQuest, resQuest);

            var untilAt = Instant.now().plusSeconds(3);
            Awaitility.await()
                    .pollInSameThread()
                    .timeout(300, TimeUnit.SECONDS)
                    .pollDelay(1 / 60, TimeUnit.SECONDS)
                    .until(() -> {
                        var tempNow = Instant.now();
                        player.update();
                        return tempNow.isAfter(untilAt);
                    });

            player.quest.reward(outRewards);
            Assertions.assertTrue(!outRewards.isEmpty());
        }

        Assertions.assertEquals(1, tQuest.getStep());
        Assertions.assertEquals(0, tQuest.getProgress());
        Assertions.assertEquals(ZClear.State.DOING, tQuest.getState());
        Assertions.assertEquals(1, tQuest.getNextStageGroupIdCondition());
        Assertions.assertEquals(6, tQuest.getNextStageIdCondition());
    }

    void questProcess(WorldPlayer player, TQuest tQuest, ResourceQuest resQuest) {
        switch (resQuest.type) {
            case CLEAR_STAGE:
                player.topic.publish(new EventPlayerClearStage(tQuest.getNextStageGroupIdCondition(), tQuest.getNextStageIdCondition()));
                break;
            case PLAY_GACHA:
                player.topic.publish(new EventPlayerPlayGacha(resQuest.condition.param1, (int) resQuest.condition.param2));
                break;
            case STATGROWTH_BASE_ATTACK:
                player.topic.publish(new EventPlayerStatGrowthLevelUp(ZStat.Type.BASE_ATTACK, (int) resQuest.condition.param1));
                break;
            case STATGROWTH_BASE_DEFENSE:
                player.topic.publish(new EventPlayerStatGrowthLevelUp(ZStat.Type.BASE_DEFENSE, (int) resQuest.condition.param1));
                break;
            case STATGROWTH_BASE_HP:
                player.topic.publish(new EventPlayerStatGrowthLevelUp(ZStat.Type.BASE_HP, (int) resQuest.condition.param1));
                break;
            case PARTS_SLOT:
                player.topic.publish(new EventPlayerUpgradePartsSlot((int) resQuest.condition.param1));
                break;
        }
    }
}
