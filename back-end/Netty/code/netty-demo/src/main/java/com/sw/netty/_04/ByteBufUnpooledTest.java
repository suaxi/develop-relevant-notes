package com.sw.netty._04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;

import static utils.ByteBufferUtil.log;

/**
 * @author suaxi
 * @date 2026/07/10 23:43
 */
public class ByteBufUnpooledTest {
    public static void main(String[] args) {
        ByteBuf bf = ByteBufAllocator.DEFAULT.buffer(4);
        bf.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBuf bf1 = ByteBufAllocator.DEFAULT.buffer(4);
        bf.writeBytes(new byte[]{5, 6, 7, 8});

        // 当包装多个 ByteBuf 时，底层使用 CompositeByteBuf
        ByteBuf bf2 = Unpooled.wrappedBuffer(bf, bf1);
        System.out.println(bf2.getClass()); // class io.netty.buffer.CompositeByteBuf
        log(bf2);
        // read index:0 write index:8 capacity:8
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 05 06 07 08                         |........        |
        // +--------+-------------------------------------------------+----------------+

        // 普通 byte 数组同理
        ByteBuf bf3 = Unpooled.wrappedBuffer(new byte[]{'a', 'b'}, new byte[]{'c', 'd'});
        System.out.println(bf3.getClass()); // class io.netty.buffer.CompositeByteBuf
        log(bf3);
        // read index:0 write index:4 capacity:4
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 61 62 63 64                                     |abcd            |
        // +--------+-------------------------------------------------+----------------+
    }
}
