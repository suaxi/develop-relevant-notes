package com.sw.netty._04.writeableEvents;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author suaxi
 * @date 2026/06/28 22:44
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        sc.connect(new InetSocketAddress("localhost", 8088));

        int count = 0;
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isConnectable()) {
                    log.info("key [{}] connected", key);
                    sc.finishConnect();
                }

                if (key.isReadable()) {
                    ByteBuffer bf = ByteBuffer.allocate(1024 * 1024);
                    count += sc.read(bf);
                    bf.clear();
                    System.out.println("接收到：" + count + " 字节数据");
                }
            }
        }
    }
}
