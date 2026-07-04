package com.sw.netty._02_EventLoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author suaxi
 * @date 2026/07/03 23:08
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 1.启动器
        Channel channel = new Bootstrap()
                // 2. EventLoop
                .group(new NioEventLoopGroup())
                // 3. channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加 handler
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 在连接建立之后被调用
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8088))
                .sync()
                .channel();
        System.out.println("test");
    }
}
