package com.sw.nio._01;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static utils.ByteBufferUtil.debugAll;

/**
 * @author suaxi
 * @date 2026/06/20 23:17
 */
public class ByteBuffer2StringTest {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(10);

        // 字符串转 ByteBuffer
        // 1. 字符串 getBytes()
        bf.put("sxc".getBytes());
        debugAll(bf);

        // +--------+-------------------- all ------------------------+----------------+
        //         position: [3], limit: [16]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 73 78 63 00 00 00 00 00 00 00 00 00 00 00 00 00 |sxc.............|
        // +--------+-------------------------------------------------+----------------+

        // 2. Charset encode之后自动切换为读模式
        ByteBuffer bf1 = StandardCharsets.UTF_8.encode("sxc");
        debugAll(bf1);

        // +--------+-------------------- all ------------------------+----------------+
        //         position: [0], limit: [3]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 73 78 63                                        |sxc             |
        // +--------+-------------------------------------------------+----------------+

        // 3. wrap 同理方法2，自动切换为读模式
        ByteBuffer bf2 = ByteBuffer.wrap("sxc".getBytes());
        debugAll(bf2);

        // +--------+-------------------- all ------------------------+----------------+
        //         position: [0], limit: [3]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 73 78 63                                        |sxc             |
        // +--------+-------------------------------------------------+----------------+

        // ByteBuffer 转字符串
        bf.flip();
        System.out.println(StandardCharsets.UTF_8.decode(bf)); //sxc

        // Charset、wrap 方法生成的 ByteBuffer 对象不需要再手动显示切换为读模式
        System.out.println(StandardCharsets.UTF_8.decode(bf1)); //sxc
        System.out.println(StandardCharsets.UTF_8.decode(bf2)); //sxc
    }
}
