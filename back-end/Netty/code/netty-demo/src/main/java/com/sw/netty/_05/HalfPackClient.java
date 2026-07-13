package com.sw.netty._05;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * 半包客户端 demo
 *
 * @author suaxi
 * @date 2026/07/13 23:13
 */
@Slf4j
public class HalfPackClient {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    log.info("connected...");
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            log.info("sending data...");
                            for (int i = 0; i < 10; i++) {
                                String data = UUID.randomUUID().toString();
                                ByteBuf bf = ctx.alloc().buffer();
                                bf.writeBytes(data.getBytes());
                                ctx.writeAndFlush(bf);
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8088);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("client error {}", e.getMessage());
        } finally {
            worker.shutdownGracefully();
        }
    }
}
