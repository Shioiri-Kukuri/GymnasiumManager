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
    public void findByAccountAndPassword(User user) {
        userDao.findByAccountAndPassword(user);
    }
}
