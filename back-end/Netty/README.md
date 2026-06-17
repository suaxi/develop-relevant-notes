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

