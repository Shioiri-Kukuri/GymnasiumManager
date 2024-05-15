package com.vky.service;

import com.vky.entity.PageResult;

public interface FieldAppointmentService {
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
