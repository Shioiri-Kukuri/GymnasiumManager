package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.annotation.AutoFill;
import com.vky.enumeration.OperationType;
import com.vky.pojo.Type;

import java.util.List;

public interface TypeDao {

    @AutoFill(value = OperationType.INSERT)
    void add(Type type);

    List<Type> findAll();

    Page<Type> selectByCondition(String queryString);

    void deleteById(Integer id);

    @AutoFill(value = OperationType.UPDATE)
    void edit(Type type);

    Type getById(Integer id);

    List<Type> isExist(String name);
}
