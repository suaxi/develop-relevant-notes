package com.sw.netty._04.writeableEvents;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author suaxi
 * @date 2026/06/28 22:44
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));

        int count = 0;
        while (true) {
            ByteBuffer bf = ByteBuffer.allocate(1024 * 1024);
            count += sc.read(bf);
            System.out.println("接收到：" + count + " 字节数据");
        }
    }
}
