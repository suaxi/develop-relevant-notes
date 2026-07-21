package com.sw.chat.server.handler;

import com.sw.chat.message.GroupMembersRequestMessage;
import com.sw.chat.message.GroupMembersResponseMessage;
import com.sw.chat.server.session.GroupSession;
import com.sw.chat.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

/**
 * 查询群聊成员请求消息处理器
 *
 * @author suaxi
 * @date 2026/07/21 23:12
 */
@ChannelHandler.Sharable
public class GroupMembersRequestMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Set<String> members = groupSession.getMembers(groupName);
        ctx.writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
