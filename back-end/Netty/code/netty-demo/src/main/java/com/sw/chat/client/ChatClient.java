package com.sw.chat.client;

import com.sw.chat.message.*;
import com.sw.chat.protocol.MessageCodecSharable;
import com.sw.chat.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

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
        CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
        AtomicBoolean LOGIN = new AtomicBoolean(false);
        AtomicBoolean EXIT = new AtomicBoolean(false);
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
                    // 3s 内未向 server 写数据，则会触发 IdleState WRITER_IDLE 写空闲事件
                    ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
                    // ChannelDuplexHandler 双工的，可以同时处理读写（出站/入站）处理器
                    ch.pipeline().addLast(new ChannelDuplexHandler() {
                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            if (evt instanceof IdleStateEvent) {
                                IdleStateEvent event =(IdleStateEvent) evt;
                                if (IdleState.WRITER_IDLE == event.state()) {
                                    // 3s 未写数据，发送心跳包
                                    // ctx.writeAndFlush(new PingMessage());
                                }
                            }
                        }
                    });
                    ch.pipeline().addLast("chat client", new ChannelInboundHandlerAdapter() {
                        // 接收响应消息
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.debug("resp msg: {}", msg);
                            if (msg instanceof LoginResponseMessage) {
                                LoginResponseMessage loginResponseMessage = (LoginResponseMessage) msg;
                                if (loginResponseMessage.isSuccess()) {
                                    LOGIN.set(true);
                                }
                                // 唤醒登录之后的操作
                                WAIT_FOR_LOGIN.countDown();
                            }
                        }

                        // 连接建立后触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 接收用户在控制台的输入，并发送给服务端
                            new Thread(() -> {
                                Scanner sc = new Scanner(System.in);
                                System.out.println("请输入用户名：");
                                if (EXIT.get()) {
                                    return;
                                }
                                String username = sc.nextLine();
                                System.out.println("请输入密码：");
                                if (EXIT.get()) {
                                    return;
                                }
                                String password = sc.nextLine();

                                // 构建登录消息对象
                                LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                                ctx.writeAndFlush(loginRequestMessage);
                                try {
                                    WAIT_FOR_LOGIN.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if (!LOGIN.get()) {
                                    ctx.channel().close();
                                    return;
                                }

                                while (true) {
                                    System.out.println("==================================");
                                    System.out.println("send [username] [content]");
                                    System.out.println("gsend [group name] [content]");
                                    System.out.println("gcreate [group name] [m1,m2,m3...]");
                                    System.out.println("gmembers [group name]");
                                    System.out.println("gjoin [group name]");
                                    System.out.println("gquit [group name]");
                                    System.out.println("quit");
                                    System.out.println("==================================");
                                    if (EXIT.get()) {
                                        return;
                                    }
                                    String[] command = sc.nextLine().split(" ");
                                    switch (command[0]) {
                                        case "send":
                                            ctx.writeAndFlush(new ChatRequestMessage(username, command[1], command[2]));
                                            break;
                                        case "gsend":
                                            ctx.writeAndFlush(new GroupChatRequestMessage(username, command[1], command[2]));
                                            break;
                                        case "gcreate":
                                            Set<String> members = new HashSet<>(Collections.singleton(command[2]));
                                            members.add(username);
                                            ctx.writeAndFlush(new GroupCreateRequestMessage(command[1], members));
                                            break;
                                        case "gmembers":
                                            ctx.writeAndFlush(new GroupMembersRequestMessage(command[1]));
                                            break;
                                        case "gjoin":
                                            ctx.writeAndFlush(new GroupJoinRequestMessage(username, command[1]));
                                            break;
                                        case "gquit":
                                            ctx.writeAndFlush(new GroupQuitRequestMessage(username, command[1]));
                                            break;
                                        case "quit":
                                            ctx.channel().close();
                                            return;
                                        default:
                                            break;
                                    }

                                }
                            }, "system.in").start();
                        }

                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            log.info("服务器超时断开连接，按任意键退出...");
                            EXIT.set(true);
                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            log.error("服务器异常断开连接：{}", cause.getMessage());
                            EXIT.set(true);
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
