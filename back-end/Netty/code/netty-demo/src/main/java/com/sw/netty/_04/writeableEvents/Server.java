package com.sw.netty._04.writeableEvents;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author suaxi
 * @date 2026/06/28 22:36
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 999999; i++) {
                        sb.append(UUID.randomUUID().toString().replace("-", ""));
                    }

                    ByteBuffer bf = Charset.defaultCharset().encode(sb.toString());
                    int write = sc.write(bf);
                    System.out.println("OP_READ - 发送：" + write + " 字节数据");

                    // 2. 判断是否有剩余内容
                    if (bf.hasRemaining()) {
                        // 3. 关注可写事件（在原关注事件的基础上，需额外关注写事件）
                        scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
                        // scKey.interestOps(scKey.interestOps() | SelectionKey.OP_WRITE);
                        // 4. 将未写完的数据挂载到 scKey 上
                        scKey.attach(bf);
                    }
                }

                if (key.isWritable()) {
                    ByteBuffer bf = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(bf);
                    System.out.println("OP_WRITE - 发送：" + write + " 字节数据");

                    // 5. 关闭资源
                    if (!bf.hasRemaining()) {
                        // 清除挂载的 buffer
                        key.attach(null);

                        // 清除关注的事件
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }
}
