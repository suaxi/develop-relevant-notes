package com.sw.chat.server.service;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        return "你好, " + msg;
    }
}