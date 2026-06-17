package com.sw.netty._01;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author suaxi
 * @date 2026/06/17 22:07
 */
@Slf4j
public class ByteBufferTest {
    public static void main(String[] args) {
        try (InputStream is = ByteBufferTest.class.getClassLoader().getResourceAsStream("ByteBufferTest.txt")) {
            if (is == null) {
                throw new IllegalArgumentException("resource not found: ByteBufferTest.txt");
            }

            try (ReadableByteChannel channel = Channels.newChannel(is)) {
                // 准备缓冲区
                ByteBuffer bf = ByteBuffer.allocate(10);
                // 从 channel 读数据，写入 buffer
                int len = channel.read(bf);
                log.info("读取到的字节数：{}", len);

                // 切换为读模式
                bf.flip();
                while (bf.hasRemaining()) {
                    byte b = bf.get();
                    log.info("读取到的字节：{}", (char) b);
                }

                // 切换为写模式
                bf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
