package com.sw.chat.client;

import com.sw.chat.client.handler.RpcResponseMessageHandler;
import com.sw.chat.message.RpcRequestMessage;
import com.sw.chat.protocol.MessageCodecSharable;
import com.sw.chat.protocol.ProcotolFrameDecoder;
import com.sw.chat.protocol.SequenceIdGenerator;
import com.sw.chat.server.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
@Slf4j
public class RpcClientManager {

    private static Channel channel = null;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        HelloService helloService = getProxyService(HelloService.class);
        String val1 = helloService.sayHello("药水哥");
        // String val2 = helloService.sayHello("带带大师兄");
    }

    /**
     * 获取 Channel（单例）
     *
     * @return channel
     */
    public static Channel getChannel() {
        if (channel != null) {
            return channel;
        }

        synchronized (LOCK) {
            if (channel != null) {
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    /**
     * 获取代理对象
     *
     * @param clazz clazz
     * @param <T>   泛型 T
     * @return T
     */
    public static <T> T getProxyService(Class<T> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        Class<?>[] interfaces = new Class[]{clazz};
        Object obj = Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            // 1. 将方法调用转换为消息对象
            int seqId = SequenceIdGenerator.nextId();
            RpcRequestMessage rpcRequestMessage = new RpcRequestMessage(
                    seqId,
                    clazz.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args);

            // 2. 发送消息
            getChannel().writeAndFlush(rpcRequestMessage);

            // 3. 请求结果处理
            // 指定 promise 对象异步接收结果的线程
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISES.put(seqId, promise);

            // 4. promise 结果
            promise.await();
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return clazz.cast(obj);
    }

    /**
     * 初始化 Channel
     */
    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProcotolFrameDecoder());
                ch.pipeline().addLast(LOGGING_HANDLER);
                ch.pipeline().addLast(MESSAGE_CODEC);
                ch.pipeline().addLast(RPC_HANDLER);
            }
        });
        try {
            channel = bootstrap.connect("localhost", 8088).sync().channel();
            channel.closeFuture().addListener(future -> group.shutdownGracefully());
        } catch (Exception e) {
            log.error("RpcClientManager error", e);
        }
    }
}
