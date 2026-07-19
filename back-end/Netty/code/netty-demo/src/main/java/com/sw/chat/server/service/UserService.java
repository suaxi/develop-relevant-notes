package com.sw.chat.server.service;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回 true, 否则返回 false
     */
    boolean login(String username, String password);
}
