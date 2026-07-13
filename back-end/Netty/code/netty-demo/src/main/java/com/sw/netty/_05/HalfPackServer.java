package com.sw.netty._05;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 半包服务端 Demo
 *
 * @author suaxi
 * @date 2026/07/13 23:11
 */
@Slf4j
public class HalfPackServer {
    public static void main(String[] args) {
        new HalfPackServer().start();
    }

    private void start() {
        NioEventLoopGroup main = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(main, worker);
            // 服务端指定接收缓冲区大小为 16 字节
            serverBootstrap.option(ChannelOption.SO_RCVBUF, 16);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            log.info("connected {}", ctx.channel());
                            super.channelActive(ctx);
                        }

                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            log.info("disconnected {}", ctx.channel());
                            super.channelInactive(ctx);
                        }
                    });
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8088);
            log.info("{} binding...", channelFuture.channel());
            channelFuture.sync();
            log.info("{} bound...", channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("server error {}", e.getMessage());
        } finally {
            main.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("server stoped");
        }
    }
}
