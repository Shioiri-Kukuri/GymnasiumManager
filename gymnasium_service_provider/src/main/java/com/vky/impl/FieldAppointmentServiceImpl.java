package com.vky.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vky.dao.FieldAppointmentDao;
import com.vky.entity.PageResult;
import com.vky.pojo.FieldAppointment;
import com.vky.service.FieldAppointmentService;
import com.vky.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = FieldAppointmentService.class)
public class FieldAppointmentServiceImpl implements FieldAppointmentService {

    @Autowired
    private FieldAppointmentDao fieldAppointmentDao;
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<FieldAppointment> page = fieldAppointmentDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
