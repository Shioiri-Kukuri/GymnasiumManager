package com.vky.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.FieldDao;
import com.vky.entity.PageResult;
import com.vky.pojo.Field;
import com.vky.service.FieldService;

public class FieldServiceImpl implements FieldService {
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Field> page = FieldDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

}