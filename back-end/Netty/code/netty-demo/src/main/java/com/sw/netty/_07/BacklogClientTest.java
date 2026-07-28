package com.sw.netty._07;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author suaxi
 * @date 2026/07/26 23:10
 */
@Slf4j
public class BacklogClientTest {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG));
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8088);
            channelFuture.sync().channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("BacklogClientTest error: ", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
