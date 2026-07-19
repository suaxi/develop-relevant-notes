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
public class GroupCreateRequestMessage extends Message {
    private String groupName;
    private Set<String> members;

    public GroupCreateRequestMessage(String groupName, Set<String> members) {
        this.groupName = groupName;
        this.members = members;
    }

    @Override
    public int getMessageType() {
        return GroupCreateRequestMessage;
    }
}
