package com.sw.chat.server;

import com.sw.chat.message.LoginRequestMessage;
import com.sw.chat.message.LoginResponseMessage;
import com.sw.chat.protocol.MessageCodecSharable;
import com.sw.chat.protocol.ProcotolFrameDecoder;
import com.sw.chat.server.service.UserServiceFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Slf4j
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup main = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(main, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<LoginRequestMessage>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
                            String username = msg.getUsername();
                            String password = msg.getPassword();
                            LoginResponseMessage loginResponseMessage;
                            if (UserServiceFactory.getUserService().login(username, password)) {
                                // 登录成功
                                loginResponseMessage = new LoginResponseMessage(true, "登录成功");
                            } else {
                                // 登录失败
                                loginResponseMessage = new LoginResponseMessage(false, "用户名或密码不正确");
                            }
                            ctx.writeAndFlush(loginResponseMessage);
                        }
                    });
                }
            });
            Channel channel = serverBootstrap.bind(8088).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("ChatServer error", e);
        } finally {
            main.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
