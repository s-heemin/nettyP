package org.supercat.growstone.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new IdleStateHandler(120, 0, 0));

        // HTTP 요청과 응답을 처리하는 핸들러 추가
        p.addLast(new HttpServerCodec()); // HTTP 요청과 응답을 처리
        p.addLast(new HttpObjectAggregator(65536)); // HTTP 메시지의 조각을 모아서 완전한 메시지로 조합

        // 웹소켓 프로토콜 핸들러 추가
        p.addLast(new WebSocketServerProtocolHandler("/ws")); // "/ws" 경로에 대한 웹소켓 핸들링

        // 사용자 정의 핸들러 추가
        p.addLast(new NettyServerHandler()); // 웹소켓 프레임을 처리할 사용자 정의 핸들러
    }
}
