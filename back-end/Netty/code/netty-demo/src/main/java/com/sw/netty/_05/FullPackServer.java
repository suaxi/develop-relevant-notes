package com.sw.netty._05;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 粘包服务端 Demo
 *
 * @author suaxi
 * @date 2026/07/13 22:49
 */
@Slf4j
public class FullPackServer {
    public static void main(String[] args) {
        new FullPackServer().start();
        // 23:08:22.078 [nioEventLoopGroup-3-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0x2615b37f, L:/127.0.0.1:8088 - R:/127.0.0.1:62128] READ: 360B
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 64 35 33 64 34 38 37 33 2d 38 65 31 64 2d 34 62 |d53d4873-8e1d-4b|
        // |00000010| 31 31 2d 39 65 65 31 2d 31 33 34 36 30 35 62 38 |11-9ee1-134605b8|
        // |00000020| 37 34 32 63 34 35 38 64 30 66 63 62 2d 34 31 61 |742c458d0fcb-41a|
        // |00000030| 34 2d 34 62 61 33 2d 62 33 30 61 2d 66 38 66 65 |4-4ba3-b30a-f8fe|
        // |00000040| 65 36 31 65 64 65 34 61 31 38 33 39 37 61 34 37 |e61ede4a18397a47|
        // |00000050| 2d 61 35 36 34 2d 34 62 37 38 2d 61 62 37 39 2d |-a564-4b78-ab79-|
        // |00000060| 66 35 39 32 36 61 32 64 62 65 38 33 62 38 31 31 |f5926a2dbe83b811|
        // |00000070| 31 37 37 66 2d 66 36 63 62 2d 34 66 36 63 2d 38 |177f-f6cb-4f6c-8|
        // |00000080| 31 32 63 2d 38 61 38 64 61 62 34 32 62 66 32 38 |12c-8a8dab42bf28|
        // |00000090| 39 62 63 35 64 33 32 31 2d 35 36 64 63 2d 34 33 |9bc5d321-56dc-43|
        // |000000a0| 31 34 2d 62 30 61 39 2d 34 62 65 64 65 39 66 63 |14-b0a9-4bede9fc|
        // |000000b0| 35 33 35 66 66 38 61 32 33 65 37 63 2d 35 36 30 |535ff8a23e7c-560|
        // |000000c0| 33 2d 34 62 66 30 2d 62 34 66 33 2d 35 30 37 32 |3-4bf0-b4f3-5072|
        // |000000d0| 30 33 35 37 66 37 64 62 63 39 31 39 66 32 32 39 |0357f7dbc919f229|
        // |000000e0| 2d 30 32 63 34 2d 34 65 35 31 2d 62 63 30 36 2d |-02c4-4e51-bc06-|
        // |000000f0| 63 33 65 63 33 66 34 35 65 61 34 30 62 32 38 35 |c3ec3f45ea40b285|
        // |00000100| 38 65 30 36 2d 33 64 36 30 2d 34 35 64 32 2d 61 |8e06-3d60-45d2-a|
        // |00000110| 64 65 63 2d 35 36 35 62 35 61 35 31 30 61 37 35 |dec-565b5a510a75|
        // |00000120| 62 62 64 65 35 65 63 31 2d 63 62 62 62 2d 34 62 |bbde5ec1-cbbb-4b|
        // |00000130| 31 61 2d 61 39 36 35 2d 63 38 32 65 37 61 30 34 |1a-a965-c82e7a04|
        // |00000140| 34 32 33 32 35 39 32 65 61 33 38 34 2d 33 32 34 |4232592ea384-324|
        // |00000150| 30 2d 34 31 63 63 2d 39 66 35 65 2d 35 65 33 63 |0-41cc-9f5e-5e3c|
        // |00000160| 33 66 62 66 37 31 34 33                         |3fbf7143        |
        // +--------+-------------------------------------------------+----------------+
    }

    private void start() {
        NioEventLoopGroup main = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(main, worker);
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
