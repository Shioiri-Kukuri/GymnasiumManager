package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.pojo.Field;

public interface FieldDao {
    static Page<Field> selectByCondition(String queryString) {
        return null;
    }
}
