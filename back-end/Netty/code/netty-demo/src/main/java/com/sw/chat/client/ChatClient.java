package com.sw.chat.client;

import com.sw.chat.message.LoginRequestMessage;
import com.sw.chat.protocol.MessageCodecSharable;
import com.sw.chat.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast("chat client", new ChannelInboundHandlerAdapter() {
                        //接收响应消息


                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.debug("resp msg: {}", msg);
                        }

                        // 连接建立后触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 接收用户在控制台的输入，并发送给服务端
                            new Thread(() -> {
                                Scanner sc = new Scanner(System.in);
                                System.out.println("请输入用户名：");
                                String username = sc.nextLine();
                                System.out.println("请输入密码：");
                                String password = sc.nextLine();

                                // 构建登录消息对象
                                LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                                ctx.writeAndFlush(loginRequestMessage);
                            }, "system.in").start();
                        }
                    });
                }
            });
            Channel channel = bootstrap.connect("localhost", 8088).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("ChatClient error", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
