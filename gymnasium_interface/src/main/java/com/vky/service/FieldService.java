package com.vky.service;

import com.vky.entity.PageResult;
import com.vky.pojo.Field;

public interface FieldService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void add(Field field);

    Field findById(Integer id);

    void edit(Field field);
}
