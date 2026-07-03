package com.sw.nio._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

/**
 * 黏包、半包
 *
 * @author suaxi
 * @date 2026/06/21 22:32
 */
public class ByteBufferExamTest {
    public static void main(String[] args) {
        /**
         * 例：通过网络发送给服务器的多条数据如下：
         * Yao Shui Ge,\n
         * Jin Se Wei Ye Na,\n
         * Zhi Bo Jian.
         * 由于各种原因，变成了如下的形式（黏包、半包）
         * Yao Shui Ge,\nJin S
         * e Wei Ye Na,\nZ
         * hi Bo Jian.
         * 现要求将黏包、半包的数据恢复为正确的按 \n 分隔的数据
         */

        ByteBuffer originBf = ByteBuffer.allocate(45);
        originBf.put("Yao Shui Ge,\nJin S".getBytes());
        split(originBf);
        originBf.put("e Wei Ye Na,\nZ".getBytes());
        split(originBf);
        originBf.put("hi Bo Jian.\n".getBytes());
        split(originBf);
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

    // +--------+-------------------- all ------------------------+----------------+
    // position: [13], limit: [13]
    //         +-------------------------------------------------+
    //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 59 61 6f 20 53 68 75 69 20 47 65 2c 0a          |Yao Shui Ge,.   |
    // +--------+-------------------------------------------------+----------------+
    // +--------+-------------------- all ------------------------+----------------+
    // position: [18], limit: [18]
    //         +-------------------------------------------------+
    //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 4a 69 6e 20 53 65 20 57 65 69 20 59 65 20 4e 61 |Jin Se Wei Ye Na|
    // |00000010| 2c 0a                                           |,.              |
    // +--------+-------------------------------------------------+----------------+
    // +--------+-------------------- all ------------------------+----------------+
    // position: [13], limit: [13]
    //         +-------------------------------------------------+
    //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 5a 68 69 20 42 6f 20 4a 69 61 6e 2e 0a          |Zhi Bo Jian..   |
    // +--------+-------------------------------------------------+----------------+
}
