package com.sw.chat.message;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Data
@ToString(callSuper = true)
public class GroupMembersResponseMessage extends Message {

    private Set<String> members;

    public GroupMembersResponseMessage(Set<String> members) {
        this.members = members;
    }

    @Override
    public int getMessageType() {
        return GroupMembersResponseMessage;
    }
}
