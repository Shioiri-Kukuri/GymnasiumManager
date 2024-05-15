package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.pojo.Field;

import java.util.List;

public interface FieldDao {
    public Page<Field> selectByCondition(String queryString);

    void add(Field field);

    Field findById(Integer id);

    void edit(Field field);

    void delete(Integer id);

    List<Field> findByStatus();
}
