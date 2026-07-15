package com.sw.netty._06;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

/**
 * @author suaxi
 * @date 2026/07/15 23:34
 */
@Slf4j
public class HttpProtocolTest {
    public static void main(String[] args) {
        NioEventLoopGroup main = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(main, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    ch.pipeline().addLast(new HttpServerCodec());
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                            log.info("request uri[{}]", msg.uri());
                            DefaultFullHttpResponse resp = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                            byte[] bytes = "sunxiaochuan".getBytes();
                            resp.headers().setInt(CONTENT_LENGTH, bytes.length);
                            resp.content().writeBytes(bytes);
                            ctx.writeAndFlush(resp);
                        }
                    });
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("RedisProtocolTest error", e);
        } finally {
            main.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    //      +-------------------------------------------------+
    //      |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 47 45 54 20 2f 20 48 54 54 50 2f 31 2e 31 0d 0a |GET / HTTP/1.1..|
    // |00000010| 48 6f 73 74 3a 20 6c 6f 63 61 6c 68 6f 73 74 3a |Host: localhost:|
    // |00000020| 38 30 38 38 0d 0a 43 6f 6e 6e 65 63 74 69 6f 6e |8088..Connection|
    // |00000030| 3a 20 6b 65 65 70 2d 61 6c 69 76 65 0d 0a 73 65 |: keep-alive..se|
    // |00000040| 63 2d 63 68 2d 75 61 3a 20 22 4e 6f 74 3b 41 3d |c-ch-ua: "Not;A=|
    // |00000050| 42 72 61 6e 64 22 3b 76 3d 22 38 22 2c 20 22 43 |Brand";v="8", "C|
    // |00000060| 68 72 6f 6d 69 75 6d 22 3b 76 3d 22 31 35 30 22 |hromium";v="150"|
    // |00000070| 2c 20 22 47 6f 6f 67 6c 65 20 43 68 72 6f 6d 65 |, "Google Chrome|
    // |00000080| 22 3b 76 3d 22 31 35 30 22 0d 0a 73 65 63 2d 63 |";v="150"..sec-c|
    // |00000090| 68 2d 75 61 2d 6d 6f 62 69 6c 65 3a 20 3f 30 0d |h-ua-mobile: ?0.|
    // |000000a0| 0a 73 65 63 2d 63 68 2d 75 61 2d 70 6c 61 74 66 |.sec-ch-ua-platf|
    // |000000b0| 6f 72 6d 3a 20 22 57 69 6e 64 6f 77 73 22 0d 0a |orm: "Windows"..|
    // |000000c0| 55 70 67 72 61 64 65 2d 49 6e 73 65 63 75 72 65 |Upgrade-Insecure|
    // |000000d0| 2d 52 65 71 75 65 73 74 73 3a 20 31 0d 0a 55 73 |-Requests: 1..Us|
    // |000000e0| 65 72 2d 41 67 65 6e 74 3a 20 4d 6f 7a 69 6c 6c |er-Agent: Mozill|
    // |000000f0| 61 2f 35 2e 30 20 28 57 69 6e 64 6f 77 73 20 4e |a/5.0 (Windows N|
    // |00000100| 54 20 31 30 2e 30 3b 20 57 69 6e 36 34 3b 20 78 |T 10.0; Win64; x|
    // |00000110| 36 34 29 20 41 70 70 6c 65 57 65 62 4b 69 74 2f |64) AppleWebKit/|
    // |00000120| 35 33 37 2e 33 36 20 28 4b 48 54 4d 4c 2c 20 6c |537.36 (KHTML, l|
    // |00000130| 69 6b 65 20 47 65 63 6b 6f 29 20 43 68 72 6f 6d |ike Gecko) Chrom|
    // |00000140| 65 2f 31 35 30 2e 30 2e 30 2e 30 20 53 61 66 61 |e/150.0.0.0 Safa|
    // |00000150| 72 69 2f 35 33 37 2e 33 36 0d 0a 41 63 63 65 70 |ri/537.36..Accep|
    // |00000160| 74 3a 20 74 65 78 74 2f 68 74 6d 6c 2c 61 70 70 |t: text/html,app|
    // |00000170| 6c 69 63 61 74 69 6f 6e 2f 78 68 74 6d 6c 2b 78 |lication/xhtml+x|
    // |00000180| 6d 6c 2c 61 70 70 6c 69 63 61 74 69 6f 6e 2f 78 |ml,application/x|
    // |00000190| 6d 6c 3b 71 3d 30 2e 39 2c 69 6d 61 67 65 2f 61 |ml;q=0.9,image/a|
    // |000001a0| 76 69 66 2c 69 6d 61 67 65 2f 77 65 62 70 2c 69 |vif,image/webp,i|
    // |000001b0| 6d 61 67 65 2f 61 70 6e 67 2c 2a 2f 2a 3b 71 3d |mage/apng,*/*;q=|
    // |000001c0| 30 2e 38 2c 61 70 70 6c 69 63 61 74 69 6f 6e 2f |0.8,application/|
    // |000001d0| 73 69 67 6e 65 64 2d 65 78 63 68 61 6e 67 65 3b |signed-exchange;|
    // |000001e0| 76 3d 62 33 3b 71 3d 30 2e 37 0d 0a 53 65 63 2d |v=b3;q=0.7..Sec-|
    // |000001f0| 46 65 74 63 68 2d 53 69 74 65 3a 20 6e 6f 6e 65 |Fetch-Site: none|
    // |00000200| 0d 0a 53 65 63 2d 46 65 74 63 68 2d 4d 6f 64 65 |..Sec-Fetch-Mode|
    // |00000210| 3a 20 6e 61 76 69 67 61 74 65 0d 0a 53 65 63 2d |: navigate..Sec-|
    // |00000220| 46 65 74 63 68 2d 55 73 65 72 3a 20 3f 31 0d 0a |Fetch-User: ?1..|
    // |00000230| 53 65 63 2d 46 65 74 63 68 2d 44 65 73 74 3a 20 |Sec-Fetch-Dest: |
    // |00000240| 64 6f 63 75 6d 65 6e 74 0d 0a 41 63 63 65 70 74 |document..Accept|
    // |00000250| 2d 45 6e 63 6f 64 69 6e 67 3a 20 67 7a 69 70 2c |-Encoding: gzip,|
    // |00000260| 20 64 65 66 6c 61 74 65 2c 20 62 72 2c 20 7a 73 | deflate, br, zs|
    // |00000270| 74 64 0d 0a 41 63 63 65 70 74 2d 4c 61 6e 67 75 |td..Accept-Langu|
    // |00000280| 61 67 65 3a 20 7a 68 2d 43 4e 2c 7a 68 3b 71 3d |age: zh-CN,zh;q=|
    // |00000290| 30 2e 39 2c 7a 68 2d 54 57 3b 71 3d 30 2e 38 0d |0.9,zh-TW;q=0.8.|
    // |000002a0| 0a 0d 0a                                        |...             |
    // +--------+-------------------------------------------------+----------------+
    // 23:41:47.151 [nioEventLoopGroup-3-1] INFO com.sw.netty._06.HttpProtocolTest - request uri[/]
    // 23:41:47.154 [nioEventLoopGroup-3-1] INFO io.netty.handler.logging.LoggingHandler - [id: 0xdcfdcd8c, L:/0:0:0:0:0:0:0:1:8088 - R:/0:0:0:0:0:0:0:1:55246] WRITE: 51B
    //      +-------------------------------------------------+
    //      |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
    // +--------+-------------------------------------------------+----------------+
    // |00000000| 48 54 54 50 2f 31 2e 31 20 32 30 30 20 4f 4b 0d |HTTP/1.1 200 OK.|
    // |00000010| 0a 63 6f 6e 74 65 6e 74 2d 6c 65 6e 67 74 68 3a |.content-length:|
    // |00000020| 20 31 32 0d 0a 0d 0a 73 75 6e 78 69 61 6f 63 68 | 12....sunxiaoch|
    // |00000030| 75 61 6e                                        |uan             |
    // +--------+-------------------------------------------------+----------------+
}
