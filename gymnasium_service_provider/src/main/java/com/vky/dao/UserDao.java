package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.entity.PageResult;
import com.vky.pojo.User;

public interface UserDao {

    Page<User> selectByCondition(String queryString);

    public User findByAccountAndPassword(User user);
    Integer findRoleIdByAccount(String Account);

    void edit(User user);

    User findByAccount(Integer account);
    void add(User user);


    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}