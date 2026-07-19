package com.sw.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Data
@ToString(callSuper = true)
public class GroupJoinRequestMessage extends Message {
    private String groupName;

    private String username;

    public GroupJoinRequestMessage(String username, String groupName) {
        this.groupName = groupName;
        this.username = username;
    }

    @Override
    public int getMessageType() {
        return GroupJoinRequestMessage;
    }
}
