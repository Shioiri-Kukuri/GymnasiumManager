package com.vky.service;

import com.vky.pojo.User;

public interface UserService {
    public User findByAccountAndPassword(User user);

    Integer findRoleIdByAccount(String Account);
}