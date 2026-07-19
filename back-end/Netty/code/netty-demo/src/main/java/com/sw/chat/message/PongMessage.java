package com.sw.chat.message;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public class PongMessage extends Message {
    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
