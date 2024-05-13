package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.vky.dao.UserDao;
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
    public User findByAccountAndPassword(User user) {
        // 用DAO返回的User实例替换这里的user
        User foundUser = userDao.findByAccountAndPassword(user);
        return foundUser; // 返回从数据库查询到的User对象
    }
    @Override
    public Integer findRoleIdByAccount(String Account) {
        return userDao.findRoleIdByAccount(Account);
    }

}
