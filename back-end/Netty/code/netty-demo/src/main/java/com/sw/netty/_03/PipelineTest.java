package com.sw.netty._03;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author suaxi
 * @date 2026/07/07 22:53
 */
@Slf4j
public class PipelineTest {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 1. pipeline
                        ChannelPipeline pipeline = ch.pipeline();
                        // 2. 添加 handler 处理器
                        pipeline.addLast("handler - 1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf bf = (ByteBuf) msg;
                                String data = bf.toString(Charset.defaultCharset());
                                log.info("handler-1 收到客户端发送的数据：{}", data);
                                // 必须显式传递给下一个 handler, channelRead 和 fireChannelRead 方法任选其一
                                // super.channelRead(ctx, ch);
                                ctx.fireChannelRead(data);
                            }
                        });

                        pipeline.addLast("handler - 2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("handler-2 收到上一个处理器处理之后的数据：{}", msg);
                                super.channelRead(ctx, msg);
                            }
                        });

                        pipeline.addLast("handler - 3", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("handler-3 收到上一个处理器处理之后的数据：{}", msg);
                                super.channelRead(ctx, msg);
                                // channel.write() 方法的流水线顺序： head -> handler-1 -> handler-2 -> handler-3 -> handler-5 -> handler-4 -> tail
                                // 入站处理器查找完后先到 tail，再从 tail 处从后往前查找出战处理器
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("sunxiaochuan".getBytes()));
                                // ctx.write() 方法的流水线顺序： head -> handler-1 -> handler-2 -> handler-3 -> tail
                                // 从调用 ctx.write() 方法的 handler 处从后往前查找
                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("sunxiaochuan".getBytes()));
                            }
                        });

                        pipeline.addLast("handler - 4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("4 处理数据...");
                                super.write(ctx, msg, promise);
                            }
                        });

                        pipeline.addLast("handler - 5", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("5 处理数据...");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                .bind(8088);
    }
}
