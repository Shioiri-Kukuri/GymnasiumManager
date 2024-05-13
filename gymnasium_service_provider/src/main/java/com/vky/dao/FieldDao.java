package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.pojo.Field;

public interface FieldDao {
    public Page<Field> selectByCondition(String queryString);

    void add(Field field);

    Field findByNo(Integer fieldNo);
}
