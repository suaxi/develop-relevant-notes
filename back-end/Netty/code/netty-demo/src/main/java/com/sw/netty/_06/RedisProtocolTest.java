package com.sw.netty._06;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author suaxi
 * @date 2026/07/15 23:07
 */
@Slf4j
public class RedisProtocolTest {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        // 指定换行符
        byte[] LINE = {13, 10};
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf setBf = ctx.alloc().buffer();
                            setBf.writeBytes("*3".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("$3".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("set".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("$4".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("test".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("$12".getBytes());
                            setBf.writeBytes(LINE);
                            setBf.writeBytes("sunxiaochuan".getBytes());
                            setBf.writeBytes(LINE);
                            ctx.writeAndFlush(setBf);

                            ByteBuf getBf = ctx.alloc().buffer();
                            getBf.writeBytes("*2".getBytes());
                            getBf.writeBytes(LINE);
                            getBf.writeBytes("$3".getBytes());
                            getBf.writeBytes(LINE);
                            getBf.writeBytes("get".getBytes());
                            getBf.writeBytes(LINE);
                            getBf.writeBytes("$4".getBytes());
                            getBf.writeBytes(LINE);
                            getBf.writeBytes("test".getBytes());
                            getBf.writeBytes(LINE);
                            ctx.writeAndFlush(getBf);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf bf = (ByteBuf) msg;
                            log.info("read data: {}", bf.toString(Charset.defaultCharset()));
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("192.168.123.88", 6379).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("RedisProtocolTest error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }

    //      +-------------------------------------------------+
    //      |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 2a 33 0d 0a 24 33 0d 0a 73 65 74 0d 0a 24 34 0d |*3..$3..set..$4.|
    // |00000010| 0a 74 65 73 74 0d 0a 24 31 32 0d 0a 73 75 6e 78 |.test..$12..sunx|
    // |00000020| 69 61 6f 63 68 75 61 6e 0d 0a                   |iaochuan..      |
    // +--------+-------------------------------------------------+----------------+
    // 23:30:05.579 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0xe34a1d05, L:/192.168.123.150:54851 - R:/192.168.123.88:6379] FLUSH
    // 23:30:05.579 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0xe34a1d05, L:/192.168.123.150:54851 - R:/192.168.123.88:6379] WRITE: 23B
    //      +-------------------------------------------------+
    //      |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 2a 32 0d 0a 24 33 0d 0a 67 65 74 0d 0a 24 34 0d |*2..$3..get..$4.|
    // |00000010| 0a 74 65 73 74 0d 0a                            |.test..         |
    // +--------+-------------------------------------------------+----------------+
    // 23:30:05.579 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0xe34a1d05, L:/192.168.123.150:54851 - R:/192.168.123.88:6379] FLUSH
    // 23:30:05.581 [nioEventLoopGroup-2-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0xe34a1d05, L:/192.168.123.150:54851 - R:/192.168.123.88:6379] READ: 24B
    //      +-------------------------------------------------+
    //      |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 2b 4f 4b 0d 0a 24 31 32 0d 0a 73 75 6e 78 69 61 |+OK..$12..sunxia|
    // |00000010| 6f 63 68 75 61 6e 0d 0a                         |ochuan..        |
    // +--------+-------------------------------------------------+----------------+
    // 23:30:05.581 [nioEventLoopGroup-2-1] INFO com.sw.netty._06.RedisProtocolTest - read data: +OK
    // $12
    // sunxiaochuan
}
