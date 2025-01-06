import com.supercat.growstone.network.messages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Constants;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.World;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.jsons.JMPlayerExplorationQuest;
import org.supercat.growstone.jsons.JMPlayerExplorationQuests;
import org.supercat.growstone.rules.NameRules;
import org.supercat.growstone.setups.SDB;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WorldPlayerTests extends BaseServerTests {
    private static final Logger logger = LoggerFactory.getLogger(WorldPlayerTests.class);

    @Test
    void makePlayerTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        final var playerName = Constants.TEMP_PLAYER_NAME + player.getId();
        Assertions.assertEquals(playerName, player.getName());
    }

    @Test
    void makeManyPlayersTest() {
        var players = Collections.synchronizedList(new ArrayList<WorldPlayer>());
        var l = new ArrayList<Runnable>();
        for (int i = 0; i < 300; i++) {
            l.add(() -> {
                var player = TestPlayerUtils.of();
                players.add(player);
            });
        }

        l.parallelStream().forEach(x -> x.run());

        Assertions.assertEquals(300, players.size());
        for (int i = 0; i < 300; i++) {
            final var playerName = Constants.TEMP_PLAYER_NAME + players.get(i).getId();
            Assertions.assertEquals(playerName, players.get(i).getName());
        }
    }

    @Test
    void changeNameUntilAtTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        final var playerName = "아직시간이안되";
        int status = player.changeName(playerName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_CHANGE_TIME, status);

        // 30시간 전으로 세팅
        player.getModel().last_change_name_at = Instant.now().minus(Duration.ofHours(30));
        status = player.changeName(playerName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);
    }

    @Test
    void changeNameTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        final var playerName = Constants.TEMP_PLAYER_NAME + player.getId();
        Assertions.assertEquals(playerName, player.getName());

        var changeName = "씨발";
        int status = NameRules.reviewName(changeName);
        Assertions.assertEquals(StatusCode.NAME_CONTAINS_BAD_CHARACTER, status);

        status = player.changeName(changeName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.NAME_CONTAINS_BAD_CHARACTER, status);

        // 8글자 이상
        changeName = "이건긴글자라안되어요";
        status = player.changeName(changeName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.INVALID_CHANGE_NAME_LENGTH, status);

        status = player.changeName("아직시간이안되", ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_CHANGE_TIME, status);

        // 30시간 전으로 세팅
        player.getModel().last_change_name_at = Instant.now().minus(Duration.ofHours(30));

        changeName = "" + player.getId();
        status = player.changeName(changeName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        //메모리 변경 확인
        Assertions.assertEquals(changeName, player.getName());

        //디비 변경 확인
        var model = SDB.dbContext.player.get(player.getId());
        Assertions.assertNotNull(model);
        Assertions.assertEquals(changeName, model.name);

        status = player.changeName(changeName, ZChangePlayerNameResponse.newBuilder());
        Assertions.assertEquals(StatusCode.CHANGE_FAIL_TO_SAME_NAME, status);
    }

    @Test
    void changePortraitIcon() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        long defaultPortrait = GameData.PLAYER.defaultPortraitIconId;
        var resPortraitIcon = ResourceManager.INSTANCE.item.getAll().stream()
                .filter(x -> x.id != defaultPortrait)
                .findAny()
                .orElseGet(null);

        var equippedIcon = player.portraitIcon.get(defaultPortrait);
        Assertions.assertNotNull(equippedIcon);
        ;

        int status = player.portraitIcon.changePortraitIcon(resPortraitIcon.id);
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        status = player.categoryFilter.add(ZCategory.Type.PORTRAIT_ICON, resPortraitIcon.id, 1);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        status = player.portraitIcon.changePortraitIcon(resPortraitIcon.id);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var newEquippedIcon = player.portraitIcon.get(resPortraitIcon.id);
        Assertions.assertNotNull(newEquippedIcon);
        Assertions.assertEquals(player.getModel().portrait_id, newEquippedIcon.icon_id);
    }

    @Test
    public void testPlayerExplorationQuest() {
        var jmPlayerExplorationQuest = new ArrayList<JMPlayerExplorationQuest>();
        for (long i = 1; i < 4; ++i) {
            var jsonObject = JMPlayerExplorationQuest.of(TExplorationQuest.newBuilder()
                    .setSlotId((int)i)
                    .setTier(ZTier.Type.COMMON)
                    .addAllPetId(List.of(i, i + 1, i + 2))
                    .addAllNeedPetTier(List.of(ZTier.Type.RARE, ZTier.Type.EPIC, ZTier.Type.LEGENDARY))
                    .setReward(TContentReward.newBuilder()
                            .setCategory(ZCategory.Type.ITEM)
                            .setDataId(i)
                            .setCount(i)
                            .setBonusCount(i)
                            .build())
                    .setUntilAt(Instant.now().toEpochMilli() + 1000 * 60 * 60 * 24)
                    .build());

            var resultJSONObject = JMPlayerExplorationQuest.ofJson(jsonObject.toJson());

            jmPlayerExplorationQuest.add(resultJSONObject);
        }
        logger.info(jmPlayerExplorationQuest.toString());

        var results = new HashMap<Integer, TExplorationQuest>();
        for (var jmQuest : jmPlayerExplorationQuest) {
            results.put(jmQuest.ofTExplorationQuest().getSlotId(), jmQuest.ofTExplorationQuest());
        }

        var jmQuests = JMPlayerExplorationQuests.of(results).toJson();
        logger.info(jmQuests.toString());

        final int player_id = 912823;
        var model = SDB.dbContext.exploration.getOrDefault(player_id);
        model.exp = 55;
        model.quests = jmQuests.toString();
        SDB.dbContext.exploration.save(model);

        var dmExploration = SDB.dbContext.exploration.getOrDefault(player_id);
        var quests = JMPlayerExplorationQuests.ofTExplorationQuests(dmExploration.quests);
        logger.info(quests.toString());
    }
}

