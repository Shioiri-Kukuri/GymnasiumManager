package com.vky.service;

import com.vky.entity.PageResult;
import com.vky.pojo.Type;

import java.util.List;

public interface TypeService {
    void save(Type type);

    List<Type> findAll();

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    void edit(Type type);

    Type getById(Integer id);
}
