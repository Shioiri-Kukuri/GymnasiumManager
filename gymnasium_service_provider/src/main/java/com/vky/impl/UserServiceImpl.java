package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.UserDao;

import com.vky.entity.PageResult;
import com.vky.pojo.User;
import com.vky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public User findByAccountAndPassword(User user) {
        // 用DAO返回的User实例替换这里的user
        User foundUser = userDao.findByAccountAndPassword(user);
        return foundUser; // 返回从数据库查询到的User对象
    }

    @Override
    public Integer findRoleIdByAccount(String Account) {
        return userDao.findRoleIdByAccount(Account);
    }

    @Override
    public User findByAccount(Integer Account) {
        return userDao.findByAccount(Account);
    }


    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<User> page = userDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
