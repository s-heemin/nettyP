package org.supercat.growstone.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SLog;
import org.supercat.growstone.WorldSession;
import org.supercat.growstone.network.PacketUtils;

import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    private static final ConcurrentHashMap<Channel, WorldSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        String remoteIP = null;
        try {
            remoteIP = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
        } catch (Exception e) {
            SLog.logException(e);
        }

        WorldSession gameSession = new WorldSession(ctx.channel(), remoteIP);
        sessionMap.put(ctx.channel(), gameSession);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        var gameSession = sessionMap.get(ctx.channel());
        if (Objects.isNull(gameSession)) {
            return;
        }

        super.handlerRemoved(ctx);
        gameSession.handleDisconnected();
        sessionMap.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        final WorldSession gameSession = sessionMap.get(ctx.channel());
        if (frame instanceof BinaryWebSocketFrame) {
            var packet = PacketUtils.decode(frame.content());
            logger.info("Received packetType: {}, sessionId: ({})", packet.getType(), gameSession.getSessionId());
            gameSession.handlePacketV2(packet);
        } else {
            throw new UnsupportedOperationException("Unsupported frame type: " + frame.getClass().getName());
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
