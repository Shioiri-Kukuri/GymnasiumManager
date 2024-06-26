package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.FieldDao;
import com.vky.entity.PageResult;
import com.vky.entity.Result;
import com.vky.pojo.Field;
import com.vky.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = FieldService.class)
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Field> page = fieldDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Field field) {
        fieldDao.add(field);
    }

    @Override
    public Field findById(Integer id) {
        return fieldDao.findById(id);
    }

    @Override
    public void edit(Field field) {
        fieldDao.edit(field);
    }

    @Override
    public void delete(Integer id) {
        fieldDao.delete(id);
    }

    @Override
    public List<Field> findByStatus(Integer status) {
        return fieldDao.findByStatus(status);
    }

    @Override
    public Field findByName(String name) {
        return fieldDao.findByName(name);
    }


}