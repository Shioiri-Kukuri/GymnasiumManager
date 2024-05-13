package com.vky.dao;

import com.vky.pojo.User;

public interface UserDao {
    public void findByAccountAndPassword(User user);
}