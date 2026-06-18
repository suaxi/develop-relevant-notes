## Netty

*参考 B站 it黑马 Netty 课程*

### 一、NIO 基础

#### 1. 三大组件

##### 1.1 Channel、Buffer

Channel 是**读写数据的双向通道**，可以从 channel 将数据读入 buffer，也可以从 buffer 将数据写入 channel，常见的 Channel 有：

- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel



Buffer 用于**缓冲读写数据**，常见的 Buffer 有：

- ByteBuffer

  - MappedByteBuffer

  - DirectByteBuffer

  - HeapByteBuffer
- ShortBuffer
- IntBuffer
- LongBuffer
- FloatBuffer
- DoubleBuffer
- CharBuffer



##### 1.2 Selector

![1.2Selector](static/1.NIO基础/1.2Selector.png)

Selector 的作用是配合一个线程来管理多个 Channel，获取不同 Channel 上发生的事件，这些 Channel 工作在非阻塞模式下，不会让线程一直工作在一个 Channel 上，适合连接数多，但数据量不大的场景



#### 2. ByteBuffer

##### 2.1 基本使用

```java
package com.sw.netty._01;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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

```

执行流程：

1. 向 buffer 写入数据, channel.read(buffer)
2. 调用 flip() 切换至读模式
3. 从 buffer 读数据，buffer.get()
4. 调用 clear() 或 compact() 切换至写模式
5. 重复步骤 1 - 4



##### 2.2 结构

buffer 包含的属性有：capacity、position、limit

开始：

![2.2.1开始](static/2.Buffer/2.2.1开始.png)

写模式下，position 表示写入位置，limit 表示容量，写入4个字节

![2.2.2写入4个字节](static/2.Buffer/2.2.2写入4个字节.png)

调用 flip 后，position 切换为读取位置， limit切换为读取限制

![2.2.3flip](static/2.Buffer/2.2.3flip.png)

读取4个字节后的状态

![2.2.4读取4个字节后](static/2.Buffer/2.2.4读取4个字节后.png)

调用 clear 后

![2.2.5调用clear](static/2.Buffer/2.2.5调用clear.png)

调用 compact 方法，它的作用是把未读完的部分向前压缩，然后切换至写模式

![2.2.6Compact](static/2.Buffer/2.2.6Compact.png)

##### 2.3 方法演示

ByteBufferReadWriteTest

```java
package com.sw.netty._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

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

        // +--------+-------------------- all ------------------------+----------------+
        //         position: [1], limit: [10]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 16 00 00 00 00 00 00 00 00 00                   |..........      |
        // +--------+-------------------------------------------------+----------------+
        //         +--------+-------------------- all ------------------------+----------------+
        //         position: [4], limit: [10]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 16 17 18 19 00 00 00 00 00 00                   |..........      |
        // +--------+-------------------------------------------------+----------------+
        //         22
        //         +--------+-------------------- all ------------------------+----------------+
        //         position: [1], limit: [4]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 16 17 18 19 00 00 00 00 00 00                   |..........      |
        // +--------+-------------------------------------------------+----------------+
        //         +--------+-------------------- all ------------------------+----------------+
        //         position: [3], limit: [10]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 17 18 19 19 00 00 00 00 00 00                   |..........      |
        // +--------+-------------------------------------------------+----------------+
        //         +--------+-------------------- all ------------------------+----------------+
        //         position: [6], limit: [10]
        // +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        //         |00000000| 17 18 19 20 21 22 00 00 00 00                   |... !"....      |
        //         +--------+-------------------------------------------------+----------------+
    }
}

```

