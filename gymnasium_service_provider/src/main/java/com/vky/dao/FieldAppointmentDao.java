package com.vky.dao;

import com.github.pagehelper.Page;
import com.vky.pojo.FieldAppointment;

public interface FieldAppointmentDao {
    Page<FieldAppointment> selectByCondition(String queryString);
}
