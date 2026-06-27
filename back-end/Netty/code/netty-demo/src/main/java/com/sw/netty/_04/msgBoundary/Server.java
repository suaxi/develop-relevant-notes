package com.sw.netty._04.msgBoundary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

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
        // 1. Selector
        Selector selector = Selector.open();

        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);

        // 2. 注册 Channel
        /**
         * 通过 SelectionKey，可以知道发生的事件和发生事件的 Channel
         * 事件类型：
         * accept：有连接请求时触发
         * connection：（客户端）连接建立后触发
         * read：读事件
         * write：写事件
         */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register channel key-[{}]", sscKey);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            // 3. select 方法，没有事件发生或事件取消时，线程阻塞，否则反之
            selector.select();

            // 4. 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理完事件后，需要显式删除对应的key（事件处理完了，但 Selector 不会删除对应的key）
                iterator.remove();

                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    log.info("Deal Accept Event");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);

                    ByteBuffer readBf = ByteBuffer.allocate(16);
                    // 将 readBf 作为附件关联到事件 key 上
                    // 将 readBf 的生命周期提升到与 SelectionKey 平级
                    SelectionKey scKey = sc.register(selector, 0, readBf);
                    scKey.interestOps(SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    try {
                        log.info("Deal Read Event");
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer readBf = (ByteBuffer) key.attachment();
                        if (-1 != channel.read(readBf)) {
                            split(readBf);

                            // 当传输的内容超过 readbf 初始容量时，需进行扩容
                            if (readBf.position() == readBf.limit()) {
                                ByteBuffer newReadBf = ByteBuffer.allocate(readBf.capacity() * 2);
                                readBf.flip();
                                newReadBf.put(readBf);
                                key.attach(newReadBf);
                            }
                        } else {
                            // 处理客户端读事件结束，正常断开
                            log.info("Client - [{}] close", key);
                            key.cancel();
                        }
                    } catch (IOException e) {
                        // 对发生异常的事件取消注册（从 Selector key 集合中移除）
                        // 如：客户端关闭主动断开连接
                        key.cancel();
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if ('\n' == source.get(i)) {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }

        // 此处不使用 clear，需使用 compact 将剩余未读的部分向前移动
        source.compact();
    }
}
