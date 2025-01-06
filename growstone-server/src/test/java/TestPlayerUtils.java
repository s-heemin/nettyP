import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZLogin;
import org.awaitility.Awaitility;
import org.supercat.growstone.*;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.auth.LoginVerifier;
import org.supercat.growstone.events.EventPlayerCreateClan;
import org.supercat.growstone.events.EventPlayerJoinClanRequest;
import org.supercat.growstone.models.DMGlobalMasterPlayer;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.BanType;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestPlayerUtils {
    public static WorldPlayer of() {
        String loginToken = String.valueOf(Instant.now().getEpochSecond() * SRandomUtils.nextFloat(0, 1));
        var globalPlayerModel = SDB.dbContext.globalMasterPlayer.getByLoginInfo(ZLogin.Type.GUEST.getNumber(), loginToken);
        if (Objects.isNull(globalPlayerModel)) {
            globalPlayerModel = DMGlobalMasterPlayer.of("TEST", "TEST", ZLogin.Type.GUEST.getNumber(), BanType.NONE.value);
            SDB.dbContext.globalMasterPlayer.insert(globalPlayerModel);
            globalPlayerModel.guest_token_id = Utility.hash(Utility.GUEST_TOKEN_PREFIX + globalPlayerModel.id);
            SDB.dbContext.globalMasterPlayer.updateTokenIdByGuest(globalPlayerModel);
        }

        var playerModel = SDB.dbContext.player.getLastConnectedPlayer(globalPlayerModel.id);
        if (Objects.isNull(playerModel)) {
            playerModel = DMPlayer.of(globalPlayerModel.id, World.INSTANCE.model.id);
            SDB.dbContext.player.save(playerModel);
        }

        {
            // 친구 코드를 넣어줘야한다.
            playerModel.friend_code = Utility.hash(Utility.FRIEND_TOKEN_PREFIX + playerModel.id);
            playerModel.name = Constants.TEMP_PLAYER_NAME + playerModel.id;
            SDB.dbContext.player.updateAfterInsertData(playerModel);
        }

        playerModel.last_connected_at = Instant.now();
        var player = new WorldPlayer(new WorldSession(null, ""), globalPlayerModel, playerModel);

        SRedis.INSTANCE.content.session.addSession(playerModel.global_master_player_id, player.getSession().getSessionId());
        SRedis.INSTANCE.content.players.addPlayerId(playerModel.id);
        return player;
    }

    public static int createClan(WorldPlayer player, boolean isSuccessful) {
        if(isSuccessful) {
            int status = player.categoryFilter.add(ZCategory.Type.ITEM, GameData.CLAN.CLAN_CREATE_NEED_ITEM_DATA_ID, GameData.CLAN.CLAN_CREATE_NEED_AMOUNT);
            if(!StatusCode.isSuccess(status)) {
                return status;
            }
        }
        player.topic.publish(new EventPlayerCreateClan(String.valueOf(player.getId()), 0L));
        awaitFunc(player, 1);

        return player.getClanId() > 0 ? StatusCode.SUCCESS : StatusCode.FAIL;
    }

    public static int joinClan(WorldPlayer player, long clanId) {
        player.topic.publish(new EventPlayerJoinClanRequest(0L, clanId));
        awaitFunc(player, 3);

        return player.getClanId() > 0 ? StatusCode.SUCCESS : StatusCode.FAIL;
    }

    public static void awaitFunc(WorldPlayer player, int second) {
        var untilAt = Instant.now().plusSeconds(second);
        Awaitility.await()
                .pollInSameThread()
                .timeout(300, TimeUnit.SECONDS)
                .pollDelay(1 / 60, TimeUnit.SECONDS)
                .until(() -> {
                    var tempNow = Instant.now();
                    player.update();
                    return tempNow.isAfter(untilAt);
                });
    }

    public static void awaitFunc(int second) {
        var untilAt = Instant.now().plusSeconds(second);
        Awaitility.await()
                .pollInSameThread()
                .timeout(300, TimeUnit.SECONDS)
                .pollDelay(1 / 60, TimeUnit.SECONDS)
                .until(() -> {
                    var tempNow = Instant.now();
                    return tempNow.isAfter(untilAt);
                });
    }
}
