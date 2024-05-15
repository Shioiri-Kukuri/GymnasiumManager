package com.vky.service;

import com.vky.entity.PageResult;
import com.vky.pojo.User;

public interface UserService {

    void edit(User user);


    User findByAccountAndPassword(User user);

    Integer findRoleIdByAccount(String Account);

    User findByAccount(Integer Account);

    void add(User user);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    void delete(Integer account);
}