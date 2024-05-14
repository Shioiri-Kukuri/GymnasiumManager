package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.FieldDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Field;
import com.vky.service.CompetitionService;
import com.vky.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Field findById(Integer fieldId) {
        return fieldDao.findById(fieldId);
    }

    @Override
    public void edit(Field field) {
        fieldDao.edit(field);
    }

}