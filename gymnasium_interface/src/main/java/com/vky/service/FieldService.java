package com.vky.service;

import com.vky.entity.PageResult;

public interface FieldService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
