package com.sw.chat.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }
}
