package com.sw.netty._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

/**
 * @author suaxi
 * @date 2026/06/18 22:51
 */
public class ByteBufferReadWriteTest {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(10);
        bf.put((byte) 0x16);
        debugAll(bf);
        bf.put(new byte[]{0x17, 0x18, 0x19});
        debugAll(bf);
        bf.flip();
        System.out.println(bf.get());
        debugAll(bf);
        bf.compact();
        debugAll(bf);
        bf.put(new byte[]{0x20, 0x21, 0x22});
        debugAll(bf);
    }
}
