package com.sw.chat.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suaxi
 * @date 2026/07/19 22:56
 */
public class UserServiceMemoryImpl implements UserService {
    private Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("sunxiaochuan", "123");
        allUserMap.put("liubo", "123");
        allUserMap.put("giaoge", "123");
    }

    @Override
    public boolean login(String username, String password) {
        String pass = allUserMap.get(username);
        if (pass == null) {
            return false;
        }
        return pass.equals(password);
    }
}
