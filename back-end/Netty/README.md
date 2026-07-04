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

ByteBufferReadWriteTest

```java
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



##### 2.3 常见方法

（1）分配空间

```java
// 使用堆内存，会受 gc 的影响
ByteBuffer.allocate(10);

// 使用直接（物理）内存，读写效率高，但分配效率低
ByteBuffer.allocateDirect(10);
```



（2）向 buffer 写入数据

调用 channel 的 read 方法

```java
int len = channel.read(bf);
```



调用 buffer 的 put 方法

```java
bf.put((byte) 0x16)
```



（3）从 buffer 读取数据

调用 channel 的 write 方法

```java
int len = channel.write(bf);
```



调用 buffer 的 get 方法

```java
// get 方法会让 position 读指针向后走
// rewind 方法可以将 position 重新置为 0
// get(index) 方法获取指定索引下标的内容时，不会移动读指针
byte b = bf.get();
```



ByteBufferReadTest

```java
package com.sw.netty._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

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

```



（4）字符串与 ByteBuffer 互转

```java
package com.sw.netty._01;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static utils.ByteBufferUtil.debugAll;

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

```



##### 2.4 Scattering Reads 分散读

ScatteringReadsTest

```java
package com.sw.netty._01;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import static utils.ByteBufferUtil.debugAll;

