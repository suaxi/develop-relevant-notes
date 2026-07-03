package com.sw.nio._04.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static utils.ByteBufferUtil.debugAll;

/**
 * block server demo
 *
 * @author suaxi
 * @date 2026/06/25 22:00
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8088));
        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            log.info("connecting...");
            // 阻塞监听
            SocketChannel channel = ssc.accept();
            log.info("connected - [{}]", channel);
            channelList.add(channel);
            for (SocketChannel sc : channelList) {
                log.info("before read - [{}]", channel);
                // 阻塞读
                sc.read(bf);
                bf.flip();
                debugAll(bf);
                bf.clear();
                log.info("after read - [{}]", channel);
            }
        }
    }
}
