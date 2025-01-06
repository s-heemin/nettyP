package org.supercat.growstone.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static final NettyServer INSTANCE = new NettyServer();

    public void serverStart() {
        try {
            start();
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public void start() throws Exception {
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer()) // `ChannelInitializer` 설정
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(8080).sync();
            System.out.println("WebSocket server started at port " + 8080);

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            if (e instanceof java.net.BindException) {
                System.err.println("Port " + 8080 + " is already in use. Please use a different port.");
            } else {
                e.printStackTrace();
            }
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.exit(-1);
        }
    }

}
