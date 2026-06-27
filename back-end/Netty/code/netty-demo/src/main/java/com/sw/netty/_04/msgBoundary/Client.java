package com.sw.netty._04.msgBoundary;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * non-blocking client demo
 *
 * @author suaxi
 * @date 2026/06/25 22:11
 */
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
