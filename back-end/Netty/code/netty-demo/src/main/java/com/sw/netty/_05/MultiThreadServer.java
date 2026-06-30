package com.sw.netty._05;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

import static utils.ByteBufferUtil.debugAll;

/**
 * @author suaxi
 * @date 2026/06/30 22:51
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8088));

        // 1. 创建 worker
        Worker worker = new Worker("worker-0");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("connected - [{}]", sc.getLocalAddress());

                    // 2. 关联读写事件的 selector
                    log.info("before register - [{}]", sc.getLocalAddress());
                    worker.register(sc);
                    log.info("after register - [{}]", sc.getLocalAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {

        private Thread thread;

        private Selector selector;

        private String name;

        private volatile boolean start = false;

        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程、Selector
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                start = true;
            }

            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });

            // 显示唤醒 worker 的 selector.select() 阻塞，注册后续的读写事件
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select(); // 阻塞
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer bf = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            log.info("read client data - [{}]", channel.getLocalAddress());
                            channel.read(bf);
                            bf.flip();
                            debugAll(bf);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
