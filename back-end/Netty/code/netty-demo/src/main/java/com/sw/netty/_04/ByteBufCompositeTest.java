package com.sw.netty._04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static utils.ByteBufferUtil.log;

/**
 * @author suaxi
 * @date 2026/07/09 23:05
 */
public class ByteBufCompositeTest {
    public static void main(String[] args) {
        ByteBuf bf = ByteBufAllocator.DEFAULT.buffer();
        bf.writeBytes(new byte[]{'0', '1', '2', '3', '4', '5'});

        ByteBuf bf1 = ByteBufAllocator.DEFAULT.buffer();
        bf1.writeBytes(new byte[]{'6', '7', '8', '9', 'a'});

        // 合并 ByteBuf
        // 方式一：（业务场景复杂，数据量大时，会产生很多io操作，不推荐）
        // ByteBuf bf2 = ByteBufAllocator.DEFAULT.buffer();
        // bf2.writeBytes(bf).writeBytes(bf1);
        // log(bf2);

        // 方式二：（可以避免频繁的数据拷贝操作）
        CompositeByteBuf bf2 = ByteBufAllocator.DEFAULT.compositeBuffer();
        // 通过 increaseWriteIndex 参数调整写入指针，不然合并不进去
        bf2.addComponents(true, bf, bf1);
        log(bf2);
        // read index:0 write index:11 capacity:11
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 30 31 32 33 34 35 36 37 38 39 61                |0123456789a     |
        // +--------+-------------------------------------------------+----------------+
    }
}
