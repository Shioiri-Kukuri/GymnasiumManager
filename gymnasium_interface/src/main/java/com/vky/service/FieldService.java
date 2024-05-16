package com.vky.service;

import com.vky.entity.PageResult;
import com.vky.entity.Result;
import com.vky.pojo.Field;

import java.util.List;

public interface FieldService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void add(Field field);

    Field findById(Integer id);

    void edit(Field field);

    void delete(Integer id);


    List<Field> findByStatus(Integer status);

    Field findByName(String name);
}
