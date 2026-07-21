package com.sw.chat.server.handler;

import com.sw.chat.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 退出请求处理器
 *
 * @author suaxi
 * @date 2026/07/21 23:36
 */
@Slf4j
public class LogoutHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        SessionFactory.getSession().unbind(channel);
        log.info("{} 连接已断开", channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        SessionFactory.getSession().unbind(channel);
        log.error("{} 连接已断开：{}", channel, cause.getMessage());
    }
}
