package com.sw.netty._02_EventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author suaxi
 * @date 2026/07/04 22:34
 */
@Slf4j
public class EventLoopTest {
    public static void main(String[] args) {
        // 1. 创建事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        // 2. 执行普通任务
        // eventExecutors.next().execute(() -> log.info("execute normal task"));

        // 3. 定时任务
        eventExecutors.next().scheduleAtFixedRate(() -> log.info("execute schedule task"), 1L, 2L, TimeUnit.SECONDS);
        log.info("main");
    }
}
