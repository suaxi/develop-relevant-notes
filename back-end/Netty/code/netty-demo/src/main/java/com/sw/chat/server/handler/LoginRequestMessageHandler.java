package com.sw.chat.server.handler;

import com.sw.chat.message.LoginRequestMessage;
import com.sw.chat.message.LoginResponseMessage;
import com.sw.chat.server.service.UserServiceFactory;
import com.sw.chat.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author suaxi
 * @date 2026/07/20 23:14
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();
        LoginResponseMessage loginResponseMessage;
        if (UserServiceFactory.getUserService().login(username, password)) {
            SessionFactory.getSession().bind(ctx.channel(), username);
            // 登录成功
            loginResponseMessage = new LoginResponseMessage(true, "登录成功");
        } else {
            // 登录失败
            loginResponseMessage = new LoginResponseMessage(false, "用户名或密码不正确");
        }
        ctx.writeAndFlush(loginResponseMessage);
    }
}
