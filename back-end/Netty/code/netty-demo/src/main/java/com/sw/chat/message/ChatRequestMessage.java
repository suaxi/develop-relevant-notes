package com.sw.chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Data
@ToString(callSuper = true)
public class ChatRequestMessage extends Message {
    private String content;
    private String to;
    private String from;

    public ChatRequestMessage() {
    }

    public ChatRequestMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public int getMessageType() {
        return ChatRequestMessage;
    }
}
