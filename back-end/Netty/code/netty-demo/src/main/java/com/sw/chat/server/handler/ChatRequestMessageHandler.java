package com.sw.chat.server.handler;

import com.sw.chat.message.ChatRequestMessage;
import com.sw.chat.message.ChatResponseMessage;
import com.sw.chat.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author suaxi
 * @date 2026/07/20 23:15
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String msgTo = msg.getTo();
        Channel channel = SessionFactory.getSession().getChannel(msgTo);
        if (channel != null) {
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        } else {
            ctx.writeAndFlush(new ChatResponseMessage(false, "用户[" + msgTo + "]不在线"));
        }
    }
}
