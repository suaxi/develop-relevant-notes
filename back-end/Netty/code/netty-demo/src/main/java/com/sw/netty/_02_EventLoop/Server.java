package com.sw.netty._02_EventLoop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author suaxi
 * @date 2026/07/04 22:43
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        DefaultEventLoopGroup defaultGroup = new DefaultEventLoopGroup();
        new ServerBootstrap()
                // accept eventLoop, read eventLoop
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handler-01", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.info("处理时间较长的请求");
                                        ByteBuf bf = (ByteBuf) msg;
                                        log.info(bf.toString(Charset.defaultCharset()));

                                        // 将消息传递给下一个 handler
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast(defaultGroup, "handler-02", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.info("处理时间正常的请求");
                                        ByteBuf bf = (ByteBuf) msg;
                                        log.info(bf.toString(Charset.defaultCharset()));
                                    }
                                });
                    }
                })
                .bind(8088);
    }
}
