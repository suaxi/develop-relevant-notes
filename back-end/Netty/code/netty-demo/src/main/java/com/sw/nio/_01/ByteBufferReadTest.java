package com.sw.nio._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

/**
 * @author suaxi
 * @date 2026/06/20 23:06
 */
public class ByteBufferReadTest {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(10);
        bf.put(new byte[]{'a', 'b', 'c', 'd'});
        bf.flip();

        // 读全部
        bf.get(new byte[4]);
        debugAll(bf);

        // 从头重新开始读取一个字节
        bf.rewind();
        System.out.println((char) bf.get());

        // +--------+-------------------- all ------------------------+----------------+
        //         position: [4], limit: [4]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 61 62 63 64 00 00 00 00 00 00                   |abcd......      |
        // +--------+-------------------------------------------------+----------------+
        // a

        // mark 标记 position 位置，reset 将 position 位置重置到 mark 标记的位置
        System.out.println((char) bf.get()); // b
        bf.mark();
        System.out.println((char) bf.get()); // c
        System.out.println((char) bf.get()); // d
        bf.reset();
        System.out.println((char) bf.get()); // c

        // get(index) 不会改变读索引的位置
        System.out.println((char) bf.get(3));
        debugAll(bf);

        // d
        // +--------+-------------------- all ------------------------+----------------+
        //         position: [1], limit: [4]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 61 62 63 64 00 00 00 00 00 00                   |abcd......      |
        // +--------+-------------------------------------------------+----------------+
    }
}
