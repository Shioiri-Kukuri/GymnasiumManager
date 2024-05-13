package com.vky.service;

import com.vky.pojo.User;

public interface UserService {
    public void findByAccountAndPassword(User user);
}