package com.sw.chat.server.handler;

import com.sw.chat.message.GroupCreateRequestMessage;
import com.sw.chat.message.GroupCreateResponseMessage;
import com.sw.chat.server.session.Group;
import com.sw.chat.server.session.GroupSession;
import com.sw.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

/**
 * 创建群聊请求消息处理器
 *
 * @author suaxi
 * @date 2026/07/20 23:28
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group == null) {
            // 给成员发送新的拉群消息
            List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已加入[" + groupName + "]群聊"));
            }
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, "群聊[" + groupName + "]创建成功"));
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, "群聊[" + groupName + "]已存在"));
        }
    }
}
