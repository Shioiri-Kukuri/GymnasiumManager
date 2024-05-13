package com.vky.dao;

import com.vky.pojo.User;

public interface UserDao {
    public User findByAccountAndPassword(User user);
    Integer findRoleIdByAccount(String Account);
}