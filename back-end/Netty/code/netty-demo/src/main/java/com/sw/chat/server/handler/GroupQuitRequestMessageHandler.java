package com.sw.chat.server.handler;

import com.sw.chat.message.GroupQuitRequestMessage;
import com.sw.chat.message.GroupQuitResponseMessage;
import com.sw.chat.server.session.Group;
import com.sw.chat.server.session.GroupSession;
import com.sw.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 退出群聊请求消息处理器
 *
 * @author suaxi
 * @date 2026/07/21 23:25
 */
@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        String username = msg.getUsername();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.removeMember(groupName, username);
        if (group == null) {
            ctx.writeAndFlush(new GroupQuitResponseMessage(false, "操作失败，群聊不存在[" + groupName + "]"));
        } else {
            ctx.writeAndFlush(new GroupQuitResponseMessage(true, "退出群聊[" + groupName + "]成功"));
            List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupQuitResponseMessage(true, "用户[" + username + "]退出群聊[" + groupName + "]"));
            }
        }
    }
}
