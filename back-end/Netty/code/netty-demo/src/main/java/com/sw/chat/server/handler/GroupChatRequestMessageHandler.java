package com.sw.chat.server.handler;

import com.sw.chat.message.GroupChatRequestMessage;
import com.sw.chat.message.GroupChatResponseMessage;
import com.sw.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 群聊请求消息处理器
 *
 * @author suaxi
 * @date 2026/07/20 23:26
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        List<Channel> membersChannel = GroupSessionFactory.getGroupSession().getMembersChannel(groupName);
        if (!membersChannel.isEmpty()) {
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupChatResponseMessage(msg.getFrom(), msg.getContent()));
            }
        }
    }
}
