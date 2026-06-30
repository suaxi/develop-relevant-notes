package com.sw.netty._05;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author suaxi
 * @date 2026/06/30 23:19
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        sc.write(Charset.defaultCharset().encode("0123456789abcdefsunxiaochuan"));
        System.in.read();
    }
}