public class ScatteringReadsTest {
    public static void main(String[] args) {
        URL resource = ScatteringReadsTest.class.getClassLoader().getResource("ScatteringReadsTest.txt");
        if (resource == null) {
            throw new IllegalArgumentException("resource not found: ScatteringReadsTest.txt");
        }

        try (FileChannel channel = FileChannel.open(Paths.get(resource.toURI()))) {
            ByteBuffer bf1 = ByteBuffer.allocate(3);
            ByteBuffer bf2 = ByteBuffer.allocate(3);
            ByteBuffer bf3 = ByteBuffer.allocate(3);
            channel.read(new ByteBuffer[]{bf1, bf2, bf3});
            bf1.flip();
            bf2.flip();
            bf3.flip();
            debugAll(bf1);
            debugAll(bf2);
            debugAll(bf3);

            // +--------+-------------------- all ------------------------+----------------+
            //         position: [0], limit: [3]
            // +-------------------------------------------------+
            //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
            // +--------+-------------------------------------------------+----------------+
            // |00000000| 31 32 33                                        |123             |
            // +--------+-------------------------------------------------+----------------+
            // +--------+-------------------- all ------------------------+----------------+
            // position: [0], limit: [3]
            // +-------------------------------------------------+
            //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
            // +--------+-------------------------------------------------+----------------+
            // |00000000| 34 35 36                                        |456             |
            // +--------+-------------------------------------------------+----------------+
            // +--------+-------------------- all ------------------------+----------------+
            // position: [0], limit: [3]
            // +-------------------------------------------------+
            //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
            // +--------+-------------------------------------------------+----------------+
            // |00000000| 37 38 39                                        |789             |
            // +--------+-------------------------------------------------+----------------+
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



##### 2.5 GatheringWrites 集中写

GatheringWritesTest

```java
package com.sw.netty._01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class GatheringWritesTest {
    public static void main(String[] args) {
        ByteBuffer bf1 = StandardCharsets.UTF_8.encode("sun");
        ByteBuffer bf2 = StandardCharsets.UTF_8.encode("xiao");
        ByteBuffer bf3 = StandardCharsets.UTF_8.encode("chuan");

        try (FileChannel channel = new RandomAccessFile("GatheringWritesTest.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{bf1, bf2, bf3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```



##### 2.6 黏包、半包

ByteBufferExamTest

```java
package com.sw.netty._01;

import java.nio.ByteBuffer;

import static utils.ByteBufferUtil.debugAll;

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

```



#### 3. 文件编程

##### 3.1 FileChannel

*FileChannel 只能工作在阻塞模式下*

（1）获取

通过 FileInputStream（读）、FileOutputStream（写）、RandomAccessFile（根据指定的模式决定读或写） 的 getChannel() 方法获取



（2）读取

```java
// 返回值表示读取的字节，-1 表示读取到了文件的末尾
int readBytes = channel.read(bf);
```



（3）写入

```java
ByteBuffer bf = ByteBuffer.allcate(16);
bf.put(...);
bf.flip();

// 后续还有值则继续写入，channel.write() 方法不一定能一次写完全部内容
while (bf.hasRemaining()) {
    channel.write(bf);
}
```



（4）关闭

channel 使用完后必须关闭，可以使用 try-with-resources 语法糖或手动关闭 channel.close()



（5）获取当前位置

```java
// 获取位置
long position = channel.position();

// 设置指定下标索引位置
channel.position(123);
```

如果设置为文件末尾：

- 这时进行读取，返回值为 -1
- 执行写入时，会进行追加，如果 position 超过了文件末尾，新内容和原末尾之间会产生空洞(00)



（6）大小

```java
channel.size();
```



（7）强制写入

写入的数据在操作系统的管理下并不是立刻写入磁盘，而是先到缓存中，可以通过 channel.force(true) 方法将文件内容和元数据进行立即写入



##### 3.2 两个 Channel 传输数据

```java
package com.sw.netty._01;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class FileChannelTransferToTest {
    public static void main(String[] args) {
        URL resource = ScatteringReadsTest.class.getClassLoader().getResource("ByteBufferTest.txt");
        if (resource == null) {
            throw new IllegalArgumentException("resource not found: ScatteringReadsTest.txt");
        }

        // try (FileChannel from = FileChannel.open(Paths.get(resource.toURI()));
        //      FileChannel to = new FileOutputStream("FileChannelTransferTo.txt").getChannel()) {
        //     // transferTo 一次最多传输 2G 的数据
        //     from.transferTo(0, from.size(), to);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        try (FileChannel from = FileChannel.open(Paths.get(resource.toURI()));
             FileChannel to = new FileOutputStream("FileChannelTransferTo.txt").getChannel()) {

            // 传输大于 2G 的文件
            long size = from.size();
            for (long left = size; left > 0; ) {
                left -= from.transferTo((size - left), left, to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```



#### 4. 网络编程

##### 4.1 阻塞模式

单线程模式下，阻塞方法之间存在互相影响

- ServerSocketChannel.accept() 方法会在没有连接建立时阻塞
- SocketChannel.read() 在没有可读数据时阻塞



Sever

```java
package com.sw.netty._04.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8088));
        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            log.info("connecting...");
            SocketChannel channel = ssc.accept();
            log.info("connected - [{}]", channel);
            channelList.add(channel);
            for (SocketChannel sc : channelList) {
                log.info("before read - [{}]", channel);
                sc.read(bf);
                bf.flip();
                debugAll(bf);
                bf.clear();
                log.info("after read - [{}]", channel);
            }
        }
    }
}

```



Client

```java
package com.sw.netty._04.block;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        log.info("waiting...");
    }
}

```



##### 4.2 非阻塞模式

非阻塞模式下，相关方法的线程不会阻塞

- ServerSocketChannel.accept() 方法会在没有连接建立时返回 null，继续运行
- SocketChannel.read() 在没有可读数据时返回 0
- 写数据时，数据写入 Channel 后，线程即可继续运行，无需等待 Channel 通过网络把数据发送出去或发送完

但非阻塞模式下，即使没有新的连接建立、可读数据，线程仍在运行；**且数据复的制过程线程是阻塞的**



Sever

```java
package com.sw.netty._04.nonBlock;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8088));
        List<SocketChannel> channelList = new ArrayList<>();
        while (true) {
            SocketChannel channel = ssc.accept();
            if (channel != null) {
                log.info("connected - [{}]", channel);
                // 切换为非阻塞模式
                channel.configureBlocking(false);
                channelList.add(channel);
            }
            for (SocketChannel sc : channelList) {
                if (sc.read(bf) > 0) {
                    bf.flip();
                    debugAll(bf);
                    bf.clear();
                    log.info("after read - [{}]", channel);
                }
            }
        }
    }
}

```



Client

```java
package com.sw.netty._04.nonBlock;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        log.info("waiting...");
    }
}

```



##### 4.3 多路复用

概念：单线程配合 Selector 完成对多个 Channel 可读、可写事件的监听

- 仅针对网络 IO、文件 IO 操作无法使用多路复用
- Selector 的作用：
  - 有可连接事件时建立连接
  - 有可读事件时执行读取操作
  - 有可写事件时执行写入操作

注：Channel 不一定时时可写，当 Channel 可写时，则会触发 Selector 的可写事件



##### 4.4 Selector

![4.4Selector](static/4.网络编程/4.4Selector.png)

与 Selector 协作的线程可以监听多个 Channel，当事件发生时才去处理对应的事件，此时的线程可被充分利用，同时也节约的线程的数量，减少了线程间的上下文切换



（1）创建

```java
Selector selector = Selector.open();
```



（2）绑定 Channel 事件（注册）

```java
Selector selector = Selector.open();
ServerSocketChannel ssc = ServerSocketChannel.open();
// 切换为非阻塞模式
ssc.configureBlocking(false);

// 注册 Channel
SelectionKey sscKey = ssc.register(selector, 0, null);
```

注：

- Channel 必须以非阻塞模式运行
- 绑定的事件类型如下：
  - accept：有连接请求时触发
  - connection：（客户端）连接建立后触发
  - read：读事件
  - write：写事件



（3）监听 Channel 事件

```java
// 方式一：阻塞直到绑定事件发生
int count = selector.select();

// 方式二：阻塞到超时时间（ms）或绑定事件发生
int count = selector.select(long timeout);

// 方式三：selector 立即返回，后续流程根据返回值检查是否有事件发生
int count = selector.selectNow();
```



（4）selector 何时不阻塞

- 发生对应事件时：
  - accept 事件 - 客户端发起连接请求
  - read 事件 - 客户端发送数据、正常/异常关闭（当客户端发送的数据过大，服务端无法一次处理完时，会触发多次读取事件）
  - write 事件 - channel 当前状态可写出数据
- 调用 selector.wakeup()
- 调用 selector.close()
- selector 所在的线程中断



##### 4.5 处理 accept 事件

```java
package com.sw.netty._04.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Selector
        Selector selector = Selector.open();

        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);

        // 2. 注册 Channel
        /**
         * 通过 SelectionKey，可以知道发生的事件和发生事件的 Channel
         * 事件类型：
         * accept：有连接请求时触发
         * connection：（客户端）连接建立后触发
         * read：读事件
         * write：写事件
         */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register channel key-[{}]", sscKey);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            // 3. select 方法，没有事件发生或事件取消时，线程阻塞，否则反之
            selector.select();

            // 4. 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理完事件后，需要显式删除对应的key（事件处理完了，但 Selector 不会删除对应的key）
                iterator.remove();

                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    log.info("Deal Accept Event");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}

```

注：当事件发生后，要么处理，要么取消，如果什么都不做，下次该事件还会触发



##### 4.6 处理 read 事件

（1）演示 Demo

```java
package com.sw.netty._04.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Selector
        Selector selector = Selector.open();

        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);

        // 2. 注册 Channel
        /**
         * 通过 SelectionKey，可以知道发生的事件和发生事件的 Channel
         * 事件类型：
         * accept：有连接请求时触发
         * connection：（客户端）连接建立后触发
         * read：读事件
         * write：写事件
         */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register channel key-[{}]", sscKey);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            // 3. select 方法，没有事件发生或事件取消时，线程阻塞，否则反之
            selector.select();

            // 4. 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理完事件后，需要显式删除对应的key（事件处理完了，但 Selector 不会删除对应的key）
                iterator.remove();

                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    log.info("Deal Accept Event");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    try {
                        log.info("Deal Read Event");
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer readBf = ByteBuffer.allocate(16);
                        if (-1 != channel.read(readBf)) {
                            readBf.flip();
                            debugAll(readBf);
                        } else {
                            // 处理客户端读事件结束，正常断开
                            log.info("Client - [{}] close", key);
                            key.cancel();
                        }
                    } catch (IOException e) {
                        // 对发生异常的事件取消注册（从 Selector key 集合中移除）
                        // 如：客户端关闭主动断开连接
                        key.cancel();
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

```



（2）iterator.remove() 解释

当 selector 事件发生后，会将对应的 key 存入 selectedKeys 集合，但在事件处理完后并不会删除对应的 key，需要显式删除处理，如：第一次触发 accept 事件，处理完后，下一次循环再进来，key.isAcceptable() 判断为真，进入对应的判断后 channel.accept() 值为空（此时并不是 accept 事件），触发空指针异常



（3）cancel 的作用

取消注册在 selector 上的 channel，并从 SelectionKeys 集合中删除对应的事件 key



##### 4.7 处理消息边界

- 固定消息长度（数据包大小一样），但是在一定程度上会浪费带宽
- 按分隔符拆分，效率低
- TLV 格式，即：Type 类型、Length 长度、Value 数据，在消息类型和长度已知的情况下，就可以方便的获取消息的大小，以此分配 buffer；需要提前分配 buffer，如果内容过大，会对 server 的吞吐量造成影响
  - HTTP 1.0 TLV 格式
  - HTTP 2.0 LTV 格式



（1）此处以按分隔符拆分为例：

Server

```java
package com.sw.netty._04.msgBoundary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 1. Selector
        Selector selector = Selector.open();

        ByteBuffer bf = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 切换为非阻塞模式
        ssc.configureBlocking(false);

        // 2. 注册 Channel
        /**
         * 通过 SelectionKey，可以知道发生的事件和发生事件的 Channel
         * 事件类型：
         * accept：有连接请求时触发
         * connection：（客户端）连接建立后触发
         * read：读事件
         * write：写事件
         */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register channel key-[{}]", sscKey);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            // 3. select 方法，没有事件发生或事件取消时，线程阻塞，否则反之
            selector.select();

            // 4. 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理完事件后，需要显式删除对应的key（事件处理完了，但 Selector 不会删除对应的key）
                iterator.remove();

                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    log.info("Deal Accept Event");
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);

                    ByteBuffer readBf = ByteBuffer.allocate(16);
                    // 将 readBf 作为附件关联到事件 key 上
                    // 将 readBf 的生命周期提升到与 SelectionKey 平级
                    SelectionKey scKey = sc.register(selector, 0, readBf);
                    scKey.interestOps(SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    try {
                        log.info("Deal Read Event");
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer readBf = (ByteBuffer) key.attachment();
                        if (-1 != channel.read(readBf)) {
                            split(readBf);

                            // 当传输的内容超过 readbf 初始容量时，需进行扩容
                            if (readBf.position() == readBf.limit()) {
                                ByteBuffer newReadBf = ByteBuffer.allocate(readBf.capacity() * 2);
                                readBf.flip();
                                newReadBf.put(readBf);
                                key.attach(newReadBf);
                            }
                        } else {
                            // 处理客户端读事件结束，正常断开
                            log.info("Client - [{}] close", key);
                            key.cancel();
                        }
                    } catch (IOException e) {
                        // 对发生异常的事件取消注册（从 Selector key 集合中移除）
                        // 如：客户端关闭主动断开连接
                        key.cancel();
                        e.printStackTrace();
                    }
                }
            }
        }
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
}

```

Client

```java
package com.sw.netty._04.msgBoundary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        sc.write(Charset.defaultCharset().encode("012\n0123456789abcdefyao\n"));
        sc.write(Charset.defaultCharset().encode("0123456789abcdefsunxiaochuan\nyaoshuige\n"));
        sc.close();
    }
}

```



##### 4.8 ByteBuffer 大小分配

- 每个 Channel 都需要记录可能被切分的消息，因为 ByteBuffer 不能被多个 Channel 共享，需要独立维护
- ByteBuffer 不能太大，需要可变：
  - 思路一：先分配一个小的 buffer，不够的时候再进行扩容，优点是消息连续存储，缺点是数据拷贝存在一定的性能损耗
  - 思路二：用数组维护 buffer，可以避免数据拷贝产生的性能损耗，但消息存储不连续



##### 4.9 处理 write 事件

此处以写入内容过多问题（一次性发送数据）为例：

Server

```java
package com.sw.netty._04.writeableEvents;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.UUID;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 999999; i++) {
                        sb.append(UUID.randomUUID().toString().replace("-", ""));
                    }

                    ByteBuffer bf = Charset.defaultCharset().encode(sb.toString());
                    while (bf.hasRemaining()) {
                        int write = sc.write(bf);
                        System.out.println("发送：" + write + " 字节数据");
                    }
                }
            }
        }
    }
}

```



Client

```java
package com.sw.netty._04.writeableEvents;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));

        int count = 0;
        while (true) {
            ByteBuffer bf = ByteBuffer.allocate(1024 * 1024);
            count += sc.read(bf);
            System.out.println("接收到：" + count + " 字节数据");
        }
    }
}

```



改进优化：

Server

```java
package com.sw.netty._04.writeableEvents;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.UUID;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8088));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 999999; i++) {
                        sb.append(UUID.randomUUID().toString().replace("-", ""));
                    }

                    ByteBuffer bf = Charset.defaultCharset().encode(sb.toString());
                    int write = sc.write(bf);
                    System.out.println("OP_READ - 发送：" + write + " 字节数据");

                    // 2. 判断是否有剩余内容
                    if (bf.hasRemaining()) {
                        // 3. 关注可写事件（在原关注事件的基础上，需额外关注写事件）
                        scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
                        // scKey.interestOps(scKey.interestOps() | SelectionKey.OP_WRITE);
                        // 4. 将未写完的数据挂载到 scKey 上
                        scKey.attach(bf);
                    }
                }

                if (key.isWritable()) {
                    ByteBuffer bf = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(bf);
                    System.out.println("OP_WRITE - 发送：" + write + " 字节数据");

                    // 5. 关闭资源
                    if (!bf.hasRemaining()) {
                        // 清除挂载的 buffer
                        key.attach(null);

                        // 清除关注的事件
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }
}

```

注：当 channel 发送数据，且 socket 缓冲区可写时，对应的事件会频繁发生，**故需要在 socket 缓冲区写不下时再关注写事件**，写完之后需要取消关注



Client

```java
package com.sw.netty._04.writeableEvents;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        sc.connect(new InetSocketAddress("localhost", 8088));

        int count = 0;
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isConnectable()) {
                    log.info("key [{}] connected", key);
                    sc.finishConnect();
                }

                if (key.isReadable()) {
                    ByteBuffer bf = ByteBuffer.allocate(1024 * 1024);
                    count += sc.read(bf);
                    bf.clear();
                    System.out.println("接收到：" + count + " 字节数据");
                }
            }
        }
    }
}

```



#### 5. 多线程优化

##### 5.1 以分组选择器为例：

- 专门用一个线程配合 Selector，只负责 Accept 事件（boss）
- 其他线程配合各自对应的 Selector，只负责 Read、Write 事件（worker）

Server

```java
package com.sw.netty._05;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8088));

        // 1. 创建 worker
        Worker worker = new Worker("worker-0");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("connected - [{}]", sc.getLocalAddress());

                    // 2. 关联读写事件的 selector
                    log.info("before register - [{}]", sc.getLocalAddress());
                    worker.register(sc);
                    log.info("after register - [{}]", sc.getLocalAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {

        private Thread thread;

        private Selector selector;

        private String name;

        private volatile boolean start = false;

        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程、Selector
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                start = true;
            }

            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });

            // 显式唤醒 worker 的 selector.select() 阻塞，注册后续的读写事件
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select(); // 阻塞
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer bf = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            log.info("read client data - [{}]", channel.getLocalAddress());
                            channel.read(bf);
                            bf.flip();
                            debugAll(bf);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

```



Client

```java
package com.sw.netty._05;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        sc.write(Charset.defaultCharset().encode("0123456789abcdefsunxiaochuan"));
        System.in.read();
    }
}

```



##### 5.2 selector.wakeup() 补充

selector.wakeup() 方法与 selector.select() 书写的前后顺序不影响运行时的阻塞唤醒， 如：wakeup 在前，select 在后，wakeup 执行时先颁发凭证，select 执行时检测到有之前颁发的凭证，此时不会阻塞，而是继续向下运行



##### 5.3 多 worker

Server

```java
package com.sw.netty._05;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.ByteBufferUtil.debugAll;

@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8088));

        // 1. 创建 worker
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }

        AtomicInteger index = new AtomicInteger();
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("connected - [{}]", sc.getLocalAddress());

                    // 2. 关联读写事件的 selector
                    log.info("before register - [{}]", sc.getLocalAddress());
                    // 轮询
                    workers[index.getAndIncrement() % workers.length].register(sc);
                    log.info("after register - [{}]", sc.getLocalAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {

        private Thread thread;

        private Selector selector;

        private String name;

        private volatile boolean start = false;

        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程、Selector
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                start = true;
            }

            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });

            // 显式唤醒 worker 的 selector.select() 阻塞，注册后续的读写事件
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select(); // 阻塞
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer bf = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            log.info("read client data - [{}]", channel.getLocalAddress());
                            channel.read(bf);
                            bf.flip();
                            debugAll(bf);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

```



### 二、Netty

#### 1. 概念

Netty 是一个异步，基于事件驱动的网络应用框架



#### 2. 快速开始

pom 依赖

```xml
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.39.Final</version>
</dependency>
```



##### 2.1 Server

```java
package com.sw.netty._01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class Server {
    public static void main(String[] args) {
        // 1. 启动器，负责组装 netty 组件
        new ServerBootstrap()
                // 2. 事件组
                .group(new NioEventLoopGroup())
                // 3. channel 实现
                .channel(NioServerSocketChannel.class)
                // 4. 声明不同的 worker 负责的工作（处理哪些事件）
                .childHandler(// 5. 与客户端进行数据读写的通道（初始化工作）
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 6. 添加具体 handler
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 7. 监听端口
                .bind(8088);
    }
}

```

注：注释 6，在 pipeline 中，下一个 handler 会使用上一个 handler 的处理结果



##### 2.2 Client

```java
package com.sw.netty._01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 1.启动器
        new Bootstrap()
                // 2. EventLoop
                .group(new NioEventLoopGroup())
                // 3. channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加 handler
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 在连接建立之后被调用
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8088))
                // 显式声明使用同步方法，等待 server 端连接建立完毕
                .sync()
                .channel()
                .writeAndFlush("sunxiaochuan");
    }
}

```



##### 2.3 补充

（1）channel 可以看作是处理数据的通道

（2）输入 ByteBuffer，经 pipeline 后会处理为具体类型的对象，最终又输出 ByteBuffer

（3）handler 处理数据，pipeline 负责分发具体的事件（读、写）给 handler，分为 Inbound 和 Outbound

（4）eventLoop 可以看作数据处理者，并且与 channel 的生命周期绑定，每位处理者有对应的任务队列（普通任务、定时任务），根据 pipeline 流水线的顺序进行处理

#### 3. 组件

##### 3.1 EventLoop

（1）概念

EventLoop 事件循环对象本质是一个单线程处理器（同时维护了一个 Selector）,它继承的父类分为：

- ScheduledExecutorService，定时任务线程池
- netty - OrderedEventExecutor，可以判断线程是否属于当前的 EventLoop，和查找线程属于哪个 EventLoop



EventLoopGroup 是 EventLoop 事件循环对象的一组集合，Channel 会通过 register 方法来注册绑定其中的一个 EventLoop，后续该 Channel 中的事件都由绑定的 EventLoop 来处理，它的功能包含：

- 遍历组中的事件循环对象
- 获取下一个事件循环对象（实现了 Iterable 接口）



（2）EventLoop 任务处理 Demo

```java
package com.sw.netty._02_EventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class EventLoopTest {
    public static void main(String[] args) {
        // 1. 创建事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        // 2. 执行普通任务
        // eventExecutors.next().execute(() -> log.info("execute normal task"));

        // 3. 定时任务
        eventExecutors.next().scheduleAtFixedRate(() -> log.info("execute schedule task"), 1L, 2L, TimeUnit.SECONDS);
        log.info("main");
    }
}

```



（3）EventLoop 处理 io 事件 Demo

Server

```java
package com.sw.netty._02_EventLoop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class Server {
    public static void main(String[] args) {
        DefaultEventLoopGroup defaultGroup = new DefaultEventLoopGroup();
        new ServerBootstrap()
                // accept eventLoop, read eventLoop
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handler-01", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.info("处理时间较长的请求");
                                        ByteBuf bf = (ByteBuf) msg;
                                        log.info(bf.toString(Charset.defaultCharset()));

                                        // 将消息传递给下一个 handler
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast(defaultGroup, "handler-02", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.info("处理时间正常的请求");
                                        ByteBuf bf = (ByteBuf) msg;
                                        log.info(bf.toString(Charset.defaultCharset()));
                                    }
                                });
                    }
                })
                .bind(8088);
    }
}

```



Client

```java
package com.sw.netty._02_EventLoop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 1.启动器
        Channel channel = new Bootstrap()
                // 2. EventLoop
                .group(new NioEventLoopGroup())
                // 3. channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加 handler
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 在连接建立之后被调用
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8088))
                .sync()
                .channel();
        System.out.println("test");
    }
}

```

