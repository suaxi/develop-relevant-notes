package com.sw.netty._04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author suaxi
 * @date 2026/07/08 22:58
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf bf = ByteBufAllocator.DEFAULT.buffer(16);
        bf.writeBytes(new byte[]{1, 2, 3, 4});
        log(bf);
        // read index:0 write index:4 capacity:10
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04                                     |....            |
        // +--------+-------------------------------------------------+----------------+

        // 写入整型 10（4字节）
        bf.writeInt(10);
        log(bf);
        // read index:0 write index:8 capacity:10
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 00 00 00 0a                         |........        |
        // +--------+-------------------------------------------------+----------------+

        // 继续写入（触发扩容）
        bf.writeBytes(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        log(bf);
        // read index:0 write index:17 capacity:64
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 00 00 00 0a 01 02 03 04 05 06 07 08 |................|
        // |00000010| 09                                              |.               |
        // +--------+-------------------------------------------------+----------------+
    }

    private static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity:").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, buffer);
        System.out.println(buf);
    }
}
