package com.sw.netty._01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author suaxi
 * @date 2026/07/03 22:56
 */
public class Server {
    public static void main(String[] args) {
        // 1. 启动器，负责组装 netty 组件
        new ServerBootstrap()
                // 2. 事件组
                .group(new NioEventLoopGroup())
                // 3. channel 实现
                .channel(NioServerSocketChannel.class)
                // 4. 声明不同的 worker 负责的工作（处理哪些事件）
                .childHandler(// 5. 与客户端进行数据读写的通道（初始化工作）
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 6. 添加具体 handler
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 6. 监听端口
                .bind(8088);
    }
}
