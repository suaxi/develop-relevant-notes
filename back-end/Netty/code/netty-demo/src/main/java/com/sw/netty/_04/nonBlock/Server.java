package com.sw.netty._04.nonBlock;

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
 * non-blocking server demo
 *
 * @author suaxi
 * @date 2026/06/25 22:00
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8088));
        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            SocketChannel channel = ssc.accept();
            if (channel != null) {
                log.info("connected - [{}]", channel);
                // 切换为非阻塞模式
                channel.configureBlocking(false);
                channelList.add(channel);
            }
            for (SocketChannel sc : channelList) {
                if (sc.read(bf) > 0) {
                    bf.flip();
                    debugAll(bf);
                    bf.clear();
                    log.info("after read - [{}]", channel);
                }
            }
        }
    }
}
