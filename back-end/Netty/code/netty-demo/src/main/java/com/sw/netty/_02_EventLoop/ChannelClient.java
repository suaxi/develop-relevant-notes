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

/**
 * @author suaxi
 * @date 2026/07/05 22:58
 */
@Slf4j
public class ChannelClient {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 1. 连接到服务端
                // connect 异步阻塞，main 线程创建 channelFuture，而后真正去执行 connect 的是 nio 线程
                .connect(new InetSocketAddress("localhost", 8088));

        // 2.1 使用 sync 方法同步处理结果
        // 调用 sync 时，线程阻塞直到 nio 线程与服务端连接建立完毕
        // channelFuture.sync();
        // Channel channel = channelFuture.channel();
        // log.info("sync {}", channel);
        // channel.writeAndFlush("sync test");

        // 2.2 使用 addListener 异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // 在 nio 线程与服务端建立好连接后，调用 operationComplete 执行后面的操作
                Channel channel = channelFuture.channel();
                log.info("async {}", channel);
                channel.writeAndFlush("async test");
            }
        });
    }
}
