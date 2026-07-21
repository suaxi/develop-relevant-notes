package com.sw.chat.server.handler;

import com.sw.chat.message.GroupJoinRequestMessage;
import com.sw.chat.message.GroupJoinResponseMessage;
import com.sw.chat.server.session.Group;
import com.sw.chat.server.session.GroupSession;
import com.sw.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 加入群聊请求消息处理器
 *
 * @author suaxi
 * @date 2026/07/21 23:19
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        String username = msg.getUsername();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.joinMember(groupName, username);
        if (group == null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(false, "操作失败，群聊不存在[" + groupName + "]"));
        } else {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, "加入群聊[" + groupName + "]成功"));
            List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupJoinResponseMessage(true, "用户[" + username + "]加入群聊[" + groupName + "]"));
            }
        }
    }
}
