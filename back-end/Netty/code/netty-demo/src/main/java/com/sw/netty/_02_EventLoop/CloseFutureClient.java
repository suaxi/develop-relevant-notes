package com.sw.netty._02_EventLoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author suaxi
 * @date 2026/07/05 23:35
 */
@Slf4j
public class CloseFutureClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8088));

        Channel channel = channelFuture.channel();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    channel.close();
                    break;
                }
                channel.writeAndFlush(input);
            }
        }, "input client").start();

        ChannelFuture closeFuture = channel.closeFuture();
        log.info("closing...");
        // 1. 同步处理关闭
        // closeFuture.sync();
        // log.info("channel closed"); // 23:45:06.789 [main] INFO com.sw.netty._02_EventLoop.CloseFutureClient - channel closed
        // eventLoopGroup.shutdownGracefully(); // 23:46:08.678 [nioEventLoopGroup-2-1] DEBUG io.netty.buffer.PoolThreadCache - Freed 1 thread-local buffer(s) from thread: nioEventLoopGroup-2-1

        // 2. 异步关闭
        closeFuture.addListener((ChannelFutureListener) future -> {
            log.info("channel closed"); // 23:48:20.725 [nioEventLoopGroup-2-1] INFO com.sw.netty._02_EventLoop.CloseFutureClient - channel closed
            eventLoopGroup.shutdownGracefully(); // 23:49:24.541 [nioEventLoopGroup-2-1] DEBUG io.netty.buffer.PoolThreadCache - Freed 1 thread-local buffer(s) from thread: nioEventLoopGroup-2-1
        });
    }
}
