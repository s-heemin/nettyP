package org.supercat.growstone;

import com.google.common.base.Strings;
import com.supercat.growstone.PacketUtils;
import com.supercat.growstone.network.messages.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.controllers.ControllerSet;
import org.supercat.growstone.models.DMGlobalMasterPlayer;
import org.supercat.growstone.models.DMPlayer;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.BanType;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorldSession {
    private static final Logger logger = LoggerFactory.getLogger(WorldSession.class);

    private int sessionId;
    private Channel channel;
    private String remoteIP;
    private WorldPlayer player;
    private ControllerSet controllerSet;

    private final AtomicBoolean isLogin = new AtomicBoolean(false);

    public WorldSession(Channel channel, String remoteIP) {
        this.sessionId = Generater.generateSessionID();
        this.channel = channel;
        this.remoteIP = remoteIP;
        this.controllerSet = new ControllerSet(this);
    }

    public WorldPlayer getPlayer() {
        return player;
    }

    public int getSessionId() {
        return sessionId;
    }
    public void changeSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void mount(WorldPlayer player) {
        this.player = player;
        controllerSet.setPlayer(player);

        logger.info("mounted player - playerId({})", player.getId());
    }

    public <Type extends com.google.protobuf.GeneratedMessageV3> void sendPacket(long id, Type.Builder contents) {
        sendPacket(PacketUtils.packet(id, contents));
    }

    public void handlePacketV2(Packet packet) {
        controllerSet.response(packet);
    }

    public ChannelFuture sendPacket(final Packet packet) {
        if (Objects.nonNull(channel)) {
            logger.info("send packetType: {}, sessionId: {}", packet.getType(), getSessionId() );

            var responseData = org.supercat.growstone.network.PacketUtils.encode(packet);
            var frame = new BinaryWebSocketFrame(io.netty.buffer.Unpooled.wrappedBuffer(responseData));
            return channel.writeAndFlush(frame);
        }
        return null;
    }

    public void close() {
        if (this.channel != null) {
            this.channel.close();
            this.channel = null;
        }

        isLogin.compareAndSet(true, false);
    }

    public void handleDisconnected() {
        WorldPlayer player = this.player;
        if (Objects.nonNull(player)) {
            if (player.getSession() == this) {
                player.logout(false);
            }
            logger.info("disconnect player - playerId({})", player.getId());
        }

        this.player = null;
        this.channel = null;
    }

    public void login(long packetId, ZLoginRequest login) {
        var loginType = login.getLoginType();
        var loginToken = login.getTokenId();

        if (isLogin.compareAndSet(false, true)) {

            // 게스트 로그인이지만 guestId가 없을 경우는 최초 로그인이다.
            // GlobalMasterPlayerId를 발급하고 저장한다.
            var globalPlayerModel = new DMGlobalMasterPlayer();
            if(loginType == ZLogin.Type.GUEST) {
                globalPlayerModel = SDB.dbContext.globalMasterPlayer.getByGuest(loginToken);
                if (Objects.isNull(globalPlayerModel)) {
                    globalPlayerModel = DMGlobalMasterPlayer.of(login.getDeviceOs(), login.getDeviceModel(),
                            ZLogin.Type.GUEST_VALUE, BanType.NONE.value);
                    SDB.dbContext.globalMasterPlayer.insert(globalPlayerModel);

                    // global master plyaer id로 해싱하여 guest_token_id를 생성한다.
                    globalPlayerModel.guest_token_id = Utility.hash(Utility.GUEST_TOKEN_PREFIX + globalPlayerModel.id);
                    SDB.dbContext.globalMasterPlayer.updateTokenIdByGuest(globalPlayerModel);
                }
            } else {
                globalPlayerModel = SDB.dbContext.globalMasterPlayer.getByLoginInfo(loginType.getNumber(), loginToken);
                if (Objects.isNull(globalPlayerModel)) {
                    globalPlayerModel = DMGlobalMasterPlayer.of(login.getDeviceOs(), login.getDeviceModel(),
                            loginType.getNumber(), BanType.NONE.value);
                    globalPlayerModel.login_id = loginToken;
                    globalPlayerModel.login_type = loginType.getNumber();

                    SDB.dbContext.globalMasterPlayer.insert(globalPlayerModel);
                }
            }

            var playerModel = SDB.dbContext.player.getLastConnectedPlayer(globalPlayerModel.id);
            if (Objects.isNull(playerModel)) {
                playerModel = DMPlayer.of(globalPlayerModel.id, World.INSTANCE.model.id);
                SDB.dbContext.player.save(playerModel);
            }

            {
                // 첫 로그인시에는 아이디를 자동 생성해줘야한다.
                if(Strings.isNullOrEmpty(playerModel.name)) {
                    playerModel.friend_code = Utility.hash(Utility.FRIEND_TOKEN_PREFIX + playerModel.id);
                    playerModel.name = Constants.TEMP_PLAYER_NAME + playerModel.id;
                    SDB.dbContext.player.updateAfterInsertData(playerModel);
                }
            }

            playerModel.last_connected_at = Instant.now();

            var oldSessionId = SRedis.INSTANCE.content.session.getUserId(playerModel.global_master_player_id);
            // 재접속을 했는데 세션아이디가 존재한다면 중복 접속으로 인지한다.
            if (oldSessionId > 0) {
                // 기존에 있던 세션은 종료시킨다.
                var oldPlayer = World.INSTANCE.worldPlayers.getPlayer(playerModel.id);
                if (Objects.nonNull(oldPlayer)) {
                    oldPlayer.sendPacket(0, ZLogoutNotify.newBuilder()
                            .setType(ZLogout.Type.DUPLICATE_CONNECT));
                    oldPlayer.getSession().changeSessionId(this.sessionId);

                    mount(oldPlayer);
                }
            } else {
                var player = new WorldPlayer(this, globalPlayerModel, playerModel);
                mount(player);
            }

            player.login(packetId);
        }
    }
}
