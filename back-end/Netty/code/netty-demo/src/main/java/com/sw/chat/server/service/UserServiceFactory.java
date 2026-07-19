package com.sw.chat.server.service;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public abstract class UserServiceFactory {

    private static UserService userService = new UserServiceMemoryImpl();

    public static UserService getUserService() {
        return userService;
    }
}
